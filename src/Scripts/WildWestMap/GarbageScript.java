package Scripts.WildWestMap;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class GarbageScript extends Script<NPC>
{
    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        addTextToTextboxQueue("It's a pile of garbage.");
        addTextToTextboxQueue("...");
        addTextToTextboxQueue("Ew.");
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
