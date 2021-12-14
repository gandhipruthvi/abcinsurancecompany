package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DB;
import dao.DBQuery;
import helper.Validator;
import model.LoginModel;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		String phoneNo = request.getParameter("phoneNo");
		String email = request.getParameter("email");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String address = request.getParameter("address");
		
		RequestDispatcher rd = null;
		
		if(Validator.isValidUser(username, password, phoneNo, email, fname, lname, address)) {
			try {
				DBQuery dbQuery = new DBQuery(DB.getConnection());
				LoginModel login = new LoginModel();
				login.setUsername(username);
				login.setPassword(password);
				login.setEmail(email);
				login.setPhoneNo(phoneNo);
				login.setAddress(address);
				login.setFname(fname);
				login.setLname(lname);
				login.setRole("user");
				dbQuery.addUser(login);
				rd = request.getRequestDispatcher("./view/admin/userMainPage.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect(request.getContextPath() + "/view/index.jsp");
	}

}
