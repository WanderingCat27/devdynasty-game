package Items;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.Item;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

public class Computer extends NPC
{
    String pathToImage = "computer.png";

    public Computer(int id, Point location)
    {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("computer.png"), 32, 32), "STAND_STILL");
        isUncollidable = false;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_STILL", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0),14)
                            .withScale(1.5f)
                            .withBounds(0, 0, 16, 16)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1),14)
                            .withScale(1.5f)
                            .withBounds(0, 0, 16, 16)
                            .build()
            }); 
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    public String getPathToImage()
    {
        return pathToImage;
    }
}
