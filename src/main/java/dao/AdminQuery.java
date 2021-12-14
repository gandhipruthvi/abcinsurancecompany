package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ProductModel;

public class AdminQuery {
	private static ResultSet results = null;
	private Connection connection;

	public AdminQuery(Connection connection) {
		this.connection = connection;
	}

	public void fetchAllUsers() throws SQLException {
		String query = "Select * from login where role!='admin'";
		PreparedStatement ps = connection.prepareStatement(query);
		results = ps.executeQuery();
	}

	public String getUserTable() throws SQLException {
		String table = "";
		table += "<table border=1><tr><th>Username</th><th>PhoneNo</th><th>Email</th><th>FirstName</th><th>LastName</th><th>Address</th><th>Role</th></tr>";

		while (results.next()) {
			table += "<tr>";
			table += "<td>";
			table += results.getString(1);
			table += "</td>";
			table += "<td>";
			table += results.getString(3);
			table += "</td>";
			table += "<td>";
			table += results.getString(4);
			table += "</td>";
			table += "<td>";
			table += results.getString(5);
			table += "</td>";
			table += "<td>";
			table += results.getString(6);
			table += "</td>";
			table += "<td>";
			table += results.getString(7);
			table += "</td>";
			table += "<td>";
			table += results.getString(8);
			table += "</td>";
			table += "<td><a href='./Admin_User_Product?username=" + results.getString(1) + "'>Registered Product</a></td>";
			table += "</tr>";
		}

		table += "</table>";
		return table;
	}

	public String getAllProducts() throws SQLException {
		String query = "Select * from product";
		PreparedStatement ps = connection.prepareStatement(query);
		results = ps.executeQuery();
		String table = "";
		table += "<table border=1><tr><th>Name</th><th>Model</th><th>Year Manufactured</th></tr>";

		while (results.next()) {
			table += "<tr>";
			table += "<td>";
			table += results.getString(2);
			table += "</td>";
			table += "<td>";
			table += results.getString(3);
			table += "</td>";
			table += "<td>";
			table += results.getString(4);
			table += "</td>";
			table += "<td><a href=Admin_Product_Add?id=" + results.getInt(1) + ">Update</a></td>";
			table += "<td><a href=Admin_Product_Delete?id=" + results.getInt(1) + ">Delete</a></td>";
			table += "</tr>";
		}

		table += "</table>";
		return table;
	}

	public boolean deleteProduct(int id) {
		try {
			String query = "DELETE FROM product WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void addProduct(ProductModel product) throws SQLException {
		String query = "Insert into product(name, model, yearManufactured) values(?, ?, ?);";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, product.getName());
		stmt.setString(2, product.getModel());
		stmt.setString(3, product.getYear());
		stmt.executeUpdate();
	}

	public ProductModel getProduct(int id) throws SQLException {
		ProductModel p = new ProductModel();
		String query = "Select * from product where id=?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		results = ps.executeQuery();
		if (results.next()) {
			p.setId(results.getInt(1));
			p.setName(results.getString(2));
			p.setModel(results.getString(3));
			p.setYear(results.getString(4));
		}
		return p;
	}

	public void updateProduct(ProductModel product) throws SQLException {
		String query = "Update product set name=?, model=?, yearManufactured=? where id=?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, product.getName());
		stmt.setString(2, product.getModel());
		stmt.setString(3, product.getYear());
		stmt.setInt(4, product.getId());
		stmt.executeUpdate();
	}

	public String getSearchedProducts(String search) throws SQLException {
		String query = "Select * from product where name like ?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, search+"%");
		results = ps.executeQuery();
		String table = "";
		table += "<table border=1><tr><th>Name</th><th>Model</th><th>Year Manufactured</th></tr>";

		while (results.next()) {
			table += "<tr>";
			table += "<td>";
			table += results.getString(2);
			table += "</td>";
			table += "<td>";
			table += results.getString(3);
			table += "</td>";
			table += "<td>";
			table += results.getString(4);
			table += "</td>";
			table += "<td><a href=Admin_Product_Update?id=" + results.getInt(1) + ">Update</a></td>";
			table += "<td><a href=Admin_Product_Delete?id=" + results.getInt(1) + ">Delete</a></td>";
			table += "</tr>";
		}
		table += "</table>";
		return table;
	}

	public String getSearchedUses(String search) throws SQLException {
		String query = "Select * from login where role!='admin' and username like ?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, search+"%");
		results = ps.executeQuery();

		String table = "";
		table += "<table border=1><tr><th>Username</th><th>PhoneNo</th><th>Email</th><th>FirstName</th><th>LastName</th><th>Address</th><th>Role</th></tr>";

		while (results.next()) {
			table += "<tr>";
			table += "<td>";
			table += results.getString(1);
			table += "</td>";
			table += "<td>";
			table += results.getString(3);
			table += "</td>";
			table += "<td>";
			table += results.getString(4);
			table += "</td>";
			table += "<td>";
			table += results.getString(5);
			table += "</td>";
			table += "<td>";
			table += results.getString(6);
			table += "</td>";
			table += "<td>";
			table += results.getString(7);
			table += "</td>";
			table += "<td>";
			table += results.getString(8);
			table += "</td>";
			table += "<td><a href='./Admin_User_Product?username=" + results.getString(1)
					+ "'>Registered Product</a></td>";
			table += "</tr>";
		}

		table += "</table>";
		return table;
	}

