package chessgame.model;

public class PawnPiece extends ChessPiece{
	public PawnPiece(/*int x, int y, boolean c, int p*/)
	{
//		this.setXlocation(x);
//		this.setYlocation(y);
//		this.setColor(c);
//		this.setHasMoved(false);
//		this.setPieceNumber(p);
	}
	
//	public String whatInitial() {
//		return "P";
//	}
	
	public String whatPiece() {
		return "Pawn";
	}
	
	public boolean checkMove(int newx, int newy, ChessBoard cb) {
		//move is out of bounds
		if(newx < 0 || newx > 7 || newy < 0 || newy > 7)
		{
			return false;
		}
		
		//not absolute value, since 
		int changeX = newx - this.getXlocation();
		int changeY = Math.abs(newy - this.getYlocation());
		
		//check to see if other pieces are in the way
		//vertical movement
		if(changeY == 0)
		{
			try
			{
				//can't take pieces if in front
				if(cb.getTile(newx, newy).getPiece() != null && changeY == 0)
				{
					return false;
				}
				
				//If it moves forward 2 checks the one right in front of it too
				if(Math.abs(changeX) == 2)
				{
					if(cb.getTile(newx, newy-=1).getPiece() != null && changeY == 0)
					{
						//System.out.println("");
						return false;
					}
				}
				
				if(cb.getTile(newx, newy).getPiece().getColor() == this.getColor())
				{
					return false;
				}
			}
			catch(NullPointerException n)
			{
			}
			
			//check to see if they take a piece
			
			//makes sure that the movements are one forward in the right direction
			//if they haven't moved can move two forward
			if(this.getHasMoved() == false)
			{
				if(this.getColor() == true)//color is white
				{
					if((changeX == -1 || changeX == -2) && changeY == 0)
					{
						return true;
					}
					
					else
					{
						return false;
					}
				}
				
				if(this.getColor() == false)//color is black
				{
					if((changeX == 1 || changeX == 2) && changeY == 0)
					{
						return true;
					}
					
					else
					{
						return false;
					}
				}
			}
			
			else if(this.getHasMoved() == true)
			{
				if(this.getColor() == true)//color is white
				{
					if(changeX == -1 && changeY == 0)
					{
						return true;
					}
					
					else
					{
						return false;
					}
				}
				
				else if(this.getColor() == false) //color is black
				{
					if(changeX == 1 && changeY == 0)
					{
						return true;
					}
					
					else
					{
						return false;
					}
				}
			}
		}
		
		//taking a piece
		else if(changeY == 1)
		{
			if(Math.abs(changeX) != 1)
			{
				return false;
			}
			
			//not moving forward
			if(this.getColor() == true && changeX != -1)
			{
				return false;
			}
			
			//not moving forward
			if(this.getColor() == false && changeX != 1)
			{
				return false;
			}
			
			try
			{				
				if(cb.getTile(newx, newy).getPiece().getColor() == this.getColor())
				{
					return false;
				}
			}
			catch(NullPointerException n)
			{
				return false;
			}
			
			//only if you move 1 forward in the right direction and the color' dont match, then you can move diagnolly
			return true;
		}
		
		return false;
	}
}
