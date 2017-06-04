package io;

import lowlevel.Cluster;
import lowlevel.State;
import lowlevel.StateMachine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

/**
 * Created by Julian Käuser on 03.06.2017.
 */
public class StateMachineWriter {

    /**
     *
     * @param fsm the StateMachine object to write out
     * @param destination the directory (without line separator in the end!) where the written .kiss2
     *                    file shall be placed
     */
    public static void writeFSM(StateMachine fsm, String destination){

        if (fsm==null || destination==null){
            System.out.println("no fsm written - destination or fsm unknown");
            return;
        }
        // catch the "fsm-has-no-name"-case
        if (fsm.name==null){
            System.out.println("fsm has no name, name set to time+date of execution");
            fsm.name=new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        }
        StringBuilder bld = new StringBuilder();
        bld.append("# "+fsm.name +" encoded cluster-wise\n");
        bld.append(".model "+fsm.name);

        // latch initilizations
        String[] latches = getLatches(fsm);
        for (String l : latches){
            bld.append(".latch "+l+"\n");
        }

        bld.append(".start_kiss");
        bld.append(".i "+fsm.getNumInputs()+"\n");
        bld.append(".o "+fsm.getNumOutputs()+"\n");
        // states and transitions
        //bld.append(".p "+fsm.getNumTransistions()+"\n");
        //bld.append(".s "+fsm.getNumStates()+"\n");
        //bld.append(".r "+fsm.getResetState());


        bld.append(".end_kiss\n");
        //latch mapping
        bld.append(".latch_order ");


        for (int ii=0; ii<latches.length; ii++){
            bld.append(latches[ii]);
        }
        bld.append("\n");

        // print codes of states
        for (Cluster c : fsm.getClusters()){
            for (State s : c.getStateArray()){
                bld.append(".code "+s.getName()+" "+c.getCode()+s.getCode()+"\n");
            }
        }

        bld.append(".end");
        destination = (destination.endsWith(System.lineSeparator())) ? destination : (destination + System.lineSeparator());
        destination += fsm.name+"_clusterEncoded.kiss2";

        try (FileWriter out = new FileWriter(destination)) {
            BufferedWriter buf = new BufferedWriter(out);
            buf.write(bld.toString());
            buf.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static String[] getLatches(StateMachine fsm){
        String[] latches = null;
        if (fsm==null) {
            latches = new String[1];
            latches[0] = "";
            return latches;
        }


        return latches;

    }

    private static String[] getExternalClusterLatches(StateMachine fsm){
        String[] latches;
        Set<Cluster> clusters = fsm.getClusters();
        latches = new String[clusters.size()];
        int ii=0;
        for (Cluster cluster : clusters){
            latches[ii] = "v"+cluster.getID()+ " ";
        }
        return latches;
    }

    private static String[] getClusterInternalLatches(Cluster cluster){
        String[] latches = null;
        int len;
        return latches;

    }
}
