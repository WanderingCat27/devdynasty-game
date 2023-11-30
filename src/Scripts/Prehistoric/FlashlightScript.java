package Scripts.Prehistoric;

import GameObject.Inventory;
import GameObject.Item;
import Level.GlobalFlagManager;
import Level.MapEntityStatus;
import Level.Script;
import Level.ScriptState;

public class FlashlightScript extends Script<Item>{





    @Override
    protected void setup()
    {
        //if the sword has been picked up, then we will set the map entity status to removed
        if(Inventory.canAdd() && this.entity.getMapEntityStatus() == MapEntityStatus.ACTIVE)
        {
            Inventory.addNPC(this.entity);
            this.entity.setMapEntityStatus(MapEntityStatus.REMOVED);
            
            //this.isActive = false;
            System.out.println(this.entity.getMapEntityStatus().toString());
            
        }
    }


    @Override
    protected void cleanup()
    {
        GlobalFlagManager.FLAG_MANAGER.setFlag("flashlightPickedUp");
    }

    @Override
    protected ScriptState execute() {
        start();
        end();
        return ScriptState.COMPLETED;
    }


    
}
