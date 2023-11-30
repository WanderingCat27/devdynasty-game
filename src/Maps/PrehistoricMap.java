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
import Items.Flashlight;
import Items.PurplePotion;
import Items.Sword;
import Items.TimeMachine;
import Level.GlobalFlagManager;
import Level.LevelManager;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Dinosaur;
import NPCs.Prehistoric.BlueDino;
import NPCs.Prehistoric.Caveman;
import NPCs.Prehistoric.Caveman2;
import NPCs.Prehistoric.YellowDino;
import NPCs.ScienceLab.AnimatedTable;
import NPCs.ScienceLab.DaggerTable;
import NPCs.ScienceLab.ItemTable;
import NPCs.ScienceLab.MadScientist;
import Scripts.ChangeLevelByString;
import Scripts.ChangeLevelScript;
import Scripts.SimpleTextScript;
import Scripts.NewMap.SwordScript;
import Scripts.Prehistoric.BlueDinoScript;
import Scripts.Prehistoric.Caveman2Script;
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
        dinosaur.setInteractScript(new SimpleTextScript("Grr... I'm just minding my own business over here..."));
        npcs.add(dinosaur);

        BlueDino blueDino = new BlueDino(3, getMapTile(8, 2).getLocation(), 25);
        blueDino.setInteractScript(new BlueDinoScript());
        npcs.add(blueDino);

        YellowDino yellowDino = new YellowDino(4, getMapTile(22, 14).getLocation());
        yellowDino.setInteractScript(new SimpleTextScript("Me? Steal a sword? I would never!"));
        npcs.add(yellowDino);

        TimeMachine timeMachine = new TimeMachine(2, getMapTile(25, 4).getLocation(), 26, 37);
        timeMachine.setInteractScript(new ChangeLevelScript(LevelManager.LAB));
        timeMachine.setIsHidden(true);
        npcs.add(timeMachine);


        Cave cave = new Cave(7, getMapTile(20, 15).getLocation(), 67, 67);
        cave.setInteractScript(new ChangeLevelScript(LevelManager.CAVE));
        npcs.add(cave);
        Caveman2 caveman2 = new Caveman2(6, getMapTile(18, 2).getLocation());
        caveman2.setInteractScript(new Caveman2Script());
        npcs.add(caveman2);

        
        return npcs;
    } 

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList<Item> list = new ArrayList<>();

      PurplePotion purplePotion = new PurplePotion(0, getMapTile(17, 2).getLocation());
      list.add(purplePotion);

      Flashlight flashlight = new Flashlight(8, getMapTile(21, 14).getLocation());

      list.add(flashlight);

      BuildingEntrance caveEntrance = new BuildingEntrance(1, getMapTile(20, 16).getLocation(), 20, 200, "cave");
      list.add(caveEntrance);
      purplePotion.setIsHidden(true);
      Sword sword = new Sword(1, getMapTile(4, 4).getLocation());
      sword.setIsHidden(true);
      list.add(sword);

      
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
