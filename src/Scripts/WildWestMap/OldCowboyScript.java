package Scripts.WildWestMap;

import Level.GlobalFlagManager;
import Level.NPC;
import Level.Script;
import Level.ScriptState;


// script for talking to walrus npc
public class OldCowboyScript extends Script<NPC> {


    @Override
    protected void setup() {
        lockPlayer();
        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToOldCowboy") && (GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToOldCowboyTwice") == false)) {
            showTextbox();
            addTextToTextboxQueue("Howdy again!");
            addTextToTextboxQueue("Be warned, stay away from that saloon.");
            addTextToTextboxQueue("Some seedy folks over there...");
        }
        else if (!GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToOldCowboy")) {
            showTextbox();
            addTextToTextboxQueue( "Howdy, fella!");
            addTextToTextboxQueue( "I haven't seen you 'round here before, methinks.");
            addTextToTextboxQueue( "Well, welcome to Westville!");
            addTextToTextboxQueue( "I can't say there's much to see here...");
            addTextToTextboxQueue( "But if you need something, give me a holler!");
        } else {
            showTextbox();
            addTextToTextboxQueue("Howdy again!");
            addTextToTextboxQueue("It's a beautiful day to be a cowboy...");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToOldCowboy")) {
            GlobalFlagManager.FLAG_MANAGER.setFlag("hasTalkedToOldCowboyTwice");
            System.out.println("flag set");
        } else {
            GlobalFlagManager.FLAG_MANAGER.setFlag("hasTalkedToOldCowboy");
        }


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

