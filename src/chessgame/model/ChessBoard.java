package chessgame.model;

public class ChessBoard {
	private Tile[][] board;
	
	public ChessBoard() {
		board = new Tile[8][8];
	}
	
	
	// bad loop
	public void createBoard() {
		for(int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				if(i%2 == 0) {
					if(j%2 == 0) {
						board[i][j].setBlack();
					}
					else {
						board[i][j].setWhite();
					}
				}
				else {
					if(j%2 == 1) {
						board[i][j].setBlack();
					}
					else {
						board[i][j].setWhite();
					}
				}
				board[i][j].setXLocation(j);
				board[i][j].setYLocation(i);
			}
		}
	}
	
	public Tile getTile(int x, int y)
	{
		//backwards since that's the way 2D arrays work
		return this.board[y][x];
	}
	
}
