package Maps.WildWest;
import java.util.ArrayList;
import GameObject.Item;
import Items.BuildingEntrance;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import Tilesets.SaloonTileset;
import NPCs.OldCowboy;
import NPCs.Saloon.RoundTable;
import NPCs.ScienceLab.Bed;
import NPCs.ScienceLab.Bookshelf;
import Scripts.WildWestMap.ChangeCostume;


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
      
        NPC oldCowboy = new OldCowboy(1, getMapTile(8, 9).getLocation());
        oldCowboy.setInteractScript(new ChangeCostume());
        npcs.add(oldCowboy);

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
