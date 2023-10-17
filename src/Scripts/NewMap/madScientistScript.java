package Scripts.NewMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.Key;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.Inventory;
import Level.*;
import Utils.Direction;
import Utils.Point;

// script for talking to walrus npc
public class madScientistScript extends Script<NPC> {

    @Override
    protected void setup() {
        System.out.println("Mad Scientist script started");
        lockPlayer();
        showTextbox();

        if (!isFlagSet("hasTalkedToScientist")) {
            addTextToTextboxQueue( "My time machine is broken, I need help.");
            addTextToTextboxQueue( "I can help you with that. (PRESS UP ARROW) \nI can't help you with that. (PRESS DOWN ARROW)");  
        }
        entity.facePlayer(player);
    }

    @Override
    protected void cleanup() {
        unlockPlayer();
        hideTextbox();

        // set flag so that if madScientist is talked to again after the first time, what he says changes
        setFlag("has talked to mad scientist");
    }

    @Override
    public ScriptState execute() {
        start();
        if(Keyboard.isKeyDown(Key.UP))
        {
            addTextToTextboxQueue( "Thank you so much! \n(SPACE TO CONTINUE)");
        }
        else if(Keyboard.isKeyDown(Key.DOWN))
        {
            addTextToTextboxQueue( "You're no help! \n(SPACE TO CONTINUE)");
        }
        if(Keyboard.isKeyDown(Key.SPACE)){
           cleanup();
        }
        
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        
        end();
        return ScriptState.COMPLETED;
    }
}
