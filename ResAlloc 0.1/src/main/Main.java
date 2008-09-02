package main;

import resallocDB.DBContractTbl;
import resallocDB.DBFundTbl;
import resallocDB.DBOwnerRes;
import agents.*;
import java.io.*;
import java.util.Random;
//import java.util.Random;

import consumption.Consumption;

public class Main {
	
	public static void main(String[] args) throws Exception {
		FileOutputStream out;
		PrintStream p;
		out = new FileOutputStream("c:/fadata-"+System.currentTimeMillis()+".csv");
		p = new PrintStream(out);
		DBContractTbl 	D1 = new DBContractTbl();
		DBFundTbl 		F1 = new DBFundTbl(); 
		DBOwnerRes		O1 = new DBOwnerRes();
		
		int average1=O1.retrieveAmountfromORtbl(1); //average must be a value of how many resources the agent has in its posession.
		int average2=O1.retrieveAmountfromORtbl(2);
		p.println("#Iteration,Agent,ContractResources,Resources,Funds,ContractsBought,SlackLowerBound,Average,Consumption");
		/**
		 * The agents execute in lock step.
		 * RationalActor(agent ID, consumption, slack)
		 */
		RationalActor t1 = new RationalActor(1);
		RationalActor t2 = new RationalActor(2);
//		int n=500;
		Random generator1 = new Random(50);
		Random generator2 = new Random();
		int cons1 = 0;
		int cons2 = 0;
		int slack1 = 0;
		int slack2 = 0;
		new PAgent(0,1000).start();
		System.out.println("<-Simulation started.->");
		for (int i = 0; i<200; i++){
			System.out.println("Iteration "+i);
			System.out.println("<-0->");
			Consumption C0 = new Consumption(0);
			C0.dischargeDue();
			System.out.println("<-1->");
			cons1 = (int)(50+(250*generator1.nextGaussian()));
//			average1 = ((int)stdDev.avg(average1, average1-cons1, i+1)); 
//			slack1 = (average1-((int)stdDev.sdKnuth(average1, average1-cons1)));
			t1.go(5000, 4000);						//want to keep close to average value.
			p.println(i+",1,"+D1.retTotalRes(1)+
					","+(O1.retrieveAmountfromORtbl(1)+D1.retTotalRes(1))+
					","+F1.retrieveAmountfromFundstbl(1)+
					","+t1.getBought()+
					","+slack1+
					","+average1+
					","+cons1*(-1));
			//Consume
			Consumption C1 = new Consumption(1);
			C1.getORres();
			if (C1.getResources()>0){
				C1.modifyRes(cons1);
			}
//			System.out.println("<-2->");
//			cons2 = (int)(40+(250*generator2.nextGaussian()));
////			average2 = ((int)stdDev.avg(average2, cons2, i+1));
////			slack2 = (average2-((int)stdDev.sdKnuth(average2, cons2)));
//			t2.go(5000, 4000);
//			p.println(i+",2,"+D1.retTotalRes(2)+
//					","+(O1.retrieveAmountfromORtbl(2)+D1.retTotalRes(2))+
//					","+F1.retrieveAmountfromFundstbl(2)+
//					","+t2.getBought()+
//					","+slack2+
//					","+average2+
//					","+cons2*(-1));
//			Consumption C2 = new Consumption(2);
//			C2.getORres();
//			if (C2.getResources()>0){
//				C2.modifyRes(cons2);
//			}
		}
		System.out.println("<-Simulation ended.->");
	}
	
}
