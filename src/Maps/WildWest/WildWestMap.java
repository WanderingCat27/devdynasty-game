package Maps.WildWest;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import EnhancedMapTiles.PushableRock;
import GameObject.Frame;
import GameObject.Item;
import Items.BuildingEntrance;
import Items.RedPotion;
import Items.TimeMachine;
import Level.EnhancedMapTile;
import Level.LevelManager;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Dinosaur;
import NPCs.CowboyOne;
import NPCs.CowboyTwo;
import NPCs.CowboyThree;
import NPCs.Walrus;
import Scripts.ChangeLevelByString;
import Scripts.ChangeLevelScript;
import NPCs.OldCowboy;
import Scripts.NewMap.RedPotionScript;
import Scripts.TestMap.DinoScript;
import Scripts.WildWestMap.CowboyThreeScript;
import Scripts.WildWestMap.CowboyTwoScript;
import Scripts.WildWestMap.OldCowboyScript;
import Scripts.WildWestMap.SaloonBounderScript;
import Tilesets.WestTileset;
import Utils.Point;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;

// Represents a test map to be used in a level
public class WildWestMap extends Map {

    public WildWestMap() {
        super("west_map.txt", new WestTileset());
        this.playerStartPosition = getMapTile(24, 26).getLocation();
        addMusic("Resources/Audio/wildWest.wav");
    }
  
    @Override
  public ArrayList<Item> loadItems() {
    ArrayList list = new ArrayList<>();

    // BuildingEntrance saloonEntrance = new BuildingEntrance(0, getMapTile(23, 11).getLocation(), 96, 20, "saloon");
    // list.add(saloonEntrance);

    BuildingEntrance wwBuildingOne = new BuildingEntrance(1, getMapTile(20, 16).getLocation(), 20, 200, "buildingOne");
    list.add(wwBuildingOne);
    BuildingEntrance wwBuildingOneTwo = new BuildingEntrance(1, getMapTile(20, 15).getLocation(), 20, 100, "buildingOne");
    list.add(wwBuildingOneTwo);

    BuildingEntrance wwBuildingTwo = new BuildingEntrance(1, getMapTile(20, 12).getLocation(), 20, 100, "buildingOne");
    list.add(wwBuildingTwo);
    
    BuildingEntrance wwBuildingThree = new BuildingEntrance(1, getMapTile(20, 20).getLocation(), 20, 50, "buildingOne");
    list.add(wwBuildingThree);

    BuildingEntrance wwBuildingFour = new BuildingEntrance(1, getMapTile(19, 24).getLocation(), 20, 50, "buildingOne");
    list.add(wwBuildingFour);
    BuildingEntrance wwBuildingFourTwo = new BuildingEntrance(1, getMapTile(19, 25).getLocation(), 20, 50, "buildingOne");
    list.add(wwBuildingFourTwo);

    BuildingEntrance wwBuildingFive = new BuildingEntrance(1, getMapTile(4, 43).getLocation(), 20, 50, "costumeChange");
    list.add(wwBuildingFive);
    BuildingEntrance wwBuildingFiveTwo = new BuildingEntrance(1, getMapTile(4, 42).getLocation(), 20, 50, "costumeChange");
    list.add(wwBuildingFiveTwo);

    BuildingEntrance wwBuildingSix = new BuildingEntrance(1, getMapTile(26, 20).getLocation(), 20, 50, "buildingTwo");
    list.add(wwBuildingSix);

    BuildingEntrance wwBuildingSeven = new BuildingEntrance(1, getMapTile(26, 15).getLocation(), 20, 50, "buildingTwo");
    list.add(wwBuildingSeven);

    BuildingEntrance ocHouse = new BuildingEntrance(2, getMapTile(14, 6).getLocation(), 20, 50, "OldCowboyHouseMap");
    list.add(ocHouse);

    return list;
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


        RedPotion redPotion = new RedPotion(4, getMapTile(26, 25).getLocation());
        redPotion.setInteractScript(new RedPotionScript());
        redPotion.setIsHidden(true);
        npcs.add(redPotion);

        CowboyOne cowboyOne = new CowboyOne(2, getMapTile(24, 11).getLocation());
        //dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        cowboyOne.setInteractScript(new SaloonBounderScript());
        npcs.add(cowboyOne);

        OldCowboy oldCowboy = new OldCowboy(9, getMapTile(13, 6).getLocation());
        oldCowboy.setInteractScript(new OldCowboyScript());
        npcs.add(oldCowboy);

        CowboyTwo cowboyTwo = new CowboyTwo(10, getMapTile(26, 18).getLocation());
        cowboyTwo.setInteractScript(new CowboyTwoScript());
        npcs.add(cowboyTwo);

        CowboyThree cowboyThree = new CowboyThree(11, getMapTile(27, 25).getLocation());
        cowboyThree.setInteractScript(new CowboyThreeScript());
        npcs.add(cowboyThree);

        TimeMachine timeMachine = new TimeMachine(6, getMapTile(4, 5).getLocation(), 26, 37);
        //timeMachine.setInteractScript(new ChangeLevelScript(LevelManager.LAB));
        npcs.add(timeMachine);
        // time machine is not visible before you defeat evil cowboy
        timeMachine.setIsHidden(true);

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
