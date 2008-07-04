package main;

import agents.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		int producerID		= 0;				//unique Producer ID number
//		int iterations		= 50;
//		int price			= 100000;			//cost of the contract
//		int time			= 12;				//duration of the contract
//		boolean delivery	= false;			//status of delivery of resource
//		int resources		= 1000;				//amount of resources to be assigned to contract
			
//		Producer MatthewProduce = new Producer(producerID);
//		Producer George = new Producer(54321);
//		DBContractTbl D1 = new DBContractTbl();
//		Buyer MatthewBuy = new Buyer(producerID);
//		MatthewProduce.produceContract(price,time,delivery,resources);
//		George.produceContract(13123,99,true,777777);
//		George.displayContract("4426415922b47017e67d3889a0e48939");
//		MatthewProduce.deleteContract("af94fddb5903794ae5c691ebf67b2b28");
//		F.modifyFunds(6330, 6000000);
//		System.out.println(F.retrieveAmountfromFundstbl(6330));
//		MatthewBuy.buyContract("4426415922b47017e67d3889a0e48939");
//		MatthewProduce.displayContract("4426415922b47017e67d3889a0e48939");
//		Buyer MatthewBuy = new Buyer(6330);
//		System.out.println("Customer #: "+MatthewBuy.getCustomerNumber());
//		System.out.println("Funds: "+MatthewBuy.F1.retrieveAmountfromFundstbl(6330));
//		MatthewBuy.findContractForSale(5);
//		DBFundTbl F1 = new DBFundTbl();
//		F1.committoFundstbl(1, 100000);
//		F1.committoFundstbl(2, 100000);
		
		new PAgent(producerID,1).start();
		for (int i = 0; i<1; i++){
			Thread t1 = new RationalActor(1);
			t1.start();
			t1.join();
			Thread t2 = new RationalActor(2);
			t2.start();
			t2.join();
		}
	}
}
