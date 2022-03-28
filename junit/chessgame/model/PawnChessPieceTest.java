package chessgame.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PawnChessPieceTest {
	private PawnPiece WPawn;
	private PawnPiece BPawn;
	private ChessBoard chessBoard;
	
	@Before
	public void setUp() {
		WPawn = new PawnPiece(6, 6, true, 1);
		BPawn = new PawnPiece(1, 1, false, 1);
		chessBoard = new ChessBoard();
	}
	
	
	@Test
	public void testPawnCheckMove() {
		//moving 1 spot
		assertTrue(WPawn.checkMove(5, 6, chessBoard));
		//moving 2 spots
		assertTrue(WPawn.checkMove(4, 6, chessBoard));
		//trying to move diagonally
		assertTrue(WPawn.checkMove(5, 5, chessBoard) == false);
		
		
		
		//moving 1 spot
		assertTrue(BPawn.checkMove(2, 1, chessBoard));
		//moving 2 spots
		assertTrue(BPawn.checkMove(3, 1, chessBoard));
		//trying to move diagonally
		assertTrue(BPawn.checkMove(2, 2, chessBoard) == false);

	}


}
