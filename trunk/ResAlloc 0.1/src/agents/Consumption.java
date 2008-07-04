package agents;

import java.sql.SQLException;
import java.util.Iterator;

import resallocDB.DBContractTbl;

/**
 * This class will simulate the consumption of resources. 
 * @author Walter
 *
 */
public class Consumption {
	int resources;
	int owner;
	private DBContractTbl D1 = new DBContractTbl();
	/**
	 * Constructor class.
	 * @param sres Starting amount of resources.
	 * @throws SQLException 
	 */
	public Consumption(int sres,int ownership) throws SQLException{
		resources = sres; //Eventually this value will have to sit in a db table somewhere
		owner = ownership;
		dischargeDue();
		modifyRes();
	}
	/**
	 * This method modifies the amount of resources owned by the agent.
	 */
	public void modifyRes(){
		if (resources > 0){
			resources--;
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
			resources = resources+D1.retRes(i.next());
		}
		
	}
}
