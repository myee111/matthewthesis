package main;

import agents.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		/**
		 * Generate new contracts.  PAgent(producerID,noOfContracts).  The producer should always have an ID
		 * of 0. 
		 */
//		new PAgent(0,9).start();
		/**
		 * The agents execute in lockstep.
		 */
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
