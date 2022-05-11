package chessgame.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
public class QueenPieceTest {
	private QueenPiece qTest;
	private QueenPiece qp;
	private ChessBoard cb;

	@Before
	public void setUp(){
		qTest = new QueenPiece();
		qTest.setColor(true);
		qTest.setxLocation(4);
		qTest.setylocation(4);
		//test piece 
		qp = new QueenPiece();
		qp.setColor(true);
		qp.setxLocation(0);
		qp.setylocation(0);
		cb = new ChessBoard();
	}

	@Test
	public void QueenMoveOutOfBounds() {
		assertFalse(qTest.checkMove(8, 8, cb));
		assertFalse(qTest.checkMove(8, 0, cb));
		assertFalse(qTest.checkMove(0, 8, cb));
		assertFalse(qTest.checkMove(-1, -1, cb));
		assertFalse(qTest.checkMove(-1, 0, cb));
		assertFalse(qTest.checkMove(0, -1, cb));



	}

	@Test
	public void bishopQueenMoveNoPieces() {
		assertTrue(qTest.checkMove(3, 3, cb));
		assertTrue(qTest.checkMove(5, 5, cb));
		assertTrue(qTest.checkMove(5, 3, cb));
		assertTrue(qTest.checkMove(3, 5, cb));
		assertTrue(qTest.checkMove(2, 2, cb));
		assertTrue(qTest.checkMove(1, 1, cb));
		assertTrue(qTest.checkMove(0, 0, cb));
		assertTrue(qTest.checkMove(6, 6, cb));
		assertTrue(qTest.checkMove(7, 7, cb));
	}

	@Test
	public void rookQueenMoveNoPieces() {
		assertTrue(qTest.checkMove(7, 4, cb));
		assertTrue(qTest.checkMove(0, 4, cb));
		assertTrue(qTest.checkMove(4, 7, cb));
		assertTrue(qTest.checkMove(4, 0, cb));
		assertTrue(qTest.checkMove(6, 4, cb));
		assertTrue(qTest.checkMove(2, 4, cb));

	}

	@Test
	public void bishopQueenMoveWtihPieces() {
		qp.setxLocation(5);
		qp.setylocation(5);
		qp.setColor(true);
		cb.setTile(5, 5, qp);
		assertFalse(qTest.checkMove(5, 5, cb));

		qp.setxLocation(3);
		qp.setylocation(3);
		qp.setColor(true);
		cb.setTile(3, 3, qp);
		assertFalse(qTest.checkMove(3, 3, cb));

		qp.setxLocation(3);
		qp.setylocation(5);
		qp.setColor(true);
		cb.setTile(3, 5, qp);
		assertFalse(qTest.checkMove(3, 5, cb));

		qp.setxLocation(5);
		qp.setylocation(3);
		qp.setColor(true);
		cb.setTile(5, 3, qp);
		assertFalse(qTest.checkMove(5, 3, cb));
		//should return true after this
		qp.setxLocation(5);
		qp.setylocation(5);
		qp.setColor(false);
		cb.setTile(5, 5, qp);
		assertTrue(qTest.checkMove(5, 5, cb));

		qp.setxLocation(3);
		qp.setylocation(3);
		qp.setColor(false);
		cb.setTile(3, 3, qp);
		assertTrue(qTest.checkMove(3, 3, cb));

		qp.setxLocation(3);
		qp.setylocation(5);
		qp.setColor(false);
		cb.setTile(3, 5, qp);
		assertTrue(qTest.checkMove(3, 5, cb));

		qp.setxLocation(5);
		qp.setylocation(3);
		qp.setColor(false);
		cb.setTile(5, 3, qp);
		assertTrue(qTest.checkMove(5, 3, cb));
		qp.setxLocation(4);
		qp.setylocation(4);
		qp.setColor(true);
	}

	@Test
	public void rookQueenMoveWithPieces() {
		qp.setxLocation(4);
		qp.setylocation(5);
		qp.setColor(true);
		cb.setTile(4, 5, qp);
		assertFalse(qTest.checkMove(4, 5, cb));
		
		qp.setxLocation(4);
		qp.setylocation(3);
		qp.setColor(true);
		cb.setTile(4, 3, qp);
		assertFalse(qTest.checkMove(4, 3, cb));
		
		qp.setxLocation(4);
		qp.setylocation(6);
		qp.setColor(true);
		cb.setTile(4, 6, qp);
		assertFalse(qTest.checkMove(4, 6, cb));
				
		qp.setxLocation(5);
		qp.setylocation(4);
		qp.setColor(true);
		cb.setTile(5, 4, qp);
		assertFalse(qTest.checkMove(5, 4, cb));
		
		qp.setxLocation(6);
		qp.setylocation(4);
		qp.setColor(true);
		cb.setTile(6, 4, qp);
		assertFalse(qTest.checkMove(6, 4, cb));
		
		qp.setxLocation(3);
		qp.setylocation(4);
		qp.setColor(true);
		cb.setTile(3, 4, qp);
		assertFalse(qTest.checkMove(3, 4, cb));
		/*
		 * below is where the piece does not match the color
		 *  of the current piece and should be a valid move
		 * 
		 * */
		qp.setxLocation(4);
		qp.setylocation(5);
		qp.setColor(false);
		cb.setTile(4, 5, qp);
		assertTrue(qTest.checkMove(4, 5, cb));
		
		qp.setxLocation(4);
		qp.setylocation(3);
		qp.setColor(false);
		cb.setTile(4, 3, qp);
		assertTrue(qTest.checkMove(4, 3, cb));
		
		qp.setxLocation(4);
		qp.setylocation(6);
		qp.setColor(false);
		cb.setTile(4, 6, qp);
		assertTrue(qTest.checkMove(4, 6, cb));
				
		qp.setxLocation(5);
		qp.setylocation(4);
		qp.setColor(false);
		cb.setTile(5, 4, qp);
		assertTrue(qTest.checkMove(5, 4, cb));
		
		qp.setxLocation(6);
		qp.setylocation(4);
		qp.setColor(false);
		cb.setTile(6, 4, qp);
		assertTrue(qTest.checkMove(6, 4, cb));
		
		qp.setxLocation(3);
		qp.setylocation(4);
		qp.setColor(false);
		cb.setTile(3, 4, qp);
		assertTrue(qTest.checkMove(3, 4, cb));
		
		qp.setxLocation(4);
		qp.setylocation(4);
		qp.setColor(false);
		
	}
}