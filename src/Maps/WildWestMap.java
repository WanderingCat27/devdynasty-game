package Maps;

import EnhancedMapTiles.PushableRock;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Dinosaur;
import NPCs.Walrus;
import Scripts.SimpleTextScript;
import Scripts.TestMap.DinoScript;
import Scripts.TestMap.LostBallScript;
import Scripts.TestMap.TreeScript;
import Scripts.TestMap.WalrusScript;
import Tilesets.CommonTileset;
import Tilesets.WestTileset;
import Scripts.ChangeMapScript;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class WildWestMap extends Map {

    public WildWestMap() {
        super("west_map.txt", new WestTileset());
        this.playerStartPosition = getMapTile(12, 2).getLocation();
        this.soundPath = "Resources/Audio/2021-08-26_-_Outlaw_Beginnings_-_www.FesliyanStudios.com.wav";
        this.setFlagManager(flagManager);
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

        Walrus walrus = new Walrus(1, getMapTile(4, 28).getLocation().subtractY(40));
        walrus.setInteractScript(new WalrusScript());
        npcs.add(walrus);

        Dinosaur dinosaur = new Dinosaur(2, getMapTile(13, 4).getLocation());
        dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        dinosaur.setInteractScript(new DinoScript());
        npcs.add(dinosaur);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(Math.round(getMapTile(12, 2).getX()), Math.round(getMapTile(12, 2).getY()), 10, 10, new ChangeMapScript(), "changeMap"));
        //triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(), "hasLostBall"));
        //triggers.add(new Trigger(790, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        //triggers.add(new Trigger(890, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        return triggers;
    }

    @Override
    public void loadScripts() {
        getMapTile(12, 22).setInteractScript(new SimpleTextScript("Cat's house"));
        getMapTile(12, 2).setInteractScript(new ChangeMapScript());
        System.out.println("changeMapScript");
    }
}

