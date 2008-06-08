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
	String db					= "resallocdb";
	
	Connection con = null;
	com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
	//SQL Statements
	String rowstatementcontracttbl = "INSERT INTO contractTbl("
		+ "contractID,"
		+ "saleStatus,"
		+ "ownership,"
		+ "delivery,"
		+ "duration,"
		+ "resources,"
		+ "price,"
		+ "commencedate)"
		+ "VALUES(?,?,?,?,?,?,?,?)";
	String retrieverowcontracttbl = "SELECT * from contractTbl WHERE contractID=?";
	String drcontracttbl = "DELETE FROM contractTbl WHERE contractID=?";
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
	public void committoContracttbl(String contractID,
									boolean saleStatus,
									int ownership,
									boolean delivery,
									int duration,
									int resources,
									int price,
									long commencedate){
		try {
			PreparedStatement ps = con.prepareStatement(rowstatementcontracttbl);
			ps.setString(1,contractID);
			ps.setBoolean(2, saleStatus);
			ps.setInt(3, ownership);
			ps.setBoolean(4, delivery);
			ps.setInt(5, duration);
			ps.setInt(6, resources);
			ps.setInt(7, price);
			ps.setLong(8, commencedate);
			ps.executeUpdate();
		} catch (SQLException e) {	
		}
		return;
	}
	public void retrieveRecordfromContracttbl(String contractID) throws SQLException{
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * from contractTbl WHERE contractID='"+contractID+"'");
		while (rs.next()){
			boolean saleStatus = rs.getBoolean("saleStatus");
			int ownership = rs.getInt("ownership");
			boolean delivery = rs.getBoolean("delivery");
			int duration = rs.getInt("duration");
			int resources = rs.getInt("resources");
			int price = rs.getInt("price");
			long commencedate = rs.getLong("commencedate");
			System.out.println(saleStatus+" "+
							   ownership+" "+
							   delivery+" "+
							   duration+" "+
							   resources+" "+
							   price+" "+
							   commencedate+" ");
		}
		return;
	}
	public boolean retrieveSaleStatus(String contractID) throws SQLException{
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * from contractTbl WHERE contractID='"+contractID+"'");
		boolean delivery = rs.getBoolean("saleStatus");
		return delivery;
	}
	public int retrieveOwnership(String contractID) throws SQLException{
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * from contractTbl WHERE contractID='"+contractID+"'");
		int ownership = rs.getInt("saleStatus");
		return ownership;
	}
	public boolean retrieveDelivery(String contractID) throws SQLException{
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * from contractTbl WHERE contractID='"+contractID+"'");
		boolean delivery = rs.getBoolean("delivery");
		return delivery;
	}
	public int retrieveDuration(String contractID) throws SQLException{
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * from contractTbl WHERE contractID='"+contractID+"'");
		int duration = rs.getInt("duration");
		return duration;
	}
	public int retrieveResources(String contractID) throws SQLException{
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * from contractTbl WHERE contractID='"+contractID+"'");
		int resources = rs.getInt("resources");
		return resources;
	}
	public int retrievePrice(String contractID) throws SQLException{
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * from contractTbl WHERE contractID='"+contractID+"'");
		int price = rs.getInt("price");
		return price;
	}
	public long retrieveCommenceDate(String contractID) throws SQLException{
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * from contractTbl WHERE contractID='"+contractID+"'");
		long commenceDate = rs.getLong("commencedate");
		return commenceDate;
	}
	public void deleteRowinContracttbl(String contractID) throws SQLException{
		PreparedStatement ps = con.prepareStatement(drcontracttbl);
		ps.setString(1,contractID);
		ps.executeUpdate();
		return;
	}
}
