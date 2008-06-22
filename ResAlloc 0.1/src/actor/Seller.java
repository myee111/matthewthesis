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
	public void setSellContract(String contractID) throws SQLException {
		super.D1.setSaleStatus(contractID, true);
		return;
	}
}
