package Maps.WildWest;
import java.util.ArrayList;
import GameObject.Item;
import Items.BuildingEntrance;
import Items.GarbagePile;
import Items.Jacket;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.EvilCowboy;
import NPCs.Saloon.CBBookshelf;
import NPCs.Saloon.EvilBat;
import NPCs.Saloon.RoundTable;
import NPCs.ScienceLab.Bed;
import NPCs.ScienceLab.Bookshelf;
import Scripts.WildWestMap.EvilBatScript;
import Scripts.WildWestMap.EvilCowboyScript;
import Tilesets.SaloonTileset;


public class OldCowboyHouseMap extends Map {
  public OldCowboyHouseMap() {
    super("OCHouseMap.txt", new SaloonTileset());
    addMusic("Resources/Audio/saloon.wav");
   this.playerStartPosition = getMapTile(4, 24).getLocation();
  }

 // @Override
   public ArrayList<Item> loadItems() {
    ArrayList<Item> list = new ArrayList<>();

    BuildingEntrance buildingOneEntrance = new BuildingEntrance(0, getMapTile(4, 26).getLocation(), 50, 100, "wildwest");
    list.add(buildingOneEntrance);

    Jacket jacket = new Jacket(1, getMapTile(37, 24).getLocation());
    list.add(jacket);

    GarbagePile garbagePile = new GarbagePile(2, getMapTile(12, 22).getLocation());
    list.add(garbagePile);

    GarbagePile garbagePile1 = new GarbagePile(3, getMapTile(30, 2).getLocation());
    list.add(garbagePile1);

    GarbagePile garbagePile2 = new GarbagePile(4, getMapTile(4, 9).getLocation());
    list.add(garbagePile2);

    return list;
  } 
 
  @Override 
    public ArrayList<NPC> loadNPCs()
    {
        ArrayList<NPC> npcs = new ArrayList<>();
      
        Bed bed = new Bed(1, getMapTile(8, 7).getLocation());
        npcs.add(bed);

        CBBookshelf cbBookshelf = new CBBookshelf(2, getMapTile(21, 8).getLocation());
        npcs.add(cbBookshelf);

        CBBookshelf cbBookshelf2 = new CBBookshelf(3, getMapTile(1, 16).getLocation());
        npcs.add(cbBookshelf2);

        Bed bed2 = new Bed(4, getMapTile(16, 0).getLocation());
        npcs.add(bed2);

      //  Bookshelf bookshelf = new Bookshelf(9, getMapTile(14, 7).getLocation());
      //  npcs.add(bookshelf);

         RoundTable roundTable = new RoundTable(5, getMapTile(14, 9).getLocation());
        npcs.add(roundTable);

        EvilBat evilBat = new EvilBat(6, getMapTile(33, 20).getLocation(), 15);
        evilBat.setInteractScript(new EvilBatScript());
        npcs.add(evilBat);

        return npcs;
    } 

  @Override
  public ArrayList<Trigger> loadTriggers() {
    ArrayList<Trigger> triggers = new ArrayList<>();
    // triggers.add(new Trigger(770, 1042, 50,10, new triggerForScientist(),
    // "hasTalkedToScientist"));
    return triggers;
  }
}
