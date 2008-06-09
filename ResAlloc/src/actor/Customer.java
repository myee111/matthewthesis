package actor;

import java.sql.SQLException;
import contract.ContractDB;

public class Customer {
	private int customerNumber = 0;	
	public ContractDB Contract = new ContractDB();
	
	public Customer(int customerID) {
		setCustomerNumber(customerID);	
	}
	public int getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(int customerID) {
		customerNumber = customerID;
		return;
	}
	public void displayContract(String contractID) throws SQLException{
		Contract.retrieveRecord(contractID);
		Contract.displayContract();
		return;
	}
	public void getContract(String contractID) throws SQLException{
		Contract.retrieveRecord(contractID);
		return;
	}
}