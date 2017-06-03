package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lowlevel.Helper;
import lowlevel.ParsedFile;
import lowlevel.State;

/**
 * This class can be used to export a dot-file to visualize an FSM.
 * @author Wolf & Gottschling
 *
 */
public class DotWriter {
	final static String lineSeparator = System.getProperty("line.separator");
	
	BufferedWriter bw;
	String name;
	
	public DotWriter(){
		
	}
	
	/**
	 * Method to trigger an export. 
	 * @param pf parsed File to be visualized
	 * @param filename name of the file
	 */
	public void writeDot(ParsedFile pf, String filename){
		name = filename.substring(filename.lastIndexOf(File.separatorChar)+1, filename.lastIndexOf('.'));
		
		try {
			bw = new BufferedWriter(new FileWriter(filename));
			
			writeHeader();
			
			for(State s: pf.getStates()){
				writeTransitions(s);
			}
			
			writeFooter();
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			System.out.println("An IOException occured");
		}
	}

	private void writeHeader() throws IOException{
		bw.write("digraph "+name+" {"+lineSeparator);
	}
	
	private void writeFooter() throws IOException{
		bw.write("}");	
	}
	
	private void writeTransitions(State s) throws IOException{
		List<State> nextStates = s.getNextStates();
		String code = Helper.longToOutputString(s.getCode());
		String name = s.getName();
		Set<State> alreadydrawn = new HashSet<State>();
		for(State state: nextStates){
			String nextCode = Helper.longToOutputString(state.getCode());
			String nextName = state.getName();
			bw.write("\t\""+name+" "+code+"\" -> \""+nextName+" "+nextCode+"\""+lineSeparator);
			alreadydrawn.add(state);
		}		
	}
}
