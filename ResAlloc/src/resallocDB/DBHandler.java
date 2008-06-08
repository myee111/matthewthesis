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
	
	private String contractID;
	private boolean saleStatus;
	private int ownership;  		//Ownership is the unique ID of the owner of the contract.
	private boolean delivery;
	private int duration;
	private int resources;
	private int price;
	private long commencedate;		//Date the contract is in effect from. 
	
	
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
	public void committoContracttbl(){
		try {
			PreparedStatement ps = con.prepareStatement(rowstatementcontracttbl);
			ps.setString(1,contractID);
			ps.setBoolean(2, saleStatus );
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
			saleStatus = rs.getBoolean("saleStatus");
			ownership = rs.getInt("ownership");
			delivery = rs.getBoolean("delivery");
			duration = rs.getInt("duration");
			resources = rs.getInt("resources");
			price = rs.getInt("price");
			commencedate = rs.getLong("commencedate");
		}
		return;
	}
	
	public String getContractID() {
		return contractID;
	}
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	public boolean isSaleStatus() {
		return saleStatus;
	}
	public void setSaleStatus(boolean saleStatus) {
		this.saleStatus = saleStatus;
	}
	public int getOwnership() {
		return ownership;
	}
	public void setOwnership(int ownership) {
		this.ownership = ownership;
	}
	public boolean isDelivery() {
		return delivery;
	}
	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getResources() {
		return resources;
	}
	public void setResources(int resources) {
		this.resources = resources;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public long getCommencedate() {
		return commencedate;
	}
	public void setCommencedate(long commencedate) {
		this.commencedate = commencedate;
	}
}
