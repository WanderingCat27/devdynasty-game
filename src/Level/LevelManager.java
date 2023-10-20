package Level;

import Maps.NewMap;
import Maps.ScienceLabMap;
import Maps.TestMap;
import Maps.WildWestMap;

public class LevelManager {

  public static final Level TEST = new Level(new NewMap());
  public static final Level WILDWEST = new Level(new WildWestMap());
  public static final Level LAB = new Level(new ScienceLabMap());
  public static final Level OLD_TEST = new Level(new TestMap());

  private static Level currentLevel;

  private LevelManager() {
  }

  public static void setLevel(Level level) {
    if (level == null)
      return;
    if (currentLevel != null)
      currentLevel.getSoundPlayer().pause();
    currentLevel = level;
    currentLevel.getSoundPlayer().play();
  }

  public static Level getCurrentLevel() {
    return currentLevel;
  }

  public static void initStartMap() {
    setLevel(LevelManager.LAB);
  }

}