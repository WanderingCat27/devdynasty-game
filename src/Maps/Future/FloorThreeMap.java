package Maps.Future;

import java.util.ArrayList;

import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.Item;
import Items.BuildingEntrance;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.InteractableObject;
import NPCs.Future.Robot;
import NPCs.Future.RobotNPCS;
import NPCs.Future.RobotSecurity;
import NPCs.Saloon.BarTable;
import Scripts.Future.RobotEnemy;
import NPCs.Saloon.RoundTable;
import Scripts.Future.RobotOneScript;
import Scripts.Future.RobotSecurityScript;
import Tilesets.FutureIndoorTileset;
import Tilesets.SaloonTileset;
import Utils.ImageUtils;

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

      // furniture 
      npcs.add(new InteractableObject(10, getMapTile(10, 12).getLocation(),
        new String[] { "Looks like a printer, but more advanced?" },
        new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("printer.png"), 2))));

      npcs.add(new InteractableObject(11, getMapTile(4, 1).getLocation(),
          new String[] { "Evil supplies" },
          new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("shelf.png"), 2))));
      npcs.add(new InteractableObject(12, getMapTile(8, 1).getLocation(),
          new String[] { "Evil supplies" },
          new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("shelf.png"), 2))));
          npcs.add(new InteractableObject(13, getMapTile(2, 1).getLocation(),
          new String[] { "locked" },
          new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("file_cabinet.png"), 3))));


          // boss
      Robot robotEvil = new Robot(9, getMapTile(4, 8).getLocation(), 50);
      robotEvil.setInteractScript(new RobotEnemy());
      npcs.add(robotEvil);

      return npcs;
  }


}
