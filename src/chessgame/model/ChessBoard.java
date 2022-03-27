package chessgame.model;

public class ChessBoard {
	private Tile[][] board;
	
	public ChessBoard() {
		board = new Tile[8][8];
	}
	
	
	public Tile getTile(int x, int y)
	{
		//backwards since that's the way 2D arrays work
		return this.board[y][x];
	}
	
}
