package MapEditor;

import java.util.ArrayList;

import Level.Map;
import Maps.CombatMap;
import Maps.NewMap;
import Maps.PrehistoricMap;
import Maps.ScienceLabMap;
import Maps.TestMap;
import Maps.TitleScreenMap;
import Maps.Future.FloorOneMap;
import Maps.Future.FloorThreeMap;
import Maps.Future.FloorTwoMap;
import Maps.Future.FutureMap;
import Maps.Future.ReceptionMap;
import Maps.WildWest.SaloonMap;
import Maps.WildWest.WWBuildingOne;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("NewMap");
            add("WildWestMap");
            add("ScienceLabMap");
            add("CombatMap");
            add("SaloonMap");
            add("PrehistoricMap");
            add("FutureMap");
            add("WWBuildingOne");
            add("Reception");
            add("Floor1");
            add("Floor2");
            add("Floor3");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TestMap":
                return new TestMap();
            case "TitleScreen":
                return new TitleScreenMap();
            case "NewMap":
                return new NewMap();
            case "WildWestMap":
                return new FutureMap();
            case "ScienceLabMap":
                return new ScienceLabMap();
            case "CombatMap":
                return new CombatMap();
            case "SaloonMap":
              return new SaloonMap();
            case "PrehistoricMap":
                return new PrehistoricMap();
            case "FutureMap":
              return new FutureMap();
            case "WWBuildingOne":
              return new WWBuildingOne();
            case "Reception":
              return new ReceptionMap();
            case "Floor1": return new FloorOneMap();
            case "Floor2": return new FloorTwoMap();
            case "Floor3": return new FloorThreeMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
