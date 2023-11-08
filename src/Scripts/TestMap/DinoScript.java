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

        if (!isFlagSet("hasTalkedToDinosaur")) {
            resetDialogue(); // Reset the dialogue
        } else if (!isChoosing) {
            hideTextbox();
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
                if (!isFlagSet("hasTalkedToDinosaur")) {
                    cleanup();
                }
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
            addTextToTextboxQueue("My heavens, surprised you made it out alive!");
            isChoosing = false;
        } else if (Keyboard.isKeyDown(Key.DOWN)) {
            addTextToTextboxQueue("Come back to me after you have fella...");
            isChoosing = false;
        }
    }

    private void resetDialogue() {
        showTextbox();
        addTextToTextboxQueue("Why hello there!");
        addTextToTextboxQueue("You must be new to town...");
        addTextToTextboxQueue("Say, have you made your way to the saloon?");
        addTextToTextboxQueue("I have. (PRESS UP ARROW)\nI have not. (PRESS DOWN ARROW)");
        isChoosing = true;
    }
}