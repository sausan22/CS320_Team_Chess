package edu.ycp.cs320.chessdb.model;

public class GamePiece {
	private int gameId;
	private int pieceId;
	
	public GamePiece() {
		
	}
	
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	public int getGameId() {
		return gameId;
	}
	
	public void setPieceId(int pieceId) {
		this.pieceId = pieceId;
	}
	
	public int getPieceId() {
		return pieceId;
	}
}