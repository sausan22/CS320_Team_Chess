package chessgame.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BishopChessPieceTest {
	private BishopPiece BishopTest;
	private BishopPiece temp;
	private ChessBoard chessBoard;
	
	@Before
	public void setUp() {
		BishopTest = new BishopPiece();
		BishopTest.setColor(true);
		BishopTest.setxLocation(4);
		BishopTest.setylocation(4);
		
		temp = new BishopPiece();
		temp.setColor(true);
		temp.setxLocation(5);
		temp.setylocation(4);
		chessBoard = new ChessBoard();
	}
	
	@Test
	public void testBishopCheckMove1() {
		fail();
//		assertTrue(BishopTest.checkMove(7, 7, chessBoard));
//		assertTrue(BishopTest.checkMove(0,0, chessBoard));
//		assertTrue(BishopTest.checkMove(1,7, chessBoard));
//		assertTrue(BishopTest.checkMove(7,1, chessBoard));
//		//outof bounds movement
//		assertFalse(BishopTest.checkMove(8, 8, chessBoard));
//		assertFalse(BishopTest.checkMove(-1, -1, chessBoard));
//		//incorrect movement
//		assertFalse(BishopTest.checkMove(5, 6, chessBoard));
//		assertFalse(BishopTest.checkMove(3, 4, chessBoard));
//		assertFalse(BishopTest.checkMove(5, 4, chessBoard));
//		
//		//piece in the way of friendly type
//		temp.setxLocation(2);
//		temp.setylocation(2);
//		chessBoard.setTile(2, 2, temp);
//		assertFalse(BishopTest.checkMove(2, 2, chessBoard));
//		//of opposite type
//		temp.setColor(false);
//		chessBoard.setTile(2, 2, temp);
//		assertTrue(BishopTest.checkMove(2, 2, chessBoard));
	}
	 

}
