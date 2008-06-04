package file;

import java.io.*;

public class FileHandler {
	FileOutputStream out;
	PrintStream p;
	
	public void writeFile() {
		try {
			out = new FileOutputStream("c:\\matthewdata.txt");
			p = new PrintStream(out);
			p.println("Test");
			p.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error writing to file.");
		}
		return;
	}
	
	public void closeFile() {
		return;
	}

}
