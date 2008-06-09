package actor;

import java.sql.SQLException;

public class Producer extends Customer {
	public Producer(int customerID) {
		super(customerID);
	}
	public void produceContract(int price, int time, boolean delivery,
			int resources) throws Exception {
		Contract.setPrice(price);
		Contract.setDuration(time);
		Contract.createContractID();
		Contract.setDelivery(delivery);
		Contract.setResources(resources);
		Contract.setOwnership(getCustomerNumber());
		Contract.setSaleStatus(true);
		Contract.commitRecord();
		return;
	}
	public void deleteContract(String contractID) throws SQLException{
		Contract.deleteRecord(contractID);
		return;
	}
}