package Items.BossItems;

import Engine.GraphicsHandler;
import GameObject.Item;
import Scripts.NewMap.SwordScript;
import Scripts.WildWestMap.CrystalScript;

public class Crystal extends Item
{
    String pathToImage = "crystal.png";

    public Crystal(int id, Utils.Point point)
    {
        super(id,point,"crystal.png");
        this.setIsUncollidable(true);
        this.setInteractScript(new CrystalScript());
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
