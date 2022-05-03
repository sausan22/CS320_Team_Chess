package chessgame.controller;

import chessgame.controller.*;
import chessgame.model.*;
import chessgame.servlet.*;
import edu.ycp.cs320.chessdb.persist.*;

public class InsertController{
	
	private IDatabase db = null;

	
	public InsertController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	
	public boolean insertCurrentTurnIntoMovesTable(int gameID, int pieceNumber, int xCord, int yCord, int turn) {
		
		// insert new book (and possibly new author) into DB
		Integer lastTurn = db.insertCurrentTurnIntoMovesTable(gameID, pieceNumber, xCord, yCord, turn);

		// check if the insertion succeeded
		if (lastTurn > 0)
		{
			System.out.println("Last Turn (Turn: " + lastTurn + ") successfully added to Moves table");
			
			return true;
		}
		else
		{
			System.out.println("Failed to insert last turn (Turn: " + lastTurn + ") into Moves table" );
			
			return false;
		}
	}	

	public boolean insertNewPieceIntoPieces(int pieceID, int gameID, int xCord, int yCord, boolean color) {
		
		// insert new book (and possibly new author) into DB
		Integer pieceNumber = db.insertNewPieceIntoPiecesTable(pieceID, gameID, xCord, yCord, color);

		// check if the insertion succeeded
		if (pieceNumber > 0)
		{
			System.out.println("New piece (ID: " + pieceNumber + ") successfully added to Pieces table");
			
			return true;
		}
		else
		{
			System.out.println("Failed to insert new piece (ID: " + pieceNumber + ") into Pieces table" );
			
			return false;
		}
	}
	
	public boolean insertNewUserIntoUserTable(String username, String password) {
		
		// insert new user into DB
		Integer userID = db.insertNewUserIntoUserTable(username, password);
		
		// check if the insertion succeeded
		if (userID > 0)
		{
			System.out.println("New User: " + username + " (ID: " + userID + " ) successfully added to User table");
			
			return true;
		}
		else
		{
			System.out.println("Failed to insert new user (ID: " + userID + ") into User table");
			return false;
		}
	}
	
	public boolean insertNewPlayerIntoPlayerTable(boolean color, int gameID, int userID) {
		
		// insert new user into DB
		Integer playerID = db.insertNewPlayerIntoPlayerTable(color, gameID, userID);
		
		// check if the insertion succeeded
		if (playerID > 0)
		{
			System.out.println("New player added into the Player table");
			
			return true;
		}
		else
		{
			System.out.println("Failed to insert new player into the Player table");
			return false;
		}
	}
}
