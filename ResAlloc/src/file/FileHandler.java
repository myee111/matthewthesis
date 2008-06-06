package file;

import java.io.*;
/**
 *Prints contract attributes to a line in a file.
 *@param fileName A filename. 
 *@param inputData The contract data.
 *@author Matthew Yee
 */
public class FileHandler {
	private FileOutputStream out;
	private PrintStream p;
	
	public void writeFile(String fileName,String[] inputData) {
		try {
			out = new FileOutputStream(fileName);
			p = new PrintStream(out);
			for (int i=0; i<inputData.length; i++) {
				p.print(inputData[i]+" ");
				System.out.print(inputData[i]+" ");
			}
			p.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error writing to file.");
		}
		return;
	}
	public void closeFile() {
		return;
	}

}
