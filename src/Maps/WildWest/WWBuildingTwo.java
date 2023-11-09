package Maps.WildWest;
import java.util.ArrayList;
import GameObject.Item;
import Items.BuildingEntrance;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import Tilesets.SaloonTileset;


public class WWBuildingTwo extends Map {
  public WWBuildingTwo() {
    super("WWBuildingTwo.txt", new SaloonTileset());
    this.playerStartPosition = getMapTile(8, 9).getLocation();
    //setCenterCamera();
  }

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList<Item> list = new ArrayList<>();
    BuildingEntrance buildingOneEntrance = new BuildingEntrance(0, getMapTile(8, 9).getLocation(), 50, 100, "wildwest");
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
