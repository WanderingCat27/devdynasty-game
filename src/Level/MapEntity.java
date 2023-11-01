package Level;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.Rectangle;
import GameObject.SpriteSheet;
import Utils.Point;

// This class represents a map entity, which is any "entity" on a map besides the player
// it is basically a game object with a few extra features for handling things what to do upon the player interacting with them
public class MapEntity extends GameObject {
    protected MapEntityStatus mapEntityStatus = MapEntityStatus.ACTIVE;

    // if true, entity cannot go out of camera's update range
    protected boolean isUpdateOffScreen = false;

    // if true, entity will no longer be updated or drawn on the map
    protected boolean isHidden = false;

    // if given an existence flag, and that flag gets set, the entity will no longer exist until the flag is unset
    protected String existenceFlag;

    // script that executes when entity is interacted with by the player
    protected Script interactScript;

    // set to true if you do not want this entity to be able to be collided with by other entities
    // example of why you would use this: this is an entity that you want to overlap the player, but you do not want the player to be blocked by/have to worry about this entity's collision bounds when trying to move
    protected boolean isUncollidable = false;

    public MapEntity(float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(spriteSheet, x, y, startingAnimation);
    }

    public MapEntity(float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);
    }

    public MapEntity(float x, float y, Frame[] frames) {
        super(x, y, frames);
    }

    public MapEntity(float x, float y, Frame frame) {
        super(x, y, frame);
    }

    public MapEntity(float x, float y) {
        super(x, y);
    }

    public MapEntityStatus getMapEntityStatus() {
        return mapEntityStatus;
    }

    public void setMapEntityStatus(MapEntityStatus mapEntityStatus) {
        this.mapEntityStatus = mapEntityStatus;
    }

    public boolean isUpdateOffScreen() {
        return isUpdateOffScreen;
    }

    public void setIsUpdateOffScreen(boolean isUpdateOffScreen) {
        this.isUpdateOffScreen = isUpdateOffScreen;
    }

    public Script getInteractScript() { return interactScript; }
    public void setInteractScript(Script interactScript) {
        this.interactScript = interactScript;
        this.interactScript.setMapEntity(this);
    }

    protected Script loadScript() {
        return null;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public Point getLocation() {
        return new Point(x, y);
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public boolean exists() {
        return this.existenceFlag == null || !GlobalFlagManager.FLAG_MANAGER.isFlagSet(this.existenceFlag);
    }

    public String getExistenceFlag() {
        return existenceFlag;
    }

    public boolean checkIfInteractScriptExists()
    {
        if (this.interactScript == null)
        {
            return false;
        }
        return true;
    }

    public void setExistenceFlag(String existenceFlag) {
        this.existenceFlag = existenceFlag;
    }

    public boolean isUncollidable() {
        return isUncollidable;
    }

    public void setIsUncollidable(boolean isUncollidable) {
        this.isUncollidable = isUncollidable;
    }
}
