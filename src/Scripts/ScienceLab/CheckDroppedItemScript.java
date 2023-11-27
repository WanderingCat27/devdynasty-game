package Scripts.ScienceLab;

import Level.LevelManager;
import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class CheckDroppedItemScript extends Script<NPC>
{
    public CheckDroppedItemScript()
    {
        this.player = LevelManager.getCurrentLevel().getPlayer();
        this.map = LevelManager.getCurrentLevel().getMap();
    }
    
    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        addTextToTextboxQueue("It doesn't seem you dropped the\nitem onto the table yet.");
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
