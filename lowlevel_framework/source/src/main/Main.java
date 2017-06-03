package main;

import io.Parser;
import lowlevel.ParsedFile;

/**
 * Main class
 * @author Wolf & Gottschling
 *
 */
public class Main {
	
	public static void main(String[] args) {
				
		if(args.length>0){
			System.out.println(" Current working directory : " + System.getProperty("user.dir"));
			
			String input_file_name = args[0];
			String relPath = "\\benchmarks\\kiss_files\\";
			String userDir = System.getProperty("user.dir");
			input_file_name = userDir+relPath+input_file_name;
			
			Parser p = new Parser();
			p.parseFile(input_file_name);
			
			// Representation of the FSM
			ParsedFile fsm = p.getParsedFile();
			System.out.println(fsm);
			

			
			// TODO - here you go 

		}
		else{
			System.out.println("No input argument given");
		}
	}
}
