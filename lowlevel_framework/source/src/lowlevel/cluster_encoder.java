package lowlevel;

import java.util.HashMap;

/**
 * Julians Idee für diese Klasse: Nehme bis zu drei States, und setze ein One-Hot-Encoding ins Slice.
 * -> Ergo 3 Flipflops
 * Created by theChaoS on 03.06.2017.
 */
public class ClusterEncoder {
    final static int k=6;
    final static int nFF=4;


    public ClusterEncoder(){

    }

    /**
     * Takes the selected states for a cluster, namely in the array states, and packs them into a LUT-feasible
     * format. Returns the encoding for this state cluster in one hot
     * @param states the states in the cluster, each entry non-null
     * @return  A mapping of states to strings containing their one-hot value (as chars of {0,1})
     *          if encoding is not possible (too many states), return null
     */
    public HashMap<State, String> encodeOneHot (State[] states, int nIn){
        HashMap<State, String> result = new HashMap<State, String>();

        /*
        precondition: states have yet been chosen regarding their clusterability
         */




    }

    /**
     * Takes the selected states for a cluster, namely in the array states, and packs them into a LUT-feasible
     * format. Returns the encoding for this state cluster in binary encoding
     * @param states the states in the cluster
     * @return A mapping of states to strings containing their binary value (as chars of {0,1})
     */
    public HashMap<State, String> encodeBinary(State[] states, int nIn){
        HashMap<State, String> result = new HashMap<State, String>();
    }

}


public class oneHotEncoder extends ClusterEncoder{

}

public class binaryEncoder extends clusterEncoder{

}