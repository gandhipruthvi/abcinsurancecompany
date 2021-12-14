package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminQuery;
import dao.DB;
import dao.DBQuery;
import dao.UserQuery;
import helper.Validator;
import model.Authenticator;
import model.LoginModel;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher rd = null;
		if(Validator.isValidCredentials(username, password)) { // user entered some value
			try {
				DBQuery dbQuery = new DBQuery(DB.getConnection());
				AdminQuery adminQuery = new AdminQuery(DB.getConnection());
				UserQuery userQuery = new UserQuery(DB.getConnection());
				String role = Authenticator.authenticate(dbQuery.doRead(username), username, password);
				if(!role.isEmpty()) {
					if(role.equals("admin")) {
						String report = adminQuery.getReports();
						request.setAttribute("report", report);
						rd = request.getRequestDispatcher("./view/admin/adminMainPage.jsp");
						rd.forward(request, response);
					}else {
						Cookie uNameCookie = new Cookie("username",username);
						Cookie pCookie = new Cookie("password",password);
						String allProducts = userQuery.fetchAllProducts(username);
						response.addCookie(uNameCookie);
						response.addCookie(pCookie);
						request.setAttribute("allProducts", allProducts);
						rd = request.getRequestDispatcher("./view/user/userMainPage.jsp");
						rd.forward(request, response);
					}
				}else {
					response.sendRedirect(request.getContextPath() + "/view/error.jsp");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/view/error.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println("coming here ");
			response.sendRedirect(request.getContextPath() + "/view/error.jsp"); 
		}
	}

}
