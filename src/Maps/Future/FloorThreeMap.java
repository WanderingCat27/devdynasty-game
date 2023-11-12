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

public class FloorThreeMap extends Map{
  public FloorThreeMap() {
    super("floor3_map.txt", new FutureIndoorTileset());
    addMusic("Resources/Audio/saloon.wav");
    this.playerStartPosition = getMapTile(15, 2).getLocation();
    setCenterCamera();
  }

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList<Item> list = new ArrayList<>();
    BuildingEntrance elevator = new BuildingEntrance(0, getMapTile(14, 2).getLocation(), 160, 32, "reception");
    list.add(elevator);
    return list;
  }


}
