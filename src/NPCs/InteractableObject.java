package NPCs;

import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Scripts.SimpleTextScript;
import Utils.Point;
import java.awt.image.BufferedImage;


public class InteractableObject extends NPC{
    
    public InteractableObject(int id, Point point, String[] dialogue, Frame frame)
    {
        super(id, point.x, point.y, frame);
        this.setInteractScript(new SimpleTextScript(dialogue));
    }

    


}
