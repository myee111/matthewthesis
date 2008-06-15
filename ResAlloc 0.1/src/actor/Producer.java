package actor;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import resallocDB.DBContractTbl;

public class Producer extends Customer {
	public Producer(int customerID) {
		super(customerID);
	}
	public void produceContract(int price, int time, boolean delivery,
			int resources) throws Exception {
		DBContractTbl db1 = new DBContractTbl();
		Date d = new Date();
		db1.committoContracttbl(
				createID(), 
				true, 
				super.getCustomerNumber(), 
				false, 
				time, 
				resources, 
				price, 
				d.getTime());
		return;
	}
	public void deleteContract(String contractID) throws SQLException{
		DBContractTbl db1 = new DBContractTbl();
		db1.deleteRecordfromContracttbl(contractID);
		return;
	}
	public int ContractIDHash() {
		Calendar myDate = Calendar.getInstance();
		return myDate.hashCode();
	}
	//Second, calculate the MD5 hash of the date.
    public String createID() throws Exception {
    	int mytimeHash = ContractIDHash();
    	String mytimeHashString = Integer.toString(mytimeHash);
       	MessageDigest m=MessageDigest.getInstance("MD5");
    	m.update(mytimeHashString.getBytes(),0,mytimeHashString.length());
    	return new BigInteger(1,m.digest()).toString(16);
    }
}