package contract;

public class Contract {
	int price;
	int duration;
	int ownership;
	boolean saleStatus;
	int resources;
	boolean delivery;
	int contractID;
	
	public Contract() {
		saleStatus = false;
		ownership = 0;
		delivery = false;
		contractID = 0;
	}
	
	public void setContractID() {
		//Set the contract ID.
		return;
	}
	public void setPrice(int newprice) {
		//Set the price for the contract.
		price = newprice;
		return;
	}
	
	public void setDuration(int newduration) {
		//Set the duration of the contract before resource must be delivered.
		duration = newduration;
		return;
	}
	
	public void setOwnership() {
		//Set the owner.
		return;
	}
	
	public void setSalesStatus(boolean status) {
		//Flag whether the contract is for sale.
		if (saleStatus!=status) saleStatus = status;
		return;
	}
	
	public void setResources() {
		//Adjust the amount of resources associated with this contract.
	}
	
	public void setDelivery() {
		//Flag whether the resource has been delivered, and thus the contract is discharged.
		return;
	}
	
	public int getContractID() {
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
	
	public void RecordContract() {
		return;
	}
}
