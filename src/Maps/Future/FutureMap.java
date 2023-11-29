package Maps.Future;

import java.util.ArrayList;

import GameObject.Item;
import Items.BuildingEntrance;
import Items.TimeMachine;
import Level.Map;
import Level.NPC;
import NPCs.OldCowboy;
import NPCs.Future.Robot;
import NPCs.Future.RobotNPCS;
import Scripts.WildWestMap.EvilBatScript;
import Scripts.WildWestMap.OldCowboyScript;
import Scripts.Future.RobotEnemy;
import Scripts.Future.RobotOneScript;
import Tilesets.FutureTileset;
import Tilesets.WestTileset;

// Represents a test map to be used in a level
public class FutureMap extends Map {

  public FutureMap() {
    super("future_map.txt", new FutureTileset());
    addMusic("Resources/Audio/future.wav");
    this.playerStartPosition = getMapTile(19, 20).getLocation();
  }

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList<Item> items = new ArrayList<>();

    items.add(new BuildingEntrance(1, getMapTile(14, 15).getLocation(), 128, 32, "reception"));
    return items;
    }

    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        return npcs;
    }

}
