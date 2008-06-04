package contractObject;

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
	public void setPrice() {
		//Set the price for the contract.
		return;
	}
	
	public void setDuration() {
		//Set the duration of the contract before resource must be delivered.
		return;
	}
	
	public void setOwnership() {
		//Set the owner.
		return;
	}
	
	public void setSalesStatus() {
		//Flag whether the contract is for sale.
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
}
