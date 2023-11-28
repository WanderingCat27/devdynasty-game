package Maps.Future;

import java.util.ArrayList;

import GameObject.Item;
import Items.BuildingEntrance;
import Items.TimeMachine;
import Level.Map;
import Level.NPC;
import NPCs.OldCowboy;
import NPCs.Robot;
import Scripts.WildWestMap.OldCowboyScript;
import Tilesets.FutureTileset;
import Tilesets.WestTileset;

// Represents a test map to be used in a level
public class FutureMap extends Map {

  public FutureMap() {
    super("future_map.txt", new FutureTileset());
    addMusic("Resources/Audio/future.wav");
    this.playerStartPosition = getMapTile(19, 54).getLocation();
  }

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList<Item> items = new ArrayList<>();

    items.add(new BuildingEntrance(1, getMapTile(14, 15).getLocation(), 128, 32, "reception"));
    return items;
    }

    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Robot robot1 = new Robot(9, getMapTile(13, 15).getLocation());
        robot1.setInteractScript(new OldCowboyScript());
        npcs.add(robot1);

        return npcs;
    }

}
