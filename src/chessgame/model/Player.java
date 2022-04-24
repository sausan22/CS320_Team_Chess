package chessgame.model;

public class Player {
	private boolean color;
	private ChessPiece[] piece;
	private int gameID;
	private int userID;
	
	public Player() {
		piece = new ChessPiece[16];
	}
	
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	
	public int getGameID() {
		return gameID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public ChessPiece getPiece(int pieceNumber) {
		return piece[pieceNumber];
	}
	
	public void setPiece(int pieceNumber, ChessPiece piece) {
		this.piece[pieceNumber] = piece;
	}
	
	public boolean getColor() {
		return color;
	}
	public void setColor(boolean color) {
		this.color = color;
	}
	

	public Player(boolean c) {
		this.color = c;
	}
	
	public boolean isCheck(ChessBoard cb, KingPiece kingPiece) {
		return false;
	}	
	
	public boolean inCheck(ChessBoard cb, KingPiece kingPiece) {
		int x = kingPiece.getXlocation();
		int y = kingPiece.getYlocation();
		
		//diagonal movement ++
		int i = 1;
		int newx = x + i;
		int newy = y + i;
		
		while(newx < 8 && newy < 8 )
		{
			try
			{
				//get piece at ++
				ChessPiece potentialPiece = cb.getTile(newx,  newy).getPiece();
				//if piece is friendly, dont worry about it
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//there is a friendly piece here so break
					break;
				}
				//in check cause piece is not friendly, and the opponent piece can move diagonally
				else if(potentialPiece.whatPiece().equals("Queen") || potentialPiece.whatPiece().equals("Bishop"))
				{
					return true;
				}
				
				else
				{
					break;
				}
			}
			
			catch(NullPointerException n)
			{
				//if no other piece in the way just continue on to the next tile
				i++;
				newx = x + i;
				newy = y + i;
			}
		}
		
		//reset
		i = 1;
		newx = x - i;
		newy = y - i;
		
		while(newx >= 0 && newy >= 0)
		{
			try
			{
				//get piece at -- backwards movement
				ChessPiece potentialPiece = cb.getTile(newx,  newy).getPiece();
				//if piece is friendly, break
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					break;
				}
				//in check cause piece is not friendly, and the opponent piece can move diagonally
				else if(potentialPiece.whatPiece().equals("Queen") || potentialPiece.whatPiece().equals("Bishop"))
				{
					return true;
				}
				
				else
				{
					break;
				}
			}
			
			catch(NullPointerException n)
			{
				//if no other piece in the way just continue on to the next tile
				i++;
				newx = x - i;
				newy = y - i;
			}
		}
		
		//reset
		i = 1;
		newx = x + i;
		newy = y - i;
		
		while(newx < 8 && newy >= 0)
		{
			try
			{
				//get piece at +-
				ChessPiece potentialPiece = cb.getTile(newx,  newy).getPiece();
				//if piece is friendly, dont worry about direction, we are fine
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//System.out.println("Friendly Piece at " + newx + ", " + newy);
					break;
				}
				//in check cause piece is not friendly, and the opponent piece can move diagonally
				else if(potentialPiece.whatPiece().equals("Queen") || potentialPiece.whatPiece().equals("Bishop"))
				{
					return true;
				}
				
				else
				{
					break;
				}
			}
			
