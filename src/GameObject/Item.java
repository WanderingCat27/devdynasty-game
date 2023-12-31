package GameObject;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import Level.NPC;
import Utils.Point;

//Pretty dumb class, but I guess its fine???
public class Item extends NPC {
  String pathToImage;
  String name;
  String desc;

  // Makes an item appear at a certain location
  public Item(int id, Point point, String pathToImage) {
    super(id, point.x, point.y, new SpriteSheet(ImageLoader.load(pathToImage), 18, 18), "STILL");
    this.pathToImage = pathToImage;
  }

  public Item(int id, Point point, String pathToImage, int height, int width) {
    super(id, point.x, point.y, new SpriteSheet(ImageLoader.load(pathToImage), height,width), "STILL");
    this.pathToImage = pathToImage;
  }

  public Item(int id, Point point, Frame frame) {
    this(id, point.x, point.y, frame);
  }

  public Item(int id, float x, float y, Frame frame) {
    super(id, x, y, frame);
  }

  public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
    return new HashMap<String, Frame[]>() {
      {
        put("STILL", new Frame[] {
            new FrameBuilder(spriteSheet.getSprite(0, 0))
                .withScale(2f)
                // .withBounds(0, 0, 1, 1)
                .build()
        });
      }
    };
  }

  public String getPathToImage() {
    return pathToImage;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  

}
