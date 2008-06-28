package agents;

import actor.Producer;

/**
 * Thread object of the market participant.
 * @author Walter
 *
 */
public class PAgent extends Thread{
	int ID;
	int iter;
	public PAgent(int customerID,int iterations){
		System.out.println("Thread: "+customerID);
		ID = customerID;
		iter = iterations;
	}
	public void run(){
		for(int i=0; i<iter; i++){
			Producer P1 = new Producer(ID);
			try {
				P1.produceContract(100, 100, false, 100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}

}
