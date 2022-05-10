package chessgame.controller;

import java.util.ArrayList;
import java.util.List;

import chessgame.controller.*;
import chessgame.model.*;
import chessgame.servlet.*;
import edu.ycp.cs320.chessdb.model.Pair;
import edu.ycp.cs320.chessdb.persist.*;

public class FindController{
	
	private IDatabase db = null;

	
	public FindController() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	
	public ArrayList<MovesDB> getCoordinatesByPieceNumber(int pieceNumber, int turn) {
		
		// get the list of (Pieces, GameDB) pairs from DB
		List<MovesDB> moveList = db.findCoordinateByPieceNumber(pieceNumber, turn);
		ArrayList<MovesDB> moves = null;
		
		if (moveList.isEmpty()) {
			System.out.println("No coordinates found for this piece");
			return null;
		}
		else {
			moves = new ArrayList<MovesDB>();
			for (MovesDB move : moveList) {
				moves.add(move);
				System.out.println("Piece Number: " + move.getPieceNumber() + ", Turn Number: " + move.getTurn() + ", Row Position: " + move.getXCord() + ", Column Position: " + move.getYCord());
			}			
		}
		
		// return of books for this author
		return moves;
	}
	
	public ArrayList<GameDB> getGameByUserID(int userID) {
		
		// get the list of (UserDB, GameDB) pairs from DB
		List<Pair<User, GameDB>> userGameList = db.findGameByUserID(userID);
		ArrayList<GameDB> games = null;
		
		if (userGameList.isEmpty()) {
			System.out.println("No games found for this user");
			return null;
		}
		else {
			games = new ArrayList<GameDB>();
			for (Pair<User, GameDB> userGame : userGameList) {
				User user = userGame.getLeft();
				GameDB game = userGame.getRight();
				games.add(game);
				System.out.println(user.getUsername() + ", Game #: " + game.getGameID());
			}			
		}
		
		// return of books for this author
		return games;
	}

	public ArrayList<MovesDB> getGameSetUpByTurn(int gameID, int turn) {
		
		// get the list of (GameDB, MovesDB) pairs from DB
		List<Pair<GameDB, MovesDB>> gameMovesList = db.findGameSetUpByTurn(gameID, turn);
		ArrayList<MovesDB> moves = null;
		
		if (gameMovesList.isEmpty()) {
			System.out.println("No games found for this user");
			return null;
		}
		else {
			moves = new ArrayList<MovesDB>();
			for (Pair<GameDB, MovesDB> gameMoves : gameMovesList) {
				GameDB game = gameMoves.getLeft();
				MovesDB move = gameMoves.getRight();
				moves.add(move);
				System.out.println("Game #: " + game.getGameID() + " on turn " + move.getTurn() + " with piece " + move.getPieceNumber() + " being located at " + move.getXCord() + "," + move.getYCord());
			}			
		}
		
		// return of moves for this game
		return moves;
	}
	
	public ArrayList<Player> getPieceOwnerByPieceNumber(int pieceNumber, int turn) {
		
		// get the list of (UserDB, GameDB) pairs from DB
		List<Pair<Player, MovesDB>> playerMovesList = db.findPieceOwnerByPieceNumber(pieceNumber, turn);
		ArrayList<Player> players = null;
		
		if (playerMovesList.isEmpty()) {
			System.out.println("No owner found for this piece");
			return null;
		}
		else {
			players = new ArrayList<Player>();
			for (Pair<Player, MovesDB> playerMoves : playerMovesList) {
				Player player = playerMoves.getLeft();
				MovesDB move = playerMoves.getRight();
				players.add(player);
				System.out.println("Player: " + player.getUserID() + " owns the " + move.getPieceNumber() + " piece.");
			}			
		}
		
		// return of books for this author
		return players;
	}
	
	public ArrayList<Player> getPlayersByGameID(int gameID) {
		
		// get the list of (PlayersDB, GameDB) pairs from DB
		List<Pair<Player, Player>> playersGameList = db.findPlayersByGameID(gameID);
		ArrayList<Player> games = null;
		
		if (playersGameList.isEmpty()) {
			System.out.println("No players found for this game");
			return null;
		}
		else {
			games = new ArrayList<Player>(); //used to be a list of games, why something that gets players was trying to return a list of games is beyond me
			for (Pair<Player, Player> playerGame : playersGameList) {
				Player player = playerGame.getLeft();
				Player p2 = playerGame.getRight();
				games.add(player);
				games.add(p2);
				//System.out.println("Game #: " + game.getGameID() + " has player 1: " + game.getUserID1() + " and player 2: " + game.getUserID2());
			}			
		}
		
		// return of players for this game
		return games;
	}
	
	
	// Re-implement findGameByGameID query
	
	public ArrayList<GameDB> getGameInfo(int GameID){
		List<GameDB> gameList = db.findGameByGameID(GameID);
		ArrayList<GameDB> games = null;
		if(gameList.isEmpty()) {
			System.out.println("No pieces found in that game");
		}
		else {
			games = new ArrayList<GameDB>();
			for(GameDB daGame : gameList) {
				games.add(daGame);
			}
		}
		return games;
	}
	
	public ArrayList<ChessPiece> getThePieces(int GameID) {
		
		// get the list of (Author, Book) pairs from DB
		List<ChessPiece> pieceList = db.findPiecesByGameID(GameID);
		ArrayList<ChessPiece> pieces = null;
		
		if (pieceList.isEmpty()) {
			System.out.println("No pieces found in library");
			return null;
		}
		else {
			pieces = new ArrayList<ChessPiece>();
			for (ChessPiece daPiece : pieceList) {
				pieces.add(daPiece);	
			}			
		}
		
		// return pieces for this game (only 1 rn)
		return pieces;
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
	
	// add query for findTurnByGameID here
	public Integer getTurnByGameID(int gameID) {
		// Assign temporary value of -1 to check for existence
		Integer turnNumber = -1;
		
		turnNumber = db.findTurnByGameID(gameID);
		
		if(turnNumber > 0) {
			System.out.println("GameID: " + gameID + " is currently on turn " + turnNumber);
		} else {
			System.out.println("GameID: " + gameID + " does not currently exist in the Database");
		}
		return turnNumber;
	}
}
