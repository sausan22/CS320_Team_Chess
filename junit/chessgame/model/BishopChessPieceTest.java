package chessgame.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BishopChessPieceTest {
	private BishopPiece BishopTest;
	private ChessBoard chessBoard;
	
	@Before
	public void setUp() {
		BishopTest = new BishopPiece(4, 4, true, 1);
		chessBoard = new ChessBoard();
	}
	
	@Test
	public void testBishopCheckMove() {
		assertTrue(BishopTest.checkMove(7, 7, chessBoard));
		assertTrue(BishopTest.checkMove(0,0, chessBoard));
		assertTrue(BishopTest.checkMove(1,7, chessBoard));
		assertTrue(BishopTest.checkMove(7,1, chessBoard));

	}

}
