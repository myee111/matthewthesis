package main;

import actor.Producer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		int producerID		= 6330;				//unique Producer ID number
		int price			= 100000;			//cost of the contract
		int time			= 12;				//duration of the contract
		boolean delivery	= false;			//status of delivery of resource
		int resources		= 1000;				//amount of resources to be assigned to contract
				
		Producer Matthew = new Producer(producerID);
		Producer George = new Producer(54321);
		
		Matthew.produceContract(price,time,delivery,resources);
		George.produceContract(13123,99,true,777777);
		George.displayContract("4426415922b47017e67d3889a0e48939");
		Matthew.deleteContract("af94fddb5903794ae5c691ebf67b2b28");
	}
}
