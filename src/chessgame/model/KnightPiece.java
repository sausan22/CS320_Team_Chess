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
		int distX = Math.abs(this.getxLocation() - newx);
		int distY = Math.abs(this.getylocation() - newy);
		//checking out of bounds
		if(newx < 0 || newy < 0 || newx > 7 || newy > 7) {
			return false;
		}
		//checking forward and backwards movement
		else if(distX == 2 && distY == 1) {
			//if the tile is not null check whats on that tile
			try {
				if(cb.getTile(newx, newy).getPiece() != null){
					//same color cannot move to that location
					if(cb.getTile(newx, newy).getPiece().getColor() == this.getColor()) {
						return false;
					}
					else {
					return true;
					}
				}
			}catch(NullPointerException e){
				System.out.println("no piece at location" + newx + ", " + newy);
			}
			//if the tile has nothing on it assert true
			return true;
		}
		//checking left to right movement
		else if(distX == 1 && distY == 2) {
			//if the tile is not null check whats on that tile
			try {
				if(cb.getTile(newx, newy).getPiece() != null){
					//same color cannot move to that location
					if(cb.getTile(newx, newy).getPiece().getColor() == this.getColor()) {
						return false;
					}
					else {
						return true;
					}
				}
			}catch(NullPointerException e) {
				
			}
			//if the tile has nothing on it assert true
			return true;
		}
		return false;
	}
	
	@Override
	public String whatPiece() {
		return "Knight";
	}


}
