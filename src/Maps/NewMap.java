package Maps;

import Level.Map;
//import Tilesets.CommonTileset;
import Tilesets.TestTileset;

public class NewMap extends Map
{
    public NewMap()
    {   
       //My naming of this will be better in the future (I hope, but I doubt it)
        super("proof_map.txt", new TestTileset());
        this.playerStartPosition = getMapTile(10, 10).getLocation();
    }    
}
