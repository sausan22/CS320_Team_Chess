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
	public List<User> findUserbyUserID(int userID);
	public List<ChessPiece> findPiecesByGameID(int gameID);
	public Integer insertNewPieceIntoPiecesTable(int pieceID, int gameID, int xCord, int yCord, boolean color);
	public Integer insertCurrentTurnIntoMovesTable(int gameID, int pieceNumber, int xCord, int yCord, int turn);
	public Integer insertNewUserIntoUserTable(String username, String password);
	public Integer insertNewPlayerIntoPlayerTable(boolean color, int gameID, int userID);
	public Integer updatePieceInformation(int pieceNumber, int xCord, int yCord);
	public Integer updateGameInformation(int gameID, int turn);
	public Integer removeGamesByGameID(int gameID);
	public Integer insertGameByGameID(int user1ID, int user2ID, int turn);

}
