package Items;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import GameObject.Frame;
import GameObject.Item;
import GameObject.SpriteSheet;
import Utils.Point;

public class RedPotion extends Item
{
    String pathToImage = "RedPotion.png";

    public RedPotion(int id, Point point)
    {
        super(id,point,"RedPotion.png");
        this.setIsUncollidable(true);
    }    

    // public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet)
    // {
    //     return new HashMap<String, Frame[]>() {{
    //         put("STILL", new Frame[] {
    //                 new FrameBuilder(spriteSheet.getSprite(2, 0))
    //                         .withScale(3)
    //                         .withBounds(14, 10, 18, 18)
    //                         .build()
    //         });
    //     }};
    // }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    public String getPathToImage()
    {
        return pathToImage;
    }
}
