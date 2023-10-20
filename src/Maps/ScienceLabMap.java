package Maps;

import java.awt.Color;
import java.util.ArrayList;

import Engine.GraphicsHandler;
import Items.Computer;
import Items.Sword;
import Items.TimeMachine;
import Level.Level;
import Level.LevelManager;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.MadScientist;
import Scripts.ChangeLevelScript;
import Scripts.NewMap.madScientistScript;
import Scripts.ScienceLab.secondMadScientistScript;
import Scripts.ScienceLab.triggerForScientist;
import Tilesets.ScienceLabTilset;

public class ScienceLabMap extends Map
{
    public ScienceLabMap()
    {
        super("science_lab_map.txt", new ScienceLabTilset());
        this.playerStartPosition = getMapTile(16, 32).getLocation();
        addMusic("Resources/Audio/AmTronic_-_Caribbean_Dub.wav");
    }
    
    @Override 
    public ArrayList<NPC> loadNPCs()
    {
        ArrayList<NPC> npcs = new ArrayList<>();
        
        Sword sword = new Sword(1, getMapTile(16, 28).getLocation());
        npcs.add(sword);

        TimeMachine timeMachine = new TimeMachine(2, getMapTile(15, 20).getLocation());
        timeMachine.setInteractScript(new ChangeLevelScript(LevelManager.WILDWEST));
        npcs.add(timeMachine);

        MadScientist madScientist = new MadScientist(3, getMapTile(21, 14).getLocation().subtractY(40));
        madScientist.setInteractScript(new secondMadScientistScript());
        npcs.add(madScientist);

        //add computer
        Computer computer = new Computer(4, getMapTile(17, 10).getLocation().subtractY(7));
        //add interact script
        npcs.add(computer);

        Computer computer2 = new Computer(5, getMapTile(15, 10).getLocation().subtractY(7));
        //add interact script
        npcs.add(computer2);
        
        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers()
    {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(770, 1042, 50,10, new triggerForScientist(), "hasTalkedToScientist"));
        return triggers;
    }

}
