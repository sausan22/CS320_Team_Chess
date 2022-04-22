package edu.ycp.cs320.booksdb.model;

public class MovesDB{
	private int gameID;
	private int turn;
	private int xCord;
	private int yCord;
	private int pieceNumber;
	
	public MovesDB() {
		
	}
	
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	
	public int getGameID() {
		return gameID;
	}
	
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public void setXCord(int xCord) {
		this.xCord = xCord;
	}
	
	public int getxCord() {
		return xCord;
	}
	
	public void setYCord(int yCord) {
		this.yCord = yCord;
	}
	
	public int getYCord() {
		return yCord;
	}
	
	public void setPieceNumber(int pieceNumber) {
		this.pieceNumber = pieceNumber;
	}
	
	public int getPieceNumber() {
		return pieceNumber;
	}
}
