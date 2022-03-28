package chessgame.model;

public class KnightPiece extends ChessPiece {
	public KnightPiece(int x, int y, boolean c, int p)
	{
		this.setXlocation(x);
		this.setYlocation(y);
		this.setColor(c);
		this.setHasMoved(false);
		this.setPieceNumber(p);
	}
}
