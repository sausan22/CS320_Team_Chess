package chessgame.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RookPieceTest {
	private RookPiece RookTest;
	private ChessBoard chessBoard;
	
	@Before
	public void setUp() {
		RookTest = new RookPiece();
//		RookTest;
		chessBoard = new ChessBoard();
	}
	@Test
	public void testRookCheckMove() {
		assertTrue(RookTest.checkMove(7, 0, chessBoard));
		
	}

}
