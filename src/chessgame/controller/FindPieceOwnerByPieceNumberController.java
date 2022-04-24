package chessgame.controller;

import java.util.ArrayList;
import java.util.List;

import chessgame.model.*;
import edu.ycp.cs320.chessdb.model.*;
import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.DerbyDatabase;
import edu.ycp.cs320.chessdb.persist.IDatabase;

public class FindPieceOwnerByPieceNumberController {

	private IDatabase db = null;

	public FindPieceOwnerByPieceNumberController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public ArrayList<Player> getPieceOwnerByPieceNumber(int pieceNumber, int turn) {
		
		// get the list of (UserDB, GameDB) pairs from DB
		List<Pair<Player, MovesDB>> playerMovesList = db.findPieceOwnerByPieceNumber(pieceNumber, turn);
		ArrayList<Player> players = null;
		
		if (playerMovesList.isEmpty()) {
			System.out.println("No owner found for this piece");
			return null;
		}
		else {
			players = new ArrayList<Player>();
			for (Pair<Player, MovesDB> playerMoves : playerMovesList) {
				Player player = playerMoves.getLeft();
				MovesDB move = playerMoves.getRight();
				players.add(player);
				System.out.println("Player: " + player.getUserID() + " owns the " + move.getPieceNumber() + " piece.");
			}			
		}
		
		// return of books for this author
		return players;
	}
}

