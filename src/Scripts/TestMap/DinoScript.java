package Scripts.TestMap;

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

public class DinoScript extends Script<NPC> {
    private boolean isChoosing = false;

    @Override
    protected void setup() {
        System.out.println("Dino Trees has started");
        lockPlayer();
        showTextbox();
        isChoosing = true;

        if (!isFlagSet("hasTalkedToDinosaur")) {
            addTextToTextboxQueue("Dino Test 1. (PRESS UP ARROW)\nDino Test 2. (PRESS DOWN ARROW)");
            isChoosing = true;
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

        if (isChoosing) {
            handleChoiceInput();
        } else {
            if (Keyboard.isKeyDown(Key.ENTER)) {
                cleanup();
            }
        }

        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }

        end();
        return ScriptState.COMPLETED;
    }

    private void handleChoiceInput() {
        if (Keyboard.isKeyDown(Key.UP)) {
            addTextToTextboxQueue("Dino Test 1 Chosen");
            isChoosing = false;
        } else if (Keyboard.isKeyDown(Key.DOWN)) {
            addTextToTextboxQueue("Dino Test 2 Chosen");
            isChoosing = false;
        }
    }
}