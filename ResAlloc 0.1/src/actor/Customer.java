package actor;

import java.sql.SQLException;
import resallocDB.DBContractTbl;
import resallocDB.DBFundTbl;
import resallocDB.DBOwnerRes;
/**
 * Customer superclass which contains DBContractTbl, DBFundTbl and customerNumber. 
 * @author Walter
 *
 */
public class Customer {
	private int customerNumber = 0;	
	public DBContractTbl D1 = new DBContractTbl();
	public DBFundTbl F1 = new DBFundTbl();
	public DBOwnerRes O1 = new DBOwnerRes();
	/**
	 * 
	 * @param customerID
	 */
	public Customer(int customerID) {
		setCustomerNumber(customerID);	
	}
	/**
	 * 
	 * @return customerNumber
	 */
	public int getCustomerNumber() {
		return customerNumber;
	}
	/**
	 * 
	 * @param customerID
	 */
	public void setCustomerNumber(int customerID) {
		customerNumber = customerID;
		return;
	}
	/**
	 * 
	 * @param contractID
	 * @throws SQLException
	 */
	public void displayContract(String contractID) throws SQLException{
		D1.retrieveRecordfromContracttbl(contractID);
		System.out.print(contractID+" ");
		System.out.print(D1.isSaleStatus()+" ");
		System.out.print(D1.getOwnership()+" ");
		System.out.print(D1.isDelivery()+" ");
		System.out.print(D1.getDuration()+" ");
		System.out.print(D1.getResources()+" ");
		System.out.print(D1.getPrice()+" ");
		System.out.println(D1.getCommencedate());
		return;
	}
	/**
	 * Displays the amount of funds belonging to a customer.
	 * @param ownership
	 * @throws SQLException
	 */
	public void displayAmount(int ownership) throws SQLException{
		System.out.println(F1.retrieveAmountfromFundstbl(ownership));
		return;
	}
}