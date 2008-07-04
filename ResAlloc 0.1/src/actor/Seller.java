package actor;

import java.sql.SQLException;
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
}
