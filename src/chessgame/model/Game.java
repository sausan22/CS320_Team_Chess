package chessgame.model;

public class Game{
	private ChessBoard chessBoard, previousStates[];
	private Player player1, player2;
	private boolean isSavedGame;
	
	public Game() {
		chessBoard = new ChessBoard();
		//previousStates = new ChessBoard();
		isSavedGame = false;
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
	
	
	
}
