package NPCs.ScienceLab;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

public class ItemTable extends NPC
{
    public static final int MAX_ITEMS = 4;
    
    public ItemTable(int id, Point point)
    {
        super(id, point.x, point.y, new SpriteSheet(ImageLoader.load("table_for_items.png"), 72, 28), "CHILL");
    }

     public void update() {
        super.update();
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet)
    {
        return new HashMap<String, Frame[]>() {{
            put("CHILL", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3f)
                            .withBounds(0, 2, 72, 20)
                            .build()
            });
            put("ONE_ITEM", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 1))
                            .withScale(3f)
                            .withBounds(0, 2, 72, 20)
                            .build()
            });
            put("TWO_ITEM", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 2))
                            .withScale(3f)
                            .withBounds(0, 2, 72, 20)
                            .build()
            });
        }};
    }
}
