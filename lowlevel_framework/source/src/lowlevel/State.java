package lowlevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents state of an FSM.
 * @author Wolf & Gottschling
 *
 */
public class State {
	private String stateName;
	private Map<Long, State> nextStateMap = new HashMap<Long, State>();
	private Map<Long, Long> outputMap = new HashMap<Long, Long>();
	private Map<State, Integer> transitionCount = new HashMap<State, Integer>();
	private List<Long> inputs = new ArrayList<Long>();
	
	private long code=-1;
	
	private int total_transitions = 0;
	
	/**
	 * Constructor
	 */
	public State(){
		this.stateName="this is magic (42)";
	}
	
	/**
	 * Alternative constructor
	 * @param name name of the state
	 */
	public State(String name){
		this.stateName=name;
	}
	
	/**
	 * Method to return an ArrayList of directly reachable states.
	 * @return List of reachable states
	 */
	public ArrayList<State> getNextStates(){
		return new ArrayList<State>(nextStateMap.values()); 
	}
	
	/**
	 * getter function for the attribute "name"
	 * @return name of the state
	 */
	public String getName(){
		return this.stateName;
	}
	
	/**
	 * Adds a transition to the state
	 * @param condition of the transition
	 * @param nextState related state  
	 */
	public void addTransition(long input, State nextState){
		inputs.add(input);
		if(nextState!=this){						// increase transition count for both, this and nextState
			nextState.addIngoingTransition(this);
			if(transitionCount.get(nextState)==null){
				transitionCount.put(nextState, 1);
			}
			transitionCount.put(nextState, transitionCount.get(nextState)+1);
			total_transitions++;
		}
		nextStateMap.put(input, nextState);
	}
	
	public void addOutput(long input, long output){
		outputMap.put(input, output);
	}
	
	public State getNextState(int input){
		return nextStateMap.get(input);
	}
	
	public long output(long input){
		return outputMap.get(input);
	}

	public long getCode() {
		return code;
	}
	
	public int getShortCode(){
		int scode=0;
		long tcode = this.code;
		int i = 0;
		while( ( tcode & (long) 0x3 ) > 0){
			if( (tcode & 0x3 ) == 2){
				 scode |= (1<<i);
			}
			i++;
			tcode >>= 2;
		}
		return scode;
	}

	public void setCode(long x) {
		this.code = x;
	}
	
	/**
	 * Adds an ingoing transition
	 * @param state previous state
	 */
	public void addIngoingTransition(State state){
		if(transitionCount.get(state)==null){
			transitionCount.put(state, 1);
		}
		transitionCount.put(state, transitionCount.get(state)+1);
		total_transitions++;
	}
	
	public int getTotalTransitionCount(){
		return total_transitions;
	}
	
	/**
	 * Returns a 2-dim array of all transitions
	 * @return transition
	 */
	public long[][] getTransitions(){
		long[][] transitions = new long[nextStateMap.size()][3];
		
		int i=0;
		for(Map.Entry<Long, State> entry : nextStateMap.entrySet()){
			transitions[i][0] = this.code; //current State in code ?
			transitions[i][1] = entry.getKey(); //Input Code ?
			transitions[i][2] = entry.getValue().getCode(); //next State Code ?
			i++;
		}
		
		return transitions;
	}
	
	/**
	 * Returns a 2-dim array representing the outputs.
	 * @return outputs
	 */
	public long[][] getOutputs(){
		long[][] outputs = new long[outputMap.size()][3];
		
		int i=0;
		for(Map.Entry<Long, Long> entry : outputMap.entrySet()){
			outputs[i][0] = this.code;
			outputs[i][1] = entry.getKey();
			outputs[i][2] = entry.getValue();
			i++;
		}
		
		return outputs;
	}
}
