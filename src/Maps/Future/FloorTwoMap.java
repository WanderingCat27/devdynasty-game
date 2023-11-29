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

public class FloorTwoMap extends Map{
  public FloorTwoMap() {
    super("floor2_map.txt", new FutureIndoorTileset());
    addMusic("Resources/Audio/future.wav");
    this.playerStartPosition = getMapTile(38, 2).getLocation();
  }

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList<Item> list = new ArrayList<>();
    BuildingEntrance elevator = new BuildingEntrance(0, getMapTile(37, 2).getLocation(), 160, 32, "floor3");
    list.add(elevator);
    return list;
  }


}
