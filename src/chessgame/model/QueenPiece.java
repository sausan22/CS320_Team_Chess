package chessgame.model;

public class QueenPiece extends ChessPiece{
	public QueenPiece(/*int x, int y, boolean c, int p*/)
	{
//		this.setXlocation(x);
//		this.setYlocation(y);
//		this.setColor(c);
//		this.setHasMoved(false);
//		this.setPieceNumber(p);
	}
	
	public String whatPiece(){
		return "Queen";
	}
	
	public boolean checkMove(int newx, int newy, ChessBoard cb) {
		//check out of bounds
		if(newx < 0 || newx > 7 || newy < 0 || newy > 7)
		{
			return false;
		}
		
		int changeX = (newx - this.getXlocation());
		int changeY = (newy - this.getYlocation());
		int oldx = this.getXlocation();
		int oldy = this.getYlocation();
		
		if(changeY == 0 && changeX != 0)
		{
			//if moving up in x
			if(newx > oldx)
			{
				//cycles through all the possible spots it could go to 
				for(int i = oldx+=1; i < newx; i++)
				{
					Tile t = cb.getTile(i, newy);
					
					try
					{
						if(t.getPiece() != null)
						{
							return false;
						}
					}
					
					catch(NullPointerException e)
					{
					}
				}
			}
			
			else if(oldx > newx)
			{
				//cycles through all the possible spots it could go to 
				for(int i = newx+=1; i < oldx; i++)
				{
					Tile t = cb.getTile(i, newy);
					try
					{
						if(t.getPiece() != null)
						{
							return false;
						}
					}
					
					catch(NullPointerException e)
					{
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
					Tile t = cb.getTile(getXlocation(), i);
					try
					{
						if(t.getPiece() != null)
						{
							return false;
						}
					}
					
					catch(NullPointerException e)
					{
					}
				}
			}
			
			else if(oldy > newy)
			{
				//cycles through all the possible spots it could go to 
				for(int i = newy+=1; i < oldy; i++)
				{
					Tile t = cb.getTile(getXlocation(), i);
					try
					{
						if(t.getPiece() != null)
						{
							return false;
						}
					}
					
					catch(NullPointerException e)
					{
					}
				}
			}
		}
		
		if(changeX > 0 && changeY > 0 && Math.abs(changeX) == Math.abs(changeY))
		{
			for(int i = oldx+=1; i < newx; i++)
			{
				int j = oldy + i - oldx + 1; //had += 1 earlier so need to + 1 since we subtract the extra + 1
				Tile t = cb.getTile(i, j);
				try
				{
					if(t.getPiece() != null)
					{
						return false;
					}
				}
				
				catch(NullPointerException e)
				{
				}
			}
		}
		
		else if(changeX < 0 && changeY < 0 && Math.abs(changeX) == Math.abs(changeY))
		{
			for(int i = newx+=1; i < oldx; i++)
			{
				int j = oldy + i - oldx;
				Tile t = cb.getTile(i, j);
				try
				{
					if(t.getPiece() != null)
					{
						return false;
					}
				}
				
				catch(NullPointerException e)
				{
				}
			}
			newx--;
		}
		
		else if(changeX > 0 && changeY < 0 && Math.abs(changeX) == Math.abs(changeY))
		{
			int testX = oldx + 1;
			int testY = oldy - 1;
			while(testX < newx || testY > newy)
			{
				Tile t = cb.getTile(testX, testY);
				
				try
				{
					if(t.getPiece() != null)
					{
						return false;
					}
				}
				
				catch(NullPointerException e)
				{
				}
				
				testX++;
				testY--;
			}
		}
		
		else if(changeX < 0 && changeY > 0 && Math.abs(changeX) == Math.abs(changeY))
		{
			int testX = oldx - 1;
			int testY = oldy + 1;
			while(testX > newx || testY < newy)
			{
				Tile t = cb.getTile(testX, testY);
				
				try
				{
					if(t.getPiece() != null)
					{
						return false;
					}
				}
				
				catch(NullPointerException e)
				{
				}
				
				testX--;
				testY++;
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
			
		}
		
		changeX = Math.abs(changeX);
		changeY = Math.abs(changeY);
		
		//vertical or horizontal, and actually moving not just up and down
		if(((changeX * changeY == 0) || (changeX == changeY)) && changeX + changeY !=0)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	
			
	}
}
