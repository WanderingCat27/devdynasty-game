package Maps.Future;

import java.util.ArrayList;
import java.util.List;

import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.Item;
import GameObject.SpriteSheet;
import Items.BuildingEntrance;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.InteractableObject;
import NPCs.Future.RobotNPCS;
import NPCs.Saloon.BarTable;
import NPCs.Saloon.RoundTable;
import NPCs.ScienceLab.AnimatedTable;
import Scripts.Future.RobotTwoScript;
import Tilesets.FutureIndoorTileset;
import Tilesets.SaloonTileset;
import Utils.ImageUtils;

public class FloorOneMap extends Map {
  public FloorOneMap() {
    super("floor1_map.txt", new FutureIndoorTileset());
    addMusic("Resources/Audio/future.wav");
    this.playerStartPosition = getMapTile(37, 2).getLocation();
  }

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList<Item> list = new ArrayList<>();
    BuildingEntrance elevator = new BuildingEntrance(0, getMapTile(36, 2).getLocation(), 160, 32, "floor2");
    list.add(elevator);
    return list;
  }

  @Override
  public ArrayList<NPC> loadNPCs() {
    ArrayList<NPC> list = new ArrayList<>();
    int id = 10;

    // lab -----
    // spawn cabinets
    for (int row = 0; row < 3; row++)
      for (int col = 0; col < 7; col++)
        if (row == 0 && col == 0) // the row and col of the special filing cabinet

          list.add(new InteractableObject(id++, getMapTile(26 + col * 2, 9 + row * 3).getLocation(),
              new String[] { "Wait there is something in here.", "You see the second code number is \"4\"" },
              new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("file_cabinet.png"), 3))));
        else
          list.add(new InteractableObject(id++, getMapTile(26 + col * 2, 9 + row * 3).getLocation(),
              new String[] { "No code number here" },
              new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("file_cabinet.png"), 3))));

    // spawn shelfs
    for (int col = 0; col < 8; col++)
      list.add(new InteractableObject(id++, getMapTile(16 + col * 3, 6).getLocation(),
          new String[] { "Just boring documents" },
          new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("shelf.png"), 2))));

    // printer
    list.add(new InteractableObject(id++, getMapTile(16, 10).getLocation(),
        new String[] { "Looks like a printer, but more advanced?" },
        new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("printer.png"), 2))));

    RobotNPCS robot1 = new RobotNPCS(9, getMapTile(18, 9).getLocation());
    robot1.setInteractScript(new RobotTwoScript());
    list.add(robot1);

    // office -----
    // printer
    list.add(new InteractableObject(id++, getMapTile(16, 23).getLocation(),
        new String[] { "Looks like a printer, but more advanced?" },
        new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("printer.png"), 2))));

    // file cabinet
    list.add(new InteractableObject(id++, getMapTile(16, 18).getLocation(),
        new String[] { "Nothing here" },
        new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("file_cabinet.png"), 3))));

    // spawn shelfs
    for (int col = 0; col < 7; col++)
      list.add(new InteractableObject(id++, getMapTile(19 + col * 3, 18).getLocation(),
          new String[] { "Just boring documents" },
          new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("shelf.png"), 2))));

    // lab ---------

    // shelfs
    for (int col = 0; col < 2; col++)
      list.add(new InteractableObject(id++, getMapTile(3 + col * 5, 6).getLocation(),
          new String[] { "Lab supplies" },
          new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("shelf.png"), 2))));

    // printer
    list.add(new InteractableObject(id++, getMapTile(0, 18).getLocation(),
        new String[] { "Looks like a printer, but more advanced?" },
        new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("printer.png"), 2))));

    // lab tables
    list.add(new AnimatedTable(id++, getMapTile(0, 22).getLocation()));
    list.add(new AnimatedTable(id++, getMapTile(8, 22).getLocation()));

    // janitor closet
    // shelf
    list.add(new InteractableObject(id++, getMapTile(2, 1).getLocation(),
        new String[] { "Storage for cleaning products" },
        new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("shelf.png"), 2))));

    // supplies
    list.add(new InteractableObject(id++, getMapTile(0, 4).getLocation(),
        new String[] { "A bunch of cleaning supplies" },
        new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("supplies.png"), 3))));

    return list;
  }

}
