package Items;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import GameObject.Frame;
import GameObject.Item;
import Scripts.ChangeLevelByString;
import Utils.Point;

public class BuildingEntrance extends Item{

  public BuildingEntrance(int id, Point location, int width, int height, String level) {
    super(id, location.x, location.y, new Frame(createClearImage(width, height)));
    this.setInteractScript(new ChangeLevelByString(level));
    this.setIsUncollidable(true);
  }

  private static BufferedImage createClearImage(int width, int height) {
    BufferedImage clearImage = new BufferedImage(96, 32, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = clearImage.createGraphics();
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f)); // Set alpha to 0
    g2d.fillRect(0, 0, clearImage.getWidth(), clearImage.getHeight());
    return clearImage;
  }
  
}
