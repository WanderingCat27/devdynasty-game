package Items;

import Engine.GraphicsHandler;
import Utils.Point;
import Engine.GraphicsHandler;
import GameObject.Item;

public class FireStaff extends Item
{
    public FireStaff(int id, Point point)
    {
        super(id,point,"FireStaff.png");
    }    

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }    
}
