package chessgame.model;

public class Player {
	private boolean color;
	

	public Player(boolean c) {
		this.color = c;
	}
	
	public boolean getColor() {
		return this.color;
	}
	
	public boolean isCheck(ChessBoard cb, KingPiece kingPiece) {
		return false;
	}
}
