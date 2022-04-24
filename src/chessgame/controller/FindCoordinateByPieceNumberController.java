package chessgame.controller;

import java.util.ArrayList;
import java.util.List;

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

	public ArrayList<MovesDB> getCoordinatesByPieceNumber(int pieceNumber) {
		
		// get the list of (Pieces, GameDB) pairs from DB
		List<Pair<PiecesDB, MovesDB>> pieceMoveList = db.findCoordinateByPieceNumber(pieceNumber);
		ArrayList<MovesDB> moves = null;
		
		if (pieceMoveList.isEmpty()) {
			System.out.println("No coordinates found for this piece");
			return null;
		}
		else {
			moves = new ArrayList<MovesDB>();
			for (Pair<PiecesDB, MovesDB> pieceMoves : pieceMoveList) {
				PiecesDB piece = pieceMoves.getLeft();
				MovesDB move = pieceMoves.getRight();
				moves.add(move);
				System.out.println("Piece Number: " + piece.getPieceNumber() + ", Turn Number: " + move.getTurn() + ", Row Position: " + move.getXCord() + ", Column Position: " + move.getYCord());
			}			
		}
		
		// return of books for this author
		return moves;
	}
}

