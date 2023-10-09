package Items;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import GameObject.Frame;
import GameObject.Item;
import Utils.Point;

public class TimeMachine extends Item
{
    String pathToImage = "TimeMachine.png";

    public TimeMachine(int id, Point point)
    {
        super(id,point,"TimeMachine.png");
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