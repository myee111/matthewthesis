package actor;

import java.sql.SQLException;

public class Seller extends Customer{
	public Seller(int customerID) {
		super(customerID);
	}
	public void setSellContract(String contractID) throws SQLException {
		super.D1.retrieveRecordfromContracttbl(contractID);
		super.D1.setSaleStatus(true);
		return;
	}
}
