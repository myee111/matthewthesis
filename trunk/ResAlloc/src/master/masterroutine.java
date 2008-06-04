package master;

import myDisplay.MyDisplay;
import contract.Contract;
import file.FileHandler;


public class masterroutine {
	
	public static void main(String[] args) {
		Contract C1 = new Contract();
		String owner = Integer.toString(C1.getOwnership());		
		System.out.println(owner);
		
		MyDisplay D1 = new MyDisplay();
		D1.myDisplay();
		FileHandler f = new FileHandler();
		f.writeFile();
	}

}
