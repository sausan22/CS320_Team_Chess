package chessgame.model;


public class Game{
	private ChessBoard chessBoard;
	private ChessBoard[] previousStates;
	private Player player1, player2;
	private boolean isSavedGame;
	private int numTurns; // added this to have a global update for number of turns to effect the previous states class
	private int gameID;
	
	public Game() {
		chessBoard = new ChessBoard();
		isSavedGame = false;
		numTurns = 0;
		previousStates = new ChessBoard[numTurns];
	}
	
	public void setChessBoard(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}
	
	public ChessBoard getChessBoard() {
		return chessBoard;
	}
	
	public void setPlayer1(Player player) {
		player1 = player;
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public void setPlayer(Player player) {
		this.player2 = player;
	}
	
	public Player getPlayer2() {
		return player2;
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

