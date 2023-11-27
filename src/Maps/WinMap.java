package Maps;

import java.util.ArrayList;

import Engine.ImageLoader;
import GameObject.Sprite;
import GameObject.SpriteSheet;
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
import Tilesets.FutureTileset;
import Tilesets.ScienceLabTilset;
import NPCs.Credits.CreditLine;

// Represents the map that is used as a background for the main menu and credits menu screen
public class WinMap extends Map {

    private Sprite player;
    private Sprite hud;

    public WinMap() {
        super("win_screen_map.txt", new FutureTileset());
        setCenterCamera();
       
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        CreditLine createdBy = new CreditLine(1, getMapTile(4, 4).getLocation(), new SpriteSheet(ImageLoader.load("CowboyTwo.png"), 10, 10));
        createdBy.setInteractScript(new WalrusScript());
        createdBy.setIsUncollidable(true);
        npcs.add(createdBy);

        return npcs;
    }

    
}
