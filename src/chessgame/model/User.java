package chessgame.model;

public class User {
	private String username;
	private String password;
	private int userID;
	
	public User() {
		
	}
	
	public void setUserID(int userID){
		this.userID = userID;
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public void setUsername(String user) {
		this.username = user;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String pw) {
		this.password = pw;
	}
	
	public String getPassword() {
		return this.password;
	}
}
