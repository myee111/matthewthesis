package master;

import contractObject.Contract;      
import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;

public class masterroutine {
	
	public static void main(String[] args) {
		Contract newContract = new Contract();
		String owner = Integer.toString(newContract.getOwnership());		
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(100,100);
		shell.open();
		/**
		Label label1 = new Label(shell, SWT.BORDER);
		label1.setText(owner);
		label1.setSize(100,20);
		label1.setLocation(10,10);
	**/
		Text text1 = new Text(shell, SWT.BORDER);
		text1.setText("Hello world");
		text1.setBounds(10,10,200,20);
		text1.setTextLimit(30);
		
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
