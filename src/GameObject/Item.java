package GameObject;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Level.Map;
import Level.MapEntity;
import Level.NPC;
import Utils.Point;
import GameObject.SpriteSheet;


//Pretty dumb class, but I guess its fine???
public class Item extends NPC
{
    String pathToImage;
    //Makes an item appear at a certain location
    public Item(int id,Point point, String pathToImage)
    {
        super(id,point.x,point.y,new SpriteSheet(ImageLoader.load(pathToImage), 18,18),"STILL");
        this.pathToImage = pathToImage;
    }


    public Item(int id, Point point, String pathToImage, int spriteWidth, int spriteHeight){
        super(id,point.x,point.y,new SpriteSheet(ImageLoader.load(pathToImage), spriteWidth,spriteHeight),"STILL");
    } 

    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet)
    {
        return new HashMap<String, Frame[]>() {{
            put("STILL", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(2f)
                            //.withBounds(0, 0, 1, 1)
                            .build()
            });
        }};
    }


    public String getPathToImage()
    {
        return pathToImage;
    }


}
