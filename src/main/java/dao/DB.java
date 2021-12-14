package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	
	private static Connection connection;
	
	private DB() {
		
	}
	
	public static Connection getConnection() throws Exception {
		if(connection == null || connection.isClosed()) {
			EnvProperty env = new EnvProperty();
			Class.forName( env.props.getProperty("driver"));
			connection = DriverManager.getConnection(env.props.getProperty("server"), env.props.getProperty("username"), env.props.getProperty("password"));
		}
		return connection;
	}

}
