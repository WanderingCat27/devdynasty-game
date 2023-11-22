package Scripts;

import Level.Level;
import Level.LevelManager;
import Level.Script;
import Level.ScriptState;
import Screens.PlayLevelScreen;

public class ChangeLevelByString extends Script {

  private final String levelStr;


  public ChangeLevelByString(String level) {

    this.levelStr = level;
  }

  @Override
  public ScriptState execute() {
    // call setup code
    start();
    LevelManager.getCurrentLevel().getPlayer().stopSound(); // stops walking sounds
    Level level = null;
    
    if (this.levelStr.equalsIgnoreCase("wildwest"))
      level = LevelManager.WILDWEST;
    else if (this.levelStr.equalsIgnoreCase("lab"))
      level = LevelManager.LAB;
    else if (this.levelStr.equalsIgnoreCase("saloon"))
      level = LevelManager.SALOON_INSIDE;
    else if (this.levelStr.equalsIgnoreCase("buildingone"))
      level = LevelManager.WWBUILDINGLEFT;
    else if (this.levelStr.equalsIgnoreCase("wwbuildingone"))
      level = LevelManager.WWBUILDINGLEFT;
    else if (this.levelStr.equalsIgnoreCase("buildingtwo"))
      level = LevelManager.WWBUILDINGRIGHT;
    else if (this.levelStr.equalsIgnoreCase("prehistoric"))
      level = LevelManager.PREHISTORIC;
    else if (this.levelStr.equalsIgnoreCase("reception"))
      level = LevelManager.RECEPTION;
    else if (this.levelStr.equalsIgnoreCase("floor1"))
      level = LevelManager.FLOOR1;
    else if (this.levelStr.equalsIgnoreCase("floor2"))
      level = LevelManager.FLOOR2;
    else if (this.levelStr.equalsIgnoreCase("floor3"))
      level = LevelManager.FLOOR3;
    else if (this.levelStr.equalsIgnoreCase("future"))
      level = LevelManager.FUTURE;
    else if (this.levelStr.equalsIgnoreCase("OldCowboyHouseMap"))
      level = LevelManager.OCHOUSE;

    if (level != null) {
      LevelManager.setLevel(level);
      PlayLevelScreen.doReload = true;
    }

    // script ends
    return ScriptState.COMPLETED;
  }

  @Override
  protected void setup() {
  }

  @Override
  protected void cleanup() {
  }

}
