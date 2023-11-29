package Scripts.Prehistoric;

import Level.GlobalFlagManager;
import Level.NPC;
import Level.Script;
import Level.ScriptState;


// script for talking to walrus npc
public class BlueDinoScript extends Script<NPC> {


    @Override
    protected void setup() {
        lockPlayer();
        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToCaveman2")) {
            showTextbox();
            addTextToTextboxQueue("You want this sword back?");
            addTextToTextboxQueue("Not without a fight!");
        }
        else if (!GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToCaveman2")) {
            showTextbox();
            addTextToTextboxQueue("Grr...");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();
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

