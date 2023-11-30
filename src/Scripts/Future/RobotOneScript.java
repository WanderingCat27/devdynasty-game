package Scripts.Future;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

// script for talking to walrus npc
public class RobotOneScript extends Script<NPC> {

    @Override
    protected void setup() {
        System.out.println("RobotOne script started");
        lockPlayer();
        showTextbox();

        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToRobotOne")) {
            addTextToTextboxQueue( "HELLO HUMAN INDIVIDUAL");
            addTextToTextboxQueue( "I DID NOT KNOW YOUR KIND EXISTED ANYMORE...");
            addTextToTextboxQueue( "WELCOME");
            addTextToTextboxQueue( "USE THE ELEVATOR TO CHANGE FLOORS");
            addTextToTextboxQueue( "THE TOP FLOOR IS OFF LIMITS");
            addTextToTextboxQueue("WITHOUT THE CODE THAT IS...");
            addTextToTextboxQueue("EACH FLOOR CONTAINS ONE CODE NUMBER");
            addTextToTextboxQueue("THE FIRST NUMBER IN THE CODE IS 7");
        }
        else {
            addTextToTextboxQueue("FEEL FREE TO USE THE ELEVATOR HUMAN...");
            addTextToTextboxQueue("THE TOP FLOOR IS OFF LIMITS");
            addTextToTextboxQueue("UNLESS YOU HAVE THE CODE...");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if walrus is talked to again after the first time, what he says changes
        setFlag("hasTalkedToRobotOne");
    }

    @Override
    public ScriptState execute() {
        start();
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        end();
        return ScriptState.COMPLETED;
    }
}
