package contract;

import java.util.Calendar;
import file.FileHandler;

public class Contract {
	private String contractID;
	private boolean saleStatus;
	private int ownership;  		//Ownership is the unique ID of the owner of the contract.
	private boolean delivery;
	private int duration;
	private int resources;
	private int price;
	private long commencedate;		//Date the contract is in effect from. 
/**
 * Eventually a pricing algorithm will take care of this from
 *perspective of the producer.  The sellers may or may not use
 *some other method.  For now, initialise as 666
 */	
	public Contract() {
		saleStatus = false; //ie, the contract has not been sold.
		ownership = 0;
		delivery = false;   //ie, the resource has not be delivered and the contract has not been discharged.
		contractID = null;  //This is an initial value.  I need some sort of routine to put a real ID in.
		duration = 5;       //This is arbitrary.
		resources = 2;      //This is arbitrary.
		price = 666;
		Calendar time = Calendar.getInstance();
		commencedate = time.getTimeInMillis();
	}
	public String setContractID() throws Exception {	//Set the contract ID.
		ContractID myID = new ContractID();	
		contractID = (String) myID.createID();
		return contractID;
	}
	public void setPrice(int newprice) {	//Set the price for the contract.
		price = newprice;
		return;
	}
	public void setDuration(int newduration) {	//Set the duration of the contract before resource must be delivered.
		duration = newduration;
		return;
	}
	public void setOwnership(int ownerID) {	//Set the owner.
		ownership = ownerID;
		return;
	}
	public void setSalesStatus(boolean status) {	//Flag whether the contract is for sale.
		if (saleStatus!=status) saleStatus = status;
		return;
	}
	public void setResources(int amount) {	//Adjust the amount of resources associated with this contract.
		resources = amount;
		return;
	}
	public void setDelivery(boolean delivered) {	//Flag whether the resource has been delivered, and thus the contract is discharged.
		delivery = delivered;
		return;
	}
	public void setCommencedate(long date) {
		commencedate = date;
		return;
	}
	public String getContractID() {
		return contractID;
	}
	public int getPrice() {
		return price;
	}
	public int getDuration() {
		return duration;
	}
	public int getOwnership() {
		return ownership;
	}
	public boolean getsalesStatus() {
		return saleStatus;
	}
	public int getResources() {
		return resources;
	}
	public boolean getDelivery() {
		return delivery;
	}
	public long getCommencedate() {
		return commencedate;
	}
	/**
	 * Writes the contract attributes somewhere...
	 * @param fileName Name of the data target.
	 * @author Matthew Yee
	 */
	public void RecordContractFile(String fileName) {	//In particular, this method writes to a file.
		FileHandler F1 = new FileHandler();
		String[] contractRecord = new String[8];
		contractRecord[0] = contractID; 
		contractRecord[1] = Integer.toString(ownership); 
		contractRecord[2] = String.valueOf(delivery);
		contractRecord[3] = Integer.toString(resources);
		contractRecord[4] = String.valueOf(saleStatus);
		contractRecord[5] = Integer.toString(duration);
		contractRecord[6] = Integer.toString(price);
		contractRecord[7] = Long.toString(commencedate);
		F1.writeFile(fileName, contractRecord);
		return;
	}
	public void RecordContractDB() {
		String[] contractRecord = new String[8];
		contractRecord[0] = contractID; 
		contractRecord[1] = Integer.toString(ownership); 
		contractRecord[2] = String.valueOf(delivery);
		contractRecord[3] = Integer.toString(resources);
		contractRecord[4] = String.valueOf(saleStatus);
		contractRecord[5] = Integer.toString(duration);
		contractRecord[6] = Integer.toString(price);
		contractRecord[7] = Long.toString(commencedate);
		return;
	}
	public Object[] ReadContract(String contractID) {
		Object[] contractFromDB = new Object[7];
		return contractFromDB;
	}
}
