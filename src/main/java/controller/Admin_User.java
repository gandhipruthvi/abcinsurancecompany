package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminQuery;
import dao.DB;
import dao.DBQuery;

/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Pruthvi Gandhi Student ID: N01415873 Date: 10-12-2021
*
********************************************************************************/

@WebServlet("/Admin_User")
public class Admin_User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/*Display all users*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = null;
		try {
			DBQuery dbQuery = new DBQuery(DB.getConnection());
			AdminQuery adminQuery = new AdminQuery(DB.getConnection());
			adminQuery.fetchAllUsers();
			String users = adminQuery.getUserTable();
			request.setAttribute("users", users);
			rd = request.getRequestDispatcher("./view/admin/listOfUsers.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*Search users*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String search = request.getParameter("search");
		RequestDispatcher rd = null;
		try {
			AdminQuery adminQuery = new AdminQuery(DB.getConnection());
			String users = adminQuery.getSearchedUses(search);
			request.setAttribute("users", users);
			rd = request.getRequestDispatcher("./view/admin/listOfUsers.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
