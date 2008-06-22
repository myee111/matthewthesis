package actor;

import java.sql.SQLException;
import resallocDB.DBFundTbl;
/**
 * The Buyer class contains methods that allow a customer to purchase a contract.
 * @author Walter
 *
 */
public class Buyer extends Customer{
	/**
	 * Passes customerID to superclass.
	 * @param customerID
	 */
	public Buyer(int customerID) {
		super(customerID);
	}
	/**
	 * Performs a 'buy' operation on a contract with 'contractID'.  This method retrieves the contract's attributes.
	 * Compares the funds available with the price of the contract.  If there are enough funds to buy the contract,
	 * the funds are transferred to the seller's account.  The ownership of the contract is set to the buyer's ID. 
	 * The salestatus of the contract is set to false.
	 * @param contractID The unique string identifier of the contract.
	 * @throws SQLException
	 */
	public void buyContract(String contractID) throws SQLException{  
		super.D1.retrieveRecordfromContracttbl(contractID);
		DBFundTbl F1 = new DBFundTbl();
		F1.retrieveAmountfromFundstbl(super.getCustomerNumber());
		if (F1.getAmountTotal() >= super.D1.getPrice()){
			F1.deductFunds(super.getCustomerNumber(), super.D1.getPrice());
			F1.addFunds(super.D1.getOwnership(), super.D1.getPrice());
			D1.setOwnership(contractID, super.getCustomerNumber());
			D1.setSaleStatus(contractID, false);
			System.out.println("Contract "+contractID+" bought.");
		} else {
			System.out.println("Not enough funds.");
		}
		return;
	}
}