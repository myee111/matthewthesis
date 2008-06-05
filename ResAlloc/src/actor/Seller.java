package actor;
import contract.Contract;

public class Seller extends Customer{
	private Contract productContract = new Contract();	//I don't think this will work.
	
	public void setSellContract(String contractID) {
		Contract saleContract = getContract(contractID);
		saleContract.setSalesStatus(true);
		return;
	}
	public void setSellContract() {
		productContract.setSalesStatus(true);
		return;
	}
	public Contract getContract(String contractID) {
		Contract myContract = new Contract();
		return myContract;
	}
}
