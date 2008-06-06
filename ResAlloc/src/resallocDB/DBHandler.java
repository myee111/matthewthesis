package resallocDB;

import java.sql.*;
import com.mysql.jdbc.Connection;

public class DBHandler {
	/**
	 * Code stolen from http://www.herongyang.com/jdbc/MySQL-JDBC-Driver-Server-Information.html
	 */
	String host					= "localhost";
	String user					= "root";
	String pw					= "not0racle";
	String db					= "Test";
	Connection con = null;
	com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
	
	public void opendbConnection() {
		
		
		try {		
			ds.setServerName(host);
			ds.setPortNumber(3306);
			ds.setDatabaseName(db);
			ds.setUser(user);
			ds.setPassword(pw);
			
			con = (Connection) ds.getConnection();
			// Getting database info
			DatabaseMetaData meta = con.getMetaData();
			System.out.println("Server name: "+ meta.getDatabaseProductName());
			System.out.println("Server version: "+ meta.getDatabaseProductVersion());
			System.out.println("Driver version: "+ meta.getDriverVersion());
		} catch (Exception e) {
			System.err.println("Exception: "+e.getMessage());
		}
		return;
	}
	public void closedbConnection() throws SQLException {
		con.close();
		return;
	}
	public void setHost(String hostname) {
		host = hostname;
		return;
	}
	public void setUser(String username) {
		user = username;
		return;
	}
	public void setPw(String password) {
		pw = password;
		return;
	}
	public void setDb(String database) {
		db = database;
		return;
	}
}
