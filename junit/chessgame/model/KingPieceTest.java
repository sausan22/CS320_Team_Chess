package chessgame.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KingPieceTest {
	ChessPiece kp;
	ChessPiece tp;
	ChessBoard cb;
	@Before
	public void setup() {
		kp = new KingPiece();
		kp.setxLocation(4);
		kp.setylocation(4);
		kp.setColor(true);
		tp = new PawnPiece();
		tp.setxLocation(0);
		tp.setylocation(0);
		tp.setColor(true);
		cb = new ChessBoard();
	}
	@Test
	public void kingTestNoPieces() {
		assertTrue(kp.checkMove(3, 4, cb));
		assertTrue(kp.checkMove(5, 4, cb));
		assertTrue(kp.checkMove(4, 3, cb));
		assertTrue(kp.checkMove(4, 5, cb));
		assertTrue(kp.checkMove(5, 5, cb));
		assertTrue(kp.checkMove(3, 3, cb));
		assertTrue(kp.checkMove(3, 5, cb));
		assertTrue(kp.checkMove(5, 3, cb));	
	}
	public void kingTestWtihPieces() {
		tp.setxLocation(3);
		tp.setylocation(4);
		cb.setTile(3, 4, tp);
		assertFalse(tp.checkMove(3, 4, cb));
		
		tp.setxLocation(5);
		tp.setylocation(4);
		cb.setTile(5, 4, tp);
		assertFalse(tp.checkMove(5, 4, cb));
		
		tp.setxLocation(5);
		tp.setylocation(5);
		cb.setTile(5, 5, tp);
		assertFalse(tp.checkMove(5, 5, cb));
		
		tp.setxLocation(3);
		tp.setylocation(3);
		cb.setTile(3, 3, tp);
		assertFalse(tp.checkMove(3, 3, cb));
		
		tp.setxLocation(3);
		tp.setylocation(4);
		cb.setTile(3, 4, tp);
		assertFalse(tp.checkMove(3, 4, cb));
		//different color
		
		tp.setColor(false);
		tp.setxLocation(3);
		tp.setylocation(4);
		cb.setTile(3, 4, tp);
		assertTrue(tp.checkMove(3, 4, cb));
		
		tp.setxLocation(5);
		tp.setylocation(4);
		cb.setTile(5, 4, tp);
		assertTrue(tp.checkMove(5, 4, cb));
		
		tp.setxLocation(5);
		tp.setylocation(5);
		cb.setTile(5, 5, tp);
		assertTrue(tp.checkMove(5, 5, cb));
		
		tp.setxLocation(3);
		tp.setylocation(3);
		cb.setTile(3, 3, tp);
		assertTrue(tp.checkMove(3, 3, cb));
		
		tp.setxLocation(3);
		tp.setylocation(4);
		cb.setTile(3, 4, tp);
		assertTrue(tp.checkMove(3, 4, cb));
	}

}
