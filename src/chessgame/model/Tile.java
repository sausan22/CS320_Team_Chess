package chessgame.model;

public class Tile {
	private ChessPiece thisSpot;
	
	public Tile() {
		thisSpot = null;
	}
	
	public Tile(ChessPiece cp) {
		this.thisSpot = cp;
	}
	
	public void setPiece(ChessPiece cp){
		this.thisSpot = cp;
	}
	
	public ChessPiece getPiece() {
		if(thisSpot.getCaptured() == true) {
			return null;
		}
		return this.thisSpot;
	}
	
	public void removePiece() {
		this.thisSpot = null;
	}
}
