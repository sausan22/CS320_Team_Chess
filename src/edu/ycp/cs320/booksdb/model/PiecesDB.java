package edu.ycp.cs320.booksdb.model;

public class PiecesDB {
	private int pieceID;
	private int pieceNumber;
	private int gameID;
	private int xCord;
	private int yCord;
	private boolean color;
	
	public PiecesDB() {
		
	}
	
	public void setPieceID(int pieceID) {
		this.pieceID = pieceID;
	}
	
	public int getPieceID() {
		return pieceID;
	}
	
	public void setPieceNumber(int pieceNumber) {
		this.pieceNumber = pieceNumber;
	}
	
	public int getPieceNumber() {
		return pieceNumber;
	}
	
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	
	public int getGameID() {
		return gameID;
	}
	
	public void setXCord(int xCord) {
		this.xCord = xCord;
	}
	
	public int getXCord() {
		return xCord;
	}
	
	public void setYCord(int yCord) {
		this.yCord = yCord;
	}
	
	public int getYCord() {
		return yCord;
	}
	
	public void setColor(boolean color) {
		this.color = color;
	}
	
	public boolean getColor() {
		return color;
	}
}
