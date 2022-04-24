package chessgame.controller;

import java.util.ArrayList;
import java.util.List;

import chessgame.model.*;
import edu.ycp.cs320.chessdb.model.*;
import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.DerbyDatabase;
import edu.ycp.cs320.chessdb.persist.IDatabase;

public class InsertNewPieceIntoPiecesTableController {

	private IDatabase db = null;

	public InsertNewPieceIntoPiecesTableController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
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
}
