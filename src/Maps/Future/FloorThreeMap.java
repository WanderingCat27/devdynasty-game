package Maps.Future;

import java.util.ArrayList;

import GameObject.Item;
import Items.BuildingEntrance;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Future.Robot;
import NPCs.Future.RobotNPCS;
import NPCs.Saloon.BarTable;
import Scripts.Future.RobotEnemy;
import NPCs.Saloon.RoundTable;
import Scripts.Future.RobotOneScript;
import Tilesets.FutureIndoorTileset;
import Tilesets.SaloonTileset;

public class FloorThreeMap extends Map{
  public FloorThreeMap() {
    super("floor3_map.txt", new FutureIndoorTileset());
    addMusic("Resources/Audio/future.wav");
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

  public ArrayList<NPC> loadNPCs() {
      ArrayList<NPC> npcs = new ArrayList<>();

      Robot robotEvil = new Robot(9, getMapTile(3, 8).getLocation(), 50);
      robotEvil.setInteractScript(new RobotEnemy());
      npcs.add(robotEvil);

      return npcs;
  }


}
