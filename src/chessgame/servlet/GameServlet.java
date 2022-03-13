package chessgame.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import chessgame.controller.*;

public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Game Servlet: doGet");
		
		req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Game Servlet: doPost");
		
		String submit = "" + req.getParameter("submit");
		
		System.out.println(submit);
		if(submit.equals("Rulebook")) {
			req.getRequestDispatcher("/_view/rulebook.jsp").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
		}	
	}
}

