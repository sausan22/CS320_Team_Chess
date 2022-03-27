package chessgame.model;

public class RookPiece extends ChessPiece{

	@Override
	public String whatPiece() {
		// TODO Auto-generated method stub
		return "R";
	}

	@Override
	public String whatInitial() {
		// TODO Auto-generated method stub
		return "Rook";
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
						if(distY == y) {
							
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
						if(distY == y) {
							return true;
						}
					}
				
				}
				
			}catch(NullPointerException n){
				
			}
			
		}
//		else if(distX > 0) {
//			try {
//				for(int x = 0; x < distX; x++) {
//					if() {
//						
//					}
//					
//				}
//			}catch(NullPointerException n) {
//				
//			}
//		}
		return false;
	}


}
