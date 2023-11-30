package EnhancedMapTiles;

import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import GameObject.SpriteSheet;
import Level.GlobalFlagManager;
import Level.Player;
import Level.PlayerState;
import Level.TileType;
import Utils.Direction;
import Utils.Point;
import EnhancedMapTiles.PushableRock;
import Engine.Keyboard; 
import java.lang.Math.*;

public class JewelRock extends PushableRock{


    protected KeyLocker keyLocker = new KeyLocker();
    protected static Key INTERACT = Key.ENTER;
    protected int color;
    protected String imageFileName, flagName;
    protected Point location, holeLocation;
    //1 is red, 2 is green, 3 is blue for color ints
    public JewelRock(Point location,String imageFileName ,int color, Point holeLocation, String flagName){
        super(location, imageFileName);
        this.imageFileName = imageFileName;
        this.color = color;
        this.location = location;
        this.holeLocation = holeLocation;
        this.flagName = flagName;
        
    }


    public void update(Player player) {
        super.update(player);
        //System.out.println(getX()+ " , " + getY() + " | " + holeLocation.toString() );
        if(isOnHole()){
            GlobalFlagManager.FLAG_MANAGER.setFlag(flagName);
        }
        




        
        if(isNearPlayer(player)){
            //System.out.println("Is near player:  " + player.getX() +",  " + getX());
                if(Keyboard.isKeyDown(INTERACT)){
                    //System.out.println("Interacting");
                    //System.out.println(player.getMoveAmountX() + ",  "+ player.getMoveAmountY());
                    player.setIsCarryingJewel(true);
                    if(player.getFacingDirection() == Direction.RIGHT){
                        setX(player.getX() + 30);
                        setY(player.getY());
                    }else if(player.getFacingDirection() == Direction.LEFT){
                        setX(player.getX()-50);
                        setY(player.getY());
                    }else if(player.getFacingDirection() == Direction.DOWN){
                        setX(player.getX());
                        setY(player.getY()+50);
                    }else{
                        setX(player.getX());
                        setY(player.getY()-50);
                    }
                    
                    
                }else{
                    player.setIsCarryingJewel(false);
                }
        }
        
    }



    public int returnColor(){
        return color;
    }


    public boolean isOnHole(){

        float xDistance = Math.abs(holeLocation.x - getX());
        float yDistance = Math.abs(holeLocation.y - getY());
        if(xDistance <= 25 && yDistance <= 25){
            return true;
        }
        return false;

    }


    private boolean isNearPlayer(Player player){
        if(Math.abs(player.getX() - getX()) <= 50){
            if(Math.abs(player.getY()- getY()) <=50){
                return true;
            }

        }
        return false;
    }







    
}
