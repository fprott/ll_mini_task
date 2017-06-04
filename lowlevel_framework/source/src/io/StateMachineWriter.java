package io;

import lowlevel.StateMachine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Julian Käuser on 03.06.2017.
 */
public class StateMachineWriter {

    public static void writeFSM(StateMachine fsm, String destination){

        if (fsm==null || destination==null){
            System.out.println("no fsm written - destination or fsm unknown");
            return;
        }
        StringBuilder bld = new StringBuilder();
        bld.append("# "+fsm.name +" encoded cluster-wise\n");
        bld.append(".i "+fsm.getNumInputs()+"\n");
        bld.append(".o "+fsm.getNumOutputs()+"\n");

        // states and transitions

        bld.append(".end_kiss\n");
        bld.append(".latch_order "); // latch mapping



        try (FileWriter out = new FileWriter(destination)) {
            BufferedWriter buf = new BufferedWriter(out);
            buf.write(bld.toString());
            buf.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
