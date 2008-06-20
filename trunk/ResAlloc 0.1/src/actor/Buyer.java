package actor;

import java.sql.SQLException;
import resallocDB.DBFundTbl;

public class Buyer extends Customer{
	public Buyer(int customerID) {
		super(customerID);
	}
	public void buyContract(String contractID) throws SQLException{  
		super.D1.retrieveRecordfromContracttbl(contractID);
		DBFundTbl F1 = new DBFundTbl();
		F1.retrieveAmountfromFundstbl(super.getCustomerNumber());
		if (F1.getAmountTotal() >= super.D1.getPrice()){
			F1.deductFunds(super.getCustomerNumber(), super.D1.getPrice());
			F1.addFunds(super.D1.getOwnership(), super.D1.getPrice());
			D1.setOwnership(super.getCustomerNumber());
			D1.
			System.out.println("Contract "+contractID+" bought.");
		} else {
			System.out.println("Not enough funds.");
		}
		return;
	}
}