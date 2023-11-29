package Maps.Future;

import java.util.ArrayList;

import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.Item;
import GameObject.SpriteSheet;
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

public class FloorOneMap extends Map {
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

  @Override
  public ArrayList<NPC> loadNPCs() {
    ArrayList<NPC> list = new ArrayList<>();

    // spawn cabinets
    for (int row = 0; row < 3; row++)
      for (int col = 0; col < 7; col++) {
        if (row == 0 && col == 0) // the row and col of the special filing cabinet

          list.add(new InteractableObject(col + 1, getMapTile(26 + col * 2, 6 + row * 4).getLocation(),
              new String[] { "Wait there is something in here.", "You a note \"7\"" },
              new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("file_cabinet.png"), 3))));
        else
          list.add(new InteractableObject(col + 1, getMapTile(26 + col * 2, 6 + row * 4).getLocation(),
              new String[] { "Nothing here" },
              new Frame(ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("file_cabinet.png"), 3))));
      }
    return list;
  }

}
