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
import chessgame.model.*;

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
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/cs320_hell_database/library.db;create=true");		
		
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
								"	userid integer primary key " +						
								"		generated always as identity (start with 1, increment by 1), " +
								"	username varchar(255)," +
								"	password varchar(255)" +
								")"
							);	
						stmt2.executeUpdate();
						System.out.println("Users table created");	
						
						//makes the game table
						stmt3 = conn.prepareStatement(
								"create table gamedb (" +
								"	gameid integer primary key " +
								"		generated always as identity (start with 1, increment by 1), " +									
								"	userid1 integer, " +
								"	userid2 integer, " +
								"	turn integer" +
								")"
							);	
						stmt3.executeUpdate();
						System.out.println("Games table created");	
						
						//makes the pieces table
						stmt4 = conn.prepareStatement(
								"create table chesspiece (" +
								"	piece_number integer primary key " + //piece number is the id i guess
								"		generated always as identity (start with 1, increment by 1), " +	
								"	pieceid integer," + //0-31 that tells what piece is
								"	gameid integer," +
								"	xcord integer," +
								"	ycord integer," +
								"	color boolean" +
								")"
							);	
						stmt4.executeUpdate();	
						System.out.println("Pieces table created");
						
						//makes the players table
						stmt1 = conn.prepareStatement(
								"create table player (" +
								"	color boolean," +
								"	gameid integer," +
								"	userid integer" +
								")"
							);	
						stmt1.executeUpdate();
						System.out.println("Players table created");	
						
						//makes the moves table
						stmt0 = conn.prepareStatement(
								"create table movesdb (" +
								"	gameid integer," +
								"	piecenumber integer," +
								"	xcord integer," +
								"	ycord integer," +
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
						insertPieces = conn.prepareStatement("insert into chesspiece (gameid, pieceid, color, xcord, ycord) values (?, ?, ?, ?, ?)");
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
						insertGames = conn.prepareStatement("insert into gamedb (userid1, userid2, turn) values (?, ?, ?)");
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
						insertPlayers = conn.prepareStatement("insert into player (color, gameid, userid) values (?, ?, ?)");
						for (Player daPlayer : playersList) {
							insertPlayers.setBoolean(1, daPlayer.getColor());
							insertPlayers.setInt(2, daPlayer.getGameID());
							insertPlayers.setInt(3, daPlayer.getUserID());
							insertPlayers.addBatch();
						}
						insertPlayers.executeBatch();
						System.out.println("Players table populated");
						
						//populate moves database with initial data from csv
						insertMoves = conn.prepareStatement("insert into movesdb (gameid, piecenumber, xcord, ycord, turn) values (?, ?, ?, ?, ?)");
						for (MovesDB daMove : movesList) {
							insertMoves.setInt(1, daMove.getGameID());
							insertMoves.setInt(2, daMove.getPieceNumber());
							insertMoves.setInt(3, daMove.getXCord());
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
							"select gamedb.* " +
							"	from users, gamedb " +
							"	where users.userid = gamedb.userid1 or users.userid = gamedb.userid2 " +
							"	and user.userid = ? "
					);
					stmt.setInt(1, userID);
					
				// establish the list (UserDB, GameDB) Pairs to receive the result	
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
	public List<MovesDB> findCoordinateByPieceNumber(int pieceNumber, int turn) {
		return executeTransaction(new Transaction<List<MovesDB>>() {
			@Override
			public List<MovesDB> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				// try to retrieve Coordinates based on Piece's number, passed into query
				try {
					stmt = conn.prepareStatement(
							"select movesdb.xcord, movesdb.ycord " +
							"	from movesdb " +
							"	where movesdb.turn = ? " +
							"	and movesdb.piecenumber = ?"
							);
					stmt.setInt(1, turn);
					stmt.setInt(2, pieceNumber);
				//Might need to ask for specific turn # later on
					
				// establish the list (PiecesDB, MovesDB) Pairs to receive the result	
				List<MovesDB> result = new ArrayList<MovesDB>();
				
				resultSet = stmt.executeQuery();
				
				// for testing that a result was returned
				Boolean found = false;
				
				while (resultSet.next()) {
					found = true;
					
					MovesDB move = new MovesDB();
					loadMoves(move, resultSet, 2);
					
					result.add(move);
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
	public List<Pair<Player, MovesDB>> findPieceOwnerByPieceNumber(int pieceNumber, int turn) {
		return executeTransaction(new Transaction<List<Pair<Player, MovesDB>>>() {
			@Override
			public List<Pair<Player, MovesDB>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
			try {
				stmt = conn.prepareStatement(
						"select player.userid " +
						"	from player, movesdb " +
						"	where movesdb.piecenumber = ? " +
						"	and moves.turn = ? " +
						"	and player.gameid = movesdb.gameid"
					);
				
					stmt.setInt(1, pieceNumber);
					stmt.setInt(2, turn);
				
				
				// establish the list (PlayersDB, MovesDB) Pairs to receive the result	
				List<Pair<Player, MovesDB>> result = new ArrayList<Pair<Player, MovesDB>>();
					
				resultSet = stmt.executeQuery();

				// for testing that a result was returned
				Boolean found = false;
				
				while (resultSet.next()) {
					found = true;
					
					Player player = new Player();
					loadPlayers(player, resultSet, 1);
					MovesDB move = new MovesDB();
					loadMoves(move, resultSet, 2);
					
					result.add(new Pair<Player, MovesDB>(player, move));
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
	public List<Pair<Player, GameDB>> findPlayersByGameID(int gameID) {
		return executeTransaction(new Transaction<List<Pair<Player,GameDB>>>() {
			@Override
			public List<Pair<Player, GameDB>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				// try to retrieve Players based on Game ID, passed into query
				try {
					stmt = conn.prepareStatement(
							"select gamedb.userid1, gamedb.userid2 " +
							"	from player, gamedb " +
							"	where player.gameid = gamedb.gameid " +
							"	gamedb.gameid = ?"
							);
					stmt.setInt(1, gameID);
					
				// establish the list (PlayersDB, GameDB) Pairs to receive the result	
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
		piece.setPieceId(resultSet.getInt(index++));
		piece.setPieceNumber(resultSet.getInt(index++));
		piece.setXlocation(resultSet.getInt(index++));
		piece.setYlocation(resultSet.getInt(index++));
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
	} @Override
	public Integer findTurnByGameID(int gameID) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					stmt = conn.prepareStatement(
							"select * "
							+ "from gamedb" 
									+" where gameid = ? "
							);
					stmt.setInt(1, gameID);
					Integer result = -1;
					resultSet = stmt.executeQuery();
					result = resultSet.getInt(4);
					return result;
				}finally {
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
							"	from chesspiece " +
							" 	where chesspiece.gameid = ? "
					);
					stmt.setInt(1, gameID);
					
					List<ChessPiece> result = new ArrayList<ChessPiece>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						ChessPiece temp = null;
						if(resultSet.getInt(2) >= 0 && resultSet.getInt(2) <= 15) {
							//set chesspiece values in this one here 
							temp = new PawnPiece();
							//grabs the neccessary values from the tables and stores them into a new pawn object
								
							temp.setPieceNumber(resultSet.getInt(1));
							temp.setPieceId(resultSet.getInt(2));
							temp.setGameID(resultSet.getInt(3));
							temp.setXlocation(resultSet.getInt(4));
							temp.setylocation(resultSet.getInt(5));
							temp.setColor(resultSet.getBoolean(6));
							result.add(temp);
						}
						else if(resultSet.getInt(2) >= 16 && resultSet.getInt(2) <= 19) {
							temp = new KnightPiece();
							temp.setPieceNumber(resultSet.getInt(1));
							temp.setPieceId(resultSet.getInt(2));
							temp.setGameID(resultSet.getInt(3));
							temp.setXlocation(resultSet.getInt(4));
							temp.setylocation(resultSet.getInt(5));
							temp.setColor(resultSet.getBoolean(6));
							result.add(temp);
						}
						else if(resultSet.getInt(2) >= 20 && resultSet.getInt(2) <= 23) {
							temp = new BishopPiece();
							temp.setPieceNumber(resultSet.getInt(1));
							temp.setPieceId(resultSet.getInt(2));
							temp.setGameID(resultSet.getInt(3));
							temp.setXlocation(resultSet.getInt(4));
							temp.setylocation(resultSet.getInt(5));
							temp.setColor(resultSet.getBoolean(6));
							result.add(temp);
						}
						else if(resultSet.getInt(2) >= 24 && resultSet.getInt(2) <= 27) {
							temp = new RookPiece();
							temp.setPieceNumber(resultSet.getInt(1));
							temp.setPieceId(resultSet.getInt(2));
							temp.setGameID(resultSet.getInt(3));
							temp.setXlocation(resultSet.getInt(4));
							temp.setylocation(resultSet.getInt(5));
							temp.setColor(resultSet.getBoolean(6));
							result.add(temp);
						}
						else if(resultSet.getInt(2) >= 28 && resultSet.getInt(2) <= 29) {
							temp = new QueenPiece();
							temp.setPieceNumber(resultSet.getInt(1));
							temp.setPieceId(resultSet.getInt(2));
							temp.setGameID(resultSet.getInt(3));
							temp.setXlocation(resultSet.getInt(4));
							temp.setylocation(resultSet.getInt(5));
							temp.setColor(resultSet.getBoolean(6));
							result.add(temp);
						}
						else if(resultSet.getInt(2) >= 30 && resultSet.getInt(2) <= 31) {
							temp = new KingPiece();
							temp.setPieceNumber(resultSet.getInt(1));
							temp.setPieceId(resultSet.getInt(2));
							temp.setGameID(resultSet.getInt(3));
							temp.setXlocation(resultSet.getInt(4));
							temp.setylocation(resultSet.getInt(5));
							temp.setColor(resultSet.getBoolean(6));
							result.add(temp);
						}
						/*
				         * 0 - 15 are pawns
				         * 16 - 19 knights
				         * 20 - 23 bishops
				         * 24 - 27 rooks
				         * 28 - 29 queens
				         * 30 - 31 kings
				         * */
						//loadPieces(piece, resultSet, 1);
						
						result.add(temp);
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
	public Integer insertNewPieceIntoPiecesTable(int pieceID, int gameID, int xCord, int yCord,
			boolean color) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				
				ResultSet resultSet = null;
				
				// for saving pieceNumber
				Integer pieceNum = -1;
				
				try {
					// insert the new Piece into Pieces Table
					stmt = conn.prepareStatement(
							"insert into chesspiece (pieceid, gameid, xcord, ycord, color) " +
							" 	values(?, ?, ?, ?, ?, ?)"
					);
					stmt.setInt(1, pieceID);
					stmt.setInt(2, gameID);
					stmt.setInt(3, xCord);
					stmt.setInt(4, yCord);
					stmt.setBoolean(5, color);
					
					// execute update to insert into piece table
					stmt.executeUpdate();
					
					// retrieve the pieceNumber for new piece
					stmt2 = conn.prepareStatement(
							"select chesspiece.piecenumber " +
							"	from chesspiece " +
							"	where chesspiece.pieceid = ? and chesspiece.gameid = ?"
					);
					stmt2.setInt(1, pieceID);
					stmt2.setInt(2, gameID);
					
					// execute the query
					resultSet = stmt2.executeQuery();
					
					pieceNum = resultSet.getInt(2);
					
					return pieceNum;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}
			}
		});
	}

	@Override
	public Integer insertCurrentTurnIntoMovesTable(int gameID, int pieceNumber, int xCord, int yCord, int turn) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				
				ResultSet resultSet = null;
				
				// temporarily returning value
				Integer valueReturner = -1;
				
				try {
					// insert current game state into moves
					stmt = conn.prepareStatement( 
							"insert into movesdb (gameid, piecenumber, xcord, ycord, turns) " +
							"	values(?, ?, ?, ?, ?)"
					);
					stmt.setInt(1, gameID);
					stmt.setInt(2, pieceNumber);
					stmt.setInt(3, xCord);
					stmt.setInt(4, yCord);
					stmt.setInt(5, turn);
					
					// execute the update
					stmt.executeUpdate();
					
					// try to retrieve turn from newly inserted game instance
					stmt2 = conn.prepareStatement(
							"select movesdb.turn " +
							"	from movesdb " +
							"	where movesdb.gameid = ? and piecenumber = ? " +
							"	and movesdb.xcord = ? and movesdb.ycord = ? "
					);
					stmt2.setInt(1, gameID);
					stmt2.setInt(2, pieceNumber);
					stmt2.setInt(3, xCord);
					stmt2.setInt(4, yCord);
					
					// execute the query
					resultSet = stmt2.executeQuery();
					
					valueReturner = resultSet.getInt(5);
					
					return valueReturner;
			} finally {
				DBUtil.closeQuietly(resultSet);
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(stmt2);
			}
			}
		});
		
	}

	// Unfinished Query as unsure what information needs returned
	@Override
	public Integer updatePieceInformation(int pieceNumber, int xCord, int yCord) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				
				ResultSet resultSet = null;
				
				Integer coordinateChanger = null;
				try {
					stmt = conn.prepareStatement(
							"update chesspiece" +
							"	set chesspiece.xcord = ?, chesspiece.ycord = ?" +
							"	where chesspiece.piecenumber = ?"	
							);
					stmt.setInt(1, xCord);
					stmt.setInt(2, yCord);
					stmt.setInt(3, pieceNumber);
					
					stmt.executeUpdate();
					
					stmt2 = conn.prepareStatement(
							"select chesspiece.xcord, chesspiece.ycord" +
							"	from chesspiece " +
							"	where chesspiece.pieceNumber = ?"
							);
					stmt2.setInt(1, pieceNumber);
					
					// execute the query
					resultSet = stmt2.executeQuery();
					
					coordinateChanger = resultSet.getInt(1);
					
					return coordinateChanger;
					
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(resultSet);
				}
				
			}
		});
	}

	@Override
	public Integer updateGameInformation(int gameID, int turn) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				
				ResultSet resultSet = null;
				
				Integer updateChecker = null;
				
				try {
					stmt = conn.prepareStatement(
							"update gamedb" +
							"	set gamedb.turn = ?" +
							"	where gamedb.gameid = ?"	
							);
					stmt.setInt(1, turn);
					stmt.setInt(2, gameID);
					
					stmt.executeUpdate();
					
					// retrieve the updated Game Turn
					stmt2 = conn.prepareStatement(
							"select gamedb.turn " +
							"	from gamedb " +
							"	where gamedb.gameid = ?"
							);
					stmt2.setInt(1, gameID);
					
					// execute the query
					resultSet = stmt2.executeQuery();
					
					updateChecker = resultSet.getInt(5);
					
					// The following is temporarily until we know what it needs to return
					return updateChecker;	
				} finally {
					DBUtil.closeQuietly(stmt);
				}
				
			}
		});
	}

	@Override
	public Integer insertNewUserIntoUserTable(String username, String password) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				
				ResultSet resultSet = null;
				
				// for saving user ID
				Integer userID = -1;
				
				// try to insert the user into the database
				try {
					stmt = conn.prepareStatement(
							"insert into users (username, password) " +
							"	values (?, ?) "
					);
					stmt.setString(1, username);
					stmt.setString(1, password);
					
					// execute the update
					stmt.executeUpdate();
					
					System.out.println("New user " + username + " has been added into the User Table");
					
					// retrieve the new userID
					stmt2 = conn.prepareStatement(
							"select userid " +
							"	from users "	 +
							"	where users.username = ? and users.password = ?"
					);
					stmt2.setString(1, username);
					stmt2.setString(2, password);
					
					// execute the query
					resultSet = stmt2.executeQuery();
					
					userID = resultSet.getInt(1);
					
					return userID;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(resultSet);
				}
				
			}
		});
	}

	@Override
	public Integer insertNewPlayerIntoPlayerTable(boolean color, int gameID, int userID) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				
				ResultSet resultSet = null;
				
				Integer playerChecker = -1;
				
				// try to insert new player information
				try {
					stmt = conn.prepareStatement(
							"insert into player (color, gameid, userid) " +
							"	values (?, ?, ?) "
					);
					stmt.setBoolean(1, color);
					stmt.setInt(2, gameID);
					stmt.setInt(3, userID);
					
					// execute the insert
					stmt.executeUpdate();
					
					System.out.println("New player (ID: " + userID + ") added into Game (ID: " + gameID + ") as color " + color);

					stmt2 = conn.prepareStatement(
							"select player.userid " +
							"	from player " +
							"	where player.color = ? and player.gameid = ?"
					);
					stmt2.setBoolean(1, color);
					stmt2.setInt(2, gameID);
					
					// execute the query
					resultSet = stmt2.executeQuery();
					
					playerChecker = resultSet.getInt(3);
					
					return playerChecker;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt2);
				}
				
				}
			});
		}
	
	public Integer insertGameByGameID(int user1ID, int user2ID, int turn) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				

				ResultSet resultSet = null;
				ResultSet resultSet2 = null;
				
				Integer gameChecker = -1;
				
				try {
					stmt = conn.prepareStatement(
							"insert into gamedb (userid1, userid2, turn) " +
							"	values (?, ?, ?) "
					);
					stmt.setInt(1, user1ID);
					stmt.setInt(2, user2ID);
					stmt.setInt(3, turn);
					
					// execute the insert
					stmt.executeUpdate();
					
					System.out.println("New Game added into Game table");

					stmt2 = conn.prepareStatement(
							"select gamedb.gameid " +
							"	from gamedb " +
							"	where gamedb.userid1 = ? and gamedb.userid2 = ?"
					);
					stmt2.setInt(1, user1ID);
					stmt2.setInt(2, user2ID);
					
					// execute the query
					resultSet = stmt2.executeQuery();
					
					gameChecker = resultSet.getInt(3);
					
					
					
					return gameChecker;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt2);					
				}
			}
		});
	}
	public Integer removeGamesByGameID(int gameID) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
					"select gamedb.* " +
					"	from gamedb " +
					"	where gamedb.gameid = ? "
					);
					
					stmt1.setInt(1, gameID);
					resultSet1 = stmt1.executeQuery();
					List<GameDB> games = new ArrayList<GameDB>();
					Integer gameInt = -1;
					while (resultSet1.next()) {
						GameDB game = new GameDB();
						loadGame(game, resultSet1, 1);
						gameInt = game.getGameID();
						games.add(game);
					}
					if(games.size() == -1) {
						System.out.println("no game was found from with gameID:" + gameID);
					}
					else {
						stmt2 = conn.prepareStatement(
								"delete from gamedb" +
								" where gameid = ? ");
						stmt2.setInt(1, gameID);
						stmt2.executeUpdate();
						System.out.println("Deleted games from game Table with game ID: " + gameID);
						
					}
					return gameInt;
				}finally {
					DBUtil.closeQuietly(resultSet1);
					
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}

	@Override
	public Integer removePiecesByPieceNumber(int pieceNumber) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
					"select chesspiece.* " +
					"	from chesspiece " +
					"	where chesspiece.piecenumber = ? "
					);
					
					stmt1.setInt(1, pieceNumber);
					resultSet1 = stmt1.executeQuery();
					List<ChessPiece> pieces = new ArrayList<ChessPiece>();
					// Fix below & onwards due to ChessPiece being abstract
					Integer pieceInt = -1;
					while (resultSet1.next()) {
							ChessPiece temp = null;
							if(resultSet1.getInt(2) >= 0 && resultSet1.getInt(2) <= 15) {
								//set chesspiece values in this one here 
								temp = new PawnPiece();
								//grabs the neccessary values from the tables and stores them into a new pawn object
									
								temp.setPieceNumber(resultSet1.getInt(1));
								temp.setPieceId(resultSet1.getInt(2));
								temp.setGameID(resultSet1.getInt(3));
								temp.setXlocation(resultSet1.getInt(4));
								temp.setylocation(resultSet1.getInt(5));
								temp.setColor(resultSet1.getBoolean(6));
								pieces.add(temp);
							}
							else if(resultSet1.getInt(2) >= 16 && resultSet1.getInt(2) <= 19) {
								temp = new KnightPiece();
								temp.setPieceNumber(resultSet1.getInt(1));
								temp.setPieceId(resultSet1.getInt(2));
								temp.setGameID(resultSet1.getInt(3));
								temp.setXlocation(resultSet1.getInt(4));
								temp.setylocation(resultSet1.getInt(5));
								temp.setColor(resultSet1.getBoolean(6));
								pieces.add(temp);
							}
							else if(resultSet1.getInt(2) >= 20 && resultSet1.getInt(2) <= 23) {
								temp = new BishopPiece();
								temp.setPieceNumber(resultSet1.getInt(1));
								temp.setPieceId(resultSet1.getInt(2));
								temp.setGameID(resultSet1.getInt(3));
								temp.setXlocation(resultSet1.getInt(4));
								temp.setylocation(resultSet1.getInt(5));
								temp.setColor(resultSet1.getBoolean(6));
								pieces.add(temp);
							}
							else if(resultSet1.getInt(2) >= 24 && resultSet1.getInt(2) <= 27) {
								temp = new RookPiece();
								temp.setPieceNumber(resultSet1.getInt(1));
								temp.setPieceId(resultSet1.getInt(2));
								temp.setGameID(resultSet1.getInt(3));
								temp.setXlocation(resultSet1.getInt(4));
								temp.setylocation(resultSet1.getInt(5));
								temp.setColor(resultSet1.getBoolean(6));
								pieces.add(temp);
							}
							else if(resultSet1.getInt(2) >= 28 && resultSet1.getInt(2) <= 29) {
								temp = new QueenPiece();
								temp.setPieceNumber(resultSet1.getInt(1));
								temp.setPieceId(resultSet1.getInt(2));
								temp.setGameID(resultSet1.getInt(3));
								temp.setXlocation(resultSet1.getInt(4));
								temp.setylocation(resultSet1.getInt(5));
								temp.setColor(resultSet1.getBoolean(6));
								pieces.add(temp);
							}
							else if(resultSet1.getInt(2) >= 30 && resultSet1.getInt(2) <= 31) {
								temp = new KingPiece();
								temp.setPieceNumber(resultSet1.getInt(1));
								temp.setPieceId(resultSet1.getInt(2));
								temp.setGameID(resultSet1.getInt(3));
								temp.setXlocation(resultSet1.getInt(4));
								temp.setylocation(resultSet1.getInt(5));
								temp.setColor(resultSet1.getBoolean(6));
								pieces.add(temp);
							}
							/*
					         * 0 - 15 are pawns
					         * 16 - 19 knights
					         * 20 - 23 bishops
					         * 24 - 27 rooks
					         * 28 - 29 queens
					         * 30 - 31 kings
					         * */
							//loadPieces(piece, resultSet, 1);
							
							pieces.add(temp);
						}
					if(pieces.size() == -1) {
						System.out.println("No piece was found with piece number: " + pieceNumber);
					}
					else {
						stmt2 = conn.prepareStatement(
								"delete from chesspiece" +
								" where chesspiece.piecenumber = ? ");
						stmt2.setInt(1, pieceNumber);
						stmt2.executeUpdate();
						System.out.println("Deleted piece from ChessPiece Table with piece number: " + pieceNumber);
						
					}
					return pieceInt;
				}finally {
					DBUtil.closeQuietly(resultSet1);
					
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}

	@Override
	public Integer removeTurnByGameIDAndTurn(int gameID, int turn) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
					"select movesdb.* " +
					"	from movesdb " +
					"	where movesdb.gameid = ? and movesdb.turn = ?"
					);
					
					stmt1.setInt(1, gameID);
					stmt1.setInt(2, turn);
					resultSet1 = stmt1.executeQuery();
					List<MovesDB> moves = new ArrayList<MovesDB>();
					Integer turnNum = -1;
					while (resultSet1.next()) {
						MovesDB move = new MovesDB();
						loadMoves(move, resultSet1, 1);
						turnNum = move.getTurn();
						moves.add(move);
					}
					if(moves.size() == -1) {
						System.out.println("Game (ID: " + gameID + " ) has not reached turn " + turn);
					}
					else {
						stmt2 = conn.prepareStatement(
								"delete from movesdb" +
								" where movesdb.gameid = ? and movesdb.turn = ?");
						stmt2.setInt(1, gameID);
						stmt2.setInt(2, turn);
						stmt2.executeUpdate();
						System.out.println("Deleted Turn " + turn + " from Game (ID: " + gameID + ") from the MovesDB Table");
						
					}
					return turnNum;
				}finally {
					DBUtil.closeQuietly(resultSet1);
					
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}	

	@Override
	public Integer removeUserByUserID(int userID) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
					"select users.* " +
					"	from users " +
					"	where users.userid = ? "
					);
					
					stmt1.setInt(1, userID);
					resultSet1 = stmt1.executeQuery();
					List<User> users = new ArrayList<User>();
					Integer userInt = -1;
					while (resultSet1.next()) {
						User user = new User();
						loadUser(user, resultSet1, 1);
						userInt = user.getUserID();
						users.add(user);
					}
					if(users.size() == -1) {
						System.out.println("No user was found with UserID:" + userID);
					}
					else {
						stmt2 = conn.prepareStatement(
								"delete from users" +
								" where users.userid = ? ");
						stmt2.setInt(1, userID);
						stmt2.executeUpdate();
						System.out.println("Deleted User from Users Table with UserID: " + userID);
						
					}
					return userInt;
				}finally {
					DBUtil.closeQuietly(resultSet1);
					
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	

	@Override
	public Integer removePlayerByUserIDAndGameID(int userID, int gameID) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				
				ResultSet resultSet1 = null;
				try {
					stmt1 = conn.prepareStatement(
					"select player.* " +
					"	from player " +
					"	where player.gameid = ? and player.userid = ?"
					);
					
					stmt1.setInt(1, gameID);
					stmt1.setInt(2, userID);
					resultSet1 = stmt1.executeQuery();
					List<Player> players = new ArrayList<Player>();
					Integer playerInt = -1;
					while (resultSet1.next()) {
						Player player = new Player();
						loadPlayers(player, resultSet1, 1);
						playerInt = player.getUserID();
						players.add(player);
					}
					if(players.size() == -1) {
						System.out.println("User (ID: " + userID + ") is not a player in Game (ID: " + gameID + ")");
					}
					else {
						stmt2 = conn.prepareStatement(
								"delete from player" +
								" where player.gameid = ? and player.userid = ?");
						stmt2.setInt(1, gameID);
						stmt2.setInt(2, userID);
						stmt2.executeUpdate();
						System.out.println("Deleted Player (ID: " + userID + ") from Game (ID: " + gameID + ")");
						
					}
					return playerInt;
				}finally {
					DBUtil.closeQuietly(resultSet1);
					
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}



}