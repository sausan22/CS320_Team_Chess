package chessgame.model;

public class Tile {
	private boolean color;//true = white//false = black
	private int xLocation;
	private int yLocation;
	private ChessPiece piece;//
	
	public Tile() {
	}
	
	public void setWhite() {
		color = true;
	}
	public void setBlack() {
		color = false;
	}
	public boolean getColor() {
		return color;
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
	public boolean isOccupied() {
		if(piece.getXlocation() == xLocation && piece.getYlocation() == yLocation) {
			return true;
		}
		return false;
	}
}
