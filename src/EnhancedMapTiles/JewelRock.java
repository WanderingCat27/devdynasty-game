package EnhancedMapTiles;

import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Level.Player;
import Level.TileType;
import Utils.Point;
import EnhancedMapTiles.PushableRock;

public class JewelRock extends PushableRock{


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
    }


    public int returnColor(){
        return color;
    }







    
}
