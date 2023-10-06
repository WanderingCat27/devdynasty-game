package Screens;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Sprite;
import Level.*;
import Maps.NewMap;
import Maps.TestMap;
//import Maps.TestMap;
import Players.Cat;
import Players.PlayerPlayer;
import Utils.Colors;
import Utils.Direction;
import Utils.Point;
import Tilesets.CommonTileset;
import GameObject.Inventory;
import GameObject.Item;
public class CombatScreen extends Screen{
    
    protected ScreenCoordinator screencoordinator;
    protected Map map;



    public CombatScreen(ScreenCoordinator screenCoordinator){
        this.screencoordinator = screenCoordinator;
    }


    
    public void initialize(){

    }

    public void update(){

    }

    public void draw(GraphicsHandler graphicsHandler){

    }
    
}
