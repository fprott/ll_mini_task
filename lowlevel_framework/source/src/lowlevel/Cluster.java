package lowlevel;

import lowlevel.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theChaoS on 03.06.2017.
 */
public class Cluster {
    private List<State> myStates;
    private List<Transition>

    @Override
    public Cluster(){
        this.myStates= new ArrayList<State>();
    }

    @Override
    public Cluster(ArrayList<State> states){
        this.myStates=states;
    }


    public void addState(State aState){
        this.myStates.add(aState);
        for(State state : this.myStates){
            state
        }

    }

    public getAllStates(){

    }

    public getOutgoingTransactions(){

    }

    public getIngoingTransactions(){

    }

    public getTransactions(){

    }
}
