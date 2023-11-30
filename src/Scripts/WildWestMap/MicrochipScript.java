package Scripts.WildWestMap;

import GameObject.Inventory;
import GameObject.Item;
import Level.GlobalFlagManager;
import Level.MapEntityStatus;
import Level.Script;
import Level.ScriptState;

public class MicrochipScript extends Script<Item>
{
    @Override
    protected void setup()
    {

        //if the sword has been picked up, then we will set the map entity status to removed
        if(Inventory.canAdd() && this.entity.getMapEntityStatus() == MapEntityStatus.ACTIVE)
        {
            Inventory.addNPC(this.entity);
            this.entity.setMapEntityStatus(MapEntityStatus.REMOVED);
            GlobalFlagManager.FLAG_MANAGER.setFlag("hasPickedUpChip");
        }
    }
    
    @Override
    protected void cleanup()
    {
        
    }

    @Override
    protected ScriptState execute() {
        start();
        end();
        return ScriptState.COMPLETED;
    }
}
