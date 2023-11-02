package Maps;

import java.awt.Color;
import java.util.ArrayList;
import Engine.GraphicsHandler;
import javax.sound.sampled.Clip;
import Items.Computer;
import Items.Sword;
import Items.TimeMachine;
import Level.Level;
import Level.LevelManager;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.AnimatedTable;
import NPCs.DaggerTable;
import NPCs.MadScientist;
import Scripts.ChangeLevelScript;
import Scripts.NewMap.madScientistScript;
import Scripts.ScienceLab.AnimatedTableScript;
import Scripts.ScienceLab.DaggerTableScript;
import Scripts.ScienceLab.secondMadScientistScript;
import Scripts.ScienceLab.triggerForScientist;
import Scripts.ScienceLab.triggerForScientistScriptV2;
import Tilesets.ScienceLabTilset;

public class ScienceLabMap extends Map
{
    public ScienceLabMap()
    {
        super("science_lab_map.txt", new ScienceLabTilset());
        this.playerStartPosition = getMapTile(16, 32).getLocation();
        addMusic("Resources/Audio/scienceLab.wav");
    }
    
    @Override 
    public ArrayList<NPC> loadNPCs()
    {
        ArrayList<NPC> npcs = new ArrayList<>();
        
        Sword sword = new Sword(1, getMapTile(16, 28).getLocation());
        npcs.add(sword);

        TimeMachine timeMachine = new TimeMachine(2, getMapTile(25, 14).getLocation(), 26, 37);
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

        AnimatedTable animatedTable = new AnimatedTable(6, getMapTile(10, 11).getLocation().subtractY(7));
        animatedTable.setInteractScript(new AnimatedTableScript());
        npcs.add(animatedTable);

        DaggerTable daggerTable = new DaggerTable(6, getMapTile(10, 17).getLocation().subtractY(7));
        daggerTable.setInteractScript(new DaggerTableScript());
        npcs.add(daggerTable);

        DaggerTable daggerTable2 = new DaggerTable(6, getMapTile(20, 17).getLocation().subtractY(7));
        npcs.add(daggerTable2);
        
        return npcs;
    } 

    @Override
    public ArrayList<Trigger> loadTriggers()
    {
        ArrayList<Trigger> triggers = new ArrayList<>();
        //just comment out the line below if you want to test the map without the trigger
       //triggers.add(new Trigger(770, 1042, 50,10, new triggerForScientist(), "hasTalkedToScientist"));
        return triggers;
    }

}
