package actor;
/**
 * Thread object of the market participant.
 * @author Walter
 *
 */
public class PAgent extends Thread{
	int ID;
	public PAgent(int customerID){
		System.out.println("Thread: "+customerID);
		ID = customerID;
	}
	public void run(){
		for(int i=0; i<5; i++){
			Producer P1 = new Producer(ID);
			try {
				P1.produceContract(100, 100, false, 100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}

}
