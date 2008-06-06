package main;

//import com.mysql.jdbc.Connection;
import actor.Producer;
import resallocDB.DBHandler;

public class Main {
	
	public static void main(String[] args) throws Exception {
		int producerID		= 6330;				//unique Producer ID number
		int price			= 100000;			//cost of the contract
		int time			= 12;				//duration of the contract
		boolean delivery	= false;			//status of delivery of resource
		int resources		= 1000;				//amount of resources to be assigned to contract
		String filename = "c:\\new-data.txt";		//filename of the data file
//		Connection con = null;
		
		DBHandler D1 = new DBHandler();
		Producer Matthew = new Producer();
		
//		con = (Connection) D1.opendbConnection(); //why do i have a cast here?
		D1.opendbConnection();
		D1.closedbConnection();
		
		Matthew.setCustomerNumber(producerID);
		Matthew.produceResource(price,time,delivery,resources);
		Matthew.setSellContract();
		Matthew.commitResource(filename);
	}

}
