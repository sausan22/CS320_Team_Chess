package chessgame.model;

public class ChessPiece {
	private int xlocation;
	private int ylocation;
	private boolean color;
	private int pieceNumber;
	private boolean captured;
	private boolean hasMoved;
	
	public void setXlocation(int x) {
		xlocation = x;
	}
	
	public int getXlocation() {
		return this.xlocation;
	}
	
	public void setYlocation(int y) {
		ylocation = y;
	}
	
	public int getYlocation() {
		return this.ylocation;
	}
	
	public void setPieceNumber(int piece) {
		pieceNumber = piece;
	}
	
	public int getPieceNumber() {
		return this.pieceNumber;
	}
	
	public void setColor(boolean c) {
		color = c;
	}
	
	public boolean getColor() {
		return this.color;
	}
	
	public void isCaptured() {
		captured = true;
	}
	
	public boolean getCaptured() {
		return this.captured;
	}
	
	public void setHasMoved(boolean hm) {
		hasMoved = hm;
	}
	
	public boolean getHasMoved() {
		return this.hasMoved;
	}
	
}
