package NPCs.ScienceLab;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

public class DaggerTable extends NPC
{
    public DaggerTable(int id, Point point) 
    {
        super(id, point.x, point.y, new SpriteSheet(ImageLoader.load("dagger_table.png"), 48, 48), "CHILL");
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
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 7)
                            .withScale(3f)
                            .withBounds(3, 15, 38, 15)
                            .build()
            });
        }};
    }

}
