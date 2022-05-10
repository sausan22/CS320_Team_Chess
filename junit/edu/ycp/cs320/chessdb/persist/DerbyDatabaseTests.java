package edu.ycp.cs320.chessdb.persist;

import static org.junit.Assert.*; 

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import chessgame.model.*;
import edu.ycp.cs320.chessdb.model.Pair;
import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.DerbyDatabase;
import edu.ycp.cs320.chessdb.persist.IDatabase;

public class DerbyDatabaseTests {

	private IDatabase db = null;
	
	ArrayList<GameDB> games = null;
	List<GameDB> gamesList = null;
	
	ArrayList<User> users = null;
	List<User> usersList = null;
	
	ArrayList<Player> player = null;
	List<Player> playerList = null;
	
	ArrayList<ChessPiece> pieces = null;
	List<ChessPiece> piecesList = null;
	
	ArrayList<MovesDB> moves = null;
	List<MovesDB> movesList = null;
	
	List<Pair<User, GameDB>> gameAndUserList = null;
	List<MovesDB> findCoordinateByPieceNumberList = null;
	List<Pair<Player, MovesDB>> findPieceOwnerByPieceNumberList= null;
	List<Pair<GameDB,MovesDB>> findGameSetUpByTurnList = null; 
	List<Pair<Player, Player>> findPlayersByGameIDList = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void findGameByGameIDTest() {
		System.out.println("\n*** Testing findGameByGameID ***");
		
		int gameID = 1;
		

		// get the list of games from DB
		gamesList = db.findGameByGameID(gameID);
		// NOTE: this is a simple test to check if no results were found in the DB
		if (games.isEmpty()) {
			System.out.println("No book found in library with title <" + gameID + ">");
			fail("No book with title <" + gameID + "> returned from Library DB");
		}
		// NOTE: assembling the results into Games lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {			
			games = new ArrayList<GameDB>();
			for (GameDB game : gamesList) {
				GameDB currGame = game;
				games.add(currGame);
				System.out.println(currGame.getGameID() + "," + currGame.getTurn() + ", " + currGame.getUserID1() + "," + currGame.getUserID2());
			}			
		}
	}

	@Test
	public void findUserByUserIDTest() {
		System.out.println("\n*** Testing findAuthorAndBooksByAuthorLastName ***");

		int userID = 1;
		
		// get the User from the DB
		usersList = db.findUserbyUserID(userID);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (usersList.isEmpty()) {
			System.out.println("No user found with the user ID: <" + userID + ">");
			fail("No books for author <" + userID + "> returned from Library DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			users = new ArrayList<User>();
			for (User tempUser : usersList) {
				users.add(tempUser);
				System.out.println(tempUser.getUserID() + "," + tempUser.getUsername() + "," + tempUser.getPassword());
			}			
		}
		
	}

	@Test
	public void piecesByGameIDTest() {
		System.out.println("\n*** Testing piecesByGameIDTests ***");
		
		int gameID = 1;
		// retrieve the piece information based on the provided gameID
		piecesList = db.findPiecesByGameID(gameID);
		
		// NOTE: this is a simple test to check if no results were found in the DB
		if (piecesList.isEmpty()) {
			System.out.println("No pieces in the database");
			fail("No Pieces returned from DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			pieces = new ArrayList<ChessPiece>();
			for (ChessPiece cp : piecesList) {
				System.out.println(cp.getPieceID() + ", " + cp.getPieceNumber() + ", " + cp.getGameID() + ", " + cp.getXlocation()+ ", " + cp.getYlocation()+ ", " + cp.getColor());
			}			
		}
	}

	@Test
	public void findGameByUserIDTest() {

		System.out.println("\n*** Testing findGameByUserID ***");
		int userID = 1;

		// retrieve the game information for each game associated with the specific player
		gameAndUserList = db.findGameByUserID(userID);

		// NOTE: this is a simple test to check if no results were found in the DB
		if (gameAndUserList.isEmpty()) {
			System.out.println("No no games found in DB");
			fail("No Games returned from DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			games = new ArrayList<GameDB>();
			for (Pair<User, GameDB> iterGame : gameAndUserList) {
				User tUser = iterGame.getLeft();
				GameDB tGame = iterGame.getRight();
				games.add(tGame);
				System.out.println(tGame.getGameID() + ", " + tGame.getUserID1() + ", " + tGame.getUserID2()+ ", " + tGame.getTurn()+ ", ");
			}			
		}
	}

	@Test
	public void findCoordinateByPieceNumberListTest() {

		System.out.println("\n*** Testing findCoordinateByPieceNumberList ***");
		int pNum = 1;
		int turn = 1;

		// retrieve the piece coordinates based on the piece provided
		findCoordinateByPieceNumberList = db.findCoordinateByPieceNumber(pNum, turn);

		// NOTE: this is a simple test to check if no results were found in the DB
		if (findCoordinateByPieceNumberList.isEmpty()) {
			System.out.println("the piece was not found in DB");
			fail("No Pieces returned from DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			moves = new ArrayList<MovesDB>();
			for (MovesDB tMoves : findCoordinateByPieceNumberList) {
				moves.add(tMoves);
				System.out.println(tMoves.getXCord() + ", " + tMoves.getYCord());
			}			
		}
	}
	
	@Test
	public void findPieceOwnerByPieceNumberTest() {

		System.out.println("\n*** Testing findPieceOwnerByPieceNumber ***");
		int pNum = 1;
		int turn = 1;

		// determine the piece owner based on the piece's identification number
		findPieceOwnerByPieceNumberList = db.findPieceOwnerByPieceNumber(pNum, turn);

		// NOTE: this is a simple test to check if no results were found in the DB
		if (findPieceOwnerByPieceNumberList.isEmpty()) {
			System.out.println("the player was not found in DB");
			fail("No players returned from DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			player = new ArrayList<Player>();
			for (Pair<Player, MovesDB> iterPiece : findPieceOwnerByPieceNumberList) {
				Player tPlayer = iterPiece.getLeft();
				MovesDB tPiece = iterPiece.getRight();
				player.add(tPlayer);
				System.out.println(tPlayer.getUserID() + ", " + tPlayer.getColor());
			}			
		}
	}
	
	@Test
	public void findGameSetUpByTurnTest() {

		System.out.println("\n*** finds game setup by turn  ***");
		int turn = 1;
		int gID = 1;

		// return the board information base on the current turn
		findGameSetUpByTurnList = db.findGameSetUpByTurn(turn, gID);

		// NOTE: this is a simple test to check if no results were found in the DB
		if (findGameSetUpByTurnList.isEmpty()) {
			System.out.println("the game was not found in DB");
			fail("No players returned from DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			moves = new ArrayList<MovesDB>();
			for (Pair<GameDB, MovesDB> iterState : findGameSetUpByTurnList) {
				GameDB tGame = iterState.getLeft();
				MovesDB tMove = iterState.getRight();
				moves.add(tMove);
				System.out.println(tMove.getPieceNumber() + ", " + tMove.getXCord() + ", " + tMove.getYCord());
			}			
		}
	}
	@Test
	public void findPlayersByGameIDTest(){
		System.out.println("\n*** Testing findPlayersByGameID ***");
		int gID = 1;

		// retrieve the current players based on the provided gameID
		findPlayersByGameIDList = db.findPlayersByGameID(gID);
		// NOTE: this is a simple test to check if no results were found in the DB
		if (findPlayersByGameIDList.isEmpty()) {
			System.out.println("the players were not found in DB");
			fail("No players returned from DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			player = new ArrayList<Player>();
			for (Pair<Player, Player> iterState : findPlayersByGameIDList) {
				Player tPlayer = iterState.getLeft();
				Player p2 = iterState.getRight();
				player.add(tPlayer);
				player.add(p2);
				System.out.println(tPlayer.getUserID() + ", " + tPlayer.getColor());
				System.out.println(p2.getUserID() + ", " + p2.getColor());
			}			
		}
	}
	
	
	@Test 
	public void insertGameTest() {
		System.out.println("\n*** Testing inserting game into game table ***");
		int userId1 = 1;
		int userId2 = 1;
		int turn = 0;
		
		Integer gameID = db.insertGameByGameID(userId1, userId2, turn);
		
		if(gameID > 0) {
			
			// try to retrieve the newly inserted game information
			gamesList = db.findGameByGameID(gameID);
			
			if(gamesList.isEmpty()) {
				System.out.println("No Games found under Game ID<" + gameID + ">");
				fail("Failed to insert new game");
			} else {
				System.out.println("new Game with ID <" + gameID + "> added to games table");
				
				// restores the DB to its original state
				Integer removedGames = db.removeGamesByGameID(gameID); 
			}
			System.out.println("Failed to insert new game(ID: " + gameID + ") into the GameDB table");
			fail("Failed to insert new game into the GameID");
		}
	}
	
	@Test
	public void insertUserTest() {
		System.out.println("\n*** Testing inserting user into users table ***");
		String username = "JoeMama";
		String password = "Trey==Lame";
		
		Integer userID = db.insertNewUserIntoUserTable(username, password);
		
		if(userID > 0) {
			
			// try to retrieve newly insert user information
			usersList = db.findUserbyUserID(userID);
			
			if(usersList.isEmpty())	{
				System.out.println("No Users found under User ID: " + userID);
				fail("Failed to insert new user");
			} else {
				System.out.println("New User with ID: " + userID + " into the Users Table.");
			
				// restores the DB to its original state
				Integer removedUsers = db.removeUserByUserID(userID);
			}
			System.out.println("Failed to insert new user(ID: " + userID + ") into the Users Table");
			fail("Failed to insert new user: " + username + "into the Users table");
		}
	}
	
	@Test
	public void insertTurnTest() {
		System.out.println("\n*** Testing inserting turn info into Moves Table ***");
		int gameID = 17;
		int pieceNumber = 43;
		int xCord = 5;
		int yCord = 3;
		int turn = 3;
		
		Integer turnID = db.insertCurrentTurnIntoMovesTable(gameID, pieceNumber, xCord, yCord, turn);
		
		if(turnID > 0) {
			
			// try to retrieve newly inserted turn info
			findGameSetUpByTurnList = db.findGameSetUpByTurn(gameID, turn);
			
			if(findGameSetUpByTurnList.isEmpty()) {
				System.out.println("No Turn Info found under Game ID: " + gameID + ", Turn: " + turn);
				fail("Failed to insert current turn info");
			} else {
				System.out.println("Current Turn with GameID: " + gameID + ", Turn: " + turn + " has been added to the Moves Table");
				
				// restores the DB to its original state
				Integer removedMoves = db.removeTurnByGameIDAndTurn(gameID, turn);
			}
			System.out.println("Failed to insert current game(ID: " + gameID + "), turn: " + turn + " into the Moves Table");
			fail("Failed to insert Game: " + gameID + ", Turn: " + turn + " into the Moves Table");
		}
	}
	
	@Test
	public void insertPlayerTest() {
		System.out.println("\n*** Testing inserting player info into Player Table ***");
		boolean color = false;
		int gameID = 42;
		int userID = 99;
		
		Integer playerID = db.insertNewPlayerIntoPlayerTable(color, gameID, userID);
		
		if(playerID > 0) {
			
			//try to retrieve newly inserted player info
			playerList = db.findSinglePlayerByGameID(gameID, userID);
			
			if(playerList.isEmpty()) {
				System.out.println("No Player Found under Game ID: " + gameID + ", User ID: " + userID);
				fail("Failed to insert player info.");
			} else {
				System.out.println("Player with GameID: " + gameID + ", UserID: " + userID + " has been added to the Player Table");
				
				// restores the DB to its original state
				Integer removedPlayers = db.removePlayerByUserIDAndGameID(userID, gameID);
			}
			System.out.println("Failed to insert Player with UserID: " + userID + " from GameID: " + gameID + " into the PlayerTable");
			fail("Failed to insert Player with UserID: " + userID + " from GameID: " + gameID + " into the PlayerTable");
		}
	}
	
	@Test
	public void insertPieceTest() {
		System.out.println("\n*** Testing inserting piece info into ChessPiece Table ***");
		int pieceID = 13;
		int gameID = 1000000;
		int xCord = 1;
		int yCord = 5;
		boolean color = true;
		
		Integer pieceNumber = db.insertNewPieceIntoPiecesTable(pieceID, gameID, xCord, yCord, color);
		
		if(pieceNumber > 0) {
			
			// try to retrieve newly inserted pieces info
			piecesList = db.findPiecesByGameID(gameID);
			
			if(piecesList.isEmpty()) {
				System.out.println("No Pieces found under GameID: " + gameID + " in the ChessPiece Table");
				fail("Failed to insert piece info");
			} else {
				System.out.println("Piece Number: " + pieceNumber + " has been added to the ChessPiece Table");
			
				// restores the DB to its original state
				Integer removedPieces = db.removePiecesByPieceNumber(pieceNumber);
			}
			System.out.println("Failed to insert Piece from GameID: " + gameID + " into the ChessPiece Table");
			fail("failed to insert piece into the ChessPiece Table");
		}
	}
	
	
}
