package actor;
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
	public void commitResource(String filenamestring) {
		productContract.RecordContractFile(filenamestring);
		return;
	}
	public void setSellContract() { 				//Ask Mike or Nir about this duplication from Seller class.
		productContract.setSalesStatus(true);
		return;
	}
}
