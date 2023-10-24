package Maps;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import GameObject.Frame;
import GameObject.Item;
import Level.LevelManager;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import Scripts.ChangeLevelScript;
import Tilesets.ScienceLabTilset;
import Utils.Point;

public class SaloonMap extends Map {
  public SaloonMap() {
    super("saloon_map.txt", new ScienceLabTilset());
    // addMusic("Resources/Audio/scienceLab.wav");
  }



  @Override
  public ArrayList<Item> loadItems() {
    ArrayList list = new ArrayList<>();

    int width = 16;
    int height = 16;

    // Create a BufferedImage with RGB mode (Red, Green, Blue)
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // Set all pixels to pink (255 red, 192 green, 203 blue in RGB)
    int pinkColor = (255 << 16) | (192 << 8) | 203;
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        img.setRGB(x, y, pinkColor);
      }
    }
    Item saloonEntrance = new Item(0, 0, 0, new Frame(img));
    saloonEntrance.setInteractScript(new ChangeLevelScript(LevelManager.SALOON));
    list.add(saloonEntrance);
    return list;
  }

  @Override
  public ArrayList<Trigger> loadTriggers() {
    ArrayList<Trigger> triggers = new ArrayList<>();
    // triggers.add(new Trigger(770, 1042, 50,10, new triggerForScientist(),
    // "hasTalkedToScientist"));
    return triggers;
  }

}
