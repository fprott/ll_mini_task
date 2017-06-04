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

    private State resetState;

    public String getEncoding(){
        return null;
    }

    public int getNumInputs() {
        return numInputs;
    }
    public int getNumOutputs(){
        return numOutputs;
    }
    public String getResetState(){ return resetState.toString(); }

    public int getNumClusters(){
        return clusters.size();
    }

    public Set<Cluster> getClusters(){
        return this.clusters;
    }
}
