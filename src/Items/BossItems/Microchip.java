package Items.BossItems;

import Engine.GraphicsHandler;
import GameObject.Item;
import Scripts.WildWestMap.MicrochipScript;

public class Microchip extends Item
{
    String pathToImage = "cpu_chip.png";

    public Microchip(int id, Utils.Point point)
    {
        super(id,point,"cpu_chip.png");
        this.setIsUncollidable(true);
        this.setInteractScript(new MicrochipScript());
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
