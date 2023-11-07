package Maps;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;

import Engine.GraphicsHandler;
import GameObject.Frame;
import GameObject.Item;
import Items.BuildingEntrance;
import Items.Computer;
import Items.Sword;
import Items.TimeMachine;
import Level.LevelManager;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Dinosaur;
import NPCs.Prehistoric.BlueDino;
import NPCs.Prehistoric.YellowDino;
import NPCs.ScienceLab.AnimatedTable;
import NPCs.ScienceLab.DaggerTable;
import NPCs.ScienceLab.ItemTable;
import NPCs.ScienceLab.MadScientist;
import Scripts.ChangeLevelByString;
import Scripts.ChangeLevelScript;
import Scripts.ScienceLab.AnimatedTableScript;
import Scripts.ScienceLab.DaggerTableScript;
import Scripts.ScienceLab.ItemTableScript;
import Scripts.ScienceLab.secondMadScientistScript;
import Tilesets.PrehistoricTileset;
import Tilesets.ScienceLabTilset;
import Utils.Point;

public class PrehistoricMap extends Map {
  public PrehistoricMap() {
    super("prehistoric_map.txt", new PrehistoricTileset());
    addMusic("Resources/Audio/prehistoric theme.wav");
    this.playerStartPosition = getMapTile(5, 3).getLocation();
    //setCenterCamera();

  }

  public ArrayList<NPC> loadNPCs()
    {
        ArrayList<NPC> npcs = new ArrayList<>();
        
        Dinosaur dinosaur = new Dinosaur(1, getMapTile(16, 4).getLocation());
        npcs.add(dinosaur);

        BlueDino blueDino = new BlueDino(3, getMapTile(8, 2).getLocation());
        npcs.add(blueDino);

        YellowDino yellowDino = new YellowDino(4, getMapTile(22, 14).getLocation());
        npcs.add(yellowDino);

        TimeMachine timeMachine = new TimeMachine(2, getMapTile(25, 4).getLocation(), 26, 37);
        timeMachine.setInteractScript(new ChangeLevelScript(LevelManager.LAB));
        npcs.add(timeMachine);
        
        return npcs;
    } 

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList list = new ArrayList<>();

       // BuildingEntrance saloonEntrance = new BuildingEntrance(0, getMapTile(4, 0).getLocation(), 96, 32, "wildwest");

   // list.add(saloonEntrance);
    return list;
  }

  @Override
  public ArrayList<Trigger> loadTriggers() {
    ArrayList<Trigger> triggers = new ArrayList<>();
    // triggers.add(new Trigger(770, 1042, 50,10, new triggerForScientist(),
    // "hasTalkedToScientist"));
    return triggers;
  }

}
