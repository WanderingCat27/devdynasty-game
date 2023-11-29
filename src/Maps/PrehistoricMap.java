package Maps;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.Item;
import GameObject.Sprite;
import Items.BuildingEntrance;
import Items.Cave;
import Items.Computer;
import Items.PurplePotion;
import Items.Sword;
import Items.TimeMachine;
import Level.LevelManager;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Dinosaur;
import NPCs.Prehistoric.BlueDino;
import NPCs.Prehistoric.Caveman;
import NPCs.Prehistoric.YellowDino;
import NPCs.ScienceLab.AnimatedTable;
import NPCs.ScienceLab.DaggerTable;
import NPCs.ScienceLab.ItemTable;
import NPCs.ScienceLab.MadScientist;
import Scripts.ChangeLevelByString;
import Scripts.ChangeLevelScript;
import Scripts.Prehistoric.CavemanScript;
import Scripts.ScienceLab.AnimatedTableScript;
import Scripts.ScienceLab.DaggerTableScript;
import Scripts.ScienceLab.ItemTableScript;
import Scripts.ScienceLab.secondMadScientistScript;
import Tilesets.PrehistoricTileset;
import Tilesets.ScienceLabTilset;
import Utils.Colors;
import Utils.Point;
import ui.SpriteUI.SpriteUI;

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
        timeMachine.setIsHidden(true);
        npcs.add(timeMachine);

        Caveman caveman = new Caveman(5, getMapTile(12, 5).getLocation(), 30);
        caveman.setInteractScript(new CavemanScript());
        npcs.add(caveman);

        Cave cave = new Cave(6, getMapTile(20, 15).getLocation(), 67, 67);
        cave.setInteractScript(new ChangeLevelScript(LevelManager.CAVE));
        npcs.add(cave);
        return npcs;
    } 

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList list = new ArrayList<>();

       PurplePotion purplePotion = new PurplePotion(0, getMapTile(4, 3).getLocation());

      list.add(purplePotion);



      BuildingEntrance caveEntrance = new BuildingEntrance(1, getMapTile(20, 16).getLocation(), 20, 200, "cave");
      list.add(caveEntrance);
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
