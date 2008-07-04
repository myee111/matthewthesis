package agents;

import actor.Buyer;

/**
 * Testing a buying Agent.
 * @author Walter
 *
 */
public class BAgent extends Thread {
	int ID;
	int iter;
	/**
	 * This is the buyer agent.
	 * @param customerID The ID of the buyer.
	 * @param iterations The number of contracts desired to be purchased.
	 */
	public BAgent(int customerID, int iterations) {
		System.out.println("Thread: "+customerID);
		ID = customerID;
		iter = iterations;
	}
	/**
	 * When the thread is spawned, the agent purchases resources and resources are deducted from the 
	 * agent's pool. 
	 */
	public void run(){
		for(int i=0; i<iter; i++){
			Buyer B1 = new Buyer(ID);
//			Seller S1 = new Seller(ID);
			try {
				B1.purchase(1);
				B1.O1.deductRes(ID,1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
