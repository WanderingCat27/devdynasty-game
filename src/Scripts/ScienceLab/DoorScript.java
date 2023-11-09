package Scripts.ScienceLab;

import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.Key;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.Inventory;
import Level.*;
import Utils.Direction;
import Utils.Point;

public class DoorScript extends Script
{
    private int sequence = 0;

    @Override
    protected void setup()
    {
        if(!isFlagSet("hasOpenedDoor"))
        {
            lockPlayer();

            if(sequence == 0)
            {
                setWaitTime(15);
            }
            else if(sequence == 1)
            {
                
            }
            else if(sequence == 2)
            {
                setWaitTime(15);
            }
            else if(sequence == 3)
            {

            }
        }
    }

    @Override
    protected void cleanup()
    {
        
        if(!isFlagSet("hasOpenedDoor"))
        {
            if(sequence == 0)
            {
                sequence++;
            }
            else if(sequence == 1)
            {
                sequence++;
            }
            else if(sequence == 2)
            {
                sequence++;
            }
            else if(sequence == 3)
            {
                sequence++;
                setFlag("hasOpenedDoor");
                getNPC(10).setIsUncollidable(true);
                unlockPlayer();
            }
        }

        
    }

    @Override
    protected ScriptState execute()
    {
        if(!isFlagSet("hasOpenedDoor"))
        {
            if(sequence == 0)
            {
                start();
                if(isWaitTimeUp())
                {
                    end();
                }
            }
            else if(sequence == 1)
            {
                start();
                getNPC(10).setCurrentAnimationName("SEMI_OPENED");
                end();
            }
            else if(sequence == 2)
            {
                start();
                if(isWaitTimeUp())
                {
                    end();
                }
            }
            else if(sequence == 3)
            {
                start();
                getNPC(10).setCurrentAnimationName("FULLY_OPENED");
                end();
            }
            return ScriptState.RUNNING;
        }
        return ScriptState.COMPLETED;
    }
    
}
