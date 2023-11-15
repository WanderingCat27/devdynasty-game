package Scripts.ScienceLab;

import Level.LevelManager;
import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class PickUpItemScript extends Script<NPC>
{
    public PickUpItemScript()
    {
        this.player = LevelManager.getCurrentLevel().getPlayer();
        this.map = LevelManager.getCurrentLevel().getMap();
    }
    
    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        addTextToTextboxQueue("It doesn't seem you picked up the item yet.");
    }

    @Override
    protected void cleanup()
    {
        hideTextbox();
        unlockPlayer();
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
