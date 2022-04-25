package edu.ycp.cs320.chessdb;



import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.DerbyDatabase;

public class InitDatabase {
	public static void init() {
		DatabaseProvider.setInstance(new DerbyDatabase());
	}
}
