package myDisplay;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;

public class MyDisplay {
		
	public void myDisplay() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(100,100);
		shell.open();
	
		Text text1 = new Text(shell, SWT.BORDER);
		text1.setText("Hello world");
		text1.setBounds(10,10,200,20);
		text1.setTextLimit(30);
		
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		return;
	}
}
