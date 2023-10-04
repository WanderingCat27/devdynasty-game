package MapEditor;

import Level.Map;
import Maps.TestMap;
import Maps.TitleScreenMap;
import Maps.NewMap;
import Maps.WildWestMap;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("NewMap");
            add("WildWestMap");
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
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
