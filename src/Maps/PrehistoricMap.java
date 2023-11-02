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
import Level.LevelManager;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import Scripts.ChangeLevelByString;
import Scripts.ChangeLevelScript;
import Tilesets.PrehistoricTileset;
import Tilesets.ScienceLabTilset;
import Utils.Point;

public class PrehistoricMap extends Map {
  public PrehistoricMap() {
    super("prehistoric_map.txt", new PrehistoricTileset());
    addMusic("Resources/Audio/saloon.wav");
    this.playerStartPosition = getMapTile(5, 0).getLocation();
    setCenterCamera();

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
