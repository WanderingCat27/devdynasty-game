package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.PushableRock;
import Items.RedPotion;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Dinosaur;
import NPCs.EvilCowboy;
import NPCs.OldCowboy;
import Scripts.NewMap.RedPotionScript;
import Scripts.TestMap.DinoScript;
import Scripts.WildWestMap.EvilCowboyScript;
import Scripts.WildWestMap.OldCowboyScript;
import Tilesets.WestTileset;

// Represents a test map to be used in a level
public class WildWestMap extends Map {

    public WildWestMap() {
        super("west_map.txt", new WestTileset());
        this.playerStartPosition = getMapTile(12, 2).getLocation();
        addMusic("Resources/Audio/wildWest.wav");
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        PushableRock pushableRock = new PushableRock(getMapTile(2, 7).getLocation());
        enhancedMapTiles.add(pushableRock);

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();


        RedPotion redPotion = new RedPotion(4, getMapTile(4, 4).getLocation());
        redPotion.setInteractScript(new RedPotionScript());
        npcs.add(redPotion);



        Dinosaur dinosaur = new Dinosaur(2, getMapTile(13, 4).getLocation());
        //dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        dinosaur.setInteractScript(new DinoScript());
        npcs.add(dinosaur);

        EvilCowboy evilCowboy = new EvilCowboy(3, getMapTile(18, 3).getLocation());
        //evilCowboy.setExistenceFlag("hasTalkedToCowboy");
        evilCowboy.setInteractScript(new EvilCowboyScript());
        npcs.add(evilCowboy);

        OldCowboy oldCowboy = new OldCowboy(9, getMapTile(2, 10).getLocation());
        oldCowboy.setInteractScript(new OldCowboyScript());
        npcs.add(oldCowboy);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
       // triggers.add(new Trigger(Math.round(getMapTile(12, 2).getX()), Math.round(getMapTile(12, 2).getY()), 10, 10, new ChangeMapScript(), "changeMap"));
        //triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(), "hasLostBall"));
        //triggers.add(new Trigger(790, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        //triggers.add(new Trigger(890, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        return triggers;
    }

    // @Override
    // public void loadScripts() {
    //   //  getMapTile(12, 22).setInteractScript(new SimpleTextScript("Cat's house"));
    //   //  getMapTile(12, 2).setInteractScript(new ChangeMapScript());
    //   //  System.out.println("changeMapScript");
    // }
}

