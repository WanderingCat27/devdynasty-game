package Scripts.Prehistoric;

import Level.GlobalFlagManager;
import Level.NPC;
import Level.Script;
import Level.ScriptState;


// script for talking to walrus npc
public class CavemanScript extends Script<NPC> {


    @Override
    protected void setup() {
        lockPlayer();
        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToCaveman")) {
            showTextbox();
            addTextToTextboxQueue("Ugh... Me tired. Me no more fight.");
        }
        else if (!GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToCaveman")) {
            showTextbox();
            addTextToTextboxQueue( "Who walks here....");
            addTextToTextboxQueue( "Me not expect visitors!");
            addTextToTextboxQueue( "Must protect precious metal! Grunt.");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if walrus is talked to again after the first time, what he says changes
        GlobalFlagManager.FLAG_MANAGER.setFlag("hasTalkedToCaveman");
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

