package chessgame.model;

public class ChessBoard {
	private Tile[][] board;
	
	public ChessBoard() {
		board = new Tile[8][8];
	}
	
	public void setTile(int x, int y) {
		board[y][x] = null;
	}
	
	public void setTile(int x, int y, Tile t) {
		board[y][x] = t;
	}
	
	public Tile getTile(int x, int y)
	{
		//backwards since that's the way 2D arrays work
		return this.board[y][x];
	}
	
	public void setTile(Tile tile, int x, int y) { 
		this.board[y][x] = tile;
	}
	public void createChessBoard() {
		for(int y = 0; y < 7; y++) {
			for(int x = 0; x < 7; x++) {
				if(y%2 == 0) {
					if(x%2 == 0) {
						board[y][x].setColor(false);
					}
					else {
						board[y][x].setColor(true);
					}
				}
				else {
					if(x%2 == 1) {
						board[y][x].setColor(true);
					}
					else {
						board[y][x].setColor(false);
					}
					
				}
				board[y][x].setXLocation(x);
				board[y][x].setYLocation(y);
			}
		}
	}
	
}
