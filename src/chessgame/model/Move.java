package chessgame.model;

public class Move {
	private ChessPiece movedPiece;
	private int xpos;
	private String ypos;
	private String state;
	private boolean tookPiece;
	private boolean wasCastle;
	private boolean firstMove;
	
	public Move(ChessPiece cp, int x, int y, String st, boolean tp, boolean wc, boolean fm)
	{
		this.movedPiece = cp;
		this.xpos = (8 - x);
		
		if(y == 0)
		{
			this.ypos = "a";
		}
		
		else if(y == 1)
		{
			this.ypos = "b";
		}
		
		else if(y == 2)
		{
			this.ypos = "c";
		}
		
		else if(y == 3)
		{
			this.ypos = "d";
		}
		
		else if(y == 4)
		{
			this.ypos = "e";
		}
		
		else if(y == 5)
		{
			this.ypos = "f";
		}
		
		else if(y == 6)
		{
			this.ypos = "g";
		}
		
		else if(y == 7)
		{
			this.ypos = "h";
		}
		
		if(st.equals("Checkmate"))
		{
			this.state = "#";
		}
		
		else if(st.equals("Check")) 
		{
			this.state = "+";
		}
		
		else
		{
			this.state = "";
		}
		
		tookPiece = tp;
		wasCastle = wc;
		firstMove = fm;
	}
	
	public boolean getFirstMove()
	{
		return this.firstMove;
	}
	
	public void printMove()

	{		
		if(wasCastle == true && ypos.equals("h"))
		{
			System.out.println("0-0");
		}

		else if(wasCastle == true && ypos.equals("a"))
		{
			System.out.println("0-0-0");
		}
		
		else if(tookPiece == false)
		{
			System.out.println(movedPiece.whatPiece() + "" + state + "" + ypos + "" + xpos);
		}
		
		else if(tookPiece == true)
		{
			System.out.println(movedPiece.whatPiece() + "x" + state + "" + ypos + "" + xpos);
		}
		
	}
	
	// get the move
	public String getMove()
	{
		if(wasCastle == true && ypos.equals("h"))
		{
			return "0-0";
		}
		
		else if(wasCastle == true && ypos.equals("a"))
		{
			return "0-0-0";
		}
		
		else if(tookPiece == false)
		{
			return movedPiece.whatPiece() + "" + state + "" + ypos + "" + xpos;
		}
		
		else if(tookPiece == true)
		{
			return movedPiece.whatPiece() + "x" + state + "" + ypos + "" + xpos;
		}
		
		else
		{
			return null;
		}
	}
}
