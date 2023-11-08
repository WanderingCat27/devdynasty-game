package Maps;
import java.util.ArrayList;
import GameObject.Item;
import Items.BuildingEntrance;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import Tilesets.SaloonTileset;


public class WWBuildingOne extends Map {
  public WWBuildingOne() {
    super("WWBuildingOne.txt", new SaloonTileset());
    this.playerStartPosition = getMapTile(8, 8).getLocation();
    //setCenterCamera();
  }

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList<Item> list = new ArrayList<>();
    BuildingEntrance buildingOneEntrance = new BuildingEntrance(0, getMapTile(10, 10).getLocation(), 160, 32, "WWBuildingOne");
    list.add(buildingOneEntrance);
    return list;
  }

  @Override
  public ArrayList<Trigger> loadTriggers() {
    ArrayList<Trigger> triggers = new ArrayList<>();
    // triggers.add(new Trigger(770, 1042, 50,10, new triggerForScientist(),
    // "hasTalkedToScientist"));
    return triggers;
  }

}
