package actor;

import java.sql.SQLException;

public class Buyer extends Customer{
	public Buyer(int customerID) {
		super(customerID);
	}
	public void buyContract(String contractID) throws SQLException{  
		super.D1.retrieveRecordfromContracttbl(contractID);
		//check buyer's funds against price.  If ok then
		//withdraw x funds from buyer's account
		//deposit x funds into seller's account
		//change ownership
		return;
	}
}