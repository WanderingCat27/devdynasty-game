package Maps.Future;

import java.util.ArrayList;

import GameObject.Item;
import Items.BuildingEntrance;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Saloon.BarTable;
import NPCs.Saloon.RoundTable;
import Tilesets.FutureIndoorTileset;
import Tilesets.SaloonTileset;

public class FloorOneMap extends Map{
  public FloorOneMap() {
    super("floor1_map.txt", new FutureIndoorTileset());
    addMusic("Resources/Audio/future.wav");
    this.playerStartPosition = getMapTile(15, 2).getLocation();
  }

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList<Item> list = new ArrayList<>();
    BuildingEntrance elevator = new BuildingEntrance(0, getMapTile(14, 2).getLocation(), 160, 32, "floor2");
    list.add(elevator);
    return list;
  }


}
