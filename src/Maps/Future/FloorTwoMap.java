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
import NPCs.Saloon.BarTable;
import NPCs.Saloon.RoundTable;
import Tilesets.FutureIndoorTileset;
import Tilesets.SaloonTileset;
import Utils.ImageUtils;

public class FloorTwoMap extends Map {
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

  @Override
  public ArrayList<NPC> loadNPCs() {
    ArrayList<NPC> list = new ArrayList<>();
    int id = 10;

    // main room
    // shelfs
    list.add(new InteractableObject(id++, getMapTile(10, 1).getLocation(),
        new String[] { "Bunch of office supplies" },
        new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("shelf.png"), 2))));
    list.add(new InteractableObject(id++, getMapTile(13, 1).getLocation(),
        new String[] { "Bunch of office supplies" },
        new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("shelf.png"), 2))));

    // printer
    list.add(new InteractableObject(id++, getMapTile(34, 21).getLocation(),
        new String[] { "Looks like a printer, but more advanced?" },
        new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("printer.png"), 2))));
    list.add(new InteractableObject(id++, getMapTile(34, 23).getLocation(),
        new String[] { "Looks like a printer, but more advanced?" },
        new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("printer.png"), 2))));

    // file cabinet
    list.add(new InteractableObject(id++, getMapTile(35, 1).getLocation(),
        new String[] { "Its locked, I don't need this information" },
        new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("file_cabinet.png"), 3))));
    list.add(new InteractableObject(id++, getMapTile(34, 1).getLocation(),
        new String[] { "Its locked, I don't need this information" },
        new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("file_cabinet.png"), 3))));

    // cubicals
    for (int row = 0; row < 5; row++)
      for (int col = 0; col < 5; col++)
        if (row != 2)
          list.add(new NPC(id++, getMapTile(15 + col * 4, 3 + row * 5).getLocation(),
              new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("cubical.png"), 3))));

    // janitors closet
    // shelf
    list.add(new InteractableObject(id++, getMapTile(3, 22).getLocation(),
        new String[] { "Storage for cleaning products" },
        new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("shelf.png"), 2))));
    list.add(new InteractableObject(id++, getMapTile(6, 22).getLocation(),
        new String[] { "Storage for cleaning products" },
        new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("shelf.png"), 2))));

    // supplies
    list.add(new InteractableObject(id++, getMapTile(0, 23).getLocation(),
        new String[] { "A bunch of cleaning supplies" },
        new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("supplies.png"), 3))));
    
    // camera room
    list.add(new InteractableObject(id++, getMapTile(0, 9).getLocation(), new String[] {"Seems to be a camera monitor", "Hopefully I am not caught on camera"}, new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("camera_display.png"), 3))));
        list.add(new InteractableObject(id++, getMapTile(5, 9).getLocation(), new String[] {"Seems to be a camera monitor", "Hopefully I am not caught on camera"}, new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("camera_display.png"), 3))));
    
        return list;
  }

}
