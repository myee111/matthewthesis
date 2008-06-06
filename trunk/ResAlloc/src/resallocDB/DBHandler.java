package resallocDB;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBHandler {
	Connection con = null;
	public void opendbConnection(Connection con) {
		String host					= "localhost";
		String user					= "root";
		String pw					= "not0racle";
		String db					= "Test";
		
		try {
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
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
	public void closedbConnection(Connection con) throws SQLException {
		con.close();
		return;
	}
}
