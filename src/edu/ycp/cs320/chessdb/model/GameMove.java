package edu.ycp.cs320.chessdb.model;

public class GameMove {
	private int gameId;
	private int moveId;
	
	public GameMove() {
		
	}
	
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	public int getGameId() {
		return gameId;
	}
	
	public void setMoveId(int moveId) {
		this.moveId = moveId;
	}
	
	public int getMoveId() {
		return moveId;
	}
}