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
	 * Constructor class.  Upon instantiation, the resources owned by 'ownership' are retrieved from the database.
	 * The local 'resources' variable is set to the owner's resources in the DBOwnerRes record and the 'owner' 
	 * is set to 'ownership'.
	 * @throws SQLException 
	 */
	public Consumption(int ownership) throws SQLException{
		owner = ownership;
		resources = OR.retrieveAmountfromORtbl(ownership);
//		dischargeDue();
//		modifyRes(); //deducts resources 1
	}
	/**
	 * This method modifies the amount of resources owned by the agent.  In other words, this is the 
	 * consumption function.
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
	 * A contract is due to be discharged when the current_time-duration = commencedate.  After 
	 * adding the resource to the owner's resource pool, must set delivery flag to true.
	 * @throws SQLException 
	 */
	public void dischargeDue() throws SQLException{
		String contractID;
		D1.retAllDisch(owner);
		Iterator<String> i = D1.disch.listIterator();
		while (i.hasNext()){
			contractID=i.next();
			OR.addRes(owner, D1.retRes(contractID));
			D1.setDelivered(contractID);
		}
		return;
	}
}
