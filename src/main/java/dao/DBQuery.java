package dao;

import java.sql.*;

import model.LoginModel;
import model.ProductModel;

public class DBQuery {
	private ResultSet results = null;
	private Connection connection;
	
	public DBQuery(Connection connection) {
		this.connection = connection;
	}

	
	public ResultSet doRead(String username) throws SQLException {
		String query = "Select * from login where username = '"+username+"'";
		PreparedStatement ps = connection.prepareStatement(query);
		results = ps.executeQuery();
		return results;
	}
	
	public void addUser(LoginModel login) throws SQLException {
		String query = "Insert into login values(?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement stmt=connection.prepareStatement(query);
		stmt.setString(1,login.getUsername());
		stmt.setString(2,login.getPassword());
		stmt.setString(3,login.getPhoneNo());
		stmt.setString(4,login.getEmail());
		stmt.setString(5,login.getFname());
		stmt.setString(6,login.getLname());
		stmt.setString(7,login.getAddress());
		stmt.setString(8,login.getRole());
		stmt.executeUpdate();
	}

//	
//	public void doDelete(int id) throws SQLException{
//		String query = "DELETE FROM friends WHERE id = ?";
//		PreparedStatement stmt = connection.prepareStatement(query);
//		stmt.setInt(1, id);
//		stmt.executeUpdate();
//	}
	

	
	

}
