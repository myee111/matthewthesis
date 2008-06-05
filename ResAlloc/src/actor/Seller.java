package actor;

import contract.Contract;

public class Seller extends Customer{
	
	public void setSellContract(String contractID) {
		Contract saleContract = getContract(contractID);
		saleContract.setSalesStatus(true);
		return;
	}
	public Contract getContract(String contractID) {
		Contract myContract = new Contract();
		return myContract;
	}

}
