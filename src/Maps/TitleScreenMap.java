package Maps;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.ImageEffect;
import GameObject.Sprite;
import Level.Map;
import Tilesets.CommonTileset;
import Utils.Colors;
import Utils.Point;

// Represents the map that is used as a background for the main menu and credits menu screen
public class TitleScreenMap extends Map {

    private Sprite player;
    private Sprite hud;

    public TitleScreenMap() {
        super("title_screen_map.txt", new CommonTileset());
        Point playerLocation = getMapTile(8, 5).getLocation().subtractX(6).subtractY(7);
        player = new Sprite(ImageLoader.loadSubImage("HumanPlayer.png", Colors.MAGENTA, 0, 0, 24, 24));
        player.setScale(3);
        player.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
        player.setLocation(playerLocation.x, playerLocation.y);
        //Hud Test code, this wiil load the hud for the title screen as proof of concept
        //  Point hudLocation = getMapTile(8, 6).getLocation().subtractX(6).subtractY(7);
        //  hud = new Sprite(ImageLoader.load("HUDForGame.png"));
        //  hud.setScale(1.5f);
        //  hud.setLocation(hudLocation.x, hudLocation.y);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        player.draw(graphicsHandler);
        //hud.draw(graphicsHandler);
    }
}
