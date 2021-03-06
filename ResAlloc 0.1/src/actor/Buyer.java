package actor;

import java.sql.SQLException;
import java.util.*;
/**
 * The Buyer class contains methods that allow a customer to purchase a contract.
 * @author Walter
 *
 */
public class Buyer extends Customer{
//	List forSale;
	/**
	 * Passes customerID to superclass.
	 * @param customerID
	 */
	int bought=0;
	
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
		super.F1.retrieveAmountfromFundstbl(super.getCustomerNumber());
		if (super.F1.getAmountTotal() >= super.D1.getPrice() && super.D1.isDBSaleStatus(contractID)){
			super.F1.deductFunds(super.getCustomerNumber(), super.D1.getPrice());
			super.F1.addFunds(super.D1.getOwnership(), super.D1.getPrice());    
			super.D1.setOwnership(contractID, super.getCustomerNumber());
			super.D1.setSaleStatus(contractID, false);
		} else {
			System.out.println("Not enough funds or contract not for sale.");
		}
		return;
	}
	/**
	 * Searches the database for contract with saleStatus flag set to 'yes'.  
	 * The forSale list within (this) object contains a list of all the contracts that 
	 * are for sale.  The retrieveForSalefromContracttbl method should return results
	 * that cost less than the total funds the customer has in his account. 
	 * @param numbertobuy The number of contracts that the customer wishes to buy.
	 * @throws SQLException 
	 */
	public void purchase(int numbertobuy) throws SQLException{
		int a = super.F1.retrieveAmountfromFundstbl(super.getCustomerNumber());
//		System.out.println("Funds: "+a);
		super.D1.retrieveForSalefromContracttbl(a);
		Iterator<String> i = super.D1.forSale.listIterator();
		while(i.hasNext() && numbertobuy>0){
			buyContract(i.next());
			numbertobuy--;
		}
		return;
	}
	/**
	 * Searches the database for contracts with saleStatus flag set to 'yes'.  The forSale list within 
	 * this object contains a list of all the contract that are for sale.  The retrieveForSalefromContractTbl
	 * method should return results that cost less than the total funds the customer has in his account.
	 * This method will purchase as many contracts as it takes to reach the 'limit' parameter. 
	 * @param numbertobuy
	 * @throws SQLException
	 */
	public void purchaseUpTo(int limit) throws SQLException{
		int a = super.F1.retrieveAmountfromFundstbl(super.getCustomerNumber());
		int count=0;
		String contractID;
		super.D1.retrieveForSalefromContracttbl(a);
		Iterator<String> i = super.D1.forSale.listIterator();
		while(i.hasNext() && limit>=0){
			contractID = i.next();
			buyContract(contractID);
			limit = limit-super.D1.retRes(contractID);
			System.out.println("Contract bought.");
			count++;
		}
		setBought(count);
		return;
	}
	public int getBought() {
		return bought;
	}
	public void setBought(int bought) {
		this.bought = bought;
	}
}