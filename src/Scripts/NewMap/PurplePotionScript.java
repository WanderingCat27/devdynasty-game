package Scripts.NewMap;

import GameObject.Inventory;
import GameObject.Item;
import Level.MapEntityStatus;
import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class PurplePotionScript extends Script<NPC>
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
        System.out.println("Purple Potion script cleaned up");
        this.isActive = false;
    }

    @Override
    protected ScriptState execute() {
        start();
        end();
        return ScriptState.COMPLETED;
    }
}
