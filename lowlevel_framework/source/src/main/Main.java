package main;

import io.Parser;
import lowlevel.ParsedFile;

import java.io.File;
import java.io.FilenameFilter;

import lowlevel.Cluster;

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
			String relPath = "\\lowlevel_framework\\benchmarks\\kiss_files\\";
			String userDir = System.getProperty("user.dir");
			input_file_name = userDir+relPath+input_file_name;

			File dir = new File(input_file_name);
			ParsedFile[] inputFiles;
			if (args.length>1 && args[1]!=null  && args[1]=="-all" && dir.isDirectory()){
				// args[0] is a directory, and all files of the directory shall be parsed
				FilenameFilter filter = new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						if (name.endsWith(".kiss") || name.endsWith(".kiss2")){
							return true;
						}
						return false;
					}
				};
				inputFiles = new ParsedFile[dir.listFiles(filter).length];
				int ii=0;
				for (File file : dir.listFiles(filter)) {
					Parser p = new Parser();
					p.parseFile(file.getAbsolutePath());
					inputFiles[ii]=p.getParsedFile();
					ii++;
				}
			}// from this point on one could work on the whole folder... just for benchmarking reasons

			Parser p = new Parser();
			p.parseFile(input_file_name);
			
			// Representation of the FSM
			ParsedFile fsm = p.getParsedFile();
			System.out.println(fsm);
			

			
			// TODO - here you go

			Cluster myCluster = new Cluster();
			myCluster.addState(fsm.getStates()[0]); //DAS kommentar !!!
		}
		else{
			System.out.println("No input argument given");
		}
	}
}
