package Maps;

import GameObject.Sprite;
import Level.Map;
import Tilesets.FutureTileset;
import Tilesets.ScienceLabTilset;

// Represents the map that is used as a background for the main menu and credits menu screen
public class WinMap extends Map {

    private Sprite player;
    private Sprite hud;

    public WinMap() {
        super("win_screen_map.txt", new FutureTileset());
        setCenterCamera();
       
    }

    
}
