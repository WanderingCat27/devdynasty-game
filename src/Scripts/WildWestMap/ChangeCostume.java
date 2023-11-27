package Scripts.WildWestMap;

import Engine.ImageLoader;
import GameObject.Sprite;
import GameObject.SpriteSheet;
import Level.Level;
import Level.LevelManager;
import Level.NPC;
import Level.Script;
import Level.ScriptState;
import Players.PlayerAsh;

public class ChangeCostume extends Script<NPC>
{
    private SpriteSheet cowboyCostume;

    public ChangeCostume()
    {
        this.cowboyCostume = new SpriteSheet(ImageLoader.load("CowboyPlayer.png"), 14, 19);
    }
    @Override
    protected void setup()
    {
        lockPlayer();
        entity.facePlayer(player);
        if(!isFlagSet("hasChangedCostume"))
        {
            showTextbox();
            addTextToTextboxQueue("Hmm...you don't look like you're from around here.");
            addTextToTextboxQueue("The costume you have on is a bit out of place.");
            addTextToTextboxQueue("It almost reminds me of an old friend I had, though...");
            addTextToTextboxQueue("Perhaps the mad scientist...?");
            addTextToTextboxQueue("If thats the case, I have a\ncostume that might suit you better.");
            addTextToTextboxQueue("Consider it a gift from me to you.");
            addTextToTextboxQueue("As for the mad scientist, well\nwish him luck for me.");
            setFlag("hasChangedCostume");
        }
        else
        {
            showTextbox();
            addTextToTextboxQueue("Howdy again!");
            addTextToTextboxQueue("It's a beautiful day to be a cowboy...");
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
        if (!isTextboxQueueEmpty()) {
            return ScriptState.RUNNING;
        }
        this.player.changeCostume(cowboyCostume, "STAND_RIGHT");
        end();
        return ScriptState.COMPLETED;
    }
    
}
