package NPCs.ScienceLab;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

public class Bed extends NPC
{
    public Bed(int id, Point point)
    {
        super(id, point.x, point.y, new SpriteSheet(ImageLoader.load("bed.png"), 40, 40), "CHILL");
    }

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
                            .withBounds(5, 0, 22, 40)
                            .build()
            });
        }};
    }
}