	public String getRegisteredProducts(String username) throws SQLException {
		String query = "Select p.name, p.model, p.yearManufactured, r.username, r.serialNo, r.purchaseDate from productpurchase r, product p where p.id = r.productId and r.username like ?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, username+"%");
		results = ps.executeQuery();
		String table = "";
		table += "<table border=1><tr><th>Product Name</th><th>Product Model</th><th>Year Manufactured</th><th>Username</th><th>Serial Number</th><th>Purchase Date</th></tr>";

		while (results.next()) {
			table += "<tr>";
			table += "<td>";
			table += results.getString(1);
			table += "</td>";
			table += "<td>";
			table += results.getString(2);
			table += "</td>";
			table += "<td>";
			table += results.getString(3);
			table += "</td>";
			table += "<td>";
			table += results.getString(4);
			table += "</td>";
			table += "<td>";
			table += results.getString(5);
			table += "</td>";
			table += "<td>";
			table += results.getString(6);
			table += "</td>";
			table += "</tr>";
		}
		table += "</table>";
		return table;
	}
	
	public String getAllClaims() throws SQLException {
		String query = "Select c.id, c.dateOfClaim, c.issueDesc, c.action, p.name, p.model, p.yearManufactured, r.username, r.serialNo, r.purchaseDate, c.status from product_claim c, productpurchase r, product p where p.id = r.productId and c.purchasedId = r.id order by c.dateOfClaim";
		PreparedStatement ps = connection.prepareStatement(query);
		results = ps.executeQuery();
		String table = "";
		table += "<table border=1><tr><th>Date Of Claim</th><th>Issue Description</th><th>Action</th><th>Name</th><th>Model</th><th>Year Manufactured</th><th>Username</th><th>Serial Number</th><th>Purchase Date</th><th>Status</th></tr>";

		while (results.next()) {
			table += "<tr>";
			table += "<td>";
			table += results.getString(2);
			table += "</td>";
			table += "<td>";
			table += results.getString(3);
			table += "</td>";
			table += "<td>";
			table += results.getString(4);
			table += "</td>";
			table += "<td>";
			table += results.getString(5);
			table += "</td>";
			table += "<td>";
			table += results.getString(6);
			table += "</td>";
			table += "<td>";
			table += results.getString(7);
			table += "</td>";
			table += "<td>";
			table += results.getString(8);
			table += "</td>";
			table += "<td>";
			table += results.getString(9);
			table += "</td>";
			table += "<td>";
			table += results.getString(10);
			table += "</td>";
			table += "<td>";
			table += results.getString(11);
			table += "</td>";
			if(results.getString(11).equals("pending"))
			{
				table += "<td><a href=Admin_Claims?id=" + results.getInt(1) + "&status=approved>Approve</a></td>";
				table += "<td><a href=Admin_Claims?id=" + results.getInt(1) + "&status=rejected>Reject</a></td>";
			}
			table += "</tr>";
		}
		table += "</table>";
		return table;
	} 
	
	public void updateClaimStatus(int id, String status) throws SQLException {
		String query = "Update product_claim set status=? where id=?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, status);
		stmt.setInt(2, id);
		stmt.executeUpdate();
	}
	
	public String getReports() throws SQLException {
		String report = "";
		String query = "Select count(*) from login where role!='admin'";
		PreparedStatement ps = connection.prepareStatement(query);
		results = ps.executeQuery();
		results.next();
		report += "Total Number of Users: " + results.getInt(1);
		
		query = "Select count(*) from product";
		ps = connection.prepareStatement(query);
		results = ps.executeQuery();
		results.next();
		report += "<br/>Total Number of Prducts: " + results.getInt(1);
		
		query = "Select count(*) from productpurchase";
		ps = connection.prepareStatement(query);
		results = ps.executeQuery();
		results.next();
		report += "<br/>Total Number of Products Purchased: " + results.getInt(1);
		
		query = "Select count(*) from product_claim where status = 'pending'";
		ps = connection.prepareStatement(query);
		results = ps.executeQuery();
		results.next();
		report += "<br/>Total Number of Claims Pending: " + results.getInt(1);
		
		query = "Select count(*) from product_claim";
		ps = connection.prepareStatement(query);
		results = ps.executeQuery();
		results.next();
		report += "<br/>Total Number of Claims: " + results.getInt(1);
		return report;
	}
}
