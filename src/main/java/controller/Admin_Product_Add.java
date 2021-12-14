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
import helper.Validator;
import model.ProductModel;

/*********************************************************************************
* ITE5332 : Project
* I declare that this assignment is my own work in accordance with Humber Academic Policy.
* No part of this assignment has been copied manually or electronically from any other source
* (including web sites) or distributed to other students.
*
* Name: Pruthvi Gandhi Student ID: N01415873 Date: 12-12-2021
*
********************************************************************************/

@WebServlet("/Admin_Product_Add")
public class Admin_Product_Add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Admin_Product_Add() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* Displaying product add and update form */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * Displaying add product form empty values are passed because same form is used
		 * for update, so when updating the product, old values will be passed and
		 * displayed in the fields
		 */
		if (request.getParameterMap().containsKey("id")) {	//if id is passed then product details are fetched from the database and displayed in the form
			String id = request.getParameter("id");

			try {
				AdminQuery adminQuery = new AdminQuery(DB.getConnection());
				ProductModel p = adminQuery.getProduct(Integer.parseInt(id));
				request.setAttribute("name", p.getName());
				request.setAttribute("year", p.getYear());
				request.setAttribute("model", p.getModel());
				request.setAttribute("id", String.valueOf(p.getId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {											//else empty values are displayed to the form
			request.setAttribute("name", "");
			request.setAttribute("year", "");
			request.setAttribute("model", "");
			request.setAttribute("id", "");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("./view/admin/addProduct.jsp");
		dispatcher.forward(request, response);
	}

	/* Product add and update */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String model = request.getParameter("model");
		String year = request.getParameter("year");

		RequestDispatcher rd = null;

		if (Validator.isValidProduct(name, model, year)) {
			try {
				AdminQuery adminQuery = new AdminQuery(DB.getConnection());
				ProductModel product = new ProductModel();
				product.setName(name);
				product.setModel(model);
				product.setYear(year);
				if (id == "") { 				// if id is empty then product is added
					adminQuery.addProduct(product);
				} else { 						// if id is not empty then product is updated
					product.setId(Integer.valueOf(id));
					adminQuery.updateProduct(product);
				}
				response.sendRedirect(request.getContextPath() + "/Admin_Product");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
