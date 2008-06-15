package actor;

import java.sql.SQLException;

import resallocDB.DBContractTbl;

public class Customer {
	private int customerNumber = 0;	
	public DBContractTbl D1 = new DBContractTbl();
	
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
}