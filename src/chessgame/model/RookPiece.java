package chessgame.model;

public class RookPiece extends ChessPiece {
	public RookPiece(/*int x, int y, boolean c, int p*/) {
		//		this.setXlocation(x);
		//		this.setYlocation(y);
		//		this.setColor(c);
		//		this.setHasMoved(false);
		//		this.setPieceNumber(p);
	}

	@Override
	public boolean checkMove(int newx, int newy, ChessBoard cb) {
		// TODO Auto-generated method stub

				// grabs the distance from the new spot
				int distanceX = Math.abs(this.getXlocation() - newx);
				int distanceY = Math.abs(this.getYlocation() - newy);

				// if the piece tile selected is full
				if (cb.getTile(newx, newy) != null || newx > 7 || newy > 7 || newx < 0 || newy < 0) {
					return false;
				}
				// checking moving only on the x axis
				
				else if (distanceY == 0 && distanceX > 0) {
					// int changeX;
					
					Tile forwardIterTile;
					Tile backwardIterTile;
					try{
					for (int x = 0; x < distanceX; x++) {
						// check moving forward through the x axis

						///////////////////////////////////////////////////////////////////////////////////////////
						// this is only for walking froward through the chessboard going in the negative direction/
						///////////////////////////////////////////////////////////////////////////////////////////
						
						// the iterTile takes the first selected location
						// and walks back through each tile up to where the
						// current piece is
						forwardIterTile = cb.getTile(newx + x, newy);

						// takes the location of the tile iterating backwards
						backwardIterTile = cb.getTile(newx - x, newy);
						if (newx < this.getXlocation()) {
							// if the pieces leading up to the player piece and if the piece exists and is
							// the same color then return false
							if (forwardIterTile.getPiece() != null
									&& forwardIterTile.getPiece().getColor() == this.getColor()) {
								return false;
							}

							// if the selected tile and all the tiles from
							// selected to the current piece are null

							else if (forwardIterTile.getPiece() == null) {

								// wait for the loop to fully iterate then return true

								if (distanceX == x) {
									return true;
								}
							}

							// this is a capture scenario if all tiles loading up to the current piece
							// allow the move when the player piece does not match the color of the selected
							// piece

							else if (forwardIterTile.getPiece() != null
									&& forwardIterTile.getPiece().getColor() != this.getColor()) {

								// wait for the loop to execute and check all
								// the spots up to the current piece then return true
								if (distanceX-- == x) {
									return true;
								}
							}
							//moving backwards along the x axis
						} else if (newx > this.getXlocation()) {
							
							if (backwardIterTile.getPiece() != null && backwardIterTile.getPiece().getColor() == this.getColor()) {
								return false;
							}
							
							// clear path for the piece to move returns true
							else if(backwardIterTile.getPiece() == null) {
								
								//wait for loop to go to tile just before the this piece 
								//and check that the path is clear
								if(distanceX-- == x) {
									return true;
								}
							}
							
							//capture scenario
							else if(backwardIterTile.getPiece() != null && backwardIterTile.getPiece().getColor() != this.getColor()) {
								
								//wait for loop to go to tile just before the this piece
								if(distanceX-- == x) {
									return true;
								}
							}
						}
					}}catch(NullPointerException e ){}
				}
				else if(distanceX == 0 && distanceY > 0) {
					Tile upwardIterTile;
					Tile downwardIterTile;
					try{
					for(int y = 0; y <= distanceY; y++) {
						upwardIterTile = cb.getTile(newx, newy - y);
						downwardIterTile = cb.getTile(newx, newy + y);
						
						
						if(newy < this.getYlocation()) {
							
							if(upwardIterTile.getPiece() != null
									&& upwardIterTile.getPiece().getColor() == this.getColor()) {
								return false;
								
							}
							else if(upwardIterTile.getPiece() == null) {
								
								if(distanceY == y) {
									return true;
								}
							}
							//capture
							else if(upwardIterTile.getPiece() != null && upwardIterTile.getPiece().getColor() != this.getColor()) {
								
								if(distanceY == y) {
									return true;
								}
							}
							
						}
						//not free and its your own piece
						else if(newy > this.getYlocation()) {
							if(downwardIterTile.getPiece() != null
									&& downwardIterTile.getPiece().getColor() == this.getColor()) {
								return false;
								
							}
							//free space
							else if(downwardIterTile.getPiece() == null) {
								
								if(distanceY == y) {
									return true;
								}
							}
							//capture
							else if(downwardIterTile.getPiece() != null && downwardIterTile.getPiece().getColor() != this.getColor()) {
								
								if(distanceY == y) {
									return true;
								}
							}
						}
					}}catch(NullPointerException e){}
					
				}
				return false;
	}

	@Override
	public String whatPiece() {
		// TODO Auto-generated method stub
		return "Rook";
	}
}