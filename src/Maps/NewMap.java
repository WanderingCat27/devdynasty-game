package Maps;

import Engine.ImageLoader;
import GameObject.Sprite;
import Level.Map;
import Tilesets.AnimatedTileset;
//import Tilesets.CommonTileset;
import Tilesets.TestTileset;
import Utils.Point;

public class NewMap extends Map
{   
    
    public NewMap()
    {   
       //My naming of this will be better in the future (I hope, but I doubt it)
        super("proof_map.txt", new AnimatedTileset());
        this.playerStartPosition = getMapTile(10, 10).getLocation();
    }    
}
