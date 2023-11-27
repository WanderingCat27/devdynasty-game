package Maps.WildWest;


import java.util.ArrayList;

import GameObject.Item;
import Items.BuildingEntrance;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.EvilCowboy;
import NPCs.Saloon.BarTable;
import NPCs.Saloon.RoundTable;
import Scripts.WildWestMap.EvilCowboyScript;
import Tilesets.SaloonTileset;


public class SaloonMap extends Map {
  public SaloonMap() {
    super("saloon_map.txt", new SaloonTileset());
    addMusic("Resources/Audio/saloon.wav");
    this.playerStartPosition = getMapTile(8, 14).getLocation();
    setCenterCamera();
  }

  @Override
  public ArrayList<Item> loadItems() {
    ArrayList<Item> list = new ArrayList<>();
    BuildingEntrance saloonEntrance = new BuildingEntrance(0, getMapTile(7, 14).getLocation(), 160, 32, "wildwest");
    list.add(saloonEntrance);
    return list;
  }

  @Override 
  public ArrayList<NPC> loadNPCs()
  {
      ArrayList<NPC> npcs = new ArrayList<>();
      NPC barTable = new BarTable(1, getMapTile(5, 5).getLocation());
      npcs.add(barTable);

      NPC roundTable = new RoundTable(2, getMapTile(3, 11).getLocation());
      npcs.add(roundTable);

      NPC roundTable2 = new RoundTable(3, getMapTile(12, 11).getLocation());
      npcs.add(roundTable2);

      EvilCowboy evilCowboy = new EvilCowboy(4, getMapTile(8, 5).getLocation(), 20);
      //evilCowboy.setExistenceFlag("hasTalkedToCowboy");
      evilCowboy.setInteractScript(new EvilCowboyScript());
      npcs.add(evilCowboy);

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
