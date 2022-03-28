package chessgame.model;

public class KingPiece extends ChessPiece{
	public KingPiece(int x, int y, boolean c, int p)
	{
		this.setXlocation(x);
		this.setYlocation(y);
		this.setColor(c);
		this.setHasMoved(false);
		this.setPieceNumber(p);
	}
}
