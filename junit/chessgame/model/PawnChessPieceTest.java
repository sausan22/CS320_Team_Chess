package chessgame.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PawnChessPieceTest {
	private PawnPiece WPawn;
	private PawnPiece BPawn;
	private PawnPiece testPawn;
	private ChessBoard chessBoard;
	
	@Before
	public void setUp() {
		WPawn = new PawnPiece();
		WPawn.setColor(true);
		WPawn.setxLocation(6);
		WPawn.setylocation(6);
		WPawn.setPieceId(0);
		WPawn.setHasMoved(false);
		
		
		BPawn = new PawnPiece();
		BPawn.setColor(false);
		BPawn.setxLocation(1);
		BPawn.setylocation(1);
		BPawn.setPieceId(15);
		BPawn.setHasMoved(false);
		//
		//test capture and movement with more than 1 piece on the board
		testPawn = new PawnPiece();
		testPawn.setColor(true);
		testPawn.setxLocation(4);
		testPawn.setylocation(4);
		testPawn.setPieceId(14);
		testPawn.setHasMoved(false);
		chessBoard = new ChessBoard();		
	}
	
	
	@Test
	public void testPawnCheckMove() {
		
		//moving 1 spot
		assertTrue(WPawn.checkMove(5, 6, chessBoard));
		//moving 2 spots
		assertTrue(WPawn.checkMove(4, 6, chessBoard));
		//moving 2 spots but the piece has already moved
		WPawn.setHasMoved(true);
		assertFalse(WPawn.checkMove(4, 6, chessBoard));
		WPawn.setHasMoved(false);
		
		//trying to move diagonally
		assertTrue(WPawn.checkMove(5, 5, chessBoard));
		assertTrue(WPawn.checkMove(5, 7, chessBoard));

		//checking that moving backwards doesn't work
		assertFalse(WPawn.checkMove(5, 6, chessBoard));
		//moving left or right is false
		assertFalse(WPawn.checkMove(6, 5, chessBoard));
		
		//teting a false move that will allow the movement
		testPawn.setColor(false);
		testPawn.setXlocation(5);
		testPawn.setylocation(6);
		chessBoard.setTile(5, 5, testPawn);
		WPawn.setXlocation(6);
		WPawn.setylocation(6);
		//checking if the move is correct when there is another
		//piece blocking its path of the opposite type
		assertTrue(WPawn.checkMove(5, 6, chessBoard));
		testPawn.setColor(true);
		chessBoard.setTile(5, 6, testPawn);
		assertFalse(WPawn.checkMove(5, 6, chessBoard));
		
		testPawn.setColor(false);
		chessBoard.setTile(5, 5, null);
	}
	@Test
	public void testBlackPawnMove() {
		
		//moving 1 spot
		assertTrue(BPawn.checkMove(2, 1, chessBoard));
		//moving 2 spots
		assertTrue(BPawn.checkMove(3, 1, chessBoard));
		//trying to move diagonally
		assertTrue(BPawn.checkMove(2, 2, chessBoard));
		//checking that moving backwards doesn't work
		assertFalse(BPawn.checkMove(0, 1, chessBoard));
		
		//moving left or right proves false
		assertFalse(BPawn.checkMove(1,0, chessBoard));
		assertFalse(BPawn.checkMove(1,2, chessBoard));
		
		testPawn.setxLocation(2);
		testPawn.setYlocation(1);
		testPawn.setColor(true);
		chessBoard.setTile(2, 1, testPawn);
		BPawn.setxLocation(1);
		BPawn.setylocation(1);
		assertTrue(BPawn.checkMove(2, 1, chessBoard));
		testPawn.setColor(false);
		chessBoard.setTile(2, 1, testPawn);
		assertFalse(BPawn.checkMove(2, 1, chessBoard));
		
		testPawn.setxLocation(4);
		testPawn.setylocation(4);
		
	}

}
