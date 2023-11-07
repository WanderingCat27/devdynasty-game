package NPCs.Saloon;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.Item;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

public class BarTable extends NPC
{
    public BarTable(int id, Point point)
    {
        super(id, point.x, point.y, new SpriteSheet(ImageLoader.load("bar_table.png"), 110, 40), "CHILL");
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
                            .withBounds(0, 10, 110, 25)
                            .build()
            });
        }};
    }

}