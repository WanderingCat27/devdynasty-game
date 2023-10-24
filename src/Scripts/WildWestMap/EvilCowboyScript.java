package Scripts.WildWestMap;

import Level.GlobalFlagManager;
import Level.NPC;
import Level.Script;
import Level.ScriptState;


// script for talking to walrus npc
public class EvilCowboyScript extends Script<NPC> {


    @Override
    protected void setup() {
        lockPlayer();
        // changes what walrus says when talking to him the first time (flag is not set) vs talking to him afterwards (flag is set)
        if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToCowboy")) {
            showTextbox();
            addTextToTextboxQueue("Well kid... you beat me fair and square.");
            addTextToTextboxQueue("You can have my trusty crystal, but I\nnever want to see you again.");
        }
        else if (!GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToCowboy")) {
            showTextbox();
            addTextToTextboxQueue( "Hey, what're you doing here, kid?");
            addTextToTextboxQueue( "...You don't look like you belong round these parts.");
            addTextToTextboxQueue( "Either way... this is my patch of grass here, fella.");
            addTextToTextboxQueue( "Get the hell out...");
            addTextToTextboxQueue( "Get the hell out...\nbefore I make you!");
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if walrus is talked to again after the first time, what he says changes
        GlobalFlagManager.FLAG_MANAGER.setFlag("hasTalkedToCowboy");
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

