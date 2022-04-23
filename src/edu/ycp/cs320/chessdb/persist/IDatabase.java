package edu.ycp.cs320.chessdb.persist;

import java.util.List;

import chessgame.model.ChessPiece;
import edu.ycp.cs320.chessdb.model.*;

public interface IDatabase {
	public List<Pair<UserDB, GameDB>> findGameByUserID(int userID);
	public List<Pair<PiecesDB, MovesDB>> findCoordinateByPieceNumber(int pieceNumber);
	public List<Pair<PlayersDB, PiecesDB>> findPieceOwnerByColor(boolean color, int pieceNumber, int gameID);
	public List<Pair<GameDB,MovesDB>> findGameSetUpByTurn(int gameID, int turn);
	public List<Pair<PlayersDB, GameDB>> findPlayersByGameID(int gameID);
//	public List<PiecesDB> findPiecesByGame(int gameID);
	public List<GameDB> findGameByGameID(int gameID);
	public List<UserDB> findUserbyUserID(int userId);
	public List<PiecesDB> findPiecesByGameID(int gameId);
}
