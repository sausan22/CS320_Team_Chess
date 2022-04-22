package chessgame.model;


public class Game{
	private ChessBoard chessBoard;
	private ChessBoard[] previousStates;
	private Player WPlayer, BPlayer;
	private boolean isSavedGame;
	private int numTurns; // added this to have a global update for number of turns to effect the previous states class
	private int gameID;
	
	public Game() {
		chessBoard = new ChessBoard();
		isSavedGame = false;
		numTurns = 0;
		previousStates = new ChessBoard[numTurns];
	}
	
	private PawnPiece WPawn1;
	private PawnPiece WPawn2;
	private PawnPiece WPawn3;
	private PawnPiece WPawn4;
	private PawnPiece WPawn5;
	private PawnPiece WPawn6;
	private PawnPiece WPawn7;
	private PawnPiece WPawn8;
	private KingPiece WKing;
	private QueenPiece WQueen;
	private BishopPiece WBishop1;
	private BishopPiece WBishop2;
	private KnightPiece WKnight1;
	private KnightPiece WKnight2;
	private RookPiece WRook1;
	private RookPiece WRook2;
	
	private PawnPiece BPawn1;
	private PawnPiece BPawn2;
	private PawnPiece BPawn3;
	private PawnPiece BPawn4;
	private PawnPiece BPawn5;
	private PawnPiece BPawn6;
	private PawnPiece BPawn7;
	private PawnPiece BPawn8;
	private KingPiece BKing;
	private QueenPiece BQueen;
	private BishopPiece BBishop1;
	private BishopPiece BBishop2;
	private KnightPiece BKnight1;
	private KnightPiece BKnight2;
	private RookPiece BRook1;
	private RookPiece BRook2;
	
	
	public void setGame() {
		chessBoard = new ChessBoard();
		//WPlayer = new Player(true);
		//BPlayer = new Player(false); remember to uncomment these before pushing
		
		//starting positions
		//black pieces
		BRook1 = new RookPiece(0, 0, false, 1);
		Tile BRookTile1 = new Tile(BRook1);
		chessBoard.setTile(0,0, BRookTile1);
		
		BKnight1 = new KnightPiece(0, 1, false, 2);
		Tile BKnightTile1 = new Tile(BKnight1);
		chessBoard.setTile(0,1, BKnightTile1);
		
		BBishop1 = new BishopPiece(0, 2, false, 3);
		Tile BBishopTile1 = new Tile(BBishop1);
		chessBoard.setTile(0,2, BBishopTile1);
		
		BQueen = new QueenPiece(0, 3, false, 4);
		Tile BQueenTile = new Tile(BQueen);
		chessBoard.setTile(0,3, BQueenTile);
		
		BKing = new KingPiece(0, 4, false, 5);
		Tile BKingTile = new Tile(BKing);
		chessBoard.setTile(0,4, BKingTile);
		
		BBishop2 = new BishopPiece(0, 5, false, 6);
		Tile BBishopTile2 = new Tile(BBishop2);
		chessBoard.setTile(0,5, BBishopTile2);
		
		BKnight2 = new KnightPiece(0, 6, false, 7);
		Tile BKnightTile2 = new Tile(BKnight2);
		chessBoard.setTile(0,6, BKnightTile2);
		
		BRook2 = new RookPiece(0, 7, false, 8);
		Tile BRookTile2 = new Tile(BRook2);
		chessBoard.setTile(0,7, BRookTile2);
		
		BPawn1 = new PawnPiece(1, 0, false, 9);
		Tile BPawnTile1 = new Tile(BPawn1);
		chessBoard.setTile(1,0, BPawnTile1);
		
		BPawn2 = new PawnPiece(1, 1, false, 10);
		Tile BPawnTile2 = new Tile(BPawn2);
		chessBoard.setTile(1,1, BPawnTile2);
		
		BPawn3 = new PawnPiece(1, 2, false, 11);
		Tile BPawnTile3 = new Tile(BPawn3);
		chessBoard.setTile(1, 2, BPawnTile3);
		
		BPawn4 = new PawnPiece(1, 3, false, 12);
		Tile BPawnTile4 = new Tile(BPawn4);
		chessBoard.setTile(1, 3, BPawnTile4);
		
		BPawn5 = new PawnPiece(1, 4, false, 13);
		Tile BPawnTile5 = new Tile(BPawn5);
		chessBoard.setTile(1, 4, BPawnTile5);
		
		BPawn6 = new PawnPiece(1, 5, false, 14);
		Tile BPawnTile6 = new Tile(BPawn6);
		chessBoard.setTile(1, 5, BPawnTile6);
		
		BPawn7 = new PawnPiece(1, 6, false, 15);
		Tile BPawnTile7 = new Tile(BPawn7);
		chessBoard.setTile(1, 6, BPawnTile7);
		
		BPawn8 = new PawnPiece(1, 7, false, 16);
		Tile BPawnTile8 = new Tile(BPawn8);
		chessBoard.setTile(1, 7, BPawnTile8);
		
		//white pieces
		WRook1 = new RookPiece(7, 0, true, 1);
		Tile WRookTile1 = new Tile(WRook1);
		chessBoard.setTile(7,0, WRookTile1);
		
		WKnight1 = new KnightPiece(7, 1, true, 2);
		Tile WKnightTile1 = new Tile(WKnight1);
		chessBoard.setTile(7,1, WKnightTile1);
		
		WBishop1 = new BishopPiece(7, 2, true, 3);
		Tile WBishopTile1 = new Tile(WBishop1);
		chessBoard.setTile(7,2, WBishopTile1);
		
		WQueen = new QueenPiece(7, 3, true, 4);
		Tile WQueenTile = new Tile(WQueen);
		chessBoard.setTile(7,3, WQueenTile);
		
		WKing = new KingPiece(7, 4, true, 5);
		Tile WKingTile = new Tile(WKing);
		chessBoard.setTile(7,4, WKingTile);
		
		WBishop2 = new BishopPiece(7, 5, true, 6);
		Tile WBishopTile2 = new Tile(WBishop2);
		chessBoard.setTile(7,5, WBishopTile2);
		
		WKnight2 = new KnightPiece(7, 6, true, 7);
		Tile WKnightTile2 = new Tile(WKnight2);
		chessBoard.setTile(7,6, WKnightTile2);
		
		WRook2 = new RookPiece(7, 7, true, 8);
		Tile WRookTile2 = new Tile(WRook2);
		chessBoard.setTile(7,7, WRookTile2);
		
		WPawn1 = new PawnPiece(6, 0, true, 9);
		Tile WPawnTile1 = new Tile(WPawn1);
		chessBoard.setTile(6,0, WPawnTile1);
		
		WPawn2 = new PawnPiece(6, 1, true, 10);
		Tile WPawnTile2 = new Tile(WPawn2);
		chessBoard.setTile(6,1, WPawnTile2);
		
		WPawn3 = new PawnPiece(6, 2, true, 11);
		Tile WPawnTile3 = new Tile(WPawn3);
		chessBoard.setTile(6, 2, WPawnTile3);
		
		WPawn4 = new PawnPiece(6, 3, true, 12);
		Tile WPawnTile4 = new Tile(WPawn4);
		chessBoard.setTile(6, 3, WPawnTile4);
		
		WPawn5 = new PawnPiece(6, 4, true, 13);
		Tile WPawnTile5 = new Tile(WPawn5);
		chessBoard.setTile(6, 4, WPawnTile5);
		
		WPawn6 = new PawnPiece(6, 5, true, 14);
		Tile WPawnTile6 = new Tile(WPawn6);
		chessBoard.setTile(6, 5, WPawnTile6);
		
		WPawn7 = new PawnPiece(6, 6, true, 15);
		Tile WPawnTile7 = new Tile(WPawn7);
		chessBoard.setTile(1, 6, WPawnTile7);
		
		WPawn8 = new PawnPiece(6, 7, true, 16);
		Tile WPawnTile8 = new Tile(WPawn8);
		chessBoard.setTile(6, 7, WPawnTile8);
	}
	
	
	public void setChessBoard(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}
	
