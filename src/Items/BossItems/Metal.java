package Items.BossItems;

import Engine.GraphicsHandler;
import GameObject.Item;
import Scripts.NewMap.SwordScript;
import Scripts.Prehistoric.MetalScript;

public class Metal extends Item
{
    String pathToImage = "rare_metal.png";

    public Metal(int id, Utils.Point point)
    {
        super(id,point,"rare_metal.png");
        this.setIsUncollidable(true);
        this.setInteractScript(new MetalScript());
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

