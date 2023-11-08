package Maps;

import Level.Map;
import Tilesets.FutureTileset;
import Tilesets.WestTileset;

// Represents a test map to be used in a level
public class FutureMap extends Map {

  public FutureMap() {
    super("future_map.txt", new FutureTileset());
    this.playerStartPosition = getMapTile(19, 54).getLocation();
    addMusic("Resources/Audio/wildWest.wav");
  }

}
