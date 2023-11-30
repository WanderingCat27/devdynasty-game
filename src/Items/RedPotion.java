package Items;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import GameObject.Frame;
import GameObject.Item;
import GameObject.SpriteSheet;
import Scripts.NewMap.RedPotionScript;
import Utils.Point;

public class RedPotion extends Item
{
    String pathToImage = "RedPotion.png";

    public RedPotion(int id, Point point)
    {
        super(id,point,"RedPotion.png");
        this.setIsUncollidable(true);
        this.setInteractScript(new RedPotionScript());

        setName("Red Potion");
    }    

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    public String getPathToImage()
    {
        return pathToImage;
    }
}
