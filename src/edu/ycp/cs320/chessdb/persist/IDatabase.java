package edu.ycp.cs320.chessdb.persist;

import java.util.List; 
import chessgame.model.*;
import edu.ycp.cs320.chessdb.model.*;

public interface IDatabase {
	public List<Pair<User, GameDB>> findGameByUserID(int userID);
	public List<MovesDB> findCoordinateByPieceNumber(int pieceNumber, int turn);
	public List<Pair<Player, MovesDB>> findPieceOwnerByPieceNumber(int pieceNumber, int turn);
	public List<Pair<GameDB,MovesDB>> findGameSetUpByTurn(int gameID, int turn);
	public List<Pair<Player, GameDB>> findPlayersByGameID(int gameID);
	public List<GameDB> findGameByGameID(int gameID);
	public List<User> findUserbyUserID(int userId);
	public List<ChessPiece> findPiecesByGameID(int gameId);
	
	public User insertNewUser(String username, String password, String SALT);
		
	public Integer checkIfUserExists(String username);
		
	public User getUserInfo(String username);
	public User getUserInfoByID(int user_id);
}
