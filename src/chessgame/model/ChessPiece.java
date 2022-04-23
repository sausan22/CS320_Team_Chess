package chessgame.model;

public abstract class ChessPiece {
	private int xlocation;
	private int ylocation;
	private boolean color;
	private int gameID;
	private int pieceID;
	private int pieceNumber;
	private boolean captured;
	public boolean hasMoved;
	
	public ChessPiece(){
		
	}
	public void setPieceId(int pieceID ) {
		this.pieceID = pieceID;
	}
	
	public int getPieceID() {
		return pieceID;
	}
	
	public void setPieceNumber(int piece) {
		pieceNumber = piece;
	}
	
	public int getPieceNumber() {
		return this.pieceNumber;
	}
	
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	
	public int getGameID() {
		return gameID;
	}
	
	//location x gets and sets
	
	public void setxLocation(int xlocation) {
		this.xlocation = xlocation;
	}
	
	public int getxLocation() {
		return xlocation;
	}
	
	public void setXlocation(int x) {
		xlocation = x;
	}
	
	public int getXlocation() {
		return this.xlocation;
	}
	
	//location y gets and sets
	
	public void setylocation(int ylocation) {
		this.ylocation = ylocation;
	}
	
	public int getylocation() {
		return ylocation;
	}
	
	
	public void setYlocation(int y) {
		ylocation = y;
	}
	
	public int getYlocation() {
		return this.ylocation;
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
	
	public abstract String whatPiece();
	
	
	public abstract boolean checkMove(int newx, int newy, ChessBoard cb);
}
