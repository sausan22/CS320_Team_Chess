package chessgame.controller;

import java.util.ArrayList;
import java.util.List;

import chessgame.model.ChessPiece;
import edu.ycp.cs320.chessdb.model.*;
import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.DerbyDatabase;
import edu.ycp.cs320.chessdb.persist.IDatabase;

public class getPiecesController {

	private IDatabase db = null;

	public getPiecesController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public ArrayList<ChessPiece> getThePieces() {
		
		// get the list of (Author, Book) pairs from DB
		List<ChessPiece> pieceList = db.findPiecesByGameID(1);
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

