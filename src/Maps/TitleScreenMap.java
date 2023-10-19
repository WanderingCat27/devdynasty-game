package Maps;

import GameObject.Sprite;
import Level.Map;
import Tilesets.ScienceLabTilset;

// Represents the map that is used as a background for the main menu and credits menu screen
public class TitleScreenMap extends Map {

    private Sprite player;
    private Sprite hud;

    public TitleScreenMap() {
        super("title_screen_map.txt", new ScienceLabTilset());
        setCenterCamera();
       
    }

    
}
