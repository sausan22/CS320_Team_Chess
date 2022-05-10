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
		int distX = Math.abs(this.getxLocation()-newx);
		int distY = Math.abs(this.getylocation()-newy);
		if(newx > 7 || newx < 0 || newy > 7 || newy< 0) {
			return false;
		}
		//along the x axis
		else if(distX > 0  && distY == 0) {
			for(int i = 1; i < distX; i++) {
				//forward
				if(newx > this.getxLocation()) {
					try {
						//no one on that tile
						if(cb.getTile(newx - (i-1), newy).getPiece() == null) {
							if(i + 1 == distX) {
								return true;
							}
						}
												
					}catch(NullPointerException e){
						
					}
					if(cb.getTile(newx - (i-1), newy).getPiece() != null){
						if(cb.getTile(newx - (i-1), newy).getPiece().getColor() == this.getColor()) {
							return false;
						}
					}
					else if(cb.getTile(newx - (i-1), newy).getPiece().getColor() != this.getColor()) {
						if(i + 1 == distX) {
							
						}
					}

				}
				else if(true) {

				}
				//backwards
				if(newx < this.getxLocation()) {
					try {
						//no one on that tile
						if(cb.getTile(this.getxLocation() - i, newy).getPiece() == null) {
							//making sure the loop fully executes before passing true;
							if(i + 1 == distX) {
								return true;
							}
							
						}
					}catch(NullPointerException e){
						
					}
					//if the next tile is not null and the 
					if(cb.getTile(this.getxLocation() - i, newy).getPiece() != null) {
						if(cb.getTile(this.getxLocation() - i, newy).getPiece().getColor() == this.getColor()) {
							return false;
						}
						else if(cb.getTile(this.getxLocation() - i, newy).getPiece().getColor() != this.getColor()) {
							if(i + 1 == distX) {
								return true;
							}
						}
					}
					
				}

			}
		}
		//along the y axis
		else if(distX == 0 && distY > 0) {
			for(int j = 1; j < distY; j++) {
				//forward
				if(newy > this.getylocation()) {
					try {
						//no one on that tile
						if(cb.getTile(newx, newy - (j-1)).getPiece() == null) {
							if(j + 1 == distY) {
								return true;
							}
						}
												
					}catch(NullPointerException e){
						
					}
					if(cb.getTile(newx, newy- (j - 1)).getPiece() != null){
						if(cb.getTile(newx, newy- (j-1)).getPiece().getColor() == this.getColor()) {
							return false;
						}
					}
					else if(cb.getTile(newx, newy - (j-1)).getPiece().getColor() != this.getColor()) {
						if(j + 1 == distY) {
							
						}
					}

				}
				else if(true) {

				}
				//backwards
				if(newy < this.getylocation()) {
					try {
						//no one on that tile
						if(cb.getTile(this.getylocation(), newy - j).getPiece() == null) {
							//making sure the loop fully executes before passing true;
							if(j + 1 == distY) {
								return true;
							}
							
						}
					}catch(NullPointerException e){
						
					}
					//if the next tile is not null and the 
					if(cb.getTile(this.getylocation(), newy - j).getPiece() != null) {
						if(cb.getTile(this.getylocation(), newy - j).getPiece().getColor() == this.getColor()) {
							return false;
						}
						else if(cb.getTile(this.getylocation(), newy - j).getPiece().getColor() != this.getColor()) {
							if(j + 1 == distY) {
								return true;
							}
						}
					}
					
				}

			}
		}
		//remove this
		return false;
	}

	@Override
	public String whatPiece() {
		// TODO Auto-generated method stub
		return "Rook";
	}
}