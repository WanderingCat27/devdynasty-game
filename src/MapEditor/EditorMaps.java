package MapEditor;

import java.util.ArrayList;

import Level.Map;
import Maps.CombatMap;
import Maps.NewMap;
import Maps.PrehistoricMap;
import Maps.SaloonMap;
import Maps.ScienceLabMap;
import Maps.TestMap;
import Maps.TitleScreenMap;
import Maps.WildWestMap;

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
                return new WildWestMap();
            case "ScienceLabMap":
                return new ScienceLabMap();
            case "CombatMap":
                return new CombatMap();
            case "SaloonMap":
              return new SaloonMap();
            case "PrehistoricMap":
                return new PrehistoricMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
