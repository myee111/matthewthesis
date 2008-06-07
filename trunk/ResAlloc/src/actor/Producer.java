package actor;
import java.sql.SQLException;

import resallocDB.DBHandler;
import contract.Contract;

public class Producer extends Customer {
	private Contract productContract = new Contract();	//Already declared in Seller class.
	
	public void produceResource(int price, int time, boolean delivery,
			int resources) throws Exception {
		productContract.setPrice(price);
		productContract.setDuration(time);
		productContract.setContractID();
		productContract.setDelivery(delivery);
		productContract.setResources(resources);
		return;
	}
//	public void commitResource(String filenamestring) {
//		productContract.RecordContractFile(filenamestring);
//		return;
//	}
	public void commitResource() throws SQLException {
		DBHandler D1 = new DBHandler();
		D1.opendbConnection();
		D1.committoContracttbl(productContract.getContractID(),
							   productContract.getsalesStatus(),
							   productContract.getOwnership(),
							   productContract.getDelivery(),
							   productContract.getDuration(),
							   productContract.getResources(),
							   productContract.getPrice(),
							   productContract.getCommencedate());
		D1.closedbConnection();
		return;
	}
	public void setSellContract() {
		productContract.setSalesStatus(true);
		return;
	}
}
