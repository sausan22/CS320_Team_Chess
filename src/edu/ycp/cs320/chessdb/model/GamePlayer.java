package edu.ycp.cs320.chessdb.model;

public class GamePlayer {
	private int gameId;
	private int playerId;
	
	public GamePlayer() {
		
	}
	
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	public int getGameId() {
		return gameId;
	}
	
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	public int getPlayerId() {
		return playerId;
	}
}
