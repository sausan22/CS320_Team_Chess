package chessgame.model;

public class Tile {
	private int xLocation;
	private int yLocation;
	private ChessPiece piece;
	private boolean color; // white == true // black == false
	
	public Tile() {
		piece = null;
	}
	
	public Tile(ChessPiece cp) {
		this.piece = cp;
	}
	
	public void setPiece(ChessPiece cp) {
		this.piece = cp;
	}
	
	public ChessPiece getPiece() {
		if(piece.getCaptured() == true) {
			return null;
		}
		return this.piece;
	}
	
	public void setXLocation(int xLoc) {
		xLocation = xLoc;
	}
	public int getXlocation() {
		return xLocation;
	}
	public void setYLocation(int yLoc) {
		yLocation = yLoc;
	}
	public int getylocation() {
		return yLocation;
	}
	
	public boolean getColor() {
		return color;
	}
	
	public void setColor(boolean color) {
		this.color = color;
	}
	
	public boolean isOccupied() {
		if(piece.getXlocation() == xLocation && piece.getYlocation() == yLocation) {
			return true;
		}
		return false;
	}
}
