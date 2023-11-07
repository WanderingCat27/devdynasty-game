package Maps;


import java.util.ArrayList;

import GameObject.Item;
import Items.BuildingEntrance;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Saloon.BarTable;
import NPCs.Saloon.RoundTable;
import Tilesets.SaloonTileset;


public class SaloonMap extends Map {
  public SaloonMap() {
    super("saloon_map.txt", new SaloonTileset());
    addMusic("Resources/Audio/saloon.wav");
    this.playerStartPosition = getMapTile(13, 19).getLocation();
    //setCenterCamera();
  }

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList<Item> list = new ArrayList<>();
    BuildingEntrance saloonEntrance = new BuildingEntrance(0, getMapTile(12, 20).getLocation(), 160, 32, "wildwest");
    list.add(saloonEntrance);
    return list;
  }

  @Override 
  public ArrayList<NPC> loadNPCs()
  {
      ArrayList<NPC> npcs = new ArrayList<>();
      NPC barTable = new BarTable(1, getMapTile(10, 9).getLocation());
      npcs.add(barTable);

      NPC roundTable = new RoundTable(2, getMapTile(8, 15).getLocation());
      npcs.add(roundTable);

      NPC roundTable2 = new RoundTable(3, getMapTile(17, 15).getLocation());
      npcs.add(roundTable2);

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
