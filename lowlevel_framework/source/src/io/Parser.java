package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lowlevel.ParsedFile;
import lowlevel.State;

/**
 * Class to represent and import an FSM saved as KISS. Converts all states into a HashMap.
 * @author Wolf & Gottschling
 *
 */
public class Parser {
	private ParsedFile pf;
	
	private Map<String, State> stateMap = new HashMap<String, State>();
	
	public Parser(){
		pf = new ParsedFile();
	}
	
	/**
	 * Getter function for a parsed file
	 * @return ParsedFile
	 */
	
	public ParsedFile getParsedFile(){
		return pf;
	}
	
	/**
	 * Functionality to import KISS files
	 * @param filename name of the file to be imported
	 */
	public void parseFile(String filename){		 

		BufferedReader kissfile = null;
		try {
			kissfile = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line = "";
		try {
			if(kissfile != null){
				while((line = kissfile.readLine()) != null){
					if(line.isEmpty() || line.startsWith("#")) continue;
					line = line.replaceAll("\\s+"," ");
					if(line.startsWith(".")){
						char c = line.charAt(1);
						String x = line.split(" ")[1];
						
						switch(c){
						case 'i':
							pf.setNum_inputs(Integer.parseInt(x));
							break;
						case 'o':
							pf.setNum_outputs(Integer.parseInt(x));
							break;
						case 'p':
							pf.setNum_transitions(Integer.parseInt(x));
							break;
						case 's':
							pf.setNum_states(Integer.parseInt(x));
							break;
						case 'r':
							String name = x;
							State init = new State(x);
							stateMap.put(x, init);
							pf.setInitialState(stateMap.get(name));
							break;
						default:
							break;
						}
					}
					else{
						parseLine(line);
					}
				}
				
				for(Map.Entry<String, State> entry : stateMap.entrySet()){
					pf.addState(entry.getValue());
				}			
			}
			kissfile.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void parseLine(String line){
		// input state nextstate output
		String[] parts = line.split(" ");
		String inputString = parts[0];
		String currentStateString = parts[1];
		String nextStateString = parts[2];
		String outputString = parts[3];
		
		long input = parseInputOutput(inputString);
		long output = parseInputOutput(outputString);
		
		if(currentStateString.equals("*")){
			if(nextStateString.equals("*")) return;

			if(stateMap.get(nextStateString) == null){
				stateMap.put(nextStateString, new State(nextStateString));
			}
			pf.setInitialState(stateMap.get(nextStateString));
			return;
		}
		
		if(stateMap.get(currentStateString) == null){
			stateMap.put(currentStateString, new State(currentStateString));
		}
		if(stateMap.get(nextStateString) == null){
			stateMap.put(nextStateString, new State(nextStateString));
		}
		
		State s = stateMap.get(currentStateString);
		
		s.addTransition(input, stateMap.get(nextStateString));
		s.addOutput(input, output);
	}
	
	private long parseInputOutput(String input){
		long x = 0;
		int n = input.length();
		
		char c;
		for(int i=0; i < n; i++){
			c = input.charAt(i);
			
			int shift = 2*(n-i-1);
			long bits = 0; 
			
			switch(c){
			case '0':
				bits = 1;
				break;
			case '1':
				bits = 2;
				break;
			case '-':
				bits = 3;
				break;
			}
			x |= (bits << shift);
		}
		
		return x;
	}
}
