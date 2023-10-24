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

    public void trees() {
        System.out.println("Mad Scientist tree started");
        lockPlayer();
        showTextbox();

        if (!isFlagSet("hasTalkedToScientist")) {
            if(Keyboard.isKeyDown(Key.UP))
            {
                addTextToTextboxQueue( "Thank you so much!");
                if(Keyboard.isKeyDown(Key.ENTER)){
                    cleanup();
                }
            }
            else if(Keyboard.isKeyDown(Key.DOWN))
            {
                addTextToTextboxQueue( "Come back here when you can help.");
                 if(Keyboard.isKeyDown(Key.ENTER)){
                    cleanup();
                }
            }
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
        trees();
        
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;   
        }
        
        end();
        return ScriptState.COMPLETED;
    }
}
