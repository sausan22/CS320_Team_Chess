package edu.ycp.cs320.chessdb;

import java.util.Scanner;

import edu.ycp.cs320.chessdb.persist.DatabaseProvider;
import edu.ycp.cs320.chessdb.persist.DerbyDatabase;
import edu.ycp.cs320.chessdb.persist.FakeDatabase;

public class InitDatabase {
	public static void init(Scanner keyboard) {
		//System.out.print("Which database (0=fake, 1=derby): ");
		int which = 1;
//		if (which == 0) { // Commenting out since the user isn't allowed to select the fake database anyways
//			DatabaseProvider.setInstance(new FakeDatabase());
		if (which == 1) {
			DatabaseProvider.setInstance(new DerbyDatabase());
		} else {
			throw new IllegalArgumentException("Invalid choice: " + which);
		}
	}
}
