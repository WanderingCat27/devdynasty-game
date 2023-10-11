package Items;

import Engine.GraphicsHandler;
import Utils.Point;
import Engine.GraphicsHandler;
import GameObject.Item;
import Scripts.NewMap.FireStaffScript;

public class FireStaff extends Item
{
    public FireStaff(int id, Point point)
    {
        super(id,point,"FireStaff.png");
        this.setIsUncollidable(true);
        this.setInteractScript(new FireStaffScript());
    }    

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }    
}
