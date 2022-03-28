package chessgame.model;

public class Player {
	private boolean color;
	private ChessPiece[] piece;
	
	public Player(boolean col) {
		this.color = col;
	}
	
	public ChessPiece getPiece(int pieceNumber) {
		return piece[pieceNumber];
	}
	
	public void setPiece(int pieceNumber, ChessPiece piece) {
		this.piece[pieceNumber] = piece;
	}
	
	public boolean getColor() {
		return color;
	}
	public void setColor(boolean color) {
		this.color = color;
	}
	
}