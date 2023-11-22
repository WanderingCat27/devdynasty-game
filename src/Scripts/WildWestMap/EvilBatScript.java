package Scripts.WildWestMap;

import Level.GlobalFlagManager;
import Level.LevelManager;
import Level.NPC;
import Level.Script;
import Level.ScriptState;


// script for talking to walrus npc
public class EvilBatScript extends Script<NPC> {


    @Override
    protected void setup() {
        lockPlayer();
        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToBat")) {
            showTextbox();
            addTextToTextboxQueue( "Hiss...");
            addTextToTextboxQueue("Eek!");
            LevelManager.getCurrentLevel().getMap().getNPCById(6).setIsHidden(true);
        }
        else if (!GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToBat")) {
            showTextbox();
            addTextToTextboxQueue( "Hiss...");
            addTextToTextboxQueue( "Hisssss...");
            addTextToTextboxQueue( "HISSSSSS...");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if walrus is talked to again after the first time, what he says changes
        GlobalFlagManager.FLAG_MANAGER.setFlag("hasTalkedToBat");
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

