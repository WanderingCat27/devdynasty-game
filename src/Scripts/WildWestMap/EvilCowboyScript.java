package Scripts.WildWestMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;


// script for talking to walrus npc
public class EvilCowboyScript extends Script<NPC> {


    @Override
    protected void setup() {
        System.out.println("Evil cowboy script has started");
        lockPlayer();
        showTextbox();
        System.out.println(isFlagSet("hasTalkedToCowboy"));
        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (!isFlagSet("hasTalkedToCowboy")) {
            addTextToTextboxQueue( "I wanna fight you");
        }
        else {
            addTextToTextboxQueue( "Im a dinosaur");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if walrus is talked to again after the first time, what he says changes
        setFlag("hasTalkedToCowboy");
        System.out.println("cleaned up");
        System.out.println(isFlagSet("hasTalkedToCowboy"));
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

