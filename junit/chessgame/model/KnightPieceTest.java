package chessgame.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KnightPieceTest {
	private KnightPiece KnightTest;
	private ChessBoard chessBoard;
	@Before
	public void setUp() {
		KnightTest = new KnightPiece(7, 6, true, 1);
		chessBoard = new ChessBoard();
	}
	@Test
	public void checkKnightMove() {
		assertTrue(KnightTest.checkMove(5, 5, chessBoard));
		assertFalse(KnightTest.checkMove(8, 8, chessBoard));
		//fail("Not yet implemented");
		
		
	}

}
