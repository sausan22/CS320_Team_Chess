package chessgame.controller;

import java.util.ArrayList;
import java.util.List;

import chessgame.model.ChessPiece;
import chessgame.model.User;
import edu.ycp.cs320.chessdb.model.*;
import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.DerbyDatabase;
import edu.ycp.cs320.chessdb.persist.IDatabase;

public class UserByUserIDController {
	private IDatabase db = null; 
	
	public UserByUserIDController() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	public ArrayList<User> getUsers(int userID){
		List<User> userList = db.findUserbyUserID(userID);
		ArrayList<User> users = null;
		if(userList.isEmpty()) {
			System.out.println("No users found in library");
		}
		else {
			users = new ArrayList<User>();
			for(User tempUser : userList) {
				users.add(tempUser);
			}
		}
		return users;
	}
		
}
