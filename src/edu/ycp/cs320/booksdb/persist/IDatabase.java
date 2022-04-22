package edu.ycp.cs320.booksdb.persist;

import java.util.List;

import edu.ycp.cs320.booksdb.model.*;
import chessgame.model.ChessPiece;

public interface IDatabase {
	public List<Pair<UserDB, GameDB>> findGameByUserID(int userID);
	public List<Pair<PiecesDB, MovesDB>> findCoordinateByPieceNumber(int pieceNumber);
	public List<Pair<PlayersDB, PiecesDB>> findPieceOwnerByColor(boolean color, int pieceNumber, int gameID);
	public List<Pair<GameDB,MovesDB>> findGameSetUpByTurn(int gameID, int turn);
	public List<Pair<PlayersDB, GameDB>> findPlayersByGameID(int gameID);
}
