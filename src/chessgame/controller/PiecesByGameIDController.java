package chessgame.controller;

import java.util.ArrayList;
import java.util.List;

import chessgame.model.ChessPiece;
import chessgame.model.User;
import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.DerbyDatabase;
import edu.ycp.cs320.chessdb.persist.IDatabase;

public class PiecesByGameIDController {
private IDatabase db = null; 
	
	public PiecesByGameIDController() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	public ArrayList<ChessPiece> getPieces(int GameID){
		List<ChessPiece> pieceList = db.findPiecesByGameID(GameID);
		ArrayList<ChessPiece> piece = null;
		if(pieceList.isEmpty()) {
			System.out.println("No pieces found in that game");
		}
		else {
			piece = new ArrayList<ChessPiece>();
			for(ChessPiece peece : pieceList) {
				piece.add(peece);
			}
		}
		return piece;
	}
	
}
