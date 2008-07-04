package agents;

import java.sql.SQLException;
import java.util.Iterator;

import resallocDB.DBContractTbl;
import resallocDB.DBOwnerRes;

/**
 * This class will simulate the consumption of resources. 
 * @author Walter
 *
 */
public class Consumption {
	int resources;
	int owner;
	private DBContractTbl D1 = new DBContractTbl();
	DBOwnerRes OR = new DBOwnerRes();
	/**
	 * Constructor class.
	 * @throws SQLException 
	 */
	public Consumption(int ownership) throws SQLException{
		owner = ownership;
		resources = OR.retrieveAmountfromORtbl(ownership);
		dischargeDue();
		modifyRes();
	}
	/**
	 * This method modifies the amount of resources owned by the agent.
	 * @throws SQLException 
	 */
	public void modifyRes() throws SQLException{
		if (OR.getResTotal() > 0){
			OR.deductRes(owner, 1);
		} else {
			System.out.println("Out of resources and contracts!");
		}
		
	}
	/**
	 * Discharge contracts that are due.  Add those resources to the running resource count.
	 * A contract is due to be discharged when the current_time-duration = commencedate.
	 * @throws SQLException 
	 */
	public void dischargeDue() throws SQLException{
		D1.retAllDisch(owner);
		Iterator<String> i = D1.disch.listIterator();
		while (i.hasNext()){
			OR.addRes(owner, D1.retRes(i.next()));
		}
		
	}
}
