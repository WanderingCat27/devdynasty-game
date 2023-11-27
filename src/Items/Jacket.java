package Items;

import java.awt.Point;
import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import GameObject.Item;
import Scripts.NewMap.SwordScript;
import Scripts.WildWestMap.JacketScript;

public class Jacket extends Item
{
    String pathToImage = "jacket.png";

    public Jacket(int id, Utils.Point point)
    {
        super(id,point,"jacket.png");
        this.setIsUncollidable(true);
        this.setInteractScript(new JacketScript());
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
