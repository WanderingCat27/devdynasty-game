package Scripts.ScienceLab;

import java.util.ArrayList;

import GameObject.Inventory;
import GameObject.Item;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Items.BossItems.*;

public class ItemTableScript extends Script<NPC>
{
    private boolean hasItem;
    private static ArrayList<Item> itemsOnTable;
    @Override
    protected void setup()
    {
        lockPlayer();
        hasItem = hasCorrectItem();
    }

    @Override
    protected void cleanup()
    {
       hasItem = false;
       unlockPlayer();
    }

    @Override
    protected ScriptState execute()
    {
        if(hasCorrectItem())
        {
            for(int i = 0; i < Inventory.getInventory().size();i++)
            {
                if(Inventory.getInventory().get(i) instanceof Crystal || Inventory.getInventory().get(i) instanceof Metal || Inventory.getInventory().get(i) instanceof Microchip || Inventory.getInventory().get(i) instanceof MysteryBox)
                {
                    //add the inventory onto the table, remove it from the players inventory
                    itemsOnTable.add(Inventory.getInventory().get(i));
                    Inventory.getInventory().remove(i);
                }
            }
            return ScriptState.COMPLETED;
        }
        else
        {
            //display correct image depending on how many items they have/are yet to collect
        }
    }

    private boolean hasCorrectItem()
    {
        ArrayList<Item> items = Inventory.getInventory();
        for(Item item : items)
        {
            if(item instanceof Crystal || item instanceof Metal || item instanceof Microchip || item instanceof MysteryBox)
            {
                return true;
            }
        }
        return false;
    }
    
}
