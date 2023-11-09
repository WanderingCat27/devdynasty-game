package Scripts.WildWestMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

// script for talking to walrus npc
public class CowboyThreeScript extends Script<NPC> {

    @Override
    protected void setup() {
        System.out.println("Test script started");
        lockPlayer();
        showTextbox();

        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToTest")) {
            addTextToTextboxQueue( "Good day to you!");
            addTextToTextboxQueue( "Hope you're enjoying your time in Westville...");
            addTextToTextboxQueue( "If ya haven't already, check out the saloon!");
            addTextToTextboxQueue( "Good luck fella...");
        }
        else {
            addTextToTextboxQueue("Hello there...");
            addTextToTextboxQueue("Not much else to say...");
            addTextToTextboxQueue("Have a blessed day!");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if walrus is talked to again after the first time, what he says changes
        setFlag("hasTalkedToTest");
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
