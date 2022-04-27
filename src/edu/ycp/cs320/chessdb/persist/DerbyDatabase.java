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
import chessgame.model.User;
import chessgame.model.GameDB; //game and moves are supposed to have the DB, don't change them
import chessgame.model.Player;
import chessgame.model.MovesDB;

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
	
	
	
	//Chess bs here
//	@Override
//	public List<ChessPiece> findPiecesByGame(final int gameID) {
//		return executeTransaction(new Transaction<List<ChessPiece>>() {
//			@Override
//			public List<ChessPiece> execute(Connection conn) throws SQLException {
//				PreparedStatement stmt = null;
//				ResultSet resultSet = null;
//				
//				try {//number, color, x, y
//					stmt = conn.prepareStatement(
//							"select pieces.piece_num, pieces.color, pieces.x_pos, pieces.y_pos " +
//							"  from  pieces " +
//							"  where pieces.game_id = ? "
//					);
//					stmt.setInt(1, gameID);
//					
//					List<ChessPiece> result = new ArrayList<ChessPiece>();
//					
//					resultSet = stmt.executeQuery();
//					
//					// for testing that a result was returned
//					Boolean found = false;
//					
//					while (resultSet.next()) {
//						found = true;
//						
//						ChessPiece daPiece = new PawnPiece(0, 0, true, 0);
//						loadChessPiece(daPiece, resultSet, 1);
//
//						result.add(daPiece);
//					}
//					
//					// check if the title was found
//					if (!found) {
//						System.out.println("game_id <" + gameID + "> was not found in the books table");
//					}
//					
//					return result;
//				} finally {
//					DBUtil.closeQuietly(resultSet);
//					DBUtil.closeQuietly(stmt);
//				}
//			}
//		});
//	}
	
	//Chess bs ends here
	
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
		//REPLACE THE BELOW LINE WITH YOUR DATABASE PATH!!!!
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/Users/nicho/eclipse-workspace/cs320_chess_database/library.db;create=true");
		System.out.println("big choice checker");
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
	
	//  creates all the tables
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt0 = null;
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				try {
					//makes the users table
					stmt2 = conn.prepareStatement(
							"create table users (" +
							"	user_id integer primary key " +						
							"		generated always as identity (start with 1, increment by 1), " +
							"	username varchar(255)," +
							"	password varchar(255)" +
							")"
						);	
					stmt2.executeUpdate();
					System.out.println("Users table created");	
					
					//makes the game table
					stmt3 = conn.prepareStatement(
							"create table games (" +
							"	game_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +									
							"	user1_id integer, " +
							"	user2_id integer, " +
							"	turn integer" +
							")"
						);	
					stmt3.executeUpdate();
					System.out.println("Games table created");	
					
					//makes the pieces table
					stmt4 = conn.prepareStatement(
							"create table pieces (" +
							"	piece_number integer primary key " + //piece number is the id i guess
							"		generated always as identity (start with 1, increment by 1), " +	
							"	piece_id integer," + //0-31 that tells what piece is
							"	game_id integer," +
							"	x_pos integer," +
							"	y_pos integer," +
							"	color boolean" +
							")"
						);	
					stmt4.executeUpdate();	
					System.out.println("Pieces table created");
					
					//makes the players table
					stmt1 = conn.prepareStatement(
							"create table players (" +
							"	color boolean," +
							"	game_id integer," +
							"	user_id integer" +
							")"
						);	
					stmt1.executeUpdate();
					System.out.println("Players table created");	
					
					//makes the moves table
					stmt0 = conn.prepareStatement(
							"create table moves (" +
							"	game_id integer," +
							"	piece_number integer," +
							"	x_pos integer," +
							"	y_pos integer," +
							"	turn integer" +
							")"
						);	
					stmt0.executeUpdate();
					System.out.println("Moves table created");	
										
					return true;
				} finally {
					DBUtil.closeQuietly(stmt0);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
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
				List<GameDB> gamesList;
				List<User> usersList;
				List<Player> playersList;
				List<MovesDB> movesList;
				
				try { //get lists of assembled objects from csvs
					pieceList = InitialData.getPieces();
					gamesList = InitialData.getGames();
					usersList = InitialData.getUsers();
					playersList = InitialData.getPlayers();
					movesList = InitialData.getMoves();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertPieces    = null;
				PreparedStatement insertGames     = null;
				PreparedStatement insertUsers     = null;
				PreparedStatement insertPlayers   = null;
				PreparedStatement insertMoves     = null;
				
				try {
					//populate pieces database with initial data from csv
					insertPieces = conn.prepareStatement("insert into pieces (game_id, piece_id, color, x_pos, y_pos) values (?, ?, ?, ?, ?)");
					for (ChessPiece daPiece : pieceList) {
						insertPieces.setInt(1, 1);
						insertPieces.setInt(2, daPiece.getPieceNumber());
						insertPieces.setBoolean(3, daPiece.getColor());
						insertPieces.setInt(4, daPiece.getXlocation());
						insertPieces.setInt(5, daPiece.getYlocation());
						insertPieces.addBatch();
						//System.out.println("adding piece with pnum "+ daPiece.getPieceNumber()+ " and color "+daPiece.getColor()+" and position ("+daPiece.getXlocation()+", "+daPiece.getYlocation()+").");
					}
					insertPieces.executeBatch();
					System.out.println("Pieces table populated");
					
					//populate games database with initial data from csv
					insertGames = conn.prepareStatement("insert into games (user1_id, user2_id, turn) values (?, ?, ?)");
					for (GameDB daGame : gamesList) {
						insertGames.setInt(1, daGame.getUserID1()); 
						insertGames.setInt(2, daGame.getUserID2()); 
						insertGames.setInt(3, daGame.getTurn());
						insertGames.addBatch();
					}
					insertGames.executeBatch();
					System.out.println("Games table populated");
					
					//populate users database with initial data from csv
					insertUsers = conn.prepareStatement("insert into users (username, password) values (?, ?)");
					for (User daUser : usersList) {
						insertUsers.setString(1, daUser.getUsername());
						insertUsers.setString(2, daUser.getPassword());
						insertUsers.addBatch();
					}
					insertUsers.executeBatch();
					System.out.println("Users table populated");
					
					//populate players database with initial data from csv
					insertPlayers = conn.prepareStatement("insert into players (color, game_id, user_id) values (?, ?, ?)");
					for (Player daPlayer : playersList) {
						insertPlayers.setBoolean(1, daPlayer.getColor());
						insertPlayers.setInt(2, daPlayer.getGameID());
						insertPlayers.setInt(3, daPlayer.getUserID());
						insertPlayers.addBatch();
					}
					insertPlayers.executeBatch();
					System.out.println("Players table populated");
					
					//populate moves database with initial data from csv
					insertMoves = conn.prepareStatement("insert into moves (game_id, piece_number, x_pos, y_pos, turn) values (?, ?, ?, ?, ?)");
					for (MovesDB daMove : movesList) {
						insertMoves.setInt(1, daMove.getGameID());
						insertMoves.setInt(2, daMove.getPieceNumber());
						insertMoves.setInt(3, daMove.getxCord());
						insertMoves.setInt(4, daMove.getYCord());
						insertMoves.setInt(5, daMove.getTurn());
						insertMoves.addBatch();
					}
					insertMoves.executeBatch();
					System.out.println("Moves table populated");
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertPieces);		
					DBUtil.closeQuietly(insertGames);	
					DBUtil.closeQuietly(insertUsers);	
					DBUtil.closeQuietly(insertPlayers);	
					DBUtil.closeQuietly(insertMoves);	
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
	public List<Pair<User, GameDB>> findGameByUserID(int userID) {
		return executeTransaction(new Transaction<List<Pair<User,GameDB>>>() {
			@Override
			public List<Pair<User, GameDB>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				// try to retrieve Games based on UserID, passed into query
				try {
					stmt = conn.prepareStatement(
							"select games.* " +
							"	from users, games " +
							"	where users.user_id = games.user1_id or users.user_id = game.user2_id " +
							"	and users.user_id = ? "
					);
					stmt.setInt(1, userID);
					
				// establish the list (User, GameDB) Pairs to receive the result	
				List<Pair<User, GameDB>> result = new ArrayList<Pair<User, GameDB>>();
				
				resultSet = stmt.executeQuery();
				
				// for testing that a result was returned
				Boolean found = false;
				
				while (resultSet.next()) {
					found = true;
					
					User user = new User();
					loadUser(user, resultSet, 1);
					GameDB game = new GameDB();
					loadGame(game, resultSet, 2);
					
					result.add(new Pair<User, GameDB>(user, game));
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
	public List<Pair<ChessPiece, MovesDB>> findCoordinateByPieceNumber(int pieceNumber) {
		return executeTransaction(new Transaction<List<Pair<ChessPiece, MovesDB>>>() {
			@Override
			public List<Pair<ChessPiece, MovesDB>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				// try to retrieve Coordinates based on Piece's number, passed into query
				try {
					stmt = conn.prepareStatement(
							"select moves.x_pos, moves.y_pos " +
							"	from pieces, moves " +
							"	where pieces.piece_number = moves.piece_number " +
							"	and pieces.piece_number = ?"
							);
					stmt.setInt(1, pieceNumber);
					
				// establish the list (ChessPiece, MovesDB) Pairs to receive the result	
				List<Pair<ChessPiece, MovesDB>> result = new ArrayList<Pair<ChessPiece, MovesDB>>();
				
				resultSet = stmt.executeQuery();
				
				// for testing that a result was returned
				Boolean found = false;
				
				while (resultSet.next()) {
					found = true;
					
					ChessPiece piece = new ChessPiece();
					loadPieces(piece, resultSet, 1);
					MovesDB move = new MovesDB();
					loadMoves(move, resultSet, 2);
					
					result.add(new Pair<ChessPiece, MovesDB>(piece, move));
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
	public List<Pair<Player, ChessPiece>> findPieceOwnerByColor(boolean color, int pieceNumber, int gameID) {
		return executeTransaction(new Transaction<List<Pair<Player, ChessPiece>>>() {
			@Override
			public List<Pair<Player, ChessPiece>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
			try {
				stmt = conn.prepareStatement(
						"select players.user_id " +
						"	from players, pieces_db " +
						"	where pieces.color = ? " +
						"	and pieces.color = players.color " +
						"	and pieces.piece_number = ? " +
						" 	and pieces.game_id = ?"
					);
				
					stmt.setBoolean(1, color);
					stmt.setInt(2, pieceNumber);
					stmt.setInt(3, gameID);
				
				
				// establish the list (Player, ChessPiece) Pairs to receive the result	
				List<Pair<Player, ChessPiece>> result = new ArrayList<Pair<Player, ChessPiece>>();
					
				resultSet = stmt.executeQuery();

				// for testing that a result was returned
				Boolean found = false;
				
				while (resultSet.next()) {
					found = true;
					
					Player player = new Player();
					loadPlayers(player, resultSet, 1);
					ChessPiece piece = new ChessPiece();
					loadPieces(piece, resultSet, 2);
					
					result.add(new Pair<Player, ChessPiece>(player, piece));
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
							"select moves.piece_number, moves.x_pos, moves.y_pos " +
							"	from games, moves " +
							"	where games.game_id = moves.game_id and moves.turn = ?" +
							"	game.game_id = ?"
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
	public List<Pair<Player, GameDB>> findPlayersByGameID(int gameID) {
		return executeTransaction(new Transaction<List<Pair<Player,GameDB>>>() {
			@Override
			public List<Pair<Player, GameDB>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				// try to retrieve Players based on Game ID, passed into query
				try {
					stmt = conn.prepareStatement(
							"select games.user1_id, gamedb.user2_id " +
							"	from players, games " +
							"	where players.game_id = games.game_id " +
							"	game.game_id = ?"
							);
					stmt.setInt(1, gameID);
					
				// establish the list (Player, GameDB) Pairs to receive the result	
				List<Pair<Player, GameDB>> result = new ArrayList<Pair<Player, GameDB>>();
					
				resultSet = stmt.executeQuery();

				// for testing that a result was returned
				Boolean found = false;
				
				while (resultSet.next()) {
					found = true;
					
					Player player = new Player();
					loadPlayers(player, resultSet, 1);
					GameDB game = new GameDB();
					loadGame(game, resultSet, 2);
					
					result.add(new Pair<Player, GameDB>(player, game));
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
	private void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
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
	private void loadPieces(ChessPiece piece, ResultSet resultSet, int index) throws SQLException {
		piece.setColor(resultSet.getBoolean(index++));
		piece.setGameID(resultSet.getInt(index++));
		piece.setPieceID(resultSet.getInt(index++));
		piece.setPieceNumber(resultSet.getInt(index++));
		piece.setXCord(resultSet.getInt(index++));
		piece.setYCord(resultSet.getInt(index++));
	}
	
	// retrieve Players information from query result set
	private void loadPlayers(Player player, ResultSet resultSet, int index) throws SQLException {
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
							"	from games " +
							" 	where games.gameid = ? "
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
	public List<User> findUserbyUserID(int userID) {
		return executeTransaction(new Transaction<List<User>>() {
			@Override
			public List<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * " +
							"	from users " +
							" 	where users.userid = ? "
					);
					stmt.setInt(1, userID);
					
					List<User> result = new ArrayList<User>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						User user = new User();
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
	public List<ChessPiece> findPiecesByGameID(int gameID) {
		return executeTransaction(new Transaction<List<ChessPiece>>() {
			@Override
			public List<ChessPiece> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * " +
							"	from pieces " +
							" 	where pieces.gameid = ? "
					);
					stmt.setInt(1, gameID);
					
					List<ChessPiece> result = new ArrayList<ChessPiece>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						ChessPiece piece = new ChessPiece();
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