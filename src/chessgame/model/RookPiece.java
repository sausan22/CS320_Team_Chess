package chessgame.model;

public class RookPiece extends ChessPiece{
	public RookPiece(int x, int y, boolean c, int p)
	{
		this.setXlocation(x);
		this.setYlocation(y);
		this.setColor(c);
		this.setHasMoved(false);
		this.setPieceNumber(p);
	}
	
	@Override
	public boolean checkMove(int newx, int newy, ChessBoard cb) {
		// TODO Auto-generated method stub
		if(newx < 0 || newx > 7 || newx > 7 || newy < 0) {
			return false;
		}
		//move left and right
		int distY = Math.abs(newy - this.getYlocation());
		int distX = Math.abs(newx - this.getYlocation());
		/*
		 * 
		 * rook moving to the right and left 
		 * board turned sideways
		 * 
		 * 
		 * */
		
		if(distY > 0) {
			try {
				
				for(int y = 0; y < distY; y++) {
					//checks every space beetween the passed in y value
					// this checks if the current location that the player wants to move to is full
					if(cb.getTile(this.getXlocation(), newy-y).getPiece() != null &&  
							// and the player has not selected an improper way to move the piece 
							distX == 0 &&
							// checking if the current color of the piece is equal to the piece 
							cb.getTile(this.getXlocation(), newy-y).getPiece().getColor() == this.getColor()) {
						
							return false;
					}
					//checking if there is nothing in the way of the peice moving laterally along the Y
					// checking  if there are no pieces  in the way of the current movement
					else if(cb.getTile(this.getXlocation(), newy - y).getPiece() == null && 
							distX == 0) {
						//making sure this can execute because the loop runs up to the spot just before 
						//the current piece the - 1 is added to make sure the return statement can execute
						if(distY - 1 == y) {
							return true;
						}
						
					}
					//check if the piece is in the way but can be captured only for lateral movement along the Y
					//
					// checks if the tile is occupied by a piece
					else if(cb.getTile(this.getXlocation(), newy - y).getPiece() != null &&
							//checks player distance int the x direction is 0
							distX == 0 && 
							//the piece that the tile is occupied by is not the same color
							cb.getTile(this.getXlocation(), newy - y).getPiece().getColor() != this.getColor()) {
						//wait for the loop to go through and check all the spots on the array and then return true
						//making sure this can execute because the loop runs up to the spot just before 
						//the current piece the - 1 is added to make sure the return statement can execute
						if(distY - 1 == y) {
							return true;
						}
						
					}
				
				}
				
			}catch(NullPointerException n){
				
			}
			
		}
		else if(distX > 0) {
			try {
				for(int x = 0; x < distX; x++) {
					
					// checks the tile selected by the user then the tile below it and so on until it the loop finishes
					// checks the tile to make sure that the piece is not null
					if(cb.getTile(newx - x, this.getYlocation()).getPiece() != null && 
							// checks that the player is not giving the piece any horizontal instructions
							distY == 0 &&
							cb.getTile(newx - x, this.getYlocation()).getPiece().getColor() == this.getColor()) {
						return false;
						
					}
					// no pieces in the way 
					// checked the tiles to make sure everything in the path is clear
					else if(cb.getTile(newx - x, this.getYlocation()).getPiece() == null &&
							//the user did not put any distance into the y
							distY == 0) {
						// once loop is finished return true if all true
						//making sure this can execute because the loop runs up to the spot just before 
						//the current piece the - 1 is added to make sure the return statement can execute
						if(distX - 1 == x) {
							return true;
						}
					}
					// capture case on the x axis 
					//if the spot is not null and the 
					else if(cb.getTile(newx - x, this.getYlocation()).getPiece() != null && 
							distY == 0 &&
							cb.getTile(newx - x, this.getYlocation()).getPiece().getColor() != this.getColor()) {
						//making sure this can execute because the loop runs up to the spot just before 
						//the current piece the - 1 is added to make sure the return statement can execute
						if(distX - 1 == x) {
							return true;
						}
						
					}
					
				}
			}catch(NullPointerException n) {
				
			}
		}
		return false;
	}
}
