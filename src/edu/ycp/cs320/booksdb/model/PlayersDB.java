package edu.ycp.cs320.booksdb.model;

public class PlayersDB {
	private boolean color;
	private int gameID;
	private int userID;

	public PlayersDB() {
		
	}
	
	public void setColor(boolean color) {
		this.color = color;
	}
	
	public boolean getColor() {
		return color;
	}
	
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	
	public int getGameID() {
		return gameID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public int getUserID() {
		return userID;
	}
}
