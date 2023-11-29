package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.PushableRock;
import GameObject.Item;
import Items.BuildingEntrance;
import EnhancedMapTiles.JewelRock;
import Level.EnhancedMapTile;
import Level.Map;
import Tilesets.CaveTileset;
import Tilesets.CommonTileset;

public class CaveMap extends Map{

    public CaveMap(){
        super("caveMap.txt", new CaveTileset());
        this.playerStartPosition = getMapTile(2, 28).getLocation();
    }


    @Override
  public ArrayList<Item> loadItems() {
    ArrayList list = new ArrayList<>();

    // BuildingEntrance saloonEntrance = new BuildingEntrance(0, getMapTile(23, 11).getLocation(), 96, 20, "saloon");
    // list.add(saloonEntrance);

    BuildingEntrance caveExit = new BuildingEntrance(1, getMapTile(1, 29).getLocation(), 20, 200, "prehistoric");
    list.add(caveExit);

    return list;

  }
    
    

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        JewelRock redJewelRock = new JewelRock(getMapTile(6, 16).getLocation(), "RedJewel2.png", 1);
        enhancedMapTiles.add(redJewelRock);

        JewelRock greenJewelRock = new JewelRock(getMapTile(37, 9).getLocation(), "GreenJewel2.png", 2);
        enhancedMapTiles.add(greenJewelRock);

        JewelRock blueJewelRock = new JewelRock(getMapTile(1, 2).getLocation(), "BlueJewel2.png", 3);
        enhancedMapTiles.add(blueJewelRock);




        return enhancedMapTiles;
    }















    
    
}
