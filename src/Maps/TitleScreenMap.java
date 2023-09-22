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

    public TitleScreenMap() {
        super("title_screen_map.txt", new CommonTileset());
        Point playerLocation = getMapTile(8, 5).getLocation().subtractX(6).subtractY(7);
        player = new Sprite(ImageLoader.loadSubImage("HumanPlayer.png", Colors.MAGENTA, 0, 0, 24, 24));
        player.setScale(3);
        player.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
        player.setLocation(playerLocation.x, playerLocation.y);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        player.draw(graphicsHandler);
    }
}
