package chessgame.model;

public class MovesDB{
	private int gameID;
	private int turn;
	private int xCord;
	private int yCord;
	private int pieceNumber;
	private ChessPiece movedPiece;
	private String ypos;
	private String state;
	private boolean tookPiece;
	private boolean firstMove;
	
	public MovesDB() {
		
	}
	
	public MovesDB(ChessPiece cp, int x, int y, String st, boolean tp, boolean wc, boolean fm)
	{
		this.movedPiece = cp;
		this.xCord = (8 - x);

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
		firstMove = fm;
	}
	
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	
	public int getGameID() {
		return gameID;
	}
	
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public void setXCord(int xCord) {
		this.xCord = xCord;
	}
	
	public int getXCord() {
		return xCord;
	}
	
	public void setYCord(int yCord) {
		this.yCord = yCord;
	}
	
	public int getYCord() {
		return yCord;
	}
	
	public void setPieceNumber(int pieceNumber) {
		this.pieceNumber = pieceNumber;
	}
	
	public int getPieceNumber() {
		return pieceNumber;
	}
}
