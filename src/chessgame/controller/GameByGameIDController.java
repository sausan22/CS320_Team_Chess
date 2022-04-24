package chessgame.controller;

import java.util.ArrayList;
import java.util.List;

import chessgame.model.ChessPiece;
import chessgame.model.GameDB;
import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.DerbyDatabase;
import edu.ycp.cs320.chessdb.persist.IDatabase;

public class GameByGameIDController {
private IDatabase db = null; 
	
	public GameByGameIDController() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	public ArrayList<GameDB> getPieces(int GameID){
		List<GameDB> gameList = db.findGameByGameID(GameID);
		ArrayList<GameDB> piece = null;
		if(gameList.isEmpty()) {
			System.out.println("No pieces found in that game");
		}
		else {
			piece = new ArrayList<GameDB>();
			for(GameDB gaem : gameList) {
				piece.add(gaem);
			}
		}
		return piece;
	}
	
}
