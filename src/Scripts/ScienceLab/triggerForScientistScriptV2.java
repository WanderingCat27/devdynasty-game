package Scripts.ScienceLab;

import Level.Script;
import Level.ScriptState;
import Utils.Direction;

public class triggerForScientistScriptV2 extends Script
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
                addTextToTextboxQueue("How was your sleep?");
                addTextToTextboxQueue("Listen to me, we need to\nfix this time machine.");
                addTextToTextboxQueue("The machine works, but\nit's missing parts.");
                addTextToTextboxQueue("If I get these parts,\nI can send both of us home.");
                addTextToTextboxQueue("Let me show you around...");
            }
            else if(sequence == 4)
            {
                amountMoved = 0;
            }
            else if(sequence == 5)
            {
                amountMoved = 0;
            }
            else if(sequence == 6)
            {
                amountMoved = 0;
            }
            else if(sequence == 7)
            {

                amountMoved = 0;
                getNPC(3).stand(Direction.LEFT);
                player.stand(Direction.RIGHT);
                showTextbox();
                addTextToTextboxQueue("This table will keep track of the items\nyou have collected.");
                addTextToTextboxQueue("Once you defeat a boss, place\nthe item on this table.");
                addTextToTextboxQueue("You can also interact with this table\nat any time to see your progress.");
            }
            else if(sequence == 8)
            {
                amountMoved = 0;
            }
            else if(sequence == 9)
            {
                amountMoved = 0;
            }
            else if(sequence == 10)
            {
                amountMoved = 0;
            }
            else if(sequence == 11)
            {
                amountMoved = 0;
                showTextbox();
                addTextToTextboxQueue("This is the time machine.");
                addTextToTextboxQueue("Once you are ready to try\nand help, interact with it.");
                addTextToTextboxQueue("Unfortunately, where you go will be random.");
                addTextToTextboxQueue("Therefore, you must be prepared for anything.");
                addTextToTextboxQueue("Defeat the final boss at each level\nand return with the part.");
                addTextToTextboxQueue("Each destination should contain\nsome items and weapons to help you.");
                addTextToTextboxQueue("You also may need to collect some\nitems to be able to enter certain areas.");
                addTextToTextboxQueue("Any item you see can be collected\nby pressing the return key...");
                addTextToTextboxQueue("granted you are close enough\nto pick it up.");
                addTextToTextboxQueue("You can select an item in your inventory\nby pressing 1,2,3, or 4.");
                addTextToTextboxQueue("If you do not want to select an item,\npress the ` key, left of the 1 key.");
                addTextToTextboxQueue("I am sure your journey\nwill be smooth sailing...");
            }
            else if(sequence == 12)
            {
                amountMoved = 0;
            }
            else if(sequence == 13)
            {
                amountMoved = 0;
                showTextbox();
                addTextToTextboxQueue("Oh...and if you want to pause the game\n and adjust the sound, hit the ESC key.");
                addTextToTextboxQueue("Also, if you are within range\nof interacting with an item...");
                addTextToTextboxQueue("...you'll see an ! pop up above your head.");
                addTextToTextboxQueue("That's all I have to say for now.");
                addTextToTextboxQueue("Good luck!");
            }
            else if(sequence == 14)
            {
                amountMoved = 0;
            }
            else if(sequence == 15)
            {
                amountMoved = 0;
            }
        }
    }

    @Override
    protected void cleanup()
    {
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
            }
            else if(sequence == 7)
            {
                hideTextbox();
                sequence++;
            }
            else if(sequence == 8)
            {
                sequence++;
            }
            else if(sequence == 9)
            {
                sequence++;
            }
            else if(sequence == 10)
            {
                sequence++;
            }
            else if(sequence == 11)
            {
                hideTextbox();
                sequence++;
            }
            else if(sequence == 12)
            {
                sequence++;
            }
            else if(sequence == 13)
            {
                hideTextbox();
                sequence++;
            }
            else if(sequence == 14)
            {
                sequence++;
            }
            else if(sequence == 15)
            {
                sequence++;
                unlockPlayer();
                setFlag("hasTalkedToScientist");
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
                System.out.println("Executing sequence 0");
                start();
                if(isWaitTimeUp())
                {
                    end();
                }
            }
            else if(sequence == 1)
            {
                System.out.println("Executing sequence 1");
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
                System.out.println("Executing sequence 2");
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
                System.out.println("Executing sequence 3");
                start();
                if(isTextboxQueueEmpty())
                {
                    end();
                }
            }
            else if(sequence == 4)
            {
                System.out.println("Executing sequence 4");
                start();
                getNPC(3).walk(Direction.UP, 2);
                player.setWalking(true);
                player.walk(Direction.UP, 2);
                amountMoved += 2;
                if(amountMoved >= 50)
                {
                    player.setWalking(false);
                    end();
                }
            }
            else if(sequence == 5)
            {
                System.out.println("Executing sequence 5");
                //Player needs to continue walking up to match the Y level of the scientist
                start();
                getNPC(3).walk(Direction.RIGHT, 2);
                player.setWalking(true);
                player.walk(Direction.UP, 2);
                amountMoved += 2;
                if(amountMoved >= 55)
                {
                    player.setWalking(false);
                    end();
                }
            }
            else if(sequence == 6)
            {
                System.out.println("Executing sequence 6");
                start();
                getNPC(3).walk(Direction.RIGHT,2);
                player.setWalking(true);
                player.walk(Direction.RIGHT, 2);
                amountMoved += 2;
                if(amountMoved >= 180)
                {
                    player.setWalking(false);
                    end();
                }
              
            }
            else if(sequence == 7)
            {
                System.out.println("Executing sequence 7");
                //Stop and explain the table
                start();
                if(isTextboxQueueEmpty())
                {
                    end();
                }
            }
            else if(sequence == 8)
            {
                System.out.println("Executing sequence 8");
                start();
                getNPC(3).walk(Direction.RIGHT, 2);
                player.setWalking(true);
                player.walk(Direction.RIGHT, 2);
                amountMoved += 2;
                if(amountMoved >= 147)
                {
                    player.setWalking(false);
                    end();
                }
            }
            else if(sequence == 9)
            {
                System.out.println("Executing sequence 9");
                //Scientist turns upwards, player has to keep walking a bit
                start();
                getNPC(3).walk(Direction.UP, 2);
                player.setWalking(true);
                player.walk(Direction.RIGHT, 2);
                amountMoved += 2;
                if(amountMoved >= 57)
                {
                    player.setWalking(false);
                    end();
                }
            }
            else if(sequence == 10)
            {
                System.out.println("Executing sequence 10");
                start();
                getNPC(3).walk(Direction.UP, 2);
                player.setWalking(true);
                player.walk(Direction.UP, 2);
                amountMoved += 2;
                if(amountMoved >= 180)
                {
                    player.setWalking(false);
                    end();
                }
            }
            else if(sequence == 11)
            {
                System.out.println("Executing sequence 11");
                start();
                getNPC(3).stand(Direction.DOWN);
                player.stand(Direction.UP);
                if(isTextboxQueueEmpty())
                {
                    end();
                }
            }
            else if(sequence == 12)
            {
                start();
                getNPC(3).walk(Direction.UP, 2);
                amountMoved += 2;
                if(amountMoved >= 180)
                {
                    end();
                }
            }
            else if(sequence == 13)
            {
                start();
                getNPC(3).stand(Direction.DOWN);
                if(isTextboxQueueEmpty())
                {
                    end();
                }
            }
            else if(sequence == 14)
            {
                start();
                getNPC(3).walk(Direction.LEFT, 2);
                amountMoved += 2;
                if(amountMoved >= 334)
                {
                    end();
                }
            }
            else if(sequence == 15)
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

