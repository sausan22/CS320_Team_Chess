package chessgame.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import chessgame.controller.*;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doGet");
		
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doPost");
		
		String submit = req.getParameter("submit");
		System.out.println(submit);
		req.setAttribute("name", req.getSession().getAttribute("name"));
		
		if(submit.equals("Login/Sign Up")) {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		else if(submit.equals("New Game")) {
			req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
		}
		else if(submit.equals("Load Game")) {
			req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
		}
		else if(submit.equals("Saved Games")) {
			req.getRequestDispatcher("/_view/savedGames.jsp").forward(req, resp);
		}
		else if(submit.equals("Rulebook")) {
			req.getRequestDispatcher("/_view/rulebook.jsp").forward(req, resp);
		}
		else if(submit.equals("In Progress Games")) {
			req.getRequestDispatcher("/_view/inProgressGames.jsp").forward(req, resp);
		}
		else {
			System.out.println("Invalid Link");
		}
		
	}
}

