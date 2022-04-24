package edu.ycp.cs320.chessdb.persist;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import chessgame.model.ChessPiece;
import chessgame.model.PawnPiece;
import chessgame.model.User;
import chessgame.model.GameDB; //game and moves are supposed to have the DB, don't change them
import chessgame.model.Player;
import chessgame.model.MovesDB;
import chessgame.model.BishopPiece;
import chessgame.model.RookPiece;
import chessgame.model.KnightPiece;
import chessgame.model.QueenPiece;
import chessgame.model.KingPiece;
import edu.ycp.cs320.chessdb.model.*;

public class InitialData {

	// reads initial Author data from CSV file and returns a List of Authors
	public static List<User> getUsers() throws IOException {
		List<User> userList = new ArrayList<User>();
		ReadCSV readUsers = new ReadCSV("users.csv");
		try {
			// auto-generated primary key for authors table
			Integer UserID = 1;
			while (true) {
				List<String> tuple = readUsers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				User user = new User();

				// read user ID from CSV file, but don't use it
				// it's there for reference purposes, just make sure that it is correct
				// when setting up the BookAuthors CSV file				
				Integer.parseInt(i.next());
				// auto-generate author ID, instead
				user.setUserID(UserID++);			
				user.setUsername(i.next());
				//how do we get the password from the tuple
				user.setPassword(i.next());
				
				userList.add(user);
			}
			System.out.println("Users loaded from CSV file");
			return userList;
		} finally {
			readUsers.close();
		}
	}
	
	// reads initial Book data from CSV file and returns a List of Books
	public static List<GameDB> getGames() throws IOException {
		List<GameDB> gameList = new ArrayList<GameDB>();
		ReadCSV readGames = new ReadCSV("games.csv");
		try {
			// auto-generated primary key for table books
			Integer gameID = 1;
			while (true) {
				List<String> tuple = readGames.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				GameDB game = new GameDB();
				
				// read book ID from CSV file, but don't use it
				// it's there for reference purposes, just make sure that it is correct
				// when setting up the BookAuthors CSV file
				Integer.parseInt(i.next());
				// auto-generate book ID, instead
				game.setGameID(gameID++);								
				game.setUserID1(Integer.parseInt(i.next()));
				game.setUserID2(Integer.parseInt(i.next()));
				game.setTurn(Integer.parseInt(i.next()));
				gameList.add(game);
			}
			System.out.println("Games loaded from CSV file");			
			return gameList;
		} finally {
			readGames.close();
		}
	}
	
	// reads initial BookAuthor data from CSV file and returns a List of BookAuthors
	public static List<Player> getPlayers() throws IOException {
		List<Player> PlayersList = new ArrayList<Player>();
		ReadCSV readPlayers = new ReadCSV("players.csv");
		try {
			//auto-generated primary key
			Integer PID = 1;
			while (true) {
				List<String> tuple = readPlayers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Player players = new Player(false);
				
				//Integer.parseInt(i.next()); //skip player id
				players.setPlayerID(PID++);
				players.setColor(Boolean.parseBoolean(i.next()));
				//players.setGameID(Integer.parseInt(i.next()));
				players.setUserID(Integer.parseInt(i.next()));
				
				PlayersList.add(players);
			}
			System.out.println("Players loaded from CSV file");			
			return PlayersList;
		} finally {
			readPlayers.close();
		}
	}
	public static List<MovesDB> getMoves() throws IOException {
		List<MovesDB> MovesList = new ArrayList<MovesDB>();
		ReadCSV movesReader = new ReadCSV("moves.csv");
		try {
			//auto-generated primary key
			Integer MID = 1;
			while (true) {
				List<String> tuple = movesReader.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				MovesDB moves = new MovesDB();
				
				//Integer.parseInt(i.next()); //skip move id
				moves.setMoveID(MID++);
				//moves.setGameID(Integer.parseInt(i.next()));
				moves.setPieceNumber(Integer.parseInt(i.next()));
				moves.setXCord(Integer.parseInt(i.next()));
				moves.setYCord(Integer.parseInt(i.next()));
				moves.setTurn(Integer.parseInt(i.next()));
				
				MovesList.add(moves);
				
			}
			System.out.println("Players loaded from CSV file");			
			return MovesList;
		} finally {
			movesReader.close();
		}
	}
	
