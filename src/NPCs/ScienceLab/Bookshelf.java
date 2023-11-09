package NPCs.ScienceLab;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

public class Bookshelf extends NPC
{
    public Bookshelf(int id, Point point)
    {
        super(id, point.x, point.y, new SpriteSheet(ImageLoader.load("bookshelf.png"), 40, 21), "CHILL");
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
                            .withBounds(2, 3, 39, 16)
                            .build()
            });
        }};
    }
    
}