			catch(NullPointerException n)
			{
				//if no other piece in the way just continue on to the next tile
				i++;
				newx = x + i;
				newy = y - i;
			}
		}
		
		//reset
		i = 1;
		newx = x - i;
		newy = y + i;
		while(newx >= 0 && newy < 8) 
		{
			try
			{
				//get piece at -+
				ChessPiece potentialPiece = cb.getTile(newx,  newy).getPiece();
				//if piece is friendly
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					break;
				}
				//if not friendly, and is a piece that can move diagonally, in check
				else if(potentialPiece.whatPiece().equals("Queen") || potentialPiece.whatPiece().equals("Bishop"))
				{
					return true;
				}
				
				else
				{
					break;
				}
			}
			
			catch(NullPointerException n)
			{
				//If no one here, move onto next tile
				i++;
				newx = x - i;
				newy = y + i;
			}
		}
		
		//reset
		i = 1;
		newx = x + i;
		while(newx < 8)
		{
			try
			{
				//get piece at +0
				ChessPiece potentialPiece = cb.getTile(newx,  y).getPiece();
				//if piece is friendly
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					break;
				}
				//if not friendly, and is a piece that can move vertical, in check
				else if(potentialPiece.whatPiece().equals("Queen") || potentialPiece.whatPiece().equals("Rook"))
				{
					return true;
				}
				
				else
				{
					break;
				}
			}
			
			catch(NullPointerException n)
			{
				//If no one here, move onto next tile
				i++;
				newx = x + i;
			}
		}
		
		//reset
		i = 1;
		newx = x - i;
		while(newx >= 0)
		{
			try
			{
				//get piece at -0
				ChessPiece potentialPiece = cb.getTile(newx,  y).getPiece();
				//if piece is friendly
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					break;
				}
				//if not friendly, and is a piece that can move vertical, in check
				else if(potentialPiece.whatPiece().equals("Queen") || potentialPiece.whatPiece().equals("Rook"))
				{
					return true;
				}
				
				else
				{
					break;
				}
			}
			
			catch(NullPointerException n)
			{
				//If no one here, move on to next tile like nothing happned 
				i++;
				newx = x - i;
			}
		}
		
		//reset
		i = 1;
		newx = x;
		newy = y + i;
		while(newy < 8)
		{
			try
			{
				//get piece at 0+
				ChessPiece potentialPiece = cb.getTile(x, newy).getPiece();
				//if piece is friendly
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//friendly piece there
					break;
				}
				//if not friendly, and is a piece that can move horizontal, in check
				else if(potentialPiece.whatPiece().equals("Queen") || potentialPiece.whatPiece().equals("Rook"))
				{
					return true;
				}
				
				else
				{
					break;
				}
			}
			
			catch(NullPointerException n)
			{
				//If no one here, continue to next tile
				i++;
				newy = y + i;
			}
		}
		
		//reset
		i = 1;
		newy = y - 1;
		while(newy >= 0)
		{
			try
			{
				//get piece at 0-
				ChessPiece potentialPiece = cb.getTile(x,  newy).getPiece();
				//if piece is friendly, dont worry about the direction
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//friendly piece there so break
					break;
				}
				//if not friendly, and is a piece that can move horizontal, in check
				else if(potentialPiece.whatPiece().equals("Queen") || potentialPiece.whatPiece().equals("Rook"))
				{
					return true;
				}
				
				else
				{
					break;
				}
			}
			
			catch(NullPointerException n)
			{
				//If no one here, continue on to next tile
				i++;
				newy = y - i;
			}
		}
		
		//checking for check with KNIGHT
		try
		{
			//get piece at 2+1+
			newx = x+2;
			newy = y+1;
			//only if in bounds
			if(newx < 8 && newy < 8)
			{
				ChessPiece potentialPiece = cb.getTile(newx, newy).getPiece();
				//if piece is friendly
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
				//friendly piece here 
				}
				//if not friendly, and is a piece that move in a way of a knight, in check
				else if(potentialPiece.whatPiece().equals("Knight"))
				{
					return true;
				}
			}
			
			else
			{
				//out of bounds
			}
		}
			
		catch(NullPointerException n)
		{
			
		}
		
		try
		{
			//get piece at 2+1-
			newx = x+2;
			newy = y-1;
			
			if(newx < 8 && newy >= 0)
			{
				ChessPiece potentialPiece = cb.getTile(newx, newy).getPiece();
				//if piece is friendly
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//friendly here
				}
				//if not friendly, and is a piece that move in a way of a knight, in check
				else if(potentialPiece.whatPiece().equals("Knight"))
				{
					return true;
				}
			}
			
			else
			{
				//out of bounds
			}
		}
			
		catch(NullPointerException n)
		{}
		
		try
		{
			//get piece at 2-1+
			newx = x-2;
			newy = y+1;
			
			if(newx >= 0 && newy < 8)
			{
				ChessPiece potentialPiece = cb.getTile(newx, newy).getPiece();
				//if piece is friendly, dont worry
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//friendly piece there
				}
				//if not friendly, and is a piece that move in a way of a knight, in check
				else if(potentialPiece.whatPiece().equals("Knight"))
				{
					return true;
				}
			}
			
			else
			{
				//out of bounds 
			}
		}
			
		catch(NullPointerException n)
		{}
		
		try
		{
			//get piece at 2-1-
			newx = x-2;
			newy = y-1;
			
			if(newx >= 0 && newy >= 0)
			{
				ChessPiece potentialPiece = cb.getTile(newx, newy).getPiece();
				//if piece is friendly, dont worry about direction
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//friendly piece there
				}
				//if not friendly, and is a piece that move in a way of a knight, we are in check
				else if(potentialPiece.whatPiece().equals("Knight"))
				{
					return true;
				}
			}
			
			else
			{
				//out of bounds 
			}
		}
			
		catch(NullPointerException n)
		{
			
		}
		
		try
		{
			//get piece at 1+2+
			newx = x+1;
			newy = y+2;
			
			if(newx < 8 && newy < 8)
			{
				ChessPiece potentialPiece = cb.getTile(newx, newy).getPiece();
				//if piece is friendly, dont worry about direction
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//friendly piece there
				}
				//if not friendly, and is a piece that move in a way of a knight, we are in check
				else if(potentialPiece.whatPiece().equals("Knight"))
				{
					return true;
				}
			}
			
			else
			{
				//Out of bounds
			}
		}
			
		catch(NullPointerException n)
		{
			
		}
		
		try
		{
			//get piece at 1+2-
			newx = x+1;
			newy = y-2;
			
			if(newx < 8 && newy >= 0)
			{
				ChessPiece potentialPiece = cb.getTile(newx, newy).getPiece();
				//if piece is friendly, dont worry about directio
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//friendly piece here
				}
				//if not friendly, and is a piece that move in a way of a knight, in check
				else if(potentialPiece.whatPiece().equals("Knight"))
				{
					return true;
				}
			}
			
			else
			{
				//Out of bounds
			}
		}
			
		catch(NullPointerException n)
		{
			
		}
		
		try
		{
			//get piece at 1-2+
			newx = x-1;
			newy = y+2;
			
			if(newx >= 0 && newy < 8)
			{
				ChessPiece potentialPiece = cb.getTile(newx, newy).getPiece();
				//if piece is friendly, dont worry about direction
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//friednly piece in way
				}
				//if not friendly, and is a piece that move in a way of a knight, we are in check
				else if(potentialPiece.whatPiece().equals("Knight"))
				{
					return true;
				}
			}
			
			else
			{
				//out of bounds
			}
		}
			
		catch(NullPointerException n)
		{
			
		}
		
		try
		{
			//get piece at 1-2-
			newx = x-1;
			newy = y-2;
			
			if(newx >= 0 && newy >= 0)
			{
				ChessPiece potentialPiece = cb.getTile(newx, newy).getPiece();
				//if piece is friendly, dont worry about direction
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//friendly piece in way
				}
				//if not friendly, and is a piece that move in a way of a knight, we are in check
				else if(potentialPiece.whatPiece().equals("Knight"))
				{
					return true;
				}
			}
			
			else
			{
				//out of bounds
			}
		}
			
		catch(NullPointerException n)
		{
			//no piece in the way
		}
		
		//still need to implement pawn checking because that's a pain in the ass
		
		
		//only if all directions (diaganol, four ways and horizontal and vertical ways) do not have a threatening piece 
		//of the opposite color
		//if the king is white
		
		if(kingPiece.getColor() == true)
		{
			
			if(kingPiece.getYlocation() != 0)
			{
				newx = kingPiece.getXlocation() - 1;
				newy = kingPiece.getYlocation() + 1;
				try
				{
					ChessPiece potentialPiece = cb.getTile(newx,  newy).getPiece();
					if(potentialPiece.getColor() == kingPiece.getColor())
					{
						//friendly piece in way
					}
					//if piece is of an opposite color and is a pawn, check
					else if(potentialPiece.whatPiece().equals("Pawn"))
					{
						return true;
					}
				}
				
				catch(NullPointerException n)
				{
					//nothing in way so nothing happens
				}
				
				newy = kingPiece.getYlocation() - 1;
				try
				{
					ChessPiece potentialPiece = cb.getTile(newx,  newy).getPiece();
					if(potentialPiece.getColor() == kingPiece.getColor())
					{
						//friendly piece in way
					}
					//if piece is of an opposite color and is a pawn, check
					else if(potentialPiece.whatPiece().equals("Pawn"))
					{
						return true;
					}
				}
				
				catch(NullPointerException n)
				{
					//no chess piece in way
				}
			}
		}
		
		
		if(kingPiece.getColor() == false)
		{
	
			if(kingPiece.getYlocation() != 0)
			{
				newx = kingPiece.getXlocation() + 1;
				newy = kingPiece.getYlocation() + 1;
				try
				{
					ChessPiece potentialPiece = cb.getTile(newx,  newy).getPiece();
					if(potentialPiece.getColor() == kingPiece.getColor())
					{
						//friendly piece here
					}
					//if piece is of an opposite color and is a pawn, check
					else if(potentialPiece.whatPiece().equals("Pawn"))
					{
						return true;
					}
				}
				
				catch(NullPointerException n)
				{
					//no chess piece in way
				}
				
				newy = kingPiece.getYlocation() - 1;
				try
				{
					ChessPiece potentialPiece = cb.getTile(newx,  newy).getPiece();
					if(potentialPiece.getColor() == kingPiece.getColor())
					{
						//friendly piece here so nothing happens
					}
					//if piece is of an opposite color and is a pawn, check
					else if(potentialPiece.whatPiece().equals("Pawn"))
					{
						return true;
					}
				}
				
				catch(NullPointerException n)
				{
					//no piece in the way
				}
			}
		}
		
		return false;
	}	
}