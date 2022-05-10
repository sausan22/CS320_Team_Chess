package chessgame.controller;

import java.util.ArrayList;
import java.util.List;

import chessgame.controller.*;
import chessgame.model.*;
import chessgame.servlet.*;
import edu.ycp.cs320.chessdb.model.Pair;
import edu.ycp.cs320.chessdb.persist.*;

public class GameController{

	private IDatabase db = null;
	private GameDB game = new GameDB();
	private GameDB gameDB = new GameDB();
	private List<GameDB> gameList = new ArrayList<GameDB>();
	private FindController find = new FindController();
	private InsertController insert = new InsertController();
	private UpdateController update = new UpdateController();
	
	public GameController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	public GameDB getGameByGameId(int gId) {
		// this might cause some issues by casting
		game = (GameDB) db.findGameByGameID(gId);
		return game;
		
		// if the above cast doesn't work
		// uncomment and use
		//
		// also change the return type to: " List<GameDB> "
		//gameList = db.findGameByGameID(gId);
		//return gameList
	}
	
	public GameDB getGameByUserId(int userId) {
		//cast might cause some issues
		
		game = (GameDB) db.findGameByUserID(userId);
		return game;
	}
	
	public List<Pair<Player, GameDB>> getFindPlayersByGameId(int gId){
		List<Pair<Player, GameDB>> gList = db.findPlayersByGameID(gId);
		
		return gList;
	}
	
	public Integer insertGame(int user1_id, int user2_id, int turn ) {
		int game = db.insertGameByGameID(user1_id, user2_id, turn);
		return game;
		
	}
	//update game
	public void updateGame() {
		//update game 
		
	}
	
	public boolean validatePieceMove(ChessPiece piece, int x, int y, ChessBoard cb, int gameId) {
		List<ChessPiece> pieceList = db.findPiecesByGameID(gameId);
		for(ChessPiece iterPiece : pieceList) {
			if(piece.getPieceID() == iterPiece.getPieceID()) {
				// makes piece to be whatever piece is returned from the 
				//findGames Query
				piece = iterPiece;
			}
		}
		
		return piece.checkMove(x, y, cb);
	}
	
	public void updateTurns(int gameId) {
		//db.updateturns on games table + 1
	}
	public boolean whosTurn(GameDB game) {
		if(game.getTurn()%2 == 0) {
			// white player case
			return true;
		} else {
			return false;
			
		}
	}
	
	public List<ChessPiece> getPiecesByGameID(int gameID){
		List<ChessPiece> pieceList = find.getThePieces(gameID);
		return pieceList;
	}
	
	public int getTurnByGameID(int gameID){
		// needs implemented once the findTurn query exists
		return -1;
	}
	
//	public boolean findPieceOwnerByPieceNumber(int pieceNum, int turn) {
//		db.findPieceOwnerByPieceNumber(pieceNum, turn);
//		
//		return null;
//	}
//	public identifyPlayers() {
//		gameDB.setUserID1(db.fin);
//	}
	
}
