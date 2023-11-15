package Maps.WildWest;
import java.util.ArrayList;
import GameObject.Item;
import Items.BuildingEntrance;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import Tilesets.SaloonTileset;
import NPCs.Saloon.RoundTable;
import NPCs.ScienceLab.Bed;
import NPCs.ScienceLab.Bookshelf;


public class WWBuildingOne extends Map {
  public WWBuildingOne() {
    super("WWBuildingOne.txt", new SaloonTileset());
    this.playerStartPosition = getMapTile(16, 9).getLocation();
    //setCenterCamera();
  }

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList<Item> list = new ArrayList<>();
    BuildingEntrance buildingOneEntrance = new BuildingEntrance(0, getMapTile(17, 9).getLocation(), 50, 100, "wildwest");
    list.add(buildingOneEntrance);
    return list;
  }

  @Override 
    public ArrayList<NPC> loadNPCs()
    {
        ArrayList<NPC> npcs = new ArrayList<>();
      
        Bed bed = new Bed(8, getMapTile(7, 10).getLocation());
        npcs.add(bed);

        Bookshelf bookshelf = new Bookshelf(9, getMapTile(8, 7).getLocation());
        npcs.add(bookshelf);

        NPC roundTable = new RoundTable(2, getMapTile(12, 9).getLocation());
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

}
