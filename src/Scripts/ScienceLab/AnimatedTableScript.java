package Scripts.ScienceLab;

import Level.NPC;
import Level.Script;
import Level.ScriptState;

public class AnimatedTableScript extends Script<NPC> {

    @Override
    protected void setup() {
        lockPlayer();
        showTextbox();
        addTextToTextboxQueue("The mad scientist is making something, but\nwho knows what.");
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
