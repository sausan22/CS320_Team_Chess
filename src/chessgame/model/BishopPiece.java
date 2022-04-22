package chessgame.model;

public class BishopPiece extends ChessPiece {
	public BishopPiece(int x, int y, boolean c, int p) {
		this.setXlocation(x);
		this.setYlocation(y);
		this.setColor(c);
		this.setHasMoved(false);
		this.setPieceNumber(p);
	}
	
	public String whatPiece() {
		return "Bishop";
	}
	
public boolean checkMove(int newx, int newy, ChessBoard cb) {
		
		if(newx < 0 || newx > 7 || newy < 0 || newy > 7) {
			return false;
		}
		
		int oldx = this.getXlocation();
		int oldy = this.getYlocation();
		int changeX = newx - oldx;
		int changeY = newy - oldy;
		
		if(changeX > 0 && changeY > 0 && Math.abs(changeX) == Math.abs(changeY)) {
			for(int i = oldx+=1; i < newx; i++) {
				int j = oldy + i - oldx + 1;
				Tile t = cb.getTile(i, j);
				
				try {
					if(t.getPiece() != null) {
						return false;
					}
				}
				catch(NullPointerException e) {
					//System.out.println("No Chess piece");
				}
			}
		}
		
		else if (changeX < 0 && changeY < 0 && Math.abs(changeX) == Math.abs(changeY)) {
			for(int i = newx+=1; i < oldx; i++ ) {
				int j = oldy + i - oldx;
				Tile t = cb.getTile(i,j);
				try {
					if(t.getPiece() != null) {
						return false;
					}
				}
				catch(NullPointerException e) {
					
				}
			}
			newx--;
		}
		
		else if(changeX > 0 && changeY < 0 && Math.abs(changeX) == Math.abs(changeY)) {
			int testX = oldx+1;
			int testY = oldy-1;
			
			while(testX < newx || testY > newy) {
				Tile t = cb.getTile(testX, testY);
				
				try {
					if(t.getPiece() != null) {
						return false;
					}
				}
				catch(NullPointerException e){
					
				}
				
				testX++;
				testY--;
			}
		}
		
		else if(changeX < 0 && changeY > 0 && Math.abs(changeX) == Math.abs(changeY)) {
			int testX = oldx-1;
			int testY = oldy+1;
			
			while(testX > newx || testY < newy) {
				Tile t = cb.getTile(testX, testY);
				
				try {
					if(t.getPiece() != null) {
						return false;
					}
				}
				catch(NullPointerException e){
					
				}
				
				testX--;
				testY++;
			}
		}
		try {
			if(cb.getTile(newx, newy).getPiece().getColor() == this.getColor()) {
				return false;
			}
		}
		catch(NullPointerException e) {
			
		}
		
		changeX = Math.abs(changeX);
		changeY = Math.abs(changeY);

		
		if(changeX == changeY && changeX + changeY != 0) {
			return true;
		}
		
		else {
			return false;
		}
	}
}
