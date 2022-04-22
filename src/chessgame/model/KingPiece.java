package chessgame.model;

public class KingPiece extends ChessPiece{
	public KingPiece(int x, int y, boolean c, int p)
	{
		this.setXlocation(x);
		this.setYlocation(y);
		this.setColor(c);
		this.setHasMoved(false);
		this.setPieceNumber(p);
	}
	
	public String whatPiece() {
		return "King";
	}
	
	public void setHasMoved(boolean moved) {
		//
	}
	
	public boolean checkMove(int newx, int newy, ChessBoard cb) {
		
		if(newx < 0 || newx > 7 || newy < 0 || newy > 7) {
			return false;
		}
		
		int changeX =  Math.abs(newx - this.getXlocation());
		int changeY =  Math.abs(newy - this.getYlocation());

		try {
			//friendly their cant move there
			if(cb.getTile(newx, newy).getPiece().getColor() == this.getColor()) {
				return false;
			}
		}catch(NullPointerException e) {
			
		}
		
		//make sure they only movement horizontally or vertically one tile
		if((changeX * changeY == 1 || changeX + changeY == 1) && changeX + changeY != 0) {
			return true;
		}
		else {
			return false;
		}
		
	}
}
