package Maps.Future;

import java.util.ArrayList;

import GameObject.Item;
import Items.BuildingEntrance;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Future.Robot;
import NPCs.Future.RobotNPCS;
import NPCs.Future.RobotSecurity;
import Scripts.Future.RobotOneScript;
import Scripts.Future.RobotSecurityScript;
import NPCs.Saloon.BarTable;
import NPCs.Saloon.RoundTable;
import Scripts.Future.RobotEnemy;
import Tilesets.FutureIndoorTileset;
import Tilesets.SaloonTileset;

public class ReceptionMap extends Map{
  public ReceptionMap() {
    super("reception_map.txt", new FutureIndoorTileset());
    addMusic("Resources/Audio/future.wav");
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

  public ArrayList<NPC> loadNPCs() {
      ArrayList<NPC> npcs = new ArrayList<>();

      RobotNPCS robot1 = new RobotNPCS(9, getMapTile(8, 6).getLocation());
      robot1.setInteractScript(new RobotOneScript());
      npcs.add(robot1);

      return npcs;
  }


}
