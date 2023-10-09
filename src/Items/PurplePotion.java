package Items;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import GameObject.Frame;
import GameObject.Item;
import GameObject.SpriteSheet;
import Scripts.NewMap.PurplePotionScript;
import Utils.Point;

public class PurplePotion extends Item
{
    public PurplePotion(int id, Point point)
    {
        super(id,point,"PurplePotion.png");
        this.setIsUncollidable(true);
        this.setInteractScript(new PurplePotionScript());
    }    

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
