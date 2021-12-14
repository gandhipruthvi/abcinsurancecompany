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

/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Pruthvi Gandhi Student ID: N01415873 Date: 12-12-2021
*
********************************************************************************/

@WebServlet("/Admin_Claims")
public class Admin_Claims extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Admin_Claims() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*if id is passed then claim status will be updated as the status passed*/
		if (request.getParameterMap().containsKey("id")) {
			String id = request.getParameter("id");
			String status = request.getParameter("status");
			RequestDispatcher rd = null;
			try {
				AdminQuery adminQuery = new AdminQuery(DB.getConnection());
				adminQuery.updateClaimStatus(Integer.valueOf(id), status);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*getting all the claims*/
		RequestDispatcher rd = null;
		try {
			AdminQuery adminQuery = new AdminQuery(DB.getConnection());
			String claims = adminQuery.getAllClaims();
			request.setAttribute("claims", claims);
			rd = request.getRequestDispatcher("./view/admin/listOfClaims.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
