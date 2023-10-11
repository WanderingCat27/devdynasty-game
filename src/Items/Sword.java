package Items;

import java.awt.Point;
import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import GameObject.Frame;
import GameObject.Item;
import GameObject.SpriteSheet;
import Scripts.NewMap.SwordScript;

public class Sword extends Item
{
    String pathToImage = "sword.png";

    public Sword(int id, Utils.Point point)
    {
        super(id,point,"sword.png");
        this.setIsUncollidable(true);
        this.setInteractScript(new SwordScript());
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
