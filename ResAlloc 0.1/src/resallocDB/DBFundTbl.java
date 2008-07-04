package resallocDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBFundTbl extends DBHandler{
	int ownership;
	int amountTotal;
	String rowstatementFundstbl = "INSERT INTO funds("
		+ "ownership,"
		+ "amountTotal)"
		+ "VALUES(?,?)";
	String retrieverowfundtbl = "SELECT * from funds WHERE ownership=?";
	String drfundstbl = "DELETE FROM funds WHERE ownership=?";
	String modifyfund = "UPDATE funds SET amountTotal=? WHERE ownership=?";
	public void committoFundstbl(int ownership, int amountTotal) throws SQLException{
			super.opendbConnection();
			PreparedStatement ps = super.con.prepareStatement(rowstatementFundstbl);
			ps.setInt(1,ownership);
			ps.setInt(2,amountTotal);
			ps.executeUpdate();
			super.closedbConnection();	
			return;
	}
	public int retrieveAmountfromFundstbl(int ownership) throws SQLException{
		super.opendbConnection();
		Statement s = super.con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * from funds WHERE ownership='"+ownership+"'");
		while (rs.next()){
			amountTotal = rs.getInt("amountTotal");
		}
		super.closedbConnection();
		return amountTotal;
	}
	/**
	 * I don't know why I wrote this.  I don't think it ever gets used.
	 * @param ownership
	 * @throws SQLException
	 */
	public void deleteRecordfromContracttbl(int ownership) throws SQLException{
		super.opendbConnection();
		PreparedStatement ps = con.prepareStatement(drfundstbl);
		ps.setInt(1,ownership);
		ps.executeUpdate();
		super.closedbConnection();
		return;
	}
	/**
	 * I don't think this method is necessary either. 
	 * @param ownership
	 * @param price
	 * @throws SQLException
	 */
	public void modifyFunds(int ownership, int price) throws SQLException{
		super.opendbConnection();
		PreparedStatement ps = super.con.prepareStatement(modifyfund);
		ps.setInt(1, price);
		ps.setInt(2, ownership);
		ps.executeUpdate();
		super.closedbConnection();
		return;
	}
	/**
	 * Adds funds to a given actor.  Before this method is to be executed, retrieveAmountFromFundsTbl() 
	 * _must_ be executed first.  The retrieveAmountFromFundsTbl() populates the local variables 'ownership'
	 * and 'amounttotal'.  
	 * @param ownership The unique ID of the actor from whom funds will be deducted.
	 * @param price The amount of funds to be deducted from the actor. 
	 * @throws SQLException
	 */
	public void addFunds(int ownership, int price) throws SQLException {
		int newamt=0;
		retrieveAmountfromFundstbl(ownership);
		newamt = getAmountTotal()+price;
		super.opendbConnection();
		PreparedStatement ps = super.con.prepareStatement(modifyfund);
		ps.setInt(1, newamt);
		ps.setInt(2, ownership);
		ps.executeUpdate();
		super.closedbConnection();
		return;
	}
	/**
	 * Works the same way as addFunds except this deducts funds.  
	 * @param ownership
	 * @param price
	 * @throws SQLException
	 */
	public void deductFunds(int ownership, int price) throws SQLException {
		int newamt=0;
		retrieveAmountfromFundstbl(ownership);
		if (getAmountTotal()>price){
			newamt = getAmountTotal()-price;
			super.opendbConnection();
			PreparedStatement ps = super.con.prepareStatement(modifyfund);
			ps.setInt(1, newamt);
			ps.setInt(2, ownership);
			ps.executeUpdate();
			super.closedbConnection();
		} 
		return;
	}
	public int getOwnership() {
		return ownership;
	}
	public void setOwnership(int ownership) {
		this.ownership = ownership;
	}
	public int getAmountTotal() {
		return amountTotal;
	}
	public void setAmountTotal(int amountTotal) {
		this.amountTotal = amountTotal;
	}

}
