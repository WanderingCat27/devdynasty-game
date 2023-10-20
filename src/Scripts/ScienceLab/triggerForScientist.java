package Scripts.ScienceLab;

import Level.Map;
import Level.NPC;

import java.util.ArrayList;
import Level.Script;
import Level.ScriptState;
import Utils.Direction;

public class triggerForScientist extends Script
{
    private int sequence = 0;
    private int amountMoved = 0;

    @Override
    protected void setup()
    {
        lockPlayer();
        if(isFlagSet("hasTalkedToScientist"))
        {
            showTextbox();
            addTextToTextboxQueue("Leave me, for I am busy beyond belief.");
        }
        else if(!isFlagSet("hasTalkedToScientist"))
        {
            if(sequence == 0)
            {
                setWaitTime(60);
            }
            else if(sequence == 1)
            {
                getNPC(3).stand(Direction.LEFT);
                amountMoved = 0;
            }
            else if(sequence == 2)
            {
                getNPC(3).stand(Direction.DOWN);
                amountMoved = 0;
            }
            else if(sequence == 3)
            {
                amountMoved = 0;
                showTextbox();
                addTextToTextboxQueue("Oh, hello there!");
                addTextToTextboxQueue("I havent seen you in quite sometime...");
                addTextToTextboxQueue("I've been thinking about fixing this time\nmachine...maybe to get us home.");
                addTextToTextboxQueue("Heres what you have to do...");
                addTextToTextboxQueue("I will send you to various time periods\nto search for various objects.");
                addTextToTextboxQueue("After you get the parts, I\nbelieve I can fix the the time machine.");
                addTextToTextboxQueue("I'm sure your journey will be smooth sailing...");
                addTextToTextboxQueue("Goodluck!");
            }
            else if(sequence == 4)
            {
                getNPC(3).stand(Direction.UP);
                amountMoved = 0;
            }
            else if(sequence == 5)
            {
                getNPC(3).stand(Direction.RIGHT);
                amountMoved = 0;
            }
            else if(sequence == 6)
            {
                getNPC(3).stand(Direction.UP);
            }
        }
    }

    @Override
    protected void cleanup()
    {
        //System.out.println("Mad scientist cleanup");
        if(isFlagSet("hasTalkedToScientist"))
        {
            unlockPlayer();
            hideTextbox();
        }
        else if(!isFlagSet("hasTalkedToScientist"))
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
                hideTextbox();
                sequence++;
            }
            else if(sequence == 4)
            {
                sequence++;
            }
            else if(sequence == 5)
            {
                sequence++;
            }
            else if(sequence == 6)
            {
                sequence++;
                setFlag("hasTalkedToScientist");
                unlockPlayer();
            }
        }

    }

    @Override
    protected ScriptState execute()
    {
        if(isFlagSet("hasTalkedToScientist"))
        {
            start();
            if(!isTextboxQueueEmpty())
            {
                return ScriptState.RUNNING;
            }
            end();
            return ScriptState.COMPLETED;
        }
        else if(!isFlagSet("hasTalkedToScientist"))
        {
            //pauses
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
                getNPC(3).walk(Direction.LEFT, 2);
                amountMoved += 2;
                if(amountMoved >= 233)
                {
                    end();
                }
            }
            else if(sequence == 2)
            {
                start();
                getNPC(3).walk(Direction.DOWN, 2);
                amountMoved += 2;
                if(amountMoved >= 355)
                {
                    getNPC(3).stand(Direction.DOWN);
                    end();
                }
            }
            else if(sequence == 3)
            {
                start();
                if(isTextboxQueueEmpty())
                {
                    end();
                }
            }
            else if(sequence == 4)
            {
                start();
                getNPC(3).walk(Direction.UP, 2);
                amountMoved += 2;
                if(amountMoved >= 470)
                {
                    end();
                }
            }
            else if(sequence == 5)
            {
                start();
                getNPC(3).walk(Direction.RIGHT, 2);
                amountMoved += 2;
                if(amountMoved >= 47)
                {
                    end();
                }
            }
            else if(sequence == 6)
            {
                start();
                getNPC(3).stand(Direction.UP);
                end();
            }
            return ScriptState.RUNNING;
        }
        return ScriptState.COMPLETED;

    }
}