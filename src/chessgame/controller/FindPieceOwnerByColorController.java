package chessgame.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.chessdb.model.*;
import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.DerbyDatabase;
import edu.ycp.cs320.chessdb.persist.IDatabase;

public class FindPieceOwnerByColorController {

	private IDatabase db = null;

	public FindPieceOwnerByColorController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public ArrayList<PlayersDB> getPieceOwnerByColor(boolean color, int pieceNumber, int gameID) {
		
		// get the list of (UserDB, GameDB) pairs from DB
		List<Pair<PlayersDB, PiecesDB>> playerPiecesList = db.findPieceOwnerByColor(color, pieceNumber, gameID);
		ArrayList<PlayersDB> players = null;
		
		if (playerPiecesList.isEmpty()) {
			System.out.println("No owner found for this piece");
			return null;
		}
		else {
			players = new ArrayList<PlayersDB>();
			for (Pair<PlayersDB, PiecesDB> playerPieces : playerPiecesList) {
				PlayersDB player = playerPieces.getLeft();
				PiecesDB piece = playerPieces.getRight();
				players.add(player);
				System.out.println("Player: " + player.getUserID() + " owns the " + piece.getPieceNumber() + " piece.");
			}			
		}
		
		// return of books for this author
		return players;
	}
}

