package Maps;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.ImageEffect;
import GameObject.Sprite;
import Level.Map;
import NPCs.Dinosaur;
import Tilesets.CommonTileset;
import Utils.Colors;
import Utils.Point;



public class CombatMap extends Map{

    public CombatMap(){
        super("combat_map.txt", new CommonTileset());
        super.setNoFollow();
    }
    

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        //hud.draw(graphicsHandler);
    }

}
