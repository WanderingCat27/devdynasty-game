package Items;

import Utils.Point;

import Engine.GraphicsHandler;
import GameObject.Item;
import Scripts.Prehistoric.FlashlightScript;

public class Flashlight extends Item{

    
    String pathToImage = "Flashlight.png";


    public Flashlight(int id, Point point){
        super(id, point, "Flashlight.png", 16, 16);
        this.setIsUncollidable(true);
        this.setInteractScript(new FlashlightScript());
        setName("Flashlight");
    }


    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }



    
}
