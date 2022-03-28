package chessgame.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chessgame.controller.LoginController;
import chessgame.model.Library;

//import chessgame.controller.*;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Library model;
	private LoginController controller;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nLoginServlet: doGet");

		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nLoginServlet: doPost");

		String errorMessage = null;
		String name         = null;
		String pw           = null;
		String pwCheck = null;
		String newName = null;
		String newPw = null;
		String submit = null;
		boolean validLogin  = false;

		// Decode form parameters and dispatch to controller
		name = req.getParameter("username");
		pw   = req.getParameter("password");
		newName = req.getParameter("usernameNew");
		newPw = req.getParameter("passwordNew");
		pwCheck = req.getParameter("passwordCheck");
		submit = req.getParameter("submit");
		
		System.out.println("   Name: <" + name + "> PW: <" + pw + ">");			
		
		if(submit.equals("Login")) {
			if (name == null || pw == null || name.equals("") || pw.equals("")) {
				errorMessage = "Please specify both user name and password";
			} else {
				model      = new Library();
				controller = new LoginController(model);
				validLogin = controller.validateCredentials(name, pw);
				System.out.println("Got to login statement");
				if (!validLogin) {
					errorMessage = "Username and/or password invalid";
				}
				else {
					req.setAttribute("username", req.getParameter("username"));
					req.setAttribute("password", req.getParameter("password"));
				}
			}
		}
		else if(submit.equals("Register")){
			if(newName == null || newPw == null || pwCheck == null || newName.equals("") || newPw.equals("") || pwCheck.equals("")) {
				errorMessage = "Please fill all fields";
			}
			else {
				model      = new Library();
				controller = new LoginController(model);
				System.out.println("Got to register statement");
				//ids don't exist yet so i don't want duplicate usernames to exist yet
				if(controller.checkUserName(newName)) {
					errorMessage = "Username already in use";
				}
				//checks to see if the two passwords match
				else if (!newPw.equals(pwCheck)){
					errorMessage = "Passwords do not match";
				}
				else {
					//controller.addUser(name, pw); uncomment this when an adduser function is added in library/logincontroller
					System.out.println("Valid login forced to true for registration");
					validLogin = true;
					req.setAttribute("username", newName);
					req.setAttribute("password", newPw);
				}
			}
		}
		
		
		
		// Add parameters as request attributes
		
		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("login",        validLogin);

		// if login is valid, start a session
		if (validLogin) {
			System.out.println("   Valid login - starting session, redirecting to /game");
			
			// store user object in session
			req.getSession().setAttribute("user", name);

			// redirect to /index page
			resp.sendRedirect(req.getContextPath() + "/index");
			
			return;
		}
			System.out.println("   Invalid login - returning to /Login");
			
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
}

