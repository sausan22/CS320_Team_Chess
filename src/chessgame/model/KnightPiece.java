package chessgame.model;

public class KnightPiece extends ChessPiece {

	@Override
	public String whatPiece() {
		// TODO Auto-generated method stub
		return "Knight";
	}

	@Override
	public String whatInitial() {
		// TODO Auto-generated method stub
		return "K";
	}

	@Override
	public boolean checkMove(int newx, int newy, ChessBoard cb) {
		// TODO Auto-generated method stub
		return false;
	}

}
