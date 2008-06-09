package actor;

public class Buyer extends Customer{
	public Buyer(int customerID) {
		super(customerID);
	}

	public void buyContract(String contractID){
//		showIntent(contractID); //Maybe I need a 'transaction' class.  Something that brokers transactions.  
		return;
	}
}