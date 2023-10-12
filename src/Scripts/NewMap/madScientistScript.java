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
            addTextToTextboxQueue( "This is the mad scientist test dialogue.");
            addTextToTextboxQueue( "Test dialogue option #1 (PRESS UP ARROW) \nTest dialogue option #2 (PRESS DOWN ARROW)");  
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
            addTextToTextboxQueue( "You chose dialogue option #1 \n(SPACE TO CONTINUE)");
        }
        else if(Keyboard.isKeyDown(Key.DOWN))
        {
            addTextToTextboxQueue( "You chose dialogue option #2 \n(SPACE TO CONTINUE)");
        }
        if(Keyboard.isKeyDown(Key.SPACE)){
            unlockPlayer();
            hideTextbox();
        }
        
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        
        end();
        return ScriptState.COMPLETED;
    }
}
