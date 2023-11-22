package Maps.WildWest;
import java.util.ArrayList;
import GameObject.Item;
import Items.BuildingEntrance;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Saloon.RoundTable;
import NPCs.ScienceLab.Bed;
import NPCs.ScienceLab.Bookshelf;
import Tilesets.SaloonTileset;


public class OldCowboyHouseMap extends Map {
  public OldCowboyHouseMap() {
    super("OCHouseMap.txt", new SaloonTileset());
   this.playerStartPosition = getMapTile(4, 24).getLocation();
    //setCenterCamera();
  }

 // @Override
   public ArrayList<Item> loadItems() {
    ArrayList<Item> list = new ArrayList<>();
    BuildingEntrance buildingOneEntrance = new BuildingEntrance(0, getMapTile(4, 26).getLocation(), 50, 100, "wildwest");
    list.add(buildingOneEntrance);
    return list;
  } 
/* 
  @Override 
    public ArrayList<NPC> loadNPCs()
    {
        ArrayList<NPC> npcs = new ArrayList<>();
      
        Bed bed = new Bed(8, getMapTile(15, 10).getLocation());
        npcs.add(bed);

        Bookshelf bookshelf = new Bookshelf(9, getMapTile(14, 7).getLocation());
        npcs.add(bookshelf);

        NPC roundTable = new RoundTable(2, getMapTile(10, 9).getLocation());
        npcs.add(roundTable);

        return npcs;
    } 

  @Override
  public ArrayList<Trigger> loadTriggers() {
    ArrayList<Trigger> triggers = new ArrayList<>();
    // triggers.add(new Trigger(770, 1042, 50,10, new triggerForScientist(),
    // "hasTalkedToScientist"));
    return triggers;
  }
*/
}
