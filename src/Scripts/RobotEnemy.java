package Scripts;

import Level.GlobalFlagManager;
import Level.LevelManager;
import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class RobotEnemy extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToRobotEnemy")) {
            showTextbox();
            addTextToTextboxQueue( "ROBOT HEALTH AT MINIMUM CAPACITY");
            addTextToTextboxQueue( "POWERING DOWN....");
        }
        else if (!GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToRobotEnemy")) {
            showTextbox();
            addTextToTextboxQueue( "HUMAN INDIVIDUAL ENCOUNTERED");
            addTextToTextboxQueue("ATTACK MODE INITIATED");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if walrus is talked to again after the first time, what he says changes
        GlobalFlagManager.FLAG_MANAGER.setFlag("hasTalkedToRobotEnemy");
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

