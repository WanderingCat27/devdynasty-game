package Level;

import Maps.NewMap;
import Maps.SaloonMap;
import Maps.ScienceLabMap;
import Maps.TestMap;
import Maps.WildWestMap;

public class LevelManager {

  // public static final Level TEST = new Level(new NewMap());
  public static final Level WILDWEST = new Level() {

    @Override
    protected Map getMapInstance() {
      return new WildWestMap();
    }

  };
  public static final Level LAB = new Level() {

    @Override
    protected Map getMapInstance() {
      return new ScienceLabMap();
    }

  };
  // public static final Level OLD_TEST = new Level(new TestMap());
  public static Level SALOON_INSIDE = new Level() {

    @Override
    protected Map getMapInstance() {
      return new SaloonMap();
    }

  };

  private static Level currentLevel;

  private LevelManager() {
  }

  public static void setLevel(Level level) {
    if (level == null)
      return;
    if (currentLevel != null)
      currentLevel.unload();
    currentLevel = level;
    currentLevel.load();
    if (currentLevel.getSoundPlayer() != null)
      currentLevel.getSoundPlayer().play();
  }

  public static Level getCurrentLevel() {
    return currentLevel;
  }

  public static void initStartMap() {
    setLevel(LevelManager.LAB);
  }

}