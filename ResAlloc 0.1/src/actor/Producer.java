package actor;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import resallocDB.DBContractTbl;
/**
 * Producer class which creates or 'produces' contracts to be traded by market participants.
 * @author Walter
 *
 */
public class Producer extends Customer {
	/**
	 * Creates a new Producer object and sets the customer ID.
	 * @param customerID
	 */
	public Producer(int customerID) {
		super(customerID);
	}
	/**
	 * Produces a contract and inserts it into the database.
	 * @param price The price of the contract.
	 * @param time The time that the contract is valid.
	 * @param delivery The delivery status of the contract.
	 * @param resources The associated resources to be delivered in the contract.
	 * @throws Exception
	 */
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
	/**
	 * Deletes contract from the database.
	 * @param contractID The contract string identifier.
	 * @throws SQLException
	 */
	public void deleteContract(String contractID) throws SQLException{
		DBContractTbl db1 = new DBContractTbl();
		db1.deleteRecordfromContracttbl(contractID);
		return;
	}
	/**
	 * Generates a hash of a date given in milliseconds.
	 * @return A hash code for the time in milliseconds.
	 */
	public int ContractIDHash() {
		Calendar myDate = Calendar.getInstance();
		return myDate.hashCode();
	}
	/**
	 * Generates an MD5 hash code of the date to be used as a unique identifier of a contract.
	 * @return An MD5 hash code of the Date given in milliseconds.
	 * @throws Exception
	 */
    public String createID() throws Exception {
    	int mytimeHash = ContractIDHash();
    	String mytimeHashString = Integer.toString(mytimeHash);
       	MessageDigest m=MessageDigest.getInstance("MD5");
    	m.update(mytimeHashString.getBytes(),0,mytimeHashString.length());
    	return new BigInteger(1,m.digest()).toString(16);
    }
}