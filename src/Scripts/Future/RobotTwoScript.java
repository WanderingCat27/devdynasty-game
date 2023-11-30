package Scripts.Future;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

// script for talking to walrus npc
public class RobotTwoScript extends Script<NPC> {

    @Override
    protected void setup() {
        System.out.println("RobotTwo script started");
        lockPlayer();
        showTextbox();

        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToRobotTwo")) {
            addTextToTextboxQueue( "HELLO HUMAN INDIVIDUAL");
            addTextToTextboxQueue( "THIS IS OUR FILE ROOM");
            addTextToTextboxQueue( "YOU MAY BROWSE BUT DO NOT TAKE ANYTHING...");
        }
        else {
            addTextToTextboxQueue("YOU MAY LOOK AROUND THE FILE ROOM");
            addTextToTextboxQueue("DO NOT TAKE ANY INFORMATION");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if walrus is talked to again after the first time, what he says changes
        setFlag("hasTalkedToRobotTwo");
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
