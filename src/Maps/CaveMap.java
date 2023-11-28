package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.PushableRock;
import EnhancedMapTiles.JewelRock;
import Level.EnhancedMapTile;
import Level.Map;
import Tilesets.CaveTileset;
import Tilesets.CommonTileset;

public class CaveMap extends Map{

    public CaveMap(){
        super("caveMap.txt", new CaveTileset());
    }



    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        JewelRock redJewelRock = new JewelRock(getMapTile(2, 7).getLocation(), "RedJewel.png", 1);
        enhancedMapTiles.add(redJewelRock);

        JewelRock greenJewelRock = new JewelRock(getMapTile(5, 7).getLocation(), "GreenJewel.png", 2);
        enhancedMapTiles.add(greenJewelRock);

        JewelRock blueJewelRock = new JewelRock(getMapTile(1, 3).getLocation(), "BlueJewel.png", 3);
        enhancedMapTiles.add(blueJewelRock);
        return enhancedMapTiles;
    }












    
    
}
