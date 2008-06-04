package master;

import myDisplay.MyDisplay;
import contract.Contract;
import file.FileHandler;

public class masterroutine {
	
	public static void main(String[] args) {
		
		Contract C1 = new Contract();
//		MyDisplay D1 = new MyDisplay();
//		FileHandler F1 = new FileHandler();
		
//		String owner = Integer.toString(C1.getOwnership());		
//		System.out.println(owner);
		
//		D1.myDisplay();
		C1.RecordContract("c:\\myData.txt");
	}

}
