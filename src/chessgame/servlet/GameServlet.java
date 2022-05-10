package chessgame.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import chessgame.controller.*;
import chessgame.model.*;
import edu.ycp.cs320.chessdb.PiecesByGameQuery;

public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Game Servlet: doGet");
		req.setAttribute("message", "enter text here");
		req.setAttribute("ipos", "");
		req.setAttribute("fpos", "");
		
		try {
			String gameId = req.getParameter("gameid");
			System.out.println("The user has selected the game with the ID of " + gameId + ".");
		}
		catch(Exception e) {
			System.out.println("lol the gameid stuff doesn't work yet");
		}
		
		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Game Servlet: doPost");
		int gameIdNum = -1;
		try {
			String[] gameIds = {req.getParameter("gameid"), req.getParameter("gameid1"), req.getParameter("gameid2"), req.getParameter("gameid3")};
			for(String daGameId: gameIds) {
				if(daGameId != null) {
					gameIdNum = Integer.parseInt(daGameId);
				}
			}
			System.out.println("The user has selected the game with the ID of " + gameIdNum + ".");
		}
		catch(Exception e) {
			System.out.println("lol the gameid stuff doesn't work yet");
		}
		
		int xMove = -1;
		int yMove = -1;
		
		GameController controller = new GameController();
		FindController findController = new FindController();
		//GameDB model = controller.getGameByGameId(gameIdNum);
		//controller.setModel(model);
		
		//ChessBoard chessBoard = new ChessBoard();
		
		String message = req.getParameter("message");
		String submit = req.getParameter("submitBtn");
		String initpos = "zz";
		String finpos = "zz";
		try {
			initpos = req.getParameter("ipos");
			finpos = req.getParameter("fpos");
			System.out.println("initial position is "+initpos);
			System.out.println("final position is "+finpos);
		}
		catch (Exception NullPointerException) {
			System.out.println("something has gone wrong");
		}
		
		String[][] daBoard = new String[8][8];
		//in the actual implementation, pieces would pull from the pieces array in game
		//fake chessboard loading
		/*ChessPiece[] daPieces = {fakeChessPiece(0, 0, 24, true), fakeChessPiece(1, 0, 16, true), 
				fakeChessPiece(2, 0, 20, true), fakeChessPiece(3, 0, 28, true), 
				fakeChessPiece(4, 0, 30, true), fakeChessPiece(5, 0, 21, true), 
				fakeChessPiece(6, 0, 17, true), fakeChessPiece(7, 0, 25, true),
				fakeChessPiece(0, 1, 0, true), fakeChessPiece(1, 1, 1, true), 
				fakeChessPiece(2, 1, 2, true), fakeChessPiece(3, 1, 3, true), 
				fakeChessPiece(4, 1, 4, true), fakeChessPiece(5, 1, 5, true), 
				fakeChessPiece(6, 1, 6, true), fakeChessPiece(7, 1, 7, true),
				fakeChessPiece(0, 6, 8, false), fakeChessPiece(1, 6, 9, false), 
				fakeChessPiece(2, 6, 10, false), fakeChessPiece(3, 6, 11, false), 
				fakeChessPiece(4, 6, 12, false), fakeChessPiece(5, 6, 13, false), 
				fakeChessPiece(6, 6, 14, false), fakeChessPiece(7, 6, 15, false),
				fakeChessPiece(0, 7, 26, false), fakeChessPiece(1, 7, 18, false), 
				fakeChessPiece(2, 7, 22, false), fakeChessPiece(3, 7, 29, false), 
				fakeChessPiece(4, 7, 31, false), fakeChessPiece(5, 7, 23, false), 
				fakeChessPiece(6, 7, 19, false), fakeChessPiece(7, 7, 27, false)};
		ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>(Arrays.asList(daPieces));*/
		//REAL chessboard loading
		System.out.println("Attempting to get pieces for game with id "+gameIdNum);
		ArrayList<ChessPiece> pieces = findController.getThePieces(gameIdNum);
		ChessBoard loadedBoard = new ChessBoard();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				try{
					pieces.add(loadedBoard.getTile(i,  j).getPiece());
					int daPieceNum = loadedBoard.getTile(i,  j).getPiece().getPieceNumber();
					int daTileNum = j + (8 * i);
					System.out.println("Put piece num "+daPieceNum+" on tile num "+daTileNum);
				}
				catch (Exception NullPointerException){
					System.out.println("empty tile lol");
				}
			}
		}
		//assemble the image path for each piece
		for(int i = 0; i < pieces.size(); i++) {
			//int pNum = pieces.get(i).getPieceNumber();
			String pieceId = "";
			String daColor = "b";
			String daClass;
			try {
				daClass = pieces.get(i).whatPiece();
			}
			catch(Error UnresolvedCompilationProblem) {
				daClass = "Pawn"; //using this for now since whatPiece isn't implemented in all classes
			}
			//rnum is used in the text/id implementation of this
			//int rNum = pNum+1;
			if(pieces.get(i).getColor()) {
				daColor = "w";
			}
			
			pieceId = "images/"+daColor+daClass+".png";
			
			//System.out.println(pieceId + " at position (" + pieces.get(i).getXlocation() + ", " + pieces.get(i).getYlocation() + ").");
			daBoard[pieces.get(i).getXlocation()][pieces.get(i).getYlocation()] = pieceId;
			Tile hell = new Tile();
			hell.setPiece(pieces.get(i));
			hell.setXLocation(pieces.get(i).getXlocation());
			hell.setYLocation(pieces.get(i).getYlocation());
			loadedBoard.setTile(pieces.get(i).getXlocation(), pieces.get(i).getYlocation(), hell);
			System.out.println(" x loc is " + pieces.get(i).getXlocation()
					+ " y loc is " + pieces.get(i).getYlocation()
					+ " piece is  " + pieces.get(i)
					+ "tile is " + loadedBoard.getTile(pieces.get(i).getXlocation(), pieces.get(i).getYlocation())
					);	
		}
		
		//applying file paths
		for(int i = 0; i < daBoard.length; i++) {
			for(int j = 0; j < daBoard[i].length; j++) {
				String tileName = ("tile" + ((8*i)+j));
				req.setAttribute(tileName, daBoard[j][i]);
				System.out.println(tileName + " should be " + daBoard[i][j]);
			}
		}
		//the fix pieces thing is just bc the values need to be loaded for some reason???
		try {
			if(message != null || submit.equals("Fix Pieces")) {
				req.setAttribute("message", message);
				req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
			}
			if(submit.equals("Rulebook")) {
				req.getRequestDispatcher("/_view/rulebook.jsp").forward(req, resp);
			}
			if(submit.equals("Submit Move")) {
				if(initpos != null && finpos != null) {
					int[] iPos = tileToPos(initpos);
					int[] fPos = tileToPos(finpos);
					//trying to update the board
					//i don't know if this is possible without an externally defined pieces array
					//probably will just use the move function in future
					System.out.println("Moving from tile "+(iPos[1]+(8*iPos[0]))+" to tile "+(fPos[1]+(8*fPos[0])));
					if(iPos[0]!=-1 && iPos[1]!=-1 && fPos[0]!=-1 && fPos[1]!=-1) {
						ChessPiece toMove;
						try {
							ChessPiece daMover = loadedBoard.getTile(iPos[1], iPos[0]).getPiece();
							String moverColor = "Black";
							if(daMover.getColor()) {
								moverColor = "White";
							}
							System.out.println("piece type is " + moverColor + daMover.whatPiece());
							boolean canMove = controller.validatePieceMove(daMover, fPos[0], fPos[1], loadedBoard, gameIdNum);
							String moveString = "the piece cannot move =(";
							if(canMove) {
								moveString = "the piece can move =)";
							}
							System.out.println(moveString);
						}
						catch (Exception NullPointerException) {
							System.out.println("Something went wrong when moving the piece");
							//probably put something here later that loads the previous board state so pieces can't get duped
						}
					}
				}
			}
		}
		catch(Exception NullPointerException) {
			System.out.println("submit input is invalid");
		}
		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
	}
	//converts a tile string to a 2d int array representing coordinates
	//mostly used in the text movement method, not used anymore
	public int[] toPos(String pos) {
		if(pos.length()>2 || pos.length()<=0) {
			int[] ret = {-1, -1};
			return ret;
		}
		else {
			//pos.toLowerCase();
			String s1 = pos.substring(0, 1);
			String s2 = pos.substring(1, 2);
			int p1;
			int p2;
			switch (s1) {
				case "A":
					p1 = 0;
					break;
				case "B":
					p1 = 1;
					break;
				case "C":
					p1 = 2;
					break;
				case "D":
					p1 = 3;
					break;
				case "E":
					p1 = 4;
					break;
				case "F":
					p1 = 5;
					break;
				case "G":
					p1 = 6;
					break;
				case "H":
					p1 = 7;
					break;
				default:
					p1 = -1;
			}
			switch (s2) {
			case "1":
				p2 = 7;
				break;
			case "2":
				p2 = 6;
				break;
			case "3":
				p2 = 5;
				break;
			case "4":
				p2 = 4;
				break;
			case "5":
				p2 = 3;
				break;
			case "6":
				p2 = 2;
				break;
			case "7":
				p2 = 1;
				break;
			case "8":
				p2 = 0;
				break;
			default:
				p2 = -1;
		}
			int ret[] = {p2, p1};
			return ret;
		}
	}
	//converts a tile number to a tile coordinates
	public int[] tileToPos(String tile) {
		Integer daNumba = Integer.parseInt(tile);
		int[] ret = {(daNumba/8), (daNumba%8)};
		return ret;
	}
	public ChessPiece fakeChessPiece(int xPos, int yPos, int pNum, boolean color) {
		ChessPiece daPiece = new PawnPiece();
		if(pNum>=0 && pNum<=15) {
			daPiece = new PawnPiece();
		}
		if(pNum>=16 && pNum<=19) {
			daPiece = new KnightPiece();
		}
		if(pNum>=20 && pNum<=23) {
			daPiece = new BishopPiece();
		}
		if(pNum>=24 && pNum<=27) {
			daPiece = new RookPiece();
		}
		if(pNum>=28 && pNum<=29) {
			daPiece = new QueenPiece();
		}
		if(pNum>=30 && pNum<=31) {
			daPiece = new KingPiece();
		}
		daPiece.setXlocation(xPos);
		daPiece.setYlocation(yPos);
		daPiece.setColor(color);
		daPiece.setPieceNumber(pNum);
		return daPiece;
	}
}

