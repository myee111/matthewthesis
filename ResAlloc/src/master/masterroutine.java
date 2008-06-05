package master;
import actor.Producer;

public class masterroutine {
	
	public static void main(String[] args) throws Exception {
		int producerID	= 6330;				//unique Producer ID number
		int price		= 100000;			//cost of the contract
		int time		= 12;				//duration of the contract
		String filename = "new-data.txt";	//filename of the data file
		
		Producer Matthew = new Producer();
		Matthew.setCustomerNumber(producerID);
		Matthew.produceResource(price,time);
		Matthew.setSellContract();
		Matthew.commitResource(filename);
	}

}
