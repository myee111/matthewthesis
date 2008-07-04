package resallocDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
/**
 * A class for handling all operations to do with the Contract Table in the resalloc DB.
 * @author Walter
 *
 */
public class DBContractTbl extends DBHandler{
	String contractID;
	boolean saleStatus;
	int ownership;
	boolean delivery;
	int duration;
	int resources;
	int price;
	long commencedate;
	public List<String> forSale = new LinkedList<String>();
	public List<String> disch = new LinkedList<String>();
	public List<String> rcpa = new LinkedList<String>();	//rcpa = resource contracts per agent
	
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
	/**
	 * Puts a new contract record into the database. 
	 * @param contractID Unique ID of the contract.
	 * @param saleStatus True denotes that the contract is for sale.
	 * @param ownership ID of the owner of the contract.
	 * @param delivery True denotes that the contract has been delivered and resources have been used.
	 * @param duration The lifetime of the contract.
	 * @param resources The resources associated with the contract.
	 * @param price The price of the contract.
	 * @param commencedate The date the contract was created.
	 * @throws SQLException
	 */
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
	/**
	 * Gets the contract record from the database and sets all the appropriate attributes within
	 * the class to the attributes in the contract.
	 * @param contractID
	 * @throws SQLException
	 */
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
	public synchronized boolean isDBSaleStatus(String contractID) throws SQLException {
		super.opendbConnection();
		Statement s = super.con.createStatement();
		ResultSet rs = s.executeQuery("SELECT saleStatus FROM contractTbl WHERE contractID='"+contractID+"'");
		while (rs.next()){
			saleStatus = rs.getBoolean("saleStatus");
		}
		super.closedbConnection();
		return saleStatus;
	}
	public boolean isSaleStatus(){
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
	/**
	 * Gets a list of all the contracts that are for sale and cost less than or equal to the value
	 * of 'fundsleft'.  This method is used to build a list of contracts for an agent to buy in the 
	 * actor.Buyer class.
	 * @param fundsleft
	 * @throws SQLException
	 */
	public void retrieveForSalefromContracttbl(int fundsleft) throws SQLException{
		super.opendbConnection();
		Statement s = super.con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * from contractTbl WHERE saleStatus=true AND price <="+fundsleft);
		while (rs.next()){
			forSale.add(rs.getString("contractID"));
		}
		super.closedbConnection();
		return;
	}
	public void retAllDisch(int ownership) throws SQLException{
		super.opendbConnection();
		Statement s = super.con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * from contractTbl WHERE ownership='"+ownership+"'");
		while (rs.next()){
			if (System.currentTimeMillis()-rs.getInt("duration")<=rs.getLong("commencedate")){
				disch.add(rs.getString("contractID"));
			}
		}
		super.closedbConnection();
		return;
	}
	public int retRes(String contractID) throws SQLException{
		int res = 0;
		super.opendbConnection();
		Statement s = super.con.createStatement();
		ResultSet rs = s.executeQuery("SELECT resources FROM contractTbl WHERE contractID='"+contractID+"'");
		while (rs.next()){
			res = rs.getInt("resources");
		}
		return res;
	}
}
