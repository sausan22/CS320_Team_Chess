package chessgame.controller;

import chessgame.controller.*;
import chessgame.model.*;
import chessgame.servlet.*;
import edu.ycp.cs320.chessdb.persist.*;

public class UpdateController{
	
	private IDatabase db = null;

	
	public UpdateController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	
	public boolean updateGameTable(int gameID, int turn) {
		// update Game Table
		Integer newTurn = db.updateGameInformation(gameID, turn);
		
		// check if the update succeeded
		if (newTurn > 0) {
			System.out.println("The Current Turn (Turn: " + newTurn + ") successfully updated in GameDB table");
			return true;
		} else {
			System.out.println("Failed to update the GameDB table");
			return false;
		}
	}
	
	public boolean updatePieceTable(int pieceNumber, int xCord, int yCord) {
		// update Piece Table
		Integer newCoords = db.updatePieceInformation(pieceNumber, xCord, yCord);
		
		// check if the update succeeded
		if (newCoords > 0) {
			System.out.println("The Piece: " + pieceNumber + " has been successfully relocated to [" + xCord + ", "+ yCord + "]");
			return true;
		} else {
			System.out.println("Failed to update the ChessPiece table");
			return false;
		}
	}
	
	public boolean removeGameByGameID(int gameID) {
		// remove the Game from the Game Table
		Integer processRemoval = db.removeGamesByGameID(gameID);
		
		// check if the removal succeeded
		if (processRemoval > 0) {
			System.out.println("The Game (ID: " + gameID + " has been removed from the Games Table");
			return true;
		} else {
			System.out.println("Failed to remove the GameDB table");
			return false;
		}
	}
}
