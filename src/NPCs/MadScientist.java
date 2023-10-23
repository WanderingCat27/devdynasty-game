package NPCs;

import java.awt.Point;
import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;

public class MadScientist extends NPC
{
    public MadScientist(int id, Utils.Point point)
    {
        super(id, point.x, point.y, new SpriteSheet(ImageLoader.load("FinalMadScientist.png"), 14, 19), "STAND_DOWN");
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
            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0))
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build()
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(1, 0))
                           .withScale(2.5f)
                           .withBounds(2, 2, 6, 6)
                           .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                           .build()
           });
            put("STAND_UP", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(2, 0))
                           .withScale(2.5f)
                           .withBounds(2, 2, 6, 6)
                           .build()
           });
            put("STAND_DOWN", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0))
                           .withScale(2.5f)
                           .withBounds(2, 2, 6, 6)
                           .build()
           });
           put("WALK_LEFT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(1, 0),7)
                           .withScale(2.5f)
                           .withBounds(2, 2, 6, 6)
                           .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1),7)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2),7)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 3),7)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build()
           });
           put("WALK_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(1, 0), 7)
                           .withScale(2.5f)
                           .withBounds(2, 2, 6, 6)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                           .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 7)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 7)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6,6)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 3), 7)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
           });
           put("WALK_DOWN", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0), 7)
                           .withScale(2.5f)
                           .withBounds(2, 2, 6, 6)
                           .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 7)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 2),7)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 3), 7)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build()
           });
           put("WALK_UP", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(2, 0), 7)
                           .withScale(2.5f)
                           .withBounds(2, 2, 6, 6)
                           .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 7)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2), 7)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 3), 7)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
           });
           

        }};
    }
}
