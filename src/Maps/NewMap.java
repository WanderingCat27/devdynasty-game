package Maps;

import java.util.ArrayList;

import Engine.ImageLoader;
import GameObject.Item;
import GameObject.Sprite;
import Items.PurplePotion;
import Items.RedPotion;
import Items.Sword;
import Level.Map;
import Level.NPC;
import NPCs.Dinosaur;
import NPCs.Walrus;
import Scripts.TestMap.DinoScript;
import Scripts.TestMap.WalrusScript;
import Tilesets.AnimatedTileset;
//import Tilesets.CommonTileset;
import Tilesets.TestTileset;
import Utils.Point;

public class NewMap extends Map
{   
    
    public NewMap()
    {   
        super("proof_map.txt", new AnimatedTileset());
        this.playerStartPosition = getMapTile(10, 10).getLocation();
    } 
    
    public ArrayList<Item> loadItems() {
        ArrayList<Item> items = new ArrayList<>();

        Sword sword = new Sword(1, getMapTile(7, 7).getLocation());
        sword.setScale(2f);
        items.add(sword);

        RedPotion redPotion = new RedPotion(2, getMapTile(15, 15).getLocation());
        redPotion.setScale(2f);
        items.add(redPotion);

        PurplePotion purplePotion = new PurplePotion(3, getMapTile(7, 10).getLocation());
        purplePotion.setScale(2f);
        items.add(purplePotion);

        return items;
    }
}
