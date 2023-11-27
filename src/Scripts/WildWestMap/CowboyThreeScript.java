package Scripts.WildWestMap;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.Key;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.Inventory;
import Items.Jacket;
import Level.*;
import Utils.Direction;
import Utils.Point;

public class CowboyThreeScript extends Script<NPC> {
    private boolean isChoosing = false;
    private boolean jacketPickedUp = true;

    @Override
    protected void setup() {
        System.out.println("cowboy quest has started");
        lockPlayer();

        if (!GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToCB3")) {
            resetDialogue(); // Reset the dialogue
        } else if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("jacketPickedUp")) {
            questWin();
        }
        else if (!isChoosing) {
            hideTextbox();
        } else {
            showTextbox();
            addTextToTextboxQueue("Great, thank you partner!");
        }

        entity.facePlayer(player);
    }

    private void questWin() {
        System.out.println("quest win");
        showTextbox();
        addTextToTextboxQueue("Great, thank you partner!");
        for (int i = 0; i < 4; i++) {
            if (Inventory.get(i) instanceof Jacket) {
                Inventory.remove(i);
                break;
            }
        }
        addTextToTextboxQueue("Here, take this.");
        LevelManager.getCurrentLevel().getMap().getNPCById(4).setIsHidden(false);
        addTextToTextboxQueue("A health potion.");
        addTextToTextboxQueue("Might come in handy later...\nYou never know who you'll run into 'round here.");
        
        GlobalFlagManager.FLAG_MANAGER.unsetFlag("jacketPickedUp");
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
                if (!isFlagSet("hasTalkedToCB3")) {
                    //cleanup();
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
            addTextToTextboxQueue("Great! Now... listen up.");
            addTextToTextboxQueue("There's an older gentleman, over to the northwest\nof here.");
            addTextToTextboxQueue("Now, I accidentally left my jacket in his house...");
            addTextToTextboxQueue("Go grab it for me, will ya?");
            addTextToTextboxQueue("And don't worry, I'll reward you handsomely.");
            GlobalFlagManager.FLAG_MANAGER.setFlag("hasTalkedToCB3");
            isChoosing = false;
        } else if (Keyboard.isKeyDown(Key.DOWN)) {
            addTextToTextboxQueue("Come back to me after you have fella...");
            isChoosing = false;
        }
    }

    private void resetDialogue() {
        showTextbox();
        addTextToTextboxQueue("Heya, kid!");
        addTextToTextboxQueue("My, you look young and spry...");
        addTextToTextboxQueue("Could you do me a favor?");
        addTextToTextboxQueue("Sure! (PRESS UP ARROW)\nNo, I'd rather not. (PRESS DOWN ARROW)");
        isChoosing = true;
    }
}