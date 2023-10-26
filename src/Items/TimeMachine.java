package Items;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import GameObject.Frame;
import GameObject.Item;
import GameObject.SpriteSheet;
import Utils.Point;

public class TimeMachine extends Item
{
    String pathToImage = "TimeMachine2.1.png";

    public TimeMachine(int id, Point point)
    {
        super(id,point,"TimeMachine2.1.png");
    }    
    
    public TimeMachine(int id, Point point, int spriteWidth, int spriteHeight){
        super(id, point,"TimeMachine2.1.png", spriteWidth, spriteHeight);
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
