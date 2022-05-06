package chessgame.model;

public class KnightPiece extends ChessPiece {
	public KnightPiece(/*int x, int y, boolean c, int p*/)
	{
//		this.setXlocation(x);
//		this.setYlocation(y);
//		this.setColor(c);
//		this.setHasMoved(false);
//		this.setPieceNumber(p);
	}
	
	public boolean checkMove(int newx, int newy, ChessBoard cb) {
		
		//checking out of bounds
		if(newx < 0 || newy < 0 || newx > 7 || newy > 7) {
			return false;
		}
		
		else if((newx == this.getXlocation() + 2 && newy == this.getYlocation() + 1) || //up 2 left 1
				((newx == this.getXlocation() + 2 && newy == this.getYlocation() - 1) ||  // up 2 right 1
				(newx == this.getXlocation() - 2 && newy == this.getYlocation() + 1) ||  // down 2 left 1
				(newx == this.getXlocation() - 2 && newy == this.getYlocation() - 1) ||  // down 2 right 1
				(newx == this.getXlocation() + 1 && newy == this.getYlocation() + 2) ||  // left 2 up 1
				(newx == this.getXlocation() - 1 && newy == this.getYlocation() + 2) ||  // left 2 down 1
				(newx == this.getXlocation() + 1 && newy == this.getYlocation() - 2) ||  // right 2 up 1
				(newx == this.getXlocation() - 1 && newy == this.getYlocation() - 2)) && // right 2 up 1
				(cb.getTile(newx, newy).getPiece() == null || 
				(cb.getTile(newx, newy).getPiece() != null || cb.getTile(newx, newy).getPiece().getColor() != this.getColor()))) {
			return true;
			
		}
		return false;
	}
	
	@Override
	public String whatPiece() {
		return "Knight";
	}


}
