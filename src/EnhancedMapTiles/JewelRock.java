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
    //1 is red, 2 is green, 3 is blue for color ints
    public JewelRock(Point location,String imageFileName ,int color){
        super(location, imageFileName);
        this.imageFileName = imageFileName;
        this.color = color;
    }


    public void update(Player player) {
        super.update(player);
        if (player.overlaps(this) && player.getPlayerState() == PlayerState.WALKING){
            if(player.getCurrentWalkingXDirection() == Direction.LEFT && Keyboard.isKeyDown(INTERACT)){
                
            }
        }
    }


    public int returnColor(){
        return color;
    }







    
}
