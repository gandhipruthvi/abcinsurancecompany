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
import helper.Validator;
import model.LoginModel;
import model.ProductModel;

/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Pruthvi Gandhi Student ID: N01415873 Date: 10-12-2021
*
********************************************************************************/

@WebServlet("/Admin_Product")
public class Admin_Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_Product() {
        super();
        // TODO Auto-generated constructor stub
    }

    /*Display all products*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = null;
		try {
			AdminQuery adminQuery = new AdminQuery(DB.getConnection());
			String products = adminQuery.getAllProducts();
			request.setAttribute("products", products);
			rd = request.getRequestDispatcher("./view/admin/products.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*Search Product*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String search = request.getParameter("search");
		RequestDispatcher rd = null;
		try {
			AdminQuery adminQuery = new AdminQuery(DB.getConnection());
			String products = adminQuery.getSearchedProducts(search);
			request.setAttribute("products", products);
			rd = request.getRequestDispatcher("./view/admin/products.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
