package edu.ycp.cs320.chessdb.persist;

import java.util.List; 
import chessgame.model.ChessPiece;
import edu.ycp.cs320.chessdb.model.*;
import chessgame.model.*;

public interface IDatabase {
	public List<Pair<User, GameDB>> findGameByUserID(int userID);
	public List<Pair<ChessPiece, MovesDB>> findCoordinateByPieceNumber(int pieceNumber);
	public List<Pair<Player, ChessPiece>> findPieceOwnerByColor(boolean color, int pieceNumber, int gameID);
	public List<Pair<GameDB,MovesDB>> findGameSetUpByTurn(int gameID, int turn);
	public List<Pair<Player, GameDB>> findPlayersByGameID(int gameID);
	public List<GameDB> findGameByGameID(int gameID);
	public List<User> findUserbyUserID(int userId);
	public List<ChessPiece> findPiecesByGameID(int gameId);
}
