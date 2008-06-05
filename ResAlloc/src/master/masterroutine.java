package master;
import contract.Contract;

public class masterroutine {
	
	public static void main(String[] args) throws Exception {
		
		Contract C1 = new Contract();
		C1.setContractID();
		C1.RecordContractFile("c:\\myData.txt");
			
	}

}
