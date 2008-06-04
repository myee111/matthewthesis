package actor;

import contract.Contract;

public class Producer extends Seller{
	Contract productContract = new Contract();
	int newprice;
	int duration;
	
	public void produceResource() {
		setPrice();
		setDuration();
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
	
	public void setSale() {
		
	}

}
