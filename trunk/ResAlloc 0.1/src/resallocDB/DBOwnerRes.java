package resallocDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The purpose of this class is to get data and write data to the owner_resources table of the resalloc database.
 * @author Walter
 *
 */
public class DBOwnerRes extends DBHandler{
	int resTotal;
	String rowstatementORtbl = "INSERT INTO owner_resources("
		+ "ownership,"
		+ "owner_res)"
		+ "VALUES(?,?)";
	String modifyres = "UPDATE owner_resources SET owner_res=? WHERE ownership=?";
	/**
	 * Commits the total resources available to an actor. 
	 * @param ownership The unique ID of the owner.
	 * @param res The amount of total resources to be comitted to the owner. 
	 * @throws SQLException
	 */
	public void committoORtbl(int ownership, int res) throws SQLException{
		super.opendbConnection();
		PreparedStatement ps = super.con.prepareStatement(rowstatementORtbl);
		ps.setInt(1,ownership);
		ps.setInt(2,res);
		ps.executeUpdate();
		super.closedbConnection();	
		return;
	}
	/**
	 * Retrieves the total amount of resources owned by an actor.
	 * @param ownership The unique ID of the owner.
	 * @return The quantity of resources available to an actor.
	 * @throws SQLException
	 */
	public int retrieveAmountfromORtbl(int ownership) throws SQLException{
		super.opendbConnection();
		Statement s = super.con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * from owner_resources WHERE ownership='"+ownership+"'");
		while (rs.next()){
			resTotal = rs.getInt("owner_res");
		}
		super.closedbConnection();
		return resTotal;
	}
	/**
	 * 
	 * @param ownership
	 * @param res
	 * @throws SQLException
	 */
	public void deductRes(int ownership, int res) throws SQLException {
		int newamt=0;
		retrieveAmountfromORtbl(ownership);
		if (getResTotal()>res){
			newamt = getResTotal()-res;
			super.opendbConnection();
			PreparedStatement ps = super.con.prepareStatement(modifyres);
			ps.setInt(1, newamt);
			ps.setInt(2, ownership);
			ps.executeUpdate();
			super.closedbConnection();
		} 
		return;
	}
	public void addRes(int ownership, int res) throws SQLException {
		int newamt=0;
		retrieveAmountfromORtbl(ownership);
		newamt = getResTotal()+res;
		super.opendbConnection();
		PreparedStatement ps = super.con.prepareStatement(modifyres);
		ps.setInt(1, newamt);
		ps.setInt(2, ownership);
		ps.executeUpdate();
		super.closedbConnection();
		return;
	}
	public int getResTotal() {
		return resTotal;
	}
	public void setResTotal(int resTotal) {
		this.resTotal = resTotal;
	}
}
