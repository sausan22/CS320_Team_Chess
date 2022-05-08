package chessgame.model;

import edu.ycp.cs320.chessdb.InitDatabase;
import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.IDatabase;

public class User {
	private String username;
	private String password;
	private String SALT;
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
	
	public void setSALT(String SALT) {
		this.SALT = SALT;
	}
	
	public String getSALT() {
		return SALT;
	}
	
	public User getUserInfo(String User) {
		InitDatabase.init();
		IDatabase db = DatabaseProvider.getInstance();
		User user = db.getUserInfo(User);
		return user;
		
	}

	//Check if username is present in the DB
	public boolean checkIfUserExists(String User) {
		InitDatabase.init();
		IDatabase db = DatabaseProvider.getInstance();
		int result = db.checkIfUserExists(User);
		
		if(result == 1) {
			return true;
		}
		
		else {
			return false;
		}
	}
}
