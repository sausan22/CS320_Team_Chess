package chessgame.controller;

import java.util.List;
import java.util.ArrayList;
import chessgame.model.Library;
import edu.ycp.cs320.chessdb.persist.*;
import chessgame.model.*;

public class LoginController {
	private IDatabase db = null;
	
	public LoginController() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	
	public boolean insertNewUserIntoUserTable(String username, String password) {
		
		// insert new user into DB
		Integer userID = db.insertNewUserIntoUserTable(username, password);
		
		// check if the insertion succeeded
		if (userID > 0)
		{
			System.out.println("New User: " + username + " (ID: " + userID + " ) successfully added to User table");
			
			return true;
		}
		else
		{
			System.out.println("Failed to insert new user (ID: " + userID + ") into User table");
			return false;
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean validateUserCredentials(String username, String password) {
		
		// get the user info based on the username from DB
		List<User> userList = db.findUserByUsername(username);
		if (userList.isEmpty()) {
			System.out.println("This username does not exist in the database");
			return false;
		} else {
			User daUser = userList.get(0);
			// Compares to user provided username and password with the database stored username and password
			if (username.equals(daUser.getUsername()) && password.equals(daUser.getPassword())) {
				return true;
			}
			return false;
		}
	}
	
	
}
