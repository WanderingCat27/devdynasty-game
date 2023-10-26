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
import Tilesets.ScienceLabTilset;
import Utils.Point;

public class SaloonMap extends Map {
  public SaloonMap() {
    super("saloon_map.txt", new ScienceLabTilset());
    addMusic("Resources/Audio/scienceLab.wav");
    this.playerStartPosition = getMapTile(5, 0).getLocation();

  }

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList list = new ArrayList<>();

        BuildingEntrance saloonEntrance = new BuildingEntrance(0, getMapTile(4, 0).getLocation(), 96, 32, "wildwest");

    list.add(saloonEntrance);
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
