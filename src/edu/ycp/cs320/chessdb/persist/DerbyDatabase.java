package edu.ycp.cs320.chessdb.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.chessdb.model.*;
import chessgame.model.ChessPiece;
import chessgame.model.PawnPiece;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	
	// wrapper SQL transaction function that calls actual transaction function (which has retries)
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	// SQL transaction function which retries the transaction MAX_ATTEMPTS times before failing
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	// TODO: Here is where you name and specify the location of your Derby SQL database
	// TODO: Change it here and in SQLDemo.java under CS320_LibraryExample_Lab06->edu.ycp.cs320.sqldemo
	// TODO: DO NOT PUT THE DB IN THE SAME FOLDER AS YOUR PROJECT - that will cause conflicts later w/Git
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/CS320-2022-LibraryExample-DB/library.db;create=true");		
		
		// Set autocommit() to false to allow the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	// retrieves ChessPiece information from query result set
		private void loadChessPiece(ChessPiece piece, ResultSet resultSet, int index) throws SQLException {
			piece.setPieceNumber(resultSet.getInt(index++));
			piece.setColor(resultSet.getBoolean(index++));
			piece.setXlocation(resultSet.getInt(index++));
			piece.setYlocation(resultSet.getInt(index++));
		}
	
	//  creates the Authors and Books tables
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt4 = null;
				try {
					stmt4 = conn.prepareStatement(
							"create table pieces (" +
							"	piece_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +									
							"	game_id integer," +
							"	piece_num integer" +
							"	color boolean" +
							"	x_pos integer" +
							"	y_pos integer" +
							"	captured boolean" +
							")"
						);	
						stmt4.executeUpdate();
						
						System.out.println("Pieces table created");
										
					return true;
				} finally {
					DBUtil.closeQuietly(stmt4);
				}
			}
		});
	}
	
	// loads data retrieved from CSV files into DB tables in batch mode
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<ChessPiece> pieceList;
				
				try {
					pieceList = InitialData.getPieces();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertPieces     = null;

				try {
					insertPieces = conn.prepareStatement("insert into pieces (piece_num, color, x_pos, y_pos) values (?, ?, ?, ?)");
					for (ChessPiece daPiece : pieceList) {
						insertPieces.setInt(1, daPiece.getPieceNumber());
						insertPieces.setBoolean(2, daPiece.getColor());
						insertPieces.setInt(3, daPiece.getXlocation());
						insertPieces.setInt(4, daPiece.getYlocation());
						insertPieces.addBatch();
					}
					insertPieces.executeBatch();
					
					System.out.println("Pieces table populated");
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertPieces);				
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Library DB successfully initialized!");
	}

	@Override
	public List<Pair<UserDB, GameDB>> findGameByUserID(int userID) {
		return executeTransaction(new Transaction<List<Pair<UserDB,GameDB>>>() {
			@Override
			public List<Pair<UserDB, GameDB>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				// try to retrieve Games based on UserID, passed into query
				try {
					stmt = conn.prepareStatement(
							"select gamedb.* " +
							"	from userdb, gamedb " +
							"	where userdb.userid = gamedb.userid1 or userdb.userid = gamedb.userid2 " +
							"	and userdb.userid = ? "
					);
					stmt.setInt(1, userID);
					
				// establish the list (UserDB, GameDB) Pairs to receive the result	
				List<Pair<UserDB, GameDB>> result = new ArrayList<Pair<UserDB, GameDB>>();
				
				resultSet = stmt.executeQuery();
				
				// for testing that a result was returned
				Boolean found = false;
				
				while (resultSet.next()) {
					found = true;
					
					UserDB user = new UserDB();
					loadUser(user, resultSet, 1);
					GameDB game = new GameDB();
					loadGame(game, resultSet, 2);
					
					result.add(new Pair<UserDB, GameDB>(user, game));
				}
				
				// check if any games were found
				if (!found) {
					System.out.println("No games were found in the database");
				}
				return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public List<Pair<PiecesDB, MovesDB>> findCoordinateByPieceNumber(int pieceNumber) {
		return executeTransaction(new Transaction<List<Pair<PiecesDB, MovesDB>>>() {
			@Override
			public List<Pair<PiecesDB, MovesDB>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				// try to retrieve Coordinates based on Piece's number, passed into query
				try {
					stmt = conn.prepareStatement(
							"select movesdb.xcord, movesdb.ycord " +
							"	from piecesdb, movesdb " +
							"	where piecesdb.piecenumber = movesdb.piecenumber " +
							"	and piecesdb.piecenumber = ?"
							);
					stmt.setInt(1, pieceNumber);
				//Might need to ask for specific turn # later on
					
				// establish the list (PiecesDB, MovesDB) Pairs to receive the result	
				List<Pair<PiecesDB, MovesDB>> result = new ArrayList<Pair<PiecesDB, MovesDB>>();
				
				resultSet = stmt.executeQuery();
				
				// for testing that a result was returned
				Boolean found = false;
				
				while (resultSet.next()) {
					found = true;
					
					PiecesDB piece = new PiecesDB();
					loadPieces(piece, resultSet, 1);
					MovesDB move = new MovesDB();
					loadMoves(move, resultSet, 2);
					
					result.add(new Pair<PiecesDB, MovesDB>(piece, move));
				}
				// check if the piece exists on the board
				if (!found) {
					System.out.println("The piece is not on the board");
				}
				return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
					
	@Override
	public List<Pair<PlayersDB, PiecesDB>> findPieceOwnerByColor(boolean color, int pieceNumber, int gameID) {
		return executeTransaction(new Transaction<List<Pair<PlayersDB, PiecesDB>>>() {
			@Override
			public List<Pair<PlayersDB, PiecesDB>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
			try {
				stmt = conn.prepareStatement(
						"select playersdb.userid " +
						"	from playersdb, piecesdb " +
						"	where piecesdb.color = ? " +
						"	and piecesdb.color = playersdb.color " +
						"	and piecesdb.piecenumber = ? " +
						" 	and piecesdb.gameid = ?"
					);
				
					stmt.setBoolean(1, color);
					stmt.setInt(2, pieceNumber);
					stmt.setInt(3, gameID);
				
				
				// establish the list (PlayersDB, PiecesDB) Pairs to receive the result	
				List<Pair<PlayersDB, PiecesDB>> result = new ArrayList<Pair<PlayersDB, PiecesDB>>();
					
				resultSet = stmt.executeQuery();

				// for testing that a result was returned
				Boolean found = false;
				
				while (resultSet.next()) {
					found = true;
					
					PlayersDB player = new PlayersDB();
					loadPlayers(player, resultSet, 1);
					PiecesDB piece = new PiecesDB();
					loadPieces(piece, resultSet, 2);
					
					result.add(new Pair<PlayersDB, PiecesDB>(player, piece));
				}
				// check if the piece has an owner
				if (!found) {
					System.out.print("Piece number");
					System.out.print(pieceNumber);
					System.out.println(" does not have a specific owner");
				}
				return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	@Override
	public List<Pair<GameDB, MovesDB>> findGameSetUpByTurn(int gameID, int turn) {
		return executeTransaction(new Transaction<List<Pair<GameDB, MovesDB>>>() {
			@Override
			public List<Pair<GameDB, MovesDB>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				// try to retrieve the board set up based on the game instance and specific turn, passed into query
				try {
					stmt = conn.prepareStatement(
							"select movesdb.piecenumber, movesdb.xcord, movesdb.ycord " +
							"	from gamedb, movesdb " +
							"	where gamedb.gameid = movesdb.gameid and movesdb.turn = ?" +
							"	gamedb.gameid = ?"
							);
					stmt.setInt(1, turn);
					stmt.setInt(2, gameID);
					
				// establish the list (GameDB, MovesDB) Pairs to receive the result	
				List<Pair<GameDB, MovesDB>> result = new ArrayList<Pair<GameDB, MovesDB>>();
					
				resultSet = stmt.executeQuery();

				// for testing that a result was returned
				Boolean found = false;
				
				while (resultSet.next()) {
					found = true;
					
					MovesDB move = new MovesDB();
					loadMoves(move, resultSet, 1);
					GameDB game = new GameDB();
					loadGame(game, resultSet, 2);
					
					result.add(new Pair<GameDB, MovesDB>(game, move));
				}
				// check if the game turn exists
				if (!found) {
					System.out.print("This game instance does not have a turn");
					System.out.println(turn);
				}
				return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public List<Pair<PlayersDB, GameDB>> findPlayersByGameID(int gameID) {
		return executeTransaction(new Transaction<List<Pair<PlayersDB,GameDB>>>() {
			@Override
			public List<Pair<PlayersDB, GameDB>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				// try to retrieve Players based on Game ID, passed into query
				try {
					stmt = conn.prepareStatement(
							"select gamedb.userid1, gamedb.userid2 " +
							"	from playersdb, gamedb " +
							"	where playersdb.gameid = gamedb.gameid " +
							"	gamedb.gameid = ?"
							);
					stmt.setInt(1, gameID);
					
				// establish the list (PlayersDB, GameDB) Pairs to receive the result	
				List<Pair<PlayersDB, GameDB>> result = new ArrayList<Pair<PlayersDB, GameDB>>();
					
				resultSet = stmt.executeQuery();

				// for testing that a result was returned
				Boolean found = false;
				
				while (resultSet.next()) {
					found = true;
					
					PlayersDB player = new PlayersDB();
					loadPlayers(player, resultSet, 1);
					GameDB game = new GameDB();
					loadGame(game, resultSet, 2);
					
					result.add(new Pair<PlayersDB, GameDB>(player, game));
				}
				// check if the game has players 
				if (!found) {
					System.out.println("There are no players associated with this Game ID");
				}
				return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	// retrieves User information from query result set
	private void loadUser(UserDB user, ResultSet resultSet, int index) throws SQLException {
		user.setUserID(resultSet.getInt(index++));
		user.setUsername(resultSet.getString(index++));
		user.setPassword(resultSet.getString(index++));
	}
	
	// retrieves Game information from query result set
	private void loadGame(GameDB game, ResultSet resultSet, int index) throws SQLException {
		game.setGameID(resultSet.getInt(index++));
		game.setTurn(resultSet.getInt(index++));
		game.setUserID1(resultSet.getInt(index++));
		game.setUserID2(resultSet.getInt(index++));
	}
	
	// retrieves Moves information from query result set
	private void loadMoves(MovesDB move, ResultSet resultSet, int index) throws SQLException {
		move.setGameID(resultSet.getInt(index++));
		move.setPieceNumber(resultSet.getInt(index++));
		move.setTurn(resultSet.getInt(index++));
		move.setXCord(resultSet.getInt(index++));
		move.setYCord(resultSet.getInt(index++));
	}
	
	// retrieves Pieces information from query result set
	private void loadPieces(PiecesDB piece, ResultSet resultSet, int index) throws SQLException {
		piece.setColor(resultSet.getBoolean(index++));
		piece.setGameID(resultSet.getInt(index++));
		piece.setPieceID(resultSet.getInt(index++));
		piece.setPieceNumber(resultSet.getInt(index++));
		piece.setXCord(resultSet.getInt(index++));
		piece.setYCord(resultSet.getInt(index++));
	}
	
	// retrieve Players information from query result set
	private void loadPlayers(PlayersDB player, ResultSet resultSet, int index) throws SQLException {
		player.setColor(resultSet.getBoolean(index++));
		player.setGameID(resultSet.getInt(index++));
		player.setUserID(resultSet.getInt(index++));
	}

	@Override
	public List<GameDB> findGameByGameID(int gameID) {
		return executeTransaction(new Transaction<List<GameDB>>() {
			@Override
			public List<GameDB> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * " +
							"	from gamesdb " +
							" 	where gamesdb.gameid = ? "
					);
					stmt.setInt(1, gameID);
					
					List<GameDB> result = new ArrayList<GameDB>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						GameDB game = new GameDB();
						loadGame(game, resultSet, 1);
						
						result.add(game);
					}
					
					// check if game exists
					if (!found) {
						System.out.println("There is no game associated with this ID");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public List<UserDB> findUserbyUserID(int userID) {
		return executeTransaction(new Transaction<List<UserDB>>() {
			@Override
			public List<UserDB> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * " +
							"	from userdb " +
							" 	where userdb.userid = ? "
					);
					stmt.setInt(1, userID);
					
					List<UserDB> result = new ArrayList<UserDB>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						UserDB user = new UserDB();
						loadUser(user, resultSet, 1);
						
						result.add(user);
					}
					
					// check if user exists
					if (!found) {
						System.out.println("There is no user associated with this ID");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public List<PiecesDB> findPiecesByGameID(int gameID) {
		return executeTransaction(new Transaction<List<PiecesDB>>() {
			@Override
			public List<PiecesDB> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * " +
							"	from piecesdb " +
							" 	where piecesdb.gameid = ? "
					);
					stmt.setInt(1, gameID);
					
					List<PiecesDB> result = new ArrayList<PiecesDB>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						PiecesDB piece = new PiecesDB();
						loadPieces(piece, resultSet, 1);
						
						result.add(piece);
					}
					
					// check if game exists
					if (!found) {
						System.out.println("There is no game associated with this ID");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
}