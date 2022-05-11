package chessgame.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RookPieceTest {
	private RookPiece RookTest;
	private ChessPiece test;
	private ChessBoard chessBoard;
	
	@Before
	public void setUp() {
		/*
         * 0 - 15 are pawns
         * 16 - 19 knights
         * 20 - 23 bishops
         * 24 - 27 rooks
         * 28 - 29 queens
         * 30 - 31 kings
         * */
		RookTest = new RookPiece();
		RookTest.setPieceId(24);
		RookTest.setxLocation(7);
		RookTest.setylocation(7);
		RookTest.setColor(true);
		chessBoard = new ChessBoard();
		test = new PawnPiece();
		test.setColor(true);
	}
	@Test 
	public void testWhiteRookMove() {
		//out of bound moves should not work
		assertFalse(RookTest.checkMove(7, 8, chessBoard));
		assertFalse(RookTest.checkMove(7, -1, chessBoard));
		assertFalse(RookTest.checkMove(8, 7, chessBoard));
		assertFalse(RookTest.checkMove(-1, 7, chessBoard));
		// 1 move forward and up
		assertTrue(RookTest.checkMove(6, 7, chessBoard));
		assertTrue(RookTest.checkMove(7, 6, chessBoard));
		
//		//on an empty chessboard movement is valid for moving forwards
//		for(int i = 0; i < 7; i++) {
//			assertTrue(RookTest.checkMove(i, 7, chessBoard));
//		}
//		//on an empty chessboard movement is valid for moving upwards
//		for(int j = 0; j < 7; j++) {
//			assertTrue(RookTest.checkMove(7, j, chessBoard));
//		}
//		
//		//on an empty chessboard movement is valid for moving forwards
//		for(int i = 7; i >= 0; i--) {
//			assertTrue(RookTest.checkMove(i, 7, chessBoard));
//		}
//		//on an empty chessboard movement is valid for moving upwards
//		for(int j = 7; j >= 0; j--) {
//			assertTrue(RookTest.checkMove(7, j, chessBoard));
//		}
		
		
		//false if the piece is the same color
//		chessBoard.setTile(6, 7, test);
//		assertFalse(RookTest.checkMove(6, 7, chessBoard));
		//move is true if there is a piece that is of opposite color
		test.setColor(false);
		chessBoard.setTile(6, 7, test);
		assertTrue(RookTest.checkMove(6, 7, chessBoard));
//		//out of bound moves should not work
//		assertFalse(RookTest.checkMove(7, 8, chessBoard));
//		assertFalse(RookTest.checkMove(7, -1, chessBoard));
//		assertFalse(RookTest.checkMove(8, 7, chessBoard));
//		assertFalse(RookTest.checkMove(-1, 7, chessBoard));
//		// 1 move forward and up
//		assertTrue(RookTest.checkMove(6, 7, chessBoard));
//		assertTrue(RookTest.checkMove(7, 6, chessBoard));
//		
//		//on an empty chessboard movement is valid for moving forwards
//		for(int i = 0; i < 7; i++) {
//			assertTrue(RookTest.checkMove(i, 7, chessBoard));
//		}
//		//on an empty chessboard movement is valid for moving upwards
//		for(int j = 0; j < 7; j++) {
//			assertTrue(RookTest.checkMove(7, j, chessBoard));
//		}
//		
//		//on an empty chessboard movement is valid for moving forwards
//		for(int i = 7; i >= 0; i--) {
//			assertTrue(RookTest.checkMove(i, 7, chessBoard));
//		}
//		//on an empty chessboard movement is valid for moving upwards
//		for(int j = 7; j >= 0; j--) {
//			assertTrue(RookTest.checkMove(7, j, chessBoard));
//		}
//		
//		
//		//false if the piece is the same color
//		chessBoard.setTile(6, 7, test);
//		assertFalse(RookTest.checkMove(6, 7, chessBoard));
//		//move is true if there is a piece that is of opposite color
//		test.setColor(false);
//		chessBoard.setTile(6, 7, test);
//		assertTrue(RookTest.checkMove(6, 7, chessBoard));
	}
//	@Test
//	public void testBlackRookMove() {
//		
//	}

}
