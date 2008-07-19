package agents;

import java.sql.SQLException;
import java.util.Iterator;

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
		C1.dischargeDue();
		System.out.println("discharged contracts");
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
			if (resources<9980){//If resources are below slack, purchase up to whatever is required to be within slack
				B1.purchaseUpTo(9980-resources);
				
			} else {
				if (resources>9980){//if resources are greater than slack...
					System.out.println("contracts for sale: "+S1.D1.forSale.size());
					if (S1.D1.forSale.size()>0) {//and resource contracts are greater than zero
						Iterator<String> i = S1.D1.forSale.listIterator();
						if(i.hasNext()){//sell 1 surplus contracts
							System.out.println("forSale: "+i.next());
							S1.sell(1);
						}
					} else {
						System.out.println("No contracts to sell.");	
					}
				}
//				do nothing...conserve resources...drink beer
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
