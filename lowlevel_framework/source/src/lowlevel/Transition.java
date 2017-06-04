package lowlevel;

/**
 * Created by theChaoS on 04.06.2017.
 */
public class Transition {
    private long input;

    private State targetState; //Ziel
    private State originState;
    private Cluster targetCluster;
    private Cluster originCluster;

    public Transition(long input, State targetState, State startState, Cluster targetCluster, Cluster startCluster){
        this.input=input;
        this.targetState=targetState;
        this.originState=startState;
        this.originCluster=startCluster;
        this.targetCluster=targetCluster;
    }

    public Transition(long input, State targetState, State startState, Cluster currentCluster){
        this.input=input;
        this.targetState=targetState;
        this.originState=startState;
        this.originCluster=currentCluster;
        this.targetCluster=currentCluster;
    }

    public long getInput(){
        return this.input;
    }

    public String getInputAsBinnary(){
        return Long.toBinaryString(this.input);
    }

    public State getTargetState(){
        return this.targetState;
    }

    public State getOriginState(){
        return this.originState;
    }

    public Cluster getTargetCluster(){
        return this.targetCluster;
    }

    public Cluster getOriginCluster(){
        return this.originCluster;
    }
}
