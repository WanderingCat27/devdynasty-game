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
import Scripts.NewMap.PurplePotionScript;
import Scripts.NewMap.RedPotionScript;
import Scripts.NewMap.SwordScript;
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
        // Links background sound to map
        this.soundPath = "Resources/Audio/AmTronic_-_Caribbean_Dub.wav";
        System.out.println(this.soundPath);

    } 
    
    // public ArrayList<Item> loadItems() {
    //     ArrayList<Item> items = new ArrayList<>();

    //     Sword sword = new Sword(1, getMapTile(7, 7).getLocation());
    //     sword.setScale(2);
    //     //System.out.println(sword.isAffectedByTriggers());
    //     sword.setInteractScript(new SwordScript());
    //     System.out.println(sword.getPathToImage());
    //     items.add(sword);

    //     RedPotion redPotion = new RedPotion(2, getMapTile(15, 15).getLocation());
    //     redPotion.setScale(3);
    //     //redPotion.setInteractScript();
    //     items.add(redPotion);

    //     PurplePotion purplePotion = new PurplePotion(3, getMapTile(7, 10).getLocation());
    //     purplePotion.setScale(3);
    //     //purplePotion.setInteractScript(new SwordScript());
    //     items.add(purplePotion);

    //     return items;
    // }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Sword sword = new Sword(3, getMapTile(10, 10).getLocation());
        sword.setInteractScript(new SwordScript());
        npcs.add(sword);

        RedPotion redPotion = new RedPotion(4, getMapTile(4, 4).getLocation());
        redPotion.setInteractScript(new RedPotionScript());
        npcs.add(redPotion);

        PurplePotion purplePotion = new PurplePotion(5, getMapTile(10, 9).getLocation());
        purplePotion.setInteractScript(new PurplePotionScript());
        npcs.add(purplePotion);

        return npcs;
    }

}
