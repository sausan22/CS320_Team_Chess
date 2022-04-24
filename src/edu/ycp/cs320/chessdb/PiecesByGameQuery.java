package edu.ycp.cs320.chessdb;

import java.util.List; 
import java.util.Scanner;
import chessgame.model.ChessPiece;
import edu.ycp.cs320.chessdb.model.PiecesDB;
import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.IDatabase;

public class PiecesByGameQuery {
	public List<PiecesDB> main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		//System.out.print("Enter a game ID: ");
		//String lastName = keyboard.nextLine();
		
		IDatabase db = DatabaseProvider.getInstance();
		List<PiecesDB> pieceList = db.findPiecesByGameID(1); //change 1 once game selection is real
		if (pieceList.isEmpty()) {
			System.out.println("Game does not exist: this is a problem since game 1 is hardcoded and should exist");
		}
		return pieceList;
	}
}