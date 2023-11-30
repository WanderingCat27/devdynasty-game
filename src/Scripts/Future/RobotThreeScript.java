package Scripts.Future;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

// script for talking to walrus npc
public class RobotThreeScript extends Script<NPC> {

    @Override
    protected void setup() {
        System.out.println("RobotThree script started");
        lockPlayer();
        showTextbox();

        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToRobotThree")) {
            addTextToTextboxQueue( "HELLO HUMAN INDIVIDUAL");
            addTextToTextboxQueue( "THE SECURITY ROOM BELOW IS OFF LIMITS");
            addTextToTextboxQueue( "IT IS GAURDED BY AN ENEMY ROBOT");
            addTextToTextboxQueue( "DEFEAT HIM AND HE WILL GIVE YOU THE \nFINAL CODE NUMBER");
        }
        else {
            addTextToTextboxQueue("DEFEAT THE GAURD TO FINISH THE CODE");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if walrus is talked to again after the first time, what he says changes
        setFlag("hasTalkedToRobotThree");
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
