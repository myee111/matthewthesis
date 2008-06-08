package actor;

import java.sql.SQLException;

import contract.Contract;

public class Producer extends Customer {
	private Contract productContract = new Contract();	//Already declared in Seller class.
	
	public Producer(int customerID){
		setCustomerNumber(customerID);		
	}
	public void produceContract(int price, int time, boolean delivery,
			int resources) throws Exception {
		productContract.setPrice(price);
		productContract.setDuration(time);
		productContract.setContractID();
		productContract.setDelivery(delivery);
		productContract.setResources(resources);
		productContract.setOwnership(getCustomerNumber());
		productContract.setSalesStatus(true);
		productContract.RecordContractDB();
		return;
	}
	public void displayContract(String contractID) throws SQLException{
		productContract.ReadContract(contractID);
		return;
	}
	public void deleteContract(){
		return;
	}
}
