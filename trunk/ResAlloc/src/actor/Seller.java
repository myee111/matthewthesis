package actor;

import java.sql.SQLException;

public class Seller extends Customer{
	public Seller(int customerID) {
		super(customerID);
	}
	public void setSellContract(String contractID) throws SQLException {
		super.getContract(contractID);
		Contract.setSaleStatus(true);
		return;
	}
}
