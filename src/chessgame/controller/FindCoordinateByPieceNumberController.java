package chessgame.controller;

import java.util.ArrayList;
import java.util.List;

import chessgame.model.*;
import edu.ycp.cs320.chessdb.model.*;
import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.DerbyDatabase;
import edu.ycp.cs320.chessdb.persist.IDatabase;

public class FindCoordinateByPieceNumberController {

	private IDatabase db = null;

	public FindCoordinateByPieceNumberController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public ArrayList<MovesDB> getCoordinatesByPieceNumber(int pieceNumber, int turn) {
		
		// get the list of (Pieces, GameDB) pairs from DB
		List<MovesDB> moveList = db.findCoordinateByPieceNumber(pieceNumber, turn);
		ArrayList<MovesDB> moves = null;
		
		if (moveList.isEmpty()) {
			System.out.println("No coordinates found for this piece");
			return null;
		}
		else {
			moves = new ArrayList<MovesDB>();
			for (MovesDB move : moveList) {
				moves.add(move);
				System.out.println("Piece Number: " + move.getPieceNumber() + ", Turn Number: " + move.getTurn() + ", Row Position: " + move.getXCord() + ", Column Position: " + move.getYCord());
			}			
		}
		
		// return of books for this author
		return moves;
	}
}

