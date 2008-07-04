package agents;

import java.sql.SQLException;
import actor.Seller;
import actor.Buyer;

/**
 * A resource trading agent.
 * @author Walter
 *
 */
public class RationalActor extends Thread {
	int ID;
	int slack;
	int surplus;
	int resources;
	/**
	 * This is the buyer agent.  Upon instantiation, 'ID' is set to customerID, 'slack' is set, 'surplus' is set
	 * and resources is set to the amount of resources belonging to a given ID.
	 * @param customerID The ID of the buyer.
	 * @throws SQLException 
	 */
	public RationalActor(int customerID) throws SQLException {
		System.out.println("Thread: "+customerID);
		ID = customerID;
		slack = 500;
		surplus = 500;
		Consumption C1 = new Consumption(ID);
		resources = C1.resources;
	}
	/**
	 * When the thread is spawned, the agent purchases resources and resources are deducted from the 
	 * agent's pool. 
	 */
	public void run(){
		Buyer B1 = new Buyer(ID);
		Seller S1 = new Seller(ID);
		System.out.println("Resources for agent "+ID+" = "+resources);
		try {
			if (resources<resources-slack){
				B1.purchase(1);
			} else {
				if (resources>surplus){
					S1.sell(1);
				}
//				do nothing...conserve resources...drink beer
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
