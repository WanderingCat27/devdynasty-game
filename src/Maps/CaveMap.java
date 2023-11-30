package Maps;

import java.util.ArrayList;

import Engine.GraphicsHandler;
import Engine.ScreenManager;
import EnhancedMapTiles.PushableRock;
import GameObject.Item;
import Items.BuildingEntrance;
import Items.Sword;
import EnhancedMapTiles.JewelRock;
import Level.EnhancedMapTile;
import Level.GlobalFlagManager;
import Level.LevelManager;
import Level.Map;
import Level.NPC;
import Level.Player;
import NPCs.Prehistoric.Caveman;
import Scripts.Prehistoric.CavemanScript;
import Tilesets.CaveTileset;
import Tilesets.CommonTileset;
import Utils.Colors;

public class CaveMap extends Map{

    public CaveMap(){
        super("caveMap.txt", new CaveTileset());
        this.playerStartPosition = getMapTile(2, 28).getLocation();
        addMusic("Resources/Audio/prehistoric theme.wav");
    }


    @Override
    public ArrayList<NPC> loadNPCs(){
      ArrayList<NPC> npcs = new ArrayList<>();

      Caveman caveman = new Caveman(5, getMapTile(38, 22).getLocation(), 20);
      caveman.setInteractScript(new CavemanScript());
      npcs.add(caveman);




      return npcs;
    }


    @Override
    public ArrayList<Item> loadItems() {
    ArrayList<Item> list = new ArrayList<>();

    // BuildingEntrance saloonEntrance = new BuildingEntrance(0, getMapTile(23, 11).getLocation(), 96, 20, "saloon");
    // list.add(saloonEntrance);

    BuildingEntrance caveExit = new BuildingEntrance(1, getMapTile(1, 29).getLocation(), 20, 200, "prehistoric");
    list.add(caveExit);

    Item lock = new Item(2, getMapTile(25, 23).getLocation(), "Lock.png", 25, 25);
    list.add(lock);
    lock.setIsHidden(true);

    Item doorUnlocked = new Item(3, getMapTile(25, 22).getLocation(), "DoorOpened.png", 16, 16);
    doorUnlocked.setScale(3f);
    doorUnlocked.setIsHidden(true);
    list.add(doorUnlocked);

    return list;

  }
    
    

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        JewelRock redJewelRock = new JewelRock(getMapTile(6, 16).getLocation(), "RedJewel2.png", 1, getMapTile(11, 28).getLocation(), "redJewelCorrect");
        enhancedMapTiles.add(redJewelRock);

        JewelRock greenJewelRock = new JewelRock(getMapTile(37, 9).getLocation(), "GreenJewel2.png", 2, getMapTile(4, 9).getLocation(), "greenJewelCorrect");
        enhancedMapTiles.add(greenJewelRock);

        JewelRock blueJewelRock = new JewelRock(getMapTile(2, 2).getLocation(), "BlueJewel2.png", 3, getMapTile(37, 14).getLocation(), "blueJewelCorrect");
        enhancedMapTiles.add(blueJewelRock);





        return enhancedMapTiles;
    }

    @Override
    public void draw(Player player,GraphicsHandler g){
      super.draw(player, g);
      System.out.println("Draw Running");
      System.out.println(ScreenManager.getScreenHeight()/2 > player.getLocation().getY());
      if(!GlobalFlagManager.FLAG_MANAGER.isFlagSet("flashlightPickedUp")){
        g.drawDarkScreenOverlay(ScreenManager.getScreenWidth(), ScreenManager.getScreenWidth(), Colors.MAGENTA);
        //g.drawFlashlightLight(player.getLocation().getX(), player.getLocation().getY());
        System.out.println(player.getLocation().getX() + " , " + player.getLocation().getY());
      }

        
    }
















    
    
}
