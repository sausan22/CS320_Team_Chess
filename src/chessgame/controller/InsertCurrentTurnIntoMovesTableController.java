package chessgame.controller;

import java.util.ArrayList;
import java.util.List;

import chessgame.model.*;
import edu.ycp.cs320.chessdb.model.*;
import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.DerbyDatabase;
import edu.ycp.cs320.chessdb.persist.IDatabase;

public class InsertCurrentTurnIntoMovesTableController {

	private IDatabase db = null;

	public InsertCurrentTurnIntoMovesTableController() {
		
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
}
