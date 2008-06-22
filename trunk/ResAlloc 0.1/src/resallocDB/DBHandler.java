package resallocDB;

import java.sql.*;
import com.mysql.jdbc.Connection;
/**
 * 
 * @author Walter
 *
 */
public class DBHandler{
	/**
	 * Code stolen from http://www.herongyang.com/jdbc/MySQL-JDBC-Driver-Server-Information.html
	 */
	String host					= "localhost";
	String user					= "root";
	String pw					= "not0racle";
	String db					= "resallocdb";
	Connection con = null;
	com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
	String rowstatementfundtbl = "INSERT INTO funds (" 
		+ "ownership,"
		+ "amountTotal)"
		+ "VALUES(?,?)";
	public void opendbConnection() {
		try {		
			ds.setServerName(host);
			ds.setPortNumber(3306);
			ds.setDatabaseName(db);
			ds.setUser(user);
			ds.setPassword(pw);
			
			con = (Connection) ds.getConnection();
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
	public String getUser() {
		return user;
	}
	public String getHost() {
		return host;
	}
	public String getPw() {
		return pw;
	}
	public String getDb() {
		return db;
	}

}
