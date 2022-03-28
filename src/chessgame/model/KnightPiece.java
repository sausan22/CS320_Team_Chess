package chessgame.model;

public class KnightPiece extends ChessPiece {

	@Override
	public String whatPiece() {
		// TODO Auto-generated method stub
		return "Knight";
	}

	@Override
	public String whatInitial() {
		// TODO Auto-generated method stub
		return "K";
	}

	@Override
	public boolean checkMove(int newx, int newy, ChessBoard cb) {
		// TODO Auto-generated method stub
		
		//checking out of bounds
		if(newx < 0 || newy < 0 || newx > 7 || newy > 7) {
			return false;
		}
		
		if((newx == this.getXlocation() + 2 && newy == this.getYlocation() + 1) || //up 2 left 1
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

}
