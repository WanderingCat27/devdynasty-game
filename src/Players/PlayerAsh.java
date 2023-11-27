package Players;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Player;

public class PlayerAsh extends Player
{
    public PlayerAsh(float x, float y)
    {
        super(new SpriteSheet(ImageLoader.load("PokemonInspiredSpriteSheet.png"), 14, 19), x, y, "STAND_RIGHT");
        walkSpeed = 2.3f;
    }

    public PlayerAsh(float x, float y, String startingAnimation)
    {
        super(new SpriteSheet(ImageLoader.load("CowboyPlayer.png"), 14, 19), x, y, startingAnimation);
        walkSpeed = 2.3f;
    }

    public void update() {
        super.update();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
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
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 2, 6, 6)
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

            put("WALK_UP", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0), 8)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 1), 8)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 2), 8)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(2, 3), 8)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build()
            });

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 8)
                            .withScale(2.5f)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 8)
                            .withScale(2.5f)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 8)
                            .withScale(2.5f)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 3), 8)
                            .withScale(2.5f)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(2, 2, 6, 6)
                            .build()
            });

            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 8)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 8)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2), 8)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 3), 8)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build()
            });
            put("WALK_DOWN", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 2), 8)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 3), 8)
                            .withScale(2.5f)
                            .withBounds(2, 2, 6, 6)
                            .build()
            });
        }};
    }

}
