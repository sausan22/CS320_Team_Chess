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
	public void bishopQueenMoveOutOfBounds() {
		
	}
	
	@Test
	public void rookQueenMoveOutOfBounds() {
		
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
	public void bishopQueenMoveWtihPieces() {
		
	}
	@Test
	public void rookQueenMoveNoPieces() {
		
	}
	@Test
	public void rookQueenMoveWithPieces() {
		
	}
	}