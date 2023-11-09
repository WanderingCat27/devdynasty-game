package Maps.Future;

import java.util.ArrayList;

import GameObject.Item;
import Items.BuildingEntrance;
import Level.Map;
import Tilesets.FutureTileset;
import Tilesets.WestTileset;

// Represents a test map to be used in a level
public class FutureMap extends Map {

  public FutureMap() {
    super("future_map.txt", new FutureTileset());
    this.playerStartPosition = getMapTile(19, 54).getLocation();
    addMusic("Resources/Audio/wildWest.wav");
  }

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList<Item> items = new ArrayList<>();

    items.add(new BuildingEntrance(1, getMapTile(14, 15).getLocation(), 128, 32, "reception"));
    return items;
    }

}
