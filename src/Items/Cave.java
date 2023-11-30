package Items;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import GameObject.Frame;
import GameObject.Item;
import GameObject.SpriteSheet;
import Utils.Point;

public class Cave extends Item{
    String pathToImage = "Cave.png";

    public Cave(int id, Point point)
    {
        super(id,point,"Cave.png");
    }    
    
    public Cave(int id, Point point, int height, int width){
        super(id, point,"Cave.png", height, width);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
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

    public String getPathToImage()
    {
        return pathToImage;
    }
}
    

