package Maps;

import java.util.ArrayList;

import Items.Sword;
import Items.TimeMachine;
import Level.LevelManager;
import Level.Map;
import Level.NPC;
import NPCs.MadScientist;
import Scripts.ChangeLevelScript;
import Scripts.NewMap.madScientistScript;
import Tilesets.ScienceLabTilset;

public class ScienceLabMap extends Map
{
    public ScienceLabMap()
    {
        super("science_lab_map.txt", new ScienceLabTilset());
        this.playerStartPosition = getMapTile(5, 5).getLocation();
        addMusic("Resources/Audio/AmTronic_-_Caribbean_Dub.wav");
        this.setFlagManager(flagManager);
    }
    
    @Override 
    public ArrayList<NPC> loadNPCs()
    {
        ArrayList<NPC> npcs = new ArrayList<>();
        
        Sword sword = new Sword(1, getMapTile(8, 8).getLocation());
        npcs.add(sword);

        TimeMachine timeMachine = new TimeMachine(2, getMapTile(28, 10).getLocation());
        timeMachine.setInteractScript(new ChangeLevelScript(LevelManager.WILDWEST));
        npcs.add(timeMachine);

        MadScientist madScientist = new MadScientist(3, getMapTile(27, 14).getLocation().subtractY(40));
        madScientist.setInteractScript(new madScientistScript());
        npcs.add(madScientist);
        return npcs;
    }
}
