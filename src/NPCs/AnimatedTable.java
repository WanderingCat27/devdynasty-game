package NPCs;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

public class AnimatedTable extends NPC
{
    public AnimatedTable(int id, Point point)
    {
        super(id, point.x, point.y, new SpriteSheet(ImageLoader.load("animated_table.png"), 48,48), "CHILL");
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
                            .withBounds(3, 15, 42, 15)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 7)
                            .withScale(3f)
                            .withBounds(3, 15, 42, 15)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 7)
                            .withScale(3f)
                            .withBounds(3, 15, 42, 15)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 3), 7)
                            .withScale(3f)
                            .withBounds(3, 15, 42, 15)
                            .build()
            });
        }};
    }
}
