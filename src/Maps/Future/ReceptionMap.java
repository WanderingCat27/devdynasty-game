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

public class ReceptionMap extends Map{
  public ReceptionMap() {
    super("reception_map.txt", new FutureIndoorTileset());
    addMusic("Resources/Audio/saloon.wav");
    this.playerStartPosition = getMapTile(9, 16).getLocation();
    setCenterCamera();
  }

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList<Item> list = new ArrayList<>();
    BuildingEntrance elevator = new BuildingEntrance(0, getMapTile(13, 2).getLocation(), 160, 32, "floor1");
    list.add(elevator);
    BuildingEntrance receptionExit = new BuildingEntrance(0, getMapTile(8, 16).getLocation(), 160, 32, "future");
    list.add(receptionExit);
    return list;
  }


}
