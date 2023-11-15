package Scripts.ScienceLab;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import GameObject.ImageEffect;
import GameObject.Inventory;
import GameObject.Item;
import Level.GlobalFlagManager;
import Level.Map;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Items.Sword;
import Items.BossItems.*;

public class ItemTableScript extends Script<NPC>
{
    private boolean hasItem;
    public static ArrayList<Item> itemsOnTable = new ArrayList<>();
    
    //Need keyboard
    protected KeyLocker keyLocker = new KeyLocker();
    protected static Key RETURN = Key.ENTER;
    
    @Override
    protected void setup()
    {
        lockPlayer();
        hasItem = hasCorrectItem();

        if(!hasCorrectItem())
        {
            switch (itemsOnTable.size())
            {
                case 0:
                    GlobalFlagManager.FLAG_MANAGER.setFlag("win");
                    showTextbox();
                    addTextToTextboxQueue("You are yet to collect any items,\nbetter get going.");
                    break;
                case 1:
                    showTextbox();
                    addTextToTextboxQueue("Only 2 more to go.");
                    break;
                case 2:
                    // test, change to case 3 once final boss fight is ready
                    GlobalFlagManager.FLAG_MANAGER.setFlag("win");
                    showTextbox();
                    addTextToTextboxQueue("Just 1 more to go.");
                    break;
                case 3:
                    GlobalFlagManager.FLAG_MANAGER.setFlag("win");
                    break;
            }
        }
    }

    @Override
    protected void cleanup()
    {
       hasItem = false;
       hideTextbox();
       unlockPlayer();
    }

    @Override
    protected ScriptState execute()
    {
        
        if(hasCorrectItem())
        {
            start();
            System.out.println("Correct item was detected");
            for(int i = 0; i < Inventory.getInventory().size();i++)
            {
                if(Inventory.getInventory().get(i) instanceof Crystal || Inventory.getInventory().get(i) instanceof Metal || Inventory.getInventory().get(i) instanceof Microchip || Inventory.getInventory().get(i) instanceof MysteryBox)
                {
                    //add the inventory onto the table, remove it from the players inventory
                    itemsOnTable.add(Inventory.getInventory().get(i));
                    Inventory.itemsInInventory.remove(i);
                    Inventory.itemsInInventorySprites.remove(i);
                    //going to have to add logic to see the item drawn on the map
                }
                if(itemsOnTable.size() == 1)
                {
                    setFlag("hasDroppedCrystalOff");
                   this.entity.setCurrentAnimationName("ONE_ITEM");
                }
                else if(itemsOnTable.size() == 2)
                {
                    this.entity.setCurrentAnimationName("TWO_ITEM");
                }
            }
            end();
            return ScriptState.COMPLETED;
        }
        else
        {
            start();
            if(!isTextboxQueueEmpty())
            {
                return ScriptState.RUNNING;
            }
            end();
            return ScriptState.COMPLETED;
        }
    }
    private boolean hasCorrectItem()
    {
        ArrayList<Item> items = Inventory.getInventory();
        for(Item item : items)
        {
            if(item instanceof Crystal || item instanceof Metal || item instanceof Microchip)
            {
                return true;
            }
        }
        return false;
    }
}

