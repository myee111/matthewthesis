package consumption;

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
	private int resources;
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
		setResources(OR.retrieveAmountfromORtbl(ownership));
	}
	/**
	 * Updates the resources counter.
	 * @throws SQLException
	 */
	public void getORres() throws SQLException{
		setResources(OR.retrieveAmountfromORtbl(owner));
	}
	/**
	 * This method modifies the amount of resources owned by the agent.  In other words, this is the 
	 * consumption function.
	 * @param amount The quantity that is deducted from the agent's resource supply.  
	 * @throws SQLException 
	 */
	public void modifyRes(int amount) throws SQLException{
		if (OR.getResTotal() > 0){
			OR.deductRes(owner, amount);
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
		System.out.println("Discharging expired contracts.");
		while (i.hasNext()){
			contractID=i.next();
			OR.addRes(owner, D1.retRes(contractID));
			D1.setDelivered(contractID);
		}
		return;
	}
	/**
	 * Discharge contracts as needed to make up for deficit.  In other words, to discharge resource contracts to bring 
	 * supply within slack. 
	 * @param quantity The amount of total resources in order to bring supply within slack.
	 * @throws SQLException
	 */
	public void dischargeNeed(int quantity) throws SQLException{
		int resCount=0;
		String contractID;
		D1.retReadyDisch(owner);
		D1.retTotalRes(owner);
		Iterator<String> i = D1.toDisch.listIterator();
		while (i.hasNext() && resCount<=quantity) {
			contractID=i.next();
			OR.addRes(owner, D1.retRes(contractID));
			D1.setDelivered(contractID);
			resCount = resCount+D1.retRes(contractID);
			System.out.println("Contract discharged from supply.");
		}
	}
	public void setResources(int resources) {
		this.resources = resources;
	}
	public int getResources() {
		return resources;
	}
}
