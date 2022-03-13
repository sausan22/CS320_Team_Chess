package chessgame.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import chessgame.controller.*;

public class RulebookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Rulebook Servlet: doGet");
		
		req.getRequestDispatcher("/_view/rulebook.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Rulebook Servlet: doPost");
		
		String submit = req.getParameter("submit");
		System.out.println("submit");
		
		if(submit.equals("Index")) {
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);

		}
		else {
			System.out.println("Invalid Link");
		}
		
	}
}

