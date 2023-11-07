package NPCs.Saloon;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import NPCs.ScienceLab.DaggerTable;
import Utils.Point;

public class RoundTable extends NPC
{
    public RoundTable(int id, Point point)
    {
        super(id, point.x, point.y, new SpriteSheet(ImageLoader.load("round_table.png"), 30, 30), "CHILL");
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
                            .withBounds(0, 0, 27, 30)
                            .build()
            });
        }};
    }

}
