package master;
import actor.Producer;

public class masterroutine {
	
	public void produce() {
		
	}
	
	public static void main(String[] args) throws Exception {
		int producerID		= 6330;				//unique Producer ID number
		int price			= 100000;			//cost of the contract
		int time			= 12;				//duration of the contract
		boolean delivery	= false;			//status of delivery of resource
		int resources		= 1000;				//amount of resources to be assigned to contract
		String filename = "c:\\new-data.txt";		//filename of the data file
		
		Producer Matthew = new Producer();
		Matthew.setCustomerNumber(producerID);
		Matthew.produceResource(price,time,delivery,resources);
		Matthew.setSellContract();
		Matthew.commitResource(filename);
	}

}
