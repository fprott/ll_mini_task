package lowlevel;

/**
 * Represents a parsed Fsm. This class could be seen as some kind of an intermediate representation
 * of an FSM.
 * @author Wolf & Gottschling
 *
 */
public class ParsedFile {
	private int num_inputs=0;
	private int num_outputs=0;
	private int num_states=0;
	private int num_transitions=0;
	private State[] states;
	private int state_counter=0;
	private State initial_state=null;
			
	public int getNumInputs() {
		return num_inputs;
	}
	public void setNum_inputs(int num_inputs) {
		this.num_inputs = num_inputs;
	}
	public int getNumOutputs() {
		return num_outputs;
	}
	public void setNum_outputs(int num_outputs) {
		this.num_outputs = num_outputs;
	}
	public int getNum_states() {
		return num_states;
	}
	
	public void setNum_states(int num_states) {
		this.num_states = num_states;
		states = new State[num_states];
	}
	public int getNum_transitions() {
		return num_transitions;
	}
	public void setNum_transitions(int num_transitions) {
		this.num_transitions = num_transitions;
	}
	public void setInitialState(State s){
		this.initial_state = s;
	}
	
	public State getInitialState(){
		return this.initial_state;
	}
	
	public void addState(State s){
		states[state_counter++]=s;
	}
	
	public State[] getStates(){
		return this.states;
	}
	
	public void overwriteStates(State[] states){
		this.states = states;
	}
	
	public String toString(){
		String s = "";
			s = "INPUTS: "+ String.valueOf(num_inputs) +"\n";
			s += "OUTPUTS: "+ String.valueOf(num_outputs) +"\n";
			s += "TRANSITIONS: "+ String.valueOf(num_transitions) +"\n";
			s += "STATES: "+ String.valueOf(num_states) +"\n";
		
		for(int i=0; i<states.length; i++){
			State st = states[i];
			s += st.getName()+" "+Helper.longToOutputString(st.getCode())+"\n";
		}
		return s;
	}
}

