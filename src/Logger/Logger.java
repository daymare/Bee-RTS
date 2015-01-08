/*
 * Logger
 * 
 * Logs data into a specified output file
 * 
 * 
 */

package Logger;

import java.io.*;

public class Logger {
	private String filepath;
	
	private PrintStream log;
	
	Logger(String filepath) {
		this.filepath = filepath;
		
		try {
			log = new PrintStream(filepath);
		}catch(FileNotFoundException e) {
			System.out.println("FILE NOT FOUND... THIS SHOULD NOT BE POSSIBLE");
			e.printStackTrace();
		}
	}
	
	public void log(String message) {
		log.println(message);
	}
	
}
