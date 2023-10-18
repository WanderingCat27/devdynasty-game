package Level;

public class LevelManager {

  private static Levels currentLevel;

  private LevelManager() {
  }

  public static void setLevel(Levels level) {
    if (currentLevel != null)
      currentLevel.getSoundPlayer().pause();
    currentLevel = level;
    currentLevel.getSoundPlayer().play();
  }

  public static Levels getCurrentLevel() {
    return currentLevel;
  }

  public static void initStartMap() {
    setLevel(Levels.TEST);
  }

  

}