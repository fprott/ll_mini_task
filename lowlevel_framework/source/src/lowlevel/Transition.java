package lowlevel;

/**
 * Created by theChaoS on 04.06.2017.
 */
public class Transition {
    private State targetState; //Ziel
    private State originState;
    private Cluster targetCluster;
    private Cluster originCluster;

    @Override
    public Transition(State targetState, State startState, Cluster targetCluster, Cluster startCluster){
        this.targetState=targetState;
        this.originState=startState;
        this.originCluster=startCluster;
        this.targetCluster=targetCluster;//Eine änderung!
    }

    @Override
    public Transition(State targetState, State startState, Cluster currentCluster){
        this.targetState=targetState;
        this.originState=startState;
        this.originCluster=currentCluster;
        this.targetCluster=currentCluster;
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
