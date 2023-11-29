package EnhancedMapTiles;

import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import GameObject.SpriteSheet;
import Level.Player;
import Level.PlayerState;
import Level.TileType;
import Utils.Direction;
import Utils.Point;
import EnhancedMapTiles.PushableRock;
import Engine.Keyboard; 

public class JewelRock extends PushableRock{


    protected KeyLocker keyLocker = new KeyLocker();
    protected static Key INTERACT = Key.ENTER;
    protected int color;
    protected String imageFileName;
    protected Point location;
    //1 is red, 2 is green, 3 is blue for color ints
    public JewelRock(Point location,String imageFileName ,int color){
        super(location, imageFileName);
        this.imageFileName = imageFileName;
        this.color = color;
        this.location = location;
    }


    public void update(Player player) {
        super.update(player);
        
    }


    public int returnColor(){
        return color;
    }


    protected boolean isOnHole(){

        switch(color){

            case 1:
                if(location.x == 11 && location.y == 28){
                    return true;
                }
            case 2:
                if(location.x == 4 && location.y == 9){
                    return true;
                }
            case 3:
                if(location.x == 37 && location.y == 14){
                    return true;
                }
        }
        return false;

    }

    // private boolean isNearPlayer(){
    //     if(player.x )
    // }







    
}
