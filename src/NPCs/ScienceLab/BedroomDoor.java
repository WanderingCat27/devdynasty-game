package NPCs.ScienceLab;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

public class BedroomDoor extends NPC
{
    public BedroomDoor(int id, Point point)
    {
        super(id, point.x, point.y, new SpriteSheet(ImageLoader.load("bedroom_door.png"), 16, 32), "CHILL");
    }

    @Override
    public void update() {
        super.update();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet)
    {
        return new HashMap<String, Frame[]>() {{
            put("CHILL", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3f)
                            .withBounds(0, 0, 16, 32)
                            .build()
            });

            put("SEMI_OPENED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 1))
                            .withScale(3f)
                            .withBounds(0, 0, 16, 32)
                            .build()
            });

            put("FULLY_OPENED", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 2))
                            .withScale(3f)
                            .withBounds(0, 0, 16, 32)
                            .build()
            });
        }};
    }
}
