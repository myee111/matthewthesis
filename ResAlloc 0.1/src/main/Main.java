package main;

import resallocDB.DBContractTbl;
import resallocDB.DBFundTbl;
import resallocDB.DBOwnerRes;
import agents.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		/**
		 * Generate new contracts.  PAgent(producerID,noOfContracts).  The producer should always have an ID
		 * of 0. 
		 */
//		new PAgent(0,50).start();
		/**
		 * The agents execute in lockstep.
		 */
		System.out.println("<-Simulation started.->");
		for (int i = 0; i<1; i++){
			System.out.println("<-1->");
			Thread t1 = new RationalActor(1);
			t1.start();
			t1.join();
			System.out.println("<-2->");
			Thread t2 = new RationalActor(2);
			t2.start();
			t2.join();
		}
		
		System.out.println("<-Simulation ended.->");
		
		//Housekeeping.
		
		DBContractTbl 	D1 = new DBContractTbl();
		DBFundTbl 		F1 = new DBFundTbl(); 
		DBOwnerRes		O1 = new DBOwnerRes();
		
		System.out.println();
		System.out.println("Agent,ContractResources,Resources,Funds ");
		System.out.println("1,"+D1.retTotalRes(1)+","+O1.retrieveAmountfromORtbl(1)+","+F1.retrieveAmountfromFundstbl(1));
		System.out.println("2,"+D1.retTotalRes(2)+","+O1.retrieveAmountfromORtbl(2)+","+F1.retrieveAmountfromFundstbl(2));
	}
	public void periodic(){
		
	}
}
