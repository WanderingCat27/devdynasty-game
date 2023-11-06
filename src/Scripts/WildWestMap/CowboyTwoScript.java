package Scripts.WildWestMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

// script for talking to walrus npc
public class CowboyTwoScript extends Script<NPC> {

    @Override
    protected void setup() {
        System.out.println("Walrus script started");
        lockPlayer();
        showTextbox();

        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToWalrus")) {
            addTextToTextboxQueue( "Greetings Partner!");
            addTextToTextboxQueue( "Feel free to enter any of these buildings...");
            addTextToTextboxQueue( "Might help you get more comfortable here...");
            addTextToTextboxQueue( "Welcome to Westville!");
        }
        else {
            addTextToTextboxQueue("Don't forget to check out any of the buildings...");
            addTextToTextboxQueue("Anyways...");
            addTextToTextboxQueue("Gooday!");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if walrus is talked to again after the first time, what he says changes
        setFlag("hasTalkedToWalrus");
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
