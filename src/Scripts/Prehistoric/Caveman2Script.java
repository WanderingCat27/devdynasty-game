package Scripts.Prehistoric;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.Key;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.Inventory;
import Items.Jacket;
import Items.Sword;
import Level.*;
import Utils.Direction;
import Utils.Point;

public class Caveman2Script extends Script<NPC> {
    private boolean isChoosing = false;
    private boolean gotSword = true;

    @Override
    protected void setup() {
        System.out.println("dino quest has started");
        lockPlayer();

        if (!GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToCaveman2")) {
            resetDialogue(); // Reset the dialogue
        } else if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("swordPickedUp")) {
            questWin();
        }
        else if (!isChoosing) {
            hideTextbox();
        } else {
            showTextbox();
            addTextToTextboxQueue("Good luck, brave soul.");
        }

        entity.facePlayer(player);
    }

    private void questWin() {
        System.out.println("quest win");
        showTextbox();
        addTextToTextboxQueue("Ah, thank you!");
        for (int i = 0; i < Inventory.getInventory().size(); i++) {
            if (Inventory.get(i) instanceof Sword) {
                Inventory.remove(i);
                break;
            }
        }
        addTextToTextboxQueue("Here... a token of my gratitude");
        LevelManager.getCurrentLevel().getMap().getItemById(0).setIsHidden(false);
        addTextToTextboxQueue("A strength potion.");
        addTextToTextboxQueue("Stay safe, it's a dangerous world out here.");
        
        GlobalFlagManager.FLAG_MANAGER.unsetFlag("swordPickedUp");
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
                if (!isFlagSet("hasTalkedToCaveman2")) {
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
            addTextToTextboxQueue("One of the dinosaurs that prowls these lands...");
            addTextToTextboxQueue("He stole my sword!");
            addTextToTextboxQueue("Win it back for me, and I will forever be\nin your debt.");
            addTextToTextboxQueue("And I'll pay you back!");
            GlobalFlagManager.FLAG_MANAGER.setFlag("hasTalkedToCaveman2");
            isChoosing = false;
        } else if (Keyboard.isKeyDown(Key.DOWN)) {
            addTextToTextboxQueue("Darn...");
            isChoosing = false;
        }
    }

    private void resetDialogue() {
        showTextbox();
        addTextToTextboxQueue("Hey you!");
        addTextToTextboxQueue("Are you a friend or a foe?");
        addTextToTextboxQueue("A friend? Well then, surely you can help me!");
        addTextToTextboxQueue("Sure! (PRESS UP ARROW)\nNo, I'd rather not. (PRESS DOWN ARROW)");
        isChoosing = true;
    }
}