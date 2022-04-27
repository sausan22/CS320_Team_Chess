package chessgame.controller;

import java.util.ArrayList;
import java.util.List;

import chessgame.model.*;
import edu.ycp.cs320.chessdb.model.*;
import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.DerbyDatabase;
import edu.ycp.cs320.chessdb.persist.IDatabase;

public class FindGameByUserIDController {

	private IDatabase db = null;

	public FindGameByUserIDController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public ArrayList<GameDB> getGameByUserID(int userID) {
		
		// get the list of (UserDB, GameDB) pairs from DB
		List<Pair<User, GameDB>> userGameList = db.findGameByUserID(userID);
		ArrayList<GameDB> games = null;
		
		if (userGameList.isEmpty()) {
			System.out.println("No games found for this user");
			return null;
		}
		else {
			games = new ArrayList<GameDB>();
			for (Pair<User, GameDB> userGame : userGameList) {
				User user = userGame.getLeft();
				GameDB game = userGame.getRight();
				games.add(game);
				System.out.println(user.getUsername() + ", Game #: " + game.getGameID());
			}			
		}
		
		// return of books for this author
		return games;
	}
}

