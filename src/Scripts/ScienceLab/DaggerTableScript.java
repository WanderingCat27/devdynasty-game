package Scripts.ScienceLab;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class DaggerTableScript extends Script<NPC>
{
    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        addTextToTextboxQueue("It seems he made a dagger. I wonder\nwhat he's going to do with it.");
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
