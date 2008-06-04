package actor;

import contract.Contract;

public class Producer {
	Contract productContract = new Contract();
	int newprice;
	int duration;
	
	public void produceResource() {
		setPrice();
		setDuration();
		//Then write the contract.
		return;
	}
	
	public void setPrice() {
		productContract.setPrice(newprice);
		return;
	}
	
	public void setDuration() {
		productContract.setDuration(duration);
		return;
	}
	
	public void setForSale() {
		productContract.setSalesStatus(true);
		return;
		
	}

}
