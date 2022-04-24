package chessgame.controller;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.chessdb.model.*;
import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.DerbyDatabase;
import edu.ycp.cs320.chessdb.persist.IDatabase;

public class FindGameSetUpByTurnController {

	private IDatabase db = null;

	public FindGameSetUpByTurnController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public ArrayList<MovesDB> getGameSetUpByTurn(int gameID, int turn) {
		
		// get the list of (GameDB, MovesDB) pairs from DB
		List<Pair<GameDB, MovesDB>> gameMovesList = db.findGameSetUpByTurn(gameID, turn);
		ArrayList<MovesDB> moves = null;
		
		if (gameMovesList.isEmpty()) {
			System.out.println("No games found for this user");
			return null;
		}
		else {
			moves = new ArrayList<MovesDB>();
			for (Pair<GameDB, MovesDB> gameMoves : gameMovesList) {
				GameDB game = gameMoves.getLeft();
				MovesDB move = gameMoves.getRight();
				moves.add(move);
				System.out.println("Game #: " + game.getGameID() + " on turn " + move.getTurn() + " with piece " + move.getPieceNumber() + " being located at " + move.getXCord() + "," + move.getYCord());
			}			
		}
		
		// return of moves for this game
		return moves;
	}
}

