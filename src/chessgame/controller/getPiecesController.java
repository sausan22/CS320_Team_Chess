package chessgame.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.DerbyDatabase;
import edu.ycp.cs320.booksdb.persist.IDatabase;
import chessgame.model.ChessPiece;

public class getPiecesController {

	private IDatabase db = null;

	public getPiecesController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public ArrayList<ChessPiece> getThePieces() {
		
		// get the list of (Author, Book) pairs from DB
		List<ChessPiece> pieceList = db.findPiecesByGame(1);
		ArrayList<ChessPiece> pieces = null;
		
		if (pieceList.isEmpty()) {
			System.out.println("No pieces found in library");
			return null;
		}
		else {
			pieces = new ArrayList<ChessPiece>();
			for (ChessPiece daPiece : pieceList) {
				pieces.add(daPiece);
				
			}			
		}
		
		// return pieces for this game (only 1 rn)
		return pieces;
	}
}

