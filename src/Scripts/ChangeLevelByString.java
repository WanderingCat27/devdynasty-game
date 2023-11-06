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
    if (this.levelStr.toLowerCase() == "wildwest")
      level = LevelManager.WILDWEST;
    else if (this.levelStr.toLowerCase() == "lab")
      level = LevelManager.LAB;
    else if (this.levelStr.toLowerCase() == "saloon")
      level = LevelManager.SALOON_INSIDE;
    else if (this.levelStr.toLowerCase() == "prehistoric")
      level = LevelManager.PREHISTORIC;

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
