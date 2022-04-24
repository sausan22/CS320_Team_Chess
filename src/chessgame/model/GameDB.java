package chessgame.model;

public class GameDB {
	private int gameID;
	private int userID1;
	private int userID2;
	private int turn;
	
	public GameDB() {
		
	}
	
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	
	public int getGameID() {
		return gameID;
	}
	
	public void setUserID1(int userID1) {
		this.userID1 = userID1;
	}
	
	public int getUserID1() {
		return userID1;
	}
	
	public void setUserID2(int userID2) {
		this.userID2 = userID2;
	}
	
	public int getUserID2() {
		return userID2;
	}
	
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public int getTurn() {
		return turn;
	}
}
