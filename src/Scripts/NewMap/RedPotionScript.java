package Scripts.NewMap;

import GameObject.Inventory;
import GameObject.Item;
import Level.MapEntityStatus;
import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class RedPotionScript extends Script<Item>
{
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
        System.out.println("Red Potion script cleaned up");
    }

    @Override
    protected ScriptState execute() {
        start();
        end();
        return ScriptState.COMPLETED;
    }
}