	public ChessBoard getChessBoard() {
		return chessBoard;
	}
	
	public void setWPlayer(Player wp) {
		WPlayer = wp;
	}
	
	public Player getWPlayer() {
		return this.WPlayer;
	}
	
	public void setBPlayer(Player bp) {
		this.BPlayer = bp;
	}
	
	public Player getBPlayer() {
		return this.BPlayer;
	}
	
	public void setIsSavedGame(boolean bool) {
		isSavedGame = bool;
	}
	
	public boolean getIsSavedGame() {
		return isSavedGame;
	}
	
	public int getNumTurns() {
		return numTurns;
	}
	
	public void setNumTurns(int turns) {
		numTurns = turns;
	}
	public void saveCurrrentStates() {
		previousStates[numTurns] = chessBoard;
	}
	public void updatePreviousStates(ChessBoard gameBoardTurn) {
		numTurns++;
		previousStates[numTurns] = gameBoardTurn;
	}
	
	public ChessBoard viewPreviousTurns(int i) {
		
		return previousStates[i];
	}
	
	public ChessBoard viewPreviousTurns(boolean beginOrEnd) {
		//true is the begining of the array so index zero
		if(beginOrEnd == true) {
			return previousStates[0];
		}
		// false will send the user back to the current turn
		else {
			return previousStates[numTurns];
		}
	}
	
	public void createGameBoard() {
		this.chessBoard.createChessBoard();
		//TODO: add pieces to the chessboard;
	}
	
	
	
}

