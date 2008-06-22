package resallocDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBContractTbl extends DBHandler{
	String contractID;
	boolean saleStatus;
	int ownership;
	boolean delivery;
	int duration;
	int resources;
	int price;
	long commencedate;
	
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
	String modifyStatus = "UPDATE contractTbl SET saleStatus=? WHERE contractID=?";
	String modifyOwnership = "UPDATE contractTbl SET ownership=? WHERE contractID=?";
	public void committoContracttbl(
			String contractID,
			boolean saleStatus,
			int ownership,
			boolean delivery,
			int duration,
			int resources,
			int price,
			long commencedate) throws SQLException{
			super.opendbConnection();
			PreparedStatement ps = super.con.prepareStatement(rowstatementcontracttbl);
			ps.setString(1,contractID);
			ps.setBoolean(2,saleStatus);
			ps.setInt(3,ownership);
			ps.setBoolean(4,delivery);
			ps.setInt(5,duration);
			ps.setInt(6,resources);
			ps.setInt(7,price);
			ps.setLong(8,commencedate);
			ps.executeUpdate();
			super.closedbConnection();	
			return;
	}
	public void retrieveRecordfromContracttbl(String contractID) throws SQLException{
		super.opendbConnection();
		Statement s = super.con.createStatement();
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
		super.closedbConnection();
		return;
	}
	public void deleteRecordfromContracttbl(String contractID) throws SQLException{
		super.opendbConnection();
		PreparedStatement ps = con.prepareStatement(drcontracttbl);
		ps.setString(1,contractID);
		ps.executeUpdate();
		super.closedbConnection();
		return;
	}
	public String getContractID() {
		return contractID;
	}
	public boolean isSaleStatus() {
		return saleStatus;
	}
	public int getOwnership() {
		return ownership;
	}
	public boolean isDelivery() {
		return delivery;
	}
	public int getDuration() {
		return duration;
	}
	public int getResources() {
		return resources;
	}
	public int getPrice() {
		return price;
	}
	public long getCommencedate() {
		return commencedate;
	}
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	public void setSaleStatus(String contractID, boolean saleStatus) throws SQLException {
		//needs to update the db
		super.opendbConnection();
		PreparedStatement ps = super.con.prepareStatement(modifyStatus);
		ps.setBoolean(1, saleStatus);
		ps.setString(2, contractID);
		ps.executeUpdate();
		super.closedbConnection();
		return;
	}
	public void setOwnership(String contractID, int ownership) throws SQLException {
		super.opendbConnection();
		PreparedStatement ps = super.con.prepareStatement(modifyOwnership);
		ps.setInt(1, ownership);
		ps.setString(2, contractID);
		ps.executeUpdate();
		super.closedbConnection();
		return;
	}
	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setResources(int resources) {
		this.resources = resources;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setCommencedate(long commencedate) {
		this.commencedate = commencedate;
	}

}
