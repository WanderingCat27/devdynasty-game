package Items;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import GameObject.Frame;
import GameObject.Item;
import GameObject.SpriteSheet;
import Utils.Point;

public class PurplePotion extends Item
{
    public PurplePotion(int id, Point point)
    {
        super(id,point,"PurplePotion.png");
    }    

    // public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet)
    // {
    //     return new HashMap<String, Frame[]>() {{
    //         put("STILL", new Frame[] {
    //                 new FrameBuilder(spriteSheet.getSprite(0, 0))
    //                         .withScale(3)
    //                         .withBounds(2, 2, 2, 2)
    //                         .build()
    //         });
    //     }};
    // }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
