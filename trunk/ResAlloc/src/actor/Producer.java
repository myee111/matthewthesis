package actor;

import java.sql.SQLException;
import contract.ContractDB;

public class Producer extends Customer {
	private ContractDB productContract = new ContractDB();	//Already declared in Seller class.
	
	public Producer(int customerID){
		setCustomerNumber(customerID);		
	}
	public void produceContract(int price, int time, boolean delivery,
			int resources) throws Exception {
		productContract.setPrice(price);
		productContract.setDuration(time);
		productContract.createContractID();
		productContract.setDelivery(delivery);
		productContract.setResources(resources);
		productContract.setOwnership(getCustomerNumber());
		productContract.setSalesStatus(true);
		productContract.commitRecord();
		return;
	}
	public void displayContract(String contractID) throws SQLException{
		productContract.retrieveRecord(contractID);
		return;
	}
	public void deleteContract(){
		return;
	}
}
