package edu.ycp.cs320.chessdb.persist;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import chessgame.model.ChessPiece;
import chessgame.model.GameDB;
import chessgame.model.PawnPiece;
import edu.ycp.cs320.chessdb.model.*;

public class InitialData {

	// reads initial Author data from CSV file and returns a List of Authors
	public static List<UserDB> getUsers() throws IOException {
		List<UserDB> userList = new ArrayList<UserDB>();
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
				UserDB user = new UserDB();

				// read user ID from CSV file, but don't use it
				// it's there for reference purposes, just make sure that it is correct
				// when setting up the BookAuthors CSV file				
				Integer.parseInt(i.next());
				// auto-generate author ID, instead
				user.setUserID(UserID++);			
				user.setUsername(i.next());
				//how do we get the password from the tuple
				//user.setPassword(i.next());
				
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
//				book.setAuthorId(Integer.parseInt(i.next()));  // no longer in books table
				
				
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
	public static List<PlayersDB> getPlayers() throws IOException {
		List<PlayersDB> PlayersList = new ArrayList<PlayersDB>();
		ReadCSV readPlayers = new ReadCSV("players.csv");
		try {
			while (true) {
				List<String> tuple = readPlayers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				PlayersDB players = new PlayersDB();
				players.setColor(Boolean.parseBoolean(i.next()));
				players.setGameID(Integer.parseInt(i.next()));
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
			while (true) {
				List<String> tuple = movesReader.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				MovesDB moves = new MovesDB();
				
				moves.setGameID(Integer.parseInt(i.next()));
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
		public static List<PiecesDB> getPieces() throws IOException {
			List<PiecesDB> pieceList = new ArrayList<PiecesDB>();
			ReadCSV readPieces = new ReadCSV("pieces.csv");
			try {
				// auto-generated primary key for table books
				Integer pieceId = 0;
				Integer pieceNumber = 1;
				while (true) {
					List<String> tuple = readPieces.next();
					if (tuple == null) {
						break;
					}
					Iterator<String> i = tuple.iterator();
					
					
					/*if(pNum>=0 && pNum<=15) {
					if(!pieces.get(i).getColor()) {
						rNum -= 8;
					}
					pieceId = ("images/"+daColor+"Pawn.png");
					//pieceId = (daColor + "pawn" + rNum);
				}
				if(pNum>=16 && pNum<=19) {
					rNum -= 16;
					if(!pieces.get(i).getColor()) {
						rNum -= 2;
					}
					pieceId = ("images/"+daColor+"Knight.png");
					//pieceId = (daColor + "knight" + rNum);
				}
				if(pNum>=20 && pNum<=23) {
					rNum -= 20;
					if(!pieces.get(i).getColor()) {
						rNum -= 2;
					}
					pieceId = ("images/"+daColor+"Bishop.png");
					//pieceId = (daColor + "bishop" + rNum);
				}
				if(pNum>=24 && pNum<=27) {
					rNum -= 24;
					if(!pieces.get(i).getColor()) {
						rNum -= 2;
					}
					pieceId = ("images/"+daColor+"Rook.png");
					//pieceId = (daColor + "rook" + rNum);
				}
				if(pNum>=28 && pNum<=29) {
					rNum -= 28;
					if(!pieces.get(i).getColor()) {
						rNum --;
					}
					pieceId = ("images/"+daColor+"Queen.png");
					//pieceId = (daColor + "queen" + rNum);
				}
				if(pNum>=30 && pNum<=31) {
					rNum -= 30;
					if(!pieces.get(i).getColor()) {
						rNum --;
					}
					pieceId = ("images/"+daColor+"King.png");
					//pieceId = (daColor + "king" + rNum);
				}*/
					
					
					// the start of assign the pieces 
					if(Integer.parseInt(i.next()) >= 0 && Integer.parseInt(i.next()) <= 15) {
						
					}
					PiecesDB daPiece = new PiecesDB();
					
					Integer.parseInt(i.next()); //skip piece id
					Integer.parseInt(i.next()); //also skip game id for now
					daPiece.setPieceNumber(Integer.parseInt(i.next()));	
					daPiece.setColor(Boolean.getBoolean(i.next()));
					daPiece.setXCord(Integer.parseInt(i.next()));
					daPiece.setYCord(Integer.parseInt(i.next()));
					Integer.parseInt(i.next()); //skip captured for now
					
					pieceList.add(daPiece);
				}
				System.out.println("pieceList loaded from CSV file");			
				return pieceList;
			} finally {
				readPieces.close();
			}
		}
}