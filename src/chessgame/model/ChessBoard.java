package chessgame.model;

public class ChessBoard {
	private Tile[][] board;
	
	public ChessBoard() {
		board = new Tile[8][8];
	}
	//what does this do
	public void setTile(int x, int y) {
		board[y][x] = null;
	}
	
	public void setTile(int x, int y, ChessPiece p) {
		Tile t = new Tile();
		//this updates the passed in pieces location
		p.setxLocation(x);
		p.setylocation(y);
		t.setPiece(p);
		t.setXLocation(x);
		t.setYLocation(y);
		
		board[y][x] = t;
	}
	
	public Tile getTile(int x, int y)
	{
		//backwards since that's the way 2D arrays work
		return this.board[y][x];
	}

}
