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
		if(newx < 0 || newx > 7 || newy < 0 || newy > 7)
		{
			return false;
		}
		
		int oldx = this.getXlocation();
		int oldy = this.getYlocation();
		int changeX = Math.abs(newx - oldx);
		int changeY = Math.abs(newy - oldy);
		
		//check to see if other pieces are in the way
		//if horizontal movement
		if(changeY == 0 && changeX != 0)
		{
			//if moving up in x
			if(newx > oldx)
			{
				//cycles through all the possible spots it could go to 
				for(int i = oldx+=1; i < newx; i++)
				{
					System.out.println("Rook Piece at " + i + " and " + getYlocation());
					Tile t = cb.getTile(i, newy);
					
					try
					{
						if(t.getPiece() != null)
						{
							System.out.println("Chess Piece at " + i + " and " + getYlocation());
							return false;
						}
					}
					
					catch(NullPointerException e)
					{
						System.out.println("No Chess Piece at " + i + " and " + getXlocation());
					}
				}
			}
			
			else if(oldx > newx)
			{
				//cycles through all the possible spots it could go to 
				for(int i = newx+=1; i < oldx; i++)
				{
					System.out.println("Rook Piece at " + i + " and " + getYlocation());
					Tile t = cb.getTile(i, newy);
					try
					{
						if(t.getPiece() != null)
						{
							System.out.println("Chess Piece at " + i + " and " + getYlocation());
							return false;
						}
					}
					
					catch(NullPointerException e)
					{
						System.out.println("No Chess Piece at " + i + " and " + getYlocation());
					}
				}
			}
		}
		
		if(changeX == 0 && changeY != 0)
		{
			//if moving up in x
			if(newy > oldy)
			{
				//cycles through all the possible spots it could go to 
				for(int i = oldy+=1; i < newy; i++)
				{
					//System.out.println("Rook Piece at " + getPosX() + " and " + i);
					Tile t = cb.getTile(getXlocation(), i);
					try
					{
						if(t.getPiece() != null)
						{
							//System.out.println("Chess Piece at " + getPosX() + " and " + i);
							return false;
						}
					}
					
					catch(NullPointerException e)
					{
						System.out.println("No Chess Piece at " + getXlocation() + " and " + i);
					}
				}
			}
			
			else if(oldy > newy)
			{
				//cycles through all the possible spots it could go to 
				for(int i = newy+=1; i < oldy; i++)
				{
					//System.out.println("Rook Piece at " + getPosX() + " and " + i);
					Tile t = cb.getTile(getXlocation(), i);
					try
					{
						if(t.getPiece() != null)
						{
							System.out.println("Chess Piece at " + getXlocation() + " and " + i);
							return false;
						}
					}
					
					catch(NullPointerException e)
					{
						System.out.println("No Chess Piece at " + getXlocation() + " and " + i);
					}
				}
			}
		}
		
		
		//check to see if they take a piece
		try
		{
			if(cb.getTile(newx, newy).getPiece().getColor() == this.getColor())
			{
				return false;
			}
		}
		catch(NullPointerException n)
		{
			System.out.println("No one at " + newx + ", " + newy);
		}
		
		//makes sure that the movements are vertical or horizontal, and actually moving not just up and down
		if(changeX * changeY == 0 && changeX + changeY != 0)
		{
			return true;
		}
		
		else
		{
			return false;
		}
//		int distX = Math.abs(this.getxLocation()-newx);
//		int distY = Math.abs(this.getylocation()-newy);
//		if(newx > 7 || newx < 0 || newy > 7 || newy< 0) {
//			return false;
//		}
//		//along the x axis
//		else if(distX > 0  && distY == 0) {
//			for(int i = 1; i < distX; i++) {
//				//forward
//				if(newx > this.getxLocation()) {
//					try {
//						//no one on that tile
//						if(cb.getTile(newx - (i-1), newy).getPiece() == null) {
//							if(i + 1 == distX) {
//								return true;
//							}
//						}
//												
//					}catch(NullPointerException e){
//						
//					}
//					if(cb.getTile(newx - (i-1), newy).getPiece() != null){
//						if(cb.getTile(newx - (i-1), newy).getPiece().getColor() == this.getColor()) {
//							return false;
//						}
//					}
//					else if(cb.getTile(newx - (i-1), newy).getPiece().getColor() != this.getColor()) {
//						if(i + 1 == distX) {
//							
//						}
//					}
//
//				}
//				else if(true) {
//
//				}
//				//backwards
//				if(newx < this.getxLocation()) {
//					try {
//						//no one on that tile
//						if(cb.getTile(this.getxLocation() - i, newy).getPiece() == null) {
//							//making sure the loop fully executes before passing true;
//							if(i + 1 == distX) {
//								return true;
//							}
//							
//						}
//					}catch(NullPointerException e){
//						
//					}
//					//if the next tile is not null and the 
//					if(cb.getTile(this.getxLocation() - i, newy).getPiece() != null) {
//						if(cb.getTile(this.getxLocation() - i, newy).getPiece().getColor() == this.getColor()) {
//							return false;
//						}
//						else if(cb.getTile(this.getxLocation() - i, newy).getPiece().getColor() != this.getColor()) {
//							if(i + 1 == distX) {
//								return true;
//							}
//						}
//					}
//					
//				}
//
//			}
//		}
//		//along the y axis
//		else if(distX == 0 && distY > 0) {
//			for(int j = 1; j < distY; j++) {
//				//forward
//				if(newy > this.getylocation()) {
//					try {
//						//no one on that tile
//						if(cb.getTile(newx, newy - (j-1)).getPiece() == null) {
//							if(j + 1 == distY) {
//								return true;
//							}
//						}
//												
//					}catch(NullPointerException e){
//						
//					}
//					if(cb.getTile(newx, newy- (j - 1)).getPiece() != null){
//						if(cb.getTile(newx, newy- (j-1)).getPiece().getColor() == this.getColor()) {
//							return false;
//						}
//					}
//					else if(cb.getTile(newx, newy - (j-1)).getPiece().getColor() != this.getColor()) {
//						if(j + 1 == distY) {
//							
//						}
//					}
//
//				}
//				else if(true) {
//
//				}
//				//backwards
//				if(newy < this.getylocation()) {
//					try {
//						//no one on that tile
//						if(cb.getTile(this.getylocation(), newy - j).getPiece() == null) {
//							//making sure the loop fully executes before passing true;
//							if(j + 1 == distY) {
//								return true;
//							}
//							
//						}
//					}catch(NullPointerException e){
//						
//					}
//					//if the next tile is not null and the 
//					if(cb.getTile(this.getylocation(), newy - j).getPiece() != null) {
//						if(cb.getTile(this.getylocation(), newy - j).getPiece().getColor() == this.getColor()) {
//							return false;
//						}
//						else if(cb.getTile(this.getylocation(), newy - j).getPiece().getColor() != this.getColor()) {
//							if(j + 1 == distY) {
//								return true;
//							}
//						}
//					}
//					
//				}
//
//			}
//		}
//		//remove this
//		return false;
	}

	@Override
	public String whatPiece() {
		// TODO Auto-generated method stub
		return "Rook";
	}
}