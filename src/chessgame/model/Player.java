package chessgame.model;

public class Player {
	private boolean color;
	private int gameID;
	private int userID;

	public Player(boolean c) {
		this.color = c;
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
	
	public boolean isCheck(ChessBoard cb, KingPiece kingPiece) {
		return false;
	}
}
