package chessgame.controller;

import chessgame.controller.*;
import chessgame.model.*;
import chessgame.servlet.*;
import edu.ycp.cs320.chessdb.persist.*;

public class GameController{
	
	private IDatabase db = null;
	private Game game = new Game();
	private GameDB gameDB = new GameDB();
	
	public GameController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	
//	public identifyPlayers() {
//		gameDB.setUserID1(db.fin);
//	}
	
	
}
