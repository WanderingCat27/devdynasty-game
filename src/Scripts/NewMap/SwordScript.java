package Scripts.NewMap;

import Level.GlobalFlagManager;
import Level.MapEntityStatus;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import GameObject.Inventory;
//import GameObject.Item; //since we will need to access the item class
import GameObject.Item;

public class SwordScript extends Script<Item>
{

    private boolean setFlag = false;
    
    @Override
    protected void setup()
    {

        //if the sword has been picked up, then we will set the map entity status to removed
        if(Inventory.canAdd() && this.entity.getMapEntityStatus() == MapEntityStatus.ACTIVE)
        {
            Inventory.addNPC(this.entity);
            this.entity.setMapEntityStatus(MapEntityStatus.REMOVED);
            setFlag = true;

            
        }
    }
    
    @Override
    protected void cleanup()
    {
        
    }

    @Override
    protected ScriptState execute() {
        start();
        if (setFlag)
            GlobalFlagManager.FLAG_MANAGER.setFlag("swordPickedUp");
        end();
        return ScriptState.COMPLETED;
    }

}

