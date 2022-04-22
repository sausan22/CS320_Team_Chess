package edu.ycp.cs320.booksdb.persist;

import java.util.List;

import edu.ycp.cs320.booksdb.model.*;
import chessgame.model.ChessPiece;

public interface IDatabase {
	public List<Pair<UserDB, GameDB>> findAllUsersGames(int userID, int gameID);
}
