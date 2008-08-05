package agents;

import java.sql.SQLException;
import consumption.Consumption;
import actor.Seller;
import actor.Buyer;

/**
 * A resource trading agent.
 * @author Walter
 *
 */
public class RationalActor extends Thread {
	int ID;
	int slackLowerBound;
	int surplusBound;
	int resources;
	int eat = 0;
	int bought;
		
	/**
	 * This is the buyer agent.  Upon instantiation, 'ID' is set to customerID, 'slack' is set, 'surplus' is set
	 * and resources is set to the amount of resources belonging to a given ID.
	 * @param customerID The ID of the buyer.
	 * @throws SQLException 
	 */
	public RationalActor(int customerID) throws SQLException {
		System.out.println("Agent: "+customerID);
		ID = customerID;
		slackLowerBound = 0;
		surplusBound = 50;
	}
	/**
	 * When the thread is spawned, the agent purchases resources and resources are deducted from the 
	 * agent's pool. 
	 * @throws SQLException 
	 */
	public void go(int ave, int slackL) throws SQLException{
		Buyer B1 = new Buyer(ID);
		Seller S1 = new Seller(ID);
		
		slackLowerBound = slackL;
		System.out.println("resources "+resources+" slack "+slackLowerBound);
		
//		eat = cons;

		Consumption C1 = new Consumption(ID);
		
		resources = C1.getResources();
//		System.out.println(eat+" resources consumed.");
		//discharge expired contracts
		C1.dischargeDue();
		C1.getORres();
		resources = C1.getResources();
		//discharge enough contracts to bring agent's supply within slack. 
		if (resources<slackLowerBound){
			C1.dischargeNeed(ave); //there's a problem here.  Want to keep close to average.  
			C1.getORres();
			resources = C1.getResources();
		}	
		
		try {
			if (resources<slackLowerBound){									//If resources are below slackLowerBound
				B1.purchaseUpTo(slackLowerBound-resources); 				//purchase up to whatever is required to be within slackLowerBound
			} else {
				if (resources>(2*(ave-slackLowerBound))){				//if resources are greater than slackLowerBound plus the upperbound
					S1.sellNeed(resources-(2*(ave-slackLowerBound)));	//sell all the contracts you need to get within slackLowerBound
				} else {
					System.out.println("No contracts to sell.");	
				}
			}
			System.out.println("Nothing to do.");							//do nothing...conserve resources.
		} catch (Exception e) {
			e.printStackTrace();
		}
		bought = B1.getBought();
//		//deduct resources.
//		C1.getORres();
//		if (C1.getResources()>0){
//			C1.modifyRes(eat);	
//			C1.getORres();
//		}
	}
	public int getBought() {
		return bought;
	}
	public void setBought(int bought) {
		this.bought = bought;
	}
}
