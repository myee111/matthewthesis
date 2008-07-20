package actor;

import java.sql.SQLException;
import java.util.Iterator;

import resallocDB.DBOwnerRes;
/**
 * A seller class for 'selling' contracts.
 * @author Walter
 *
 */
public class Seller extends Customer{
	/**
	 * Seller is constructed with the customerID.
	 * @param customerID
	 */
	public Seller(int customerID) {
		super(customerID);
	}
	/**
	 * Sells a contract.
	 * @param contracts Number of contracts to be sold.
	 */
	public void sell(int contracts) {
		for (int i=0; i<contracts; i++){
			System.out.println("A contract is sold.");
		}
		return;
	}
	/**
	 * Changes the status of the contract to be 'sold' in the contract record in the resallocDB.  
	 * @param contractID
	 * @throws SQLException
	 */
	public void setSellContract(String contractID) throws SQLException {
		super.D1.setSaleStatus(contractID, true);
		return;
	}
	/**
	 * Sells the number of contracts required to reduce the surplus within slack.
	 * @param quantity
	 * @throws SQLException
	 */
	public void sellNeed(int quantity) throws SQLException{
		int resCount=0;
		String contractID;
		DBOwnerRes OR = new DBOwnerRes();
		super.D1.retReadyToSell(super.getCustomerNumber());
		super.D1.retTotalRes(super.getCustomerNumber());
		Iterator<String> i = super.D1.toSell.listIterator();
		while (i.hasNext() && resCount<=quantity) {
			contractID=i.next();
			OR.addRes(super.getCustomerNumber(), super.D1.retRes(contractID));
			super.D1.setSaleStatus(contractID, true);
			resCount = resCount+super.D1.retRes(contractID);
			System.out.println("Contract: "+contractID+" is for sale.");
		}
	}
}
