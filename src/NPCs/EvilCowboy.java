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

public class EvilCowboy extends NPC
{
    public EvilCowboy(int id, Utils.Point point)
    {
        super(id, point.x, point.y, new SpriteSheet(ImageLoader.load("EvilCowboy.png"), 14, 19), "STAND_DOWN");
    }

    public EvilCowboy(int id, Utils.Point point, int startingHealth){
        super(id, point.x, point.y, new SpriteSheet(ImageLoader.load("EvilCowboy.png"), 14, 19), "STAND_DOWN", startingHealth);
    }

    @Override
    public String getPathToImage(){
        return "EvilCowboy.png";
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
                            .withBounds(0, 0, 14, 19)
                            .build()
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(1, 0))
                           .withScale(2.5f)
                           .withBounds(0, 0, 14, 19)
                           .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                           .build()
           });
            put("STAND_UP", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(2, 0))
                           .withScale(2.5f)
                           .withBounds(0, 0, 14, 19)
                           .build()
           });
            put("STAND_DOWN", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0))
                           .withScale(2.5f)
                           .withBounds(0, 0, 14, 19)
                           .build()
           });
        }};
    }
}
