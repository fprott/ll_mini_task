package lowlevel;

import java.util.Set;

/**
 * Created by Julian Käuser on 03.06.2017.
 */
public class StateMachine {

    public String name;
    private Set<Cluster> clusters;
    private int numInputs;
    private int numOutputs;

    public String getEncoding(){
        return null;
    }

    public int getNumInputs() {
        return numInputs;
    }
    public int getNumOutputs(){
        return numOutputs;
    }
}