	// reads initial Book data from CSV file and returns a List of Books
		public static List<ChessPiece> getPieces() throws IOException {
			List<ChessPiece> pieceList = new ArrayList<ChessPiece>();
			ReadCSV readPieces = new ReadCSV("pieces.csv");
			try {
				// auto-generated primary key for table pieces
				Integer PID = 1;
				while (true) {
					List<String> tuple = readPieces.next();
					if (tuple == null) {
						break;
					}
					Iterator<String> i = tuple.iterator();
					
					ChessPiece daPiece = new PawnPiece(-1, -1, true, -1);
					
					Integer trueID = Integer.parseInt(i.next()); //skip piece number
					Integer pNum = Integer.parseInt(i.next()); //save the pieceID
					//Integer gameID = Integer.parseInt(i.next()); //save the gameID
					Integer xPos = Integer.parseInt(i.next()); //save the xPos
					Integer yPos = Integer.parseInt(i.next()); //save the yPos
					boolean color = Boolean.getBoolean(i.next()); //save the color
					
					if(pNum>=0 && pNum<=15) {
						daPiece = new PawnPiece(xPos, yPos, color, pNum);
					}
					if(pNum>=16 && pNum<=19) {
						daPiece = new KnightPiece(xPos, yPos, color, pNum);
					}
					if(pNum>=20 && pNum<=23) {
						daPiece = new BishopPiece(xPos, yPos, color, pNum);
					}
					if(pNum>=24 && pNum<=27) {
						daPiece = new RookPiece(xPos, yPos, color, pNum);
					}
					if(pNum>=28 && pNum<=29) {
						daPiece = new QueenPiece(xPos, yPos, color, pNum);
					}
					if(pNum>=30 && pNum<=31) {
						daPiece = new KingPiece(xPos, yPos, color, pNum);
					}
					//daPiece.setGameID(gameID);
					daPiece.setPieceId(pNum);
					daPiece.setPieceNumber(PID++);
					pieceList.add(daPiece);
				}
				System.out.println("pieceList loaded from CSV file");			
				return pieceList;
			} finally {
				readPieces.close();
			}
		}
		
		public static List<GameMove> getGameMoves() throws IOException {
			List<GameMove> GameMovesList = new ArrayList<GameMove>();
			ReadCSV readGameMoves = new ReadCSV("gamemoves.csv");
			try {
				while (true) {
					List<String> tuple = readGameMoves.next();
					if (tuple == null) {
						break;
					}
					Iterator<String> i = tuple.iterator();
					GameMove daGameMove = new GameMove();
					daGameMove.setGameId(Integer.parseInt(i.next()));
					daGameMove.setMoveId(Integer.parseInt(i.next()));
					
					GameMovesList.add(daGameMove);
				}
				System.out.println("GameMoves loaded from CSV file");			
				return GameMovesList;
			} finally {
				readGameMoves.close();
			}
		}
		
		public static List<GamePiece> getGamePieces() throws IOException {
			List<GamePiece> GamePiecesList = new ArrayList<GamePiece>();
			ReadCSV readGamePieces = new ReadCSV("gamepieces.csv");
			try {
				while (true) {
					List<String> tuple = readGamePieces.next();
					if (tuple == null) {
						break;
					}
					Iterator<String> i = tuple.iterator();
					GamePiece daGamePiece = new GamePiece();
					daGamePiece.setGameId(Integer.parseInt(i.next()));
					daGamePiece.setPieceId(Integer.parseInt(i.next()));
					
					GamePiecesList.add(daGamePiece);
				}
				System.out.println("GamePieces loaded from CSV file");			
				return GamePiecesList;
			} finally {
				readGamePieces.close();
			}
		}
		
		public static List<GamePlayer> getGamePlayers() throws IOException {
			List<GamePlayer> GamePlayersList = new ArrayList<GamePlayer>();
			ReadCSV readGamePlayers = new ReadCSV("gameplayers.csv");
			try {
				while (true) {
					List<String> tuple = readGamePlayers.next();
					if (tuple == null) {
						break;
					}
					Iterator<String> i = tuple.iterator();
					GamePlayer daGamePlayer = new GamePlayer();
					daGamePlayer.setGameId(Integer.parseInt(i.next()));
					daGamePlayer.setPlayerId(Integer.parseInt(i.next()));
					
					GamePlayersList.add(daGamePlayer);
				}
				System.out.println("GamePlayers loaded from CSV file");			
				return GamePlayersList;
			} finally {
				readGamePlayers.close();
			}
		}
}