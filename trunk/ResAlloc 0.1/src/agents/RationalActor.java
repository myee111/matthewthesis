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
	int eat;
	/**
	 * This is the buyer agent.  Upon instantiation, 'ID' is set to customerID, 'slack' is set, 'surplus' is set
	 * and resources is set to the amount of resources belonging to a given ID.
	 * @param customerID The ID of the buyer.
	 * @throws SQLException 
	 */
	public RationalActor(int customerID, int consumption, int slack) throws SQLException {
		System.out.println("Agent: "+customerID);
		ID = customerID;
		slackLowerBound = 0;
		surplusBound = 50;
		eat=0;
		
		eat = consumption;
		slackLowerBound = slack;
	
		Consumption C1 = new Consumption(ID);
		//deduct resources by 1.
		C1.modifyRes(eat);	
		C1.getORres();
		resources = C1.getResources();
		System.out.println(eat+" resources consumed.");
		//discharge expired contracts
		C1.dischargeDue();
		C1.getORres();
		resources = C1.getResources();
		//discharge enough contracts to bring agent's supply within slack. 
		if (resources<slackLowerBound){
			C1.dischargeNeed(slackLowerBound-resources);
			C1.getORres();
			resources = C1.getResources();
		}	
		
	}
	/**
	 * When the thread is spawned, the agent purchases resources and resources are deducted from the 
	 * agent's pool. 
	 */
	public void run(){
		Buyer B1 = new Buyer(ID);
		Seller S1 = new Seller(ID);
		
		System.out.println("resources "+resources+" slack "+slackLowerBound);
		try {
			if (resources<slackLowerBound){									//If resources are below slackLowerBound
				
				B1.purchaseUpTo(slackLowerBound-resources); 				//purchase up to whatever is required to be within slackLowerBound
			} else {
				if (resources>slackLowerBound+surplusBound){				//if resources are greater than slackLowerBound plus the upperbound
					S1.sellNeed(resources-(slackLowerBound+surplusBound));	//sell all the contracts you need to get within slackLowerBound
				} else {
					System.out.println("No contracts to sell.");	
				}
			}
			System.out.println("Nothing to do.");							//do nothing...conserve resources.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
