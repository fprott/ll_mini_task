package lowlevel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Julian Käuser on 03.06.2017.
 */
public class Cluster {

    private Set<State> states;

    // should be either "binary" or "onehot"
    private String internalEncoding;

    private long id;

    private int numInputs;

    private String code;

    public Cluster(){
        states = new HashSet<State>();
    }

    public boolean addState(State state){
        return states.add(state);
    }

    public State[] getStateArray(){
        return (State[]) states.toArray();
    }

    public boolean removeState(State state){
        return states.remove(state);
    }

    public String getEncoding(){
        return internalEncoding;
    }

    public void setEncoding(String enc){
        internalEncoding=enc;
    }

    public void setID(long id){
        this.id = id;
    }

    public long getID(){
        return id;
    }

    public int getNumStates(){
        return states.size();
    }

    public int getInputs(){
        return numInputs;
    }




    public String getEncodedCluster(){
        StringBuilder bld = new StringBuilder();

        ClusterEncoder enc = new ClusterEncoder();
        HashMap<State, String> map = null;
        switch (getEncoding()) {
            case "binary":
                map = enc.encodeBinary(this.getStateArray(), this.getInputs());
            case "onehot":
                map = enc.encodeOneHot(this.getStateArray(), this.getInputs());
        }
        for (State s : map.keySet()) {
            String str = ".code "+s.getName()+" "+map.get(s)+"\n";
            bld.append(str);
        }
        return bld.toString();
    }

    public String getCode() {
        return code;
    }
}
