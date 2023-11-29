package Scripts.WildWestMap;

import Items.BuildingEntrance;
import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class SaloonBounderScript extends Script<NPC>
{

    @Override
    protected void setup()
    {
        lockPlayer();
        entity.facePlayer(player);

        if(!isFlagSet("hasChangedCostume"))
        {
            showTextbox();
            addTextToTextboxQueue("You don't look from around these ends...");
            addTextToTextboxQueue("Sorry pal, but you ain't getting in here.");
            addTextToTextboxQueue("Maybe switch up your look and come back.");
            addTextToTextboxQueue("Theres an old feller deep to the south-west\nthat could give you a costume.");
            addTextToTextboxQueue("He's a bit of a weirdo, but\nhe's got the goods in his store.");
        }
        else
        {
            BuildingEntrance saloonEntrance = new BuildingEntrance(0, getMapTile(23, 11).getLocation(), 96, 20, "saloon");
            showTextbox();
            addTextToTextboxQueue("Howdy partner.");
            addTextToTextboxQueue("Sorry for the trouble earlier.");
            addTextToTextboxQueue("Have a good little time in there, but be safe.");
            this.map.addItem(saloonEntrance);
        }
    }

    @Override
    protected void cleanup()
    {
        unlockPlayer();
        hideTextbox();
    }

    @Override
    protected ScriptState execute()
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
