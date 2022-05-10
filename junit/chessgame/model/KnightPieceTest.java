package chessgame.model;

import static org.junit.Assert.*; 

import org.junit.Before;
import org.junit.Test;

public class KnightPieceTest {
	private KnightPiece KnightTest;
	private KnightPiece tempPiece;
	private ChessBoard chessBoard;
	@Before
	public void setUp() {
		KnightTest = new KnightPiece();
		KnightTest.setxLocation(7);
		KnightTest.setylocation(6);
		KnightTest.setColor(true);
		
		tempPiece = new KnightPiece();
		tempPiece.setxLocation(0);
		tempPiece.setylocation(1);
		chessBoard = new ChessBoard();
	}
	@Test
	public void checkNoPiecesMove() {
		//out of bounds tests
		assertFalse(KnightTest.checkMove(8, 8, chessBoard));
		assertFalse(KnightTest.checkMove(-1, -1, chessBoard));
		
	}
	@Test
	public void checkKnightMove() {
		//basic movement
				assertTrue(KnightTest.checkMove(5, 5, chessBoard));
				assertTrue(KnightTest.checkMove(5, 7, chessBoard));
				assertTrue(KnightTest.checkMove(5, 7, chessBoard));
				KnightTest.setxLocation(4);
				KnightTest.setylocation(4);
				assertTrue(KnightTest.checkMove(5, 2, chessBoard));
				assertTrue(KnightTest.checkMove(6, 5, chessBoard));
				assertTrue(KnightTest.checkMove(6, 3, chessBoard));
				assertTrue(KnightTest.checkMove(3, 6, chessBoard));
				assertTrue(KnightTest.checkMove(3, 6, chessBoard));
				
				//matching color movement should be false
				tempPiece.setColor(true);
				chessBoard.setTile(0, 1, tempPiece);
				KnightTest.setxLocation(2);
				KnightTest.setylocation(2);
				assertFalse(KnightTest.checkMove(2, 2, chessBoard));
				tempPiece.setColor(false);
				chessBoard.setTile(0, 1, tempPiece);
				assertTrue(KnightTest.checkMove(0, 1, chessBoard));
				KnightTest.setxLocation(7);
				KnightTest.setylocation(6);
	}

}
