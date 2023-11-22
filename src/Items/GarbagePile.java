package Items;

import java.awt.Point;
import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import GameObject.Item;
import Scripts.SimpleTextScript;
import Scripts.NewMap.SwordScript;
import Scripts.WildWestMap.GarbageScript;

public class GarbagePile extends Item
{
    String pathToImage = "garbagePile.png";

    public GarbagePile(int id, Utils.Point point)
    {
        super(id,point,"garbagePile.png");
        this.setIsUncollidable(true);
        this.setInteractScript(new GarbageScript());
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
