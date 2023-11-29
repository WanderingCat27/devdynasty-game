package Maps.Future;

import java.util.ArrayList;

import GameObject.Item;
import Items.BuildingEntrance;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Future.RobotNPCS;
import NPCs.Saloon.BarTable;
import NPCs.Saloon.RoundTable;
import Scripts.Future.RobotTwoScript;
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

  public ArrayList<NPC> loadNPCs() {
      ArrayList<NPC> npcs = new ArrayList<>();

      RobotNPCS robot1 = new RobotNPCS(9, getMapTile(8, 6).getLocation());
      robot1.setInteractScript(new RobotTwoScript());
      npcs.add(robot1);

      return npcs;
  }


}
