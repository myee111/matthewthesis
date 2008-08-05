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
	long duration;
	int resources;
	int price;
	long commencedate;
	int inventory = 0;
	public List<String> forSale = new LinkedList<String>();
	public List<String> disch = new LinkedList<String>();
	public List<String> toDisch = new LinkedList<String>();
	public List<String> toSell = new LinkedList<String>();
	
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
	String drcontracttbl = "DELETE FROM contractTbl WHERE contractID=? AND delivery=0";
	String modifyStatus = "UPDATE contractTbl SET saleStatus=? WHERE contractID=? AND delivery=0";
	String modifyOwnership = "UPDATE contractTbl SET ownership=? WHERE contractID=? AND delivery=0";
	String setDelivered = "UPDATE contractTbl SET delivery=1 WHERE contractID=? AND delivery=0";
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
			long duration,
			int resources,
			int price,
			long commencedate) throws SQLException{
			super.opendbConnection();
			PreparedStatement ps = super.con.prepareStatement(rowstatementcontracttbl);
			ps.setString(1,contractID);
			ps.setBoolean(2,saleStatus);
			ps.setInt(3,ownership);
			ps.setBoolean(4,delivery);
			ps.setLong(5,duration);
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
		ResultSet rs = s.executeQuery("SELECT * from contractTbl WHERE contractID='"+contractID+"' AND delivery=0");
		while (rs.next()){
			saleStatus = rs.getBoolean("saleStatus");
			ownership = rs.getInt("ownership");
			delivery = rs.getBoolean("delivery");
			duration = rs.getLong("duration");
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
	public boolean isDBSaleStatus(String contractID) throws SQLException {
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
	public long getDuration() {
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
	public void setDuration(long duration) {
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
	 * actor.Buyer class.  The contract IDs are inserted into the public list 'forSale'. 
	 * @param fundsleft
	 * @throws SQLException
	 */
	public void retrieveForSalefromContracttbl(int fundsleft) throws SQLException{
		int total=0;
		super.opendbConnection();
		Statement s = super.con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * from contractTbl WHERE saleStatus=true AND price <="+fundsleft+" AND delivery=0");
		while (rs.next() && total<fundsleft){
			forSale.add(rs.getString("contractID"));
			total=total+rs.getInt("price");
		}
		super.closedbConnection();
		return;
	}
	/**
	 * Gets a list of all contracts that have reached their delivery date.  The contract IDs are inserted into 
	 * the public list, 'disch'
	 * @param ownership
	 * @throws SQLException
	 */
	public void retAllDisch(int ownership) throws SQLException{
		super.opendbConnection();
		Statement s = super.con.createStatement();
		Date d = new Date();
		ResultSet rs = s.executeQuery("SELECT * from contractTbl WHERE ownership='"+ownership+"' AND delivery=0");
		while (rs.next()){
			if (d.getTime()>(rs.getLong("duration")+rs.getLong("commencedate"))){
				disch.add(rs.getString("contractID"));
				System.out.println("Getting expired contracts.");
			}
		}
		super.closedbConnection();
		return;
	}
	/**
	 * This method will return the resources for a given contract.
	 * @param contractID
	 * @return The resrources in the contract.
	 * @throws SQLException
	 */
	public int retRes(String contractID) throws SQLException{
		int res = 0;
		super.opendbConnection();
		Statement s = super.con.createStatement();
		ResultSet rs = s.executeQuery("SELECT resources FROM contractTbl WHERE contractID='"+contractID+"'");
		while (rs.next()){
			res = rs.getInt("resources");
		}
		super.closedbConnection();
		return res;
	}
	/**
	 * Calculates a running total of all resources belonging to a specified owner.
	 * Actually I don't think I need this method.  Wait yes I do.  For testing.  
	 * @param owner
	 * @return The total resources from the contracts owned by the owner.
	 * @throws SQLException
	 */
	public int retTotalRes(int owner) throws SQLException{
		int rcpa=0; //rcpa = resource contracts per agent
		super.opendbConnection();
		Statement s = super.con.createStatement();
		ResultSet rs = s.executeQuery("SELECT resources FROM contractTbl WHERE ownership='"+owner+"' AND delivery=0");
		while (rs.next()){
			rcpa = rcpa+rs.getInt("resources");
		}
		super.closedbConnection();
		return rcpa;
	}
	/**
	 * Sets a contract's delivery status to true to denote that it has been discharged.  Unlike
	 * setDelivery, this method directly modifies the delivery field in the contract record within the 
	 * database.
	 * @param owner
	 * @throws SQLException
	 */
	public void setDelivered(String contractID) throws SQLException {
		//needs to update the db
		super.opendbConnection();
		PreparedStatement ps = super.con.prepareStatement(setDelivered);
		ps.setString(1, contractID);
		ps.executeUpdate();
		System.out.println("Setting contract delivered.");
		super.closedbConnection();
		return;
	}
	/**
	 * Returns a list of all valid contracts owned by 'ownership'.  
	 * @param ownership
	 * @throws SQLException
	 */
	public void retReadyDisch(int ownership) throws SQLException{
		super.opendbConnection();
		Statement s = super.con.createStatement();
		Date d = new Date();
		ResultSet rs = s.executeQuery("SELECT * from contractTbl WHERE ownership='"+ownership+"' AND delivery=0");
		while (rs.next()){
			if (d.getTime()<(rs.getLong("duration")+rs.getLong("commencedate"))){
				toDisch.add(rs.getString("contractID"));
			}
		}
		super.closedbConnection();
		return;
	}
	/**
	 * Pretty much the same method as retReadyDisch() except the SQL query includes the 
	 * parameter for contracts that are not for sale. 
	 * @param ownership
	 * @throws SQLException
	 */
	public void retReadyToSell(int ownership) throws SQLException{
		this.inventory = 0;
		super.opendbConnection();
		Statement s = super.con.createStatement();
		Date d = new Date();
		ResultSet rs = s.executeQuery("SELECT * from contractTbl WHERE ownership='"+ownership+"' AND delivery=0 AND saleStatus=0");
		while (rs.next()){
			if (d.getTime()<(rs.getLong("duration")+rs.getLong("commencedate"))){
				toSell.add(rs.getString("contractID"));
//				System.out.println("Contract can be sold: "+rs.getString("contractID"));
				this.inventory++; 
			}
		}
		super.closedbConnection();
		return;
	}
	public int retInv(){
		return this.inventory;
	}
}
