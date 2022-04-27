package chessgame.controller;

import java.util.ArrayList;
import java.util.List;

import chessgame.model.*;
import edu.ycp.cs320.chessdb.model.*;
import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.DerbyDatabase;
import edu.ycp.cs320.chessdb.persist.IDatabase;

public class FindPlayersByGameIDController {

	private IDatabase db = null;

	public FindPlayersByGameIDController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public ArrayList<GameDB> getPlayersByGameID(int gameID) {
		
		// get the list of (PlayersDB, GameDB) pairs from DB
		List<Pair<Player, GameDB>> playersGameList = db.findPlayersByGameID(gameID);
		ArrayList<GameDB> games = null;
		
		if (playersGameList.isEmpty()) {
			System.out.println("No players found for this game");
			return null;
		}
		else {
			games = new ArrayList<GameDB>();
			for (Pair<Player, GameDB> playerGame : playersGameList) {
				Player player = playerGame.getLeft();
				GameDB game = playerGame.getRight();
				games.add(game);
				System.out.println("Game #: " + game.getGameID() + " has player 1: " + game.getUserID1() + " and player 2: " + game.getUserID2());
			}			
		}
		
		// return of players for this game
		return games;
	}
}

