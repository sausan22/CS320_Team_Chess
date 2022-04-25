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
	List<Pair<ChessPiece, MovesDB>> findCoordinateByPieceNumberList = null;
	List<Pair<Player, ChessPiece>> findPieceOwnerByColorList= null;
	List<Pair<GameDB,MovesDB>> findGameSetUpByTurnList = null; 
	List<Pair<Player, GameDB>> findPlayersByGameIDList = null;
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
		
		int gameId = 1;
		

		// get the list of games from DB
		gamesList = db.findGameByGameID(gameId);
		// NOTE: this is a simple test to check if no results were found in the DB
		if (games.isEmpty()) {
			System.out.println("No book found in library with title <" + gameId + ">");
			fail("No book with title <" + gameId + "> returned from Library DB");
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
		
		int gameId = 1;
		// get the list of (Author, Book) pairs from DB
		piecesList = db.findPiecesByGameID(gameId);
		
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

		// get the list of (Author, Book) pairs from DB
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
		Integer pNum = 1;

		// get the piece coords of the piece selceted from in the DB
		findCoordinateByPieceNumberList = db.findCoordinateByPieceNumber(pNum);

		// NOTE: this is a simple test to check if no results were found in the DB
		if (findCoordinateByPieceNumberList.isEmpty()) {
			System.out.println("the piece was not found in DB");
			fail("No Pieces returned from DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			pieces = new ArrayList<ChessPiece>();
			for (Pair<ChessPiece, MovesDB> iterPiece : findCoordinateByPieceNumberList) {
				
				
				ChessPiece tPiece = iterPiece.getLeft();
				MovesDB tMove = iterPiece.getRight();
				pieces.add(tPiece);
				System.out.println(tPiece.getXlocation() + ", " + tPiece.getYlocation());
			}			
		}
	}
	
	@Test
	public void findPieceOwnerByColorTest() {

		System.out.println("\n*** Testing findPieceOwnerByColor ***");
		boolean color = true;
		int pNum = 1;
		int gID = 1; 

		// get the piece coords of the piece selceted from in the DB
		findPieceOwnerByColorList = db.findPieceOwnerByColor(color, pNum, gID);

		// NOTE: this is a simple test to check if no results were found in the DB
		if (findPieceOwnerByColorList.isEmpty()) {
			System.out.println("the player was not found in DB");
			fail("No players returned from DB");
		}
		// NOTE: assembling the results into Author and Book lists so that they could be
		//       inspected for correct content - well-formed objects with correct content
		else {
			player = new ArrayList<Player>();
			for (Pair<Player, ChessPiece> iterPiece : findPieceOwnerByColorList) {
				Player tPlayer = iterPiece.getLeft();
				ChessPiece tPiece = iterPiece.getRight();
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

		// get the piece coords of the piece selceted from in the DB
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
				System.out.println(tMove.getPieceNumber() + ", " + tMove.getxCord() + ", " + tMove.getYCord());
			}			
		}
	}
	@Test
	public void findPlayersByGameIDTest(){
		System.out.println("\n*** Testing findPlayersByGameID ***");
		int gID = 1;

		// get the piece coords of the piece selceted from in the DB
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
			for (Pair<Player, GameDB> iterState : findPlayersByGameIDList) {
				Player tPlayer = iterState.getLeft();
				GameDB tGame = iterState.getRight();
				player.add(tPlayer);
				System.out.println(tPlayer.getUserID() + ", " + tPlayer.getColor());
			}			
		}
	}
	/*
	@Test 
	public void insertGameTest() {
		System.out.println("\n*** Testing inserting game into game table ***");
		int gameId = 1;
		int userId1 = 1;
		int userId2 = 1;
		int turn = 0;
		
		Integer game_id = db.insertGamesIntoGamesTable(gameId, userId1, userId2, turn);
		
		if(game_id > 0) {
			System.out.println("No Games found under Game ID<" + gameId + ">");
			fail("Failed to insert new game");
		}
		else {
			System.out.println("new Game with ID <" + gameId + "> added to games table");
			
			//
			//
			// add the remove games query to derby database
			//
			//
			
		}
	}
	*/
}
