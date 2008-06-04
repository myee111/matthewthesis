package file;

import java.io.*;

public class FileHandler {
	FileOutputStream out;
	PrintStream p;
	
	public void openFile() {
		try {
			out = new FileOutputStream("data.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	public void closeFile() {
		return;
	}
	
	public void writeFile() {
		return;
	}

}
