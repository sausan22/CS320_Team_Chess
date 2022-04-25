package edu.ycp.cs320.chessdb.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.persist.DBUtil;
import edu.ycp.cs320.booksdb.persist.DerbyDatabase.Transaction;
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
//	public List<PiecesDB> findPiecesByGame(final int gameID) {
//		return executeTransaction(new Transaction<List<PiecesDB>>() {
//			@Override
//			public List<PiecesDB> execute(Connection conn) throws SQLException {
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
//					List<PiecesDB> result = new ArrayList<PiecesDB>();
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
		Connection conn = DriverManager.getConnection("jdbc:derby:C:\\Users\\Trey Mac\\DataBase For Team Project/library.db;create=true");
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
							"	game_id_pieces integer," +
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
							"	game_id_players integer," +
							"	user_id integer" +
							")"
						);	
					stmt1.executeUpdate();
					System.out.println("Players table created");	
					
					//makes the moves table
					stmt0 = conn.prepareStatement(
							"create table moves (" +
							"	game_id_moves integer," +
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
					insertPieces = conn.prepareStatement("insert into pieces (game_id_pieces, piece_id, color, x_pos, y_pos) values (?, ?, ?, ?, ?)");
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
					insertPlayers = conn.prepareStatement("insert into players (color, game_id_players, user_id) values (?, ?, ?)");
					for (Player daPlayer : playersList) {
						insertPlayers.setBoolean(1, daPlayer.getColor());
						insertPlayers.setInt(2, daPlayer.getGameID());
						insertPlayers.setInt(3, daPlayer.getUserID());
						insertPlayers.addBatch();
					}
					insertPlayers.executeBatch();
					System.out.println("Players table populated");
					
					//populate moves database with initial data from csv
					insertMoves = conn.prepareStatement("insert into moves (game_id_moves, piece_number, x_pos, y_pos, turn) values (?, ?, ?, ?, ?)");
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
	public Integer insertGamesIntoGamesTable(final int gameId, final int userId1, final int userId2, final int turn) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;		
				
				ResultSet resultSet1 = null;
				ResultSet resultSet3 = null;
				ResultSet resultSet5 = null;				
				
				// for saving author ID and book ID
				Integer game_id = -1;
				Integer user_id = -1;

				// try to retrieve author_id (if it exists) from DB, for Author's full name, passed into query
				try {
					stmt1 = conn.prepareStatement(
							"select games_id from games " +
							"  where game_id = ? "
					);
					stmt1.setInt(1, gameId);
					
					// execute the query, get the result
					resultSet1 = stmt1.executeQuery();

					
					// if game was found 				
					if (resultSet1.next())
					{
						game_id = resultSet1.getInt(1);
						System.out.println("Game found with Game ID: " + game_id);						
					}
					else
					{
						System.out.println("Game not found under game ID: " + game_id);
				
						// if the Author is new, insert new Author into Authors table
						if (game_id <= 0) {
							// prepare SQL insert statement to add Author to Authors table
							stmt2 = conn.prepareStatement(
									"insert into games (game_id, user_id1, user_id2, turn)" +
									"  values(?, ?, ?, ?) "
							);
							stmt2.setInt(1, gameId);
							stmt2.setInt(2, userId1);
							stmt2.setInt(3, userId2);
							stmt2.setInt(4, turn);
							
							// execute the update
							stmt2.executeUpdate();
							
							System.out.println("New Game with game ID: <" + gameId + ">!");
							//
							//the below code outputs the inserted game by 
							//grabbing the user from the game table
							//
							// try to retrieve author_id for new Author - DB auto-generates author_id
							stmt3 = conn.prepareStatement(
									"select game_id, user_id1, user_id2, turn " +
									"  where game_id = ? and user_id1 = ? " + 
											"and user_id2 and turn = ?"
							);
							stmt3.setInt(1, gameId);
							stmt3.setInt(2, userId1);
							stmt3.setInt(3, userId2);
							stmt3.setInt(4, turn);
							
							// execute the query							
							resultSet3 = stmt3.executeQuery();
							
							// get the result - there had better be one							
							if (resultSet3.next())
							{
								game_id = resultSet3.getInt(1);
								int user_Id1 = resultSet3.getInt(2);
								int user_Id2 = resultSet3.getInt(3);
								int tempTurn = resultSet3.getInt(4);
								System.out.println("New game with Game ID<" + game_id + ">");
								System.out.println("New game with User ID 1<" + user_Id1 + ">");
								System.out.println("New game with Game ID 2<" + user_Id2 + ">");
								System.out.println("New game with turn <" + tempTurn + ">");
							}
							else	// really should throw an exception here - the new author should have been inserted, but we didn't find them
							{
								System.out.println("Game with Game Id<" + gameId + "> and User 1 ID <" + userId1+ "> and User ID 2 <" + userId2+ "> and turn <" + turn+ "> was not found in the table");
							}
						}
					}
					
					// now insert new Book into Books table
					// prepare SQL insert statement to add new Book to Books table
					
					return game_id;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);					
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);					
					DBUtil.closeQuietly(resultSet5);
				}
			}
		});
	}
	/*
	public List<GameDB> removeGamesByGameId(final int gameId) {
		return executeTransaction(new Transaction<List<GameDB>>() {
			@Override
			public List<GameDB> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;
				
				ResultSet resultSet1    = null;			
				ResultSet resultSet2    = null;
				ResultSet resultSet5    = null;
				
				try {
					// first get the Author(s) of the Book to be deleted
					// just in case it's the only Book by this Author
					// in which case, we should also remove the Author(s)
					stmt1 = conn.prepareStatement(
							"select authors.* " +
							"  from  authors, books, bookAuthors " +
							"  where books.title = ? " +
							"    and authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id"
					);
					
					// get the Book's Author(s)
					stmt1.setString(1, title);
					resultSet1 = stmt1.executeQuery();
					
					// assemble list of Authors from query
					List<Author> authors = new ArrayList<Author>();					
				
					while (resultSet1.next()) {
						Author author = new Author();
						loadAuthor(author, resultSet1, 1);
						authors.add(author);
					}
					
					// check if any Authors were found
					// this shouldn't be necessary, there should not be a Book in the DB without an Author
					if (authors.size() == 0) {
						System.out.println("No author was found for title <" + title + "> in the database");
					}
										
					// now get the Book(s) to be deleted
					// we will need the book_id to remove associated entries in BookAuthors (junction table)
				
					stmt2 = conn.prepareStatement(
							"select books.* " +
							"  from  books " +
							"  where books.title = ? "
					);
					
					// get the Book(s)
					stmt2.setString(1, title);
					resultSet2 = stmt2.executeQuery();
					
					// assemble list of Books from query
					List<Book> books = new ArrayList<Book>();					
				
					while (resultSet2.next()) {
						Book book = new Book();
						loadBook(book, resultSet2, 1);
						books.add(book);
					}
					
					// first delete entries in BookAuthors junction table
					// can't delete entries in Books or Authors tables while they have foreign keys in junction table
					// prepare to delete the junction table entries (bookAuthors)
					stmt3 = conn.prepareStatement(
							"delete from bookAuthors " +
							"  where book_id = ? "
					);
					
					// delete the junction table entries from the DB for this title
					// this works if there are not multiple books with the same name
					stmt3.setInt(1, books.get(0).getBookId());
					stmt3.executeUpdate();
					
					System.out.println("Deleted junction table entries for book(s) <" + title + "> from DB");									
					
					// now delete entries in Books table for this title
					stmt4 = conn.prepareStatement(
							"delete from books " +
							"  where title = ? "
					);
					
					// delete the Book entries from the DB for this title
					stmt4.setString(1, title);
					stmt4.executeUpdate();
					
					System.out.println("Deleted book(s) with title <" + title + "> from DB");									
					
					// now check if the Author(s) have any Books remaining in the DB
					// only need to check if there are any entries in junction table that have this author ID
					for (int i = 0; i < authors.size(); i++) {
						// prepare to find Books for this Author
						stmt5 = conn.prepareStatement(
								"select books.book_id from books, bookAuthors " +
								"  where bookAuthors.author_id = ? "
						);
						
						// retrieve any remaining books for this Author
						stmt5.setInt(1, books.get(i).getAuthorId());
						resultSet5 = stmt5.executeQuery();						

						// if nothing returned, then delete Author
						if (!resultSet5.next()) {
							stmt6 = conn.prepareStatement(
								"delete from authors " +
								"  where author_id = ?"
							);
							
							// delete the Author from DB
							stmt6.setInt(1, authors.get(i).getAuthorId());
							stmt6.executeUpdate();
							
							System.out.println("Deleted author <" + authors.get(i).getLastname() + ", " + authors.get(i).getFirstname() + "> from DB");
							
							// we're done with this, so close it, since we might have more to do
							DBUtil.closeQuietly(stmt6);
						}
						
						// we're done with this, so close it since we might have more to do
						DBUtil.closeQuietly(resultSet5);
						DBUtil.closeQuietly(stmt5);
					}
					return authors;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(resultSet2);
					
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);					
					DBUtil.closeQuietly(stmt4);					
				}
			}
		});
	}
	*/
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