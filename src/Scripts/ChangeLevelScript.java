package Scripts;

import Level.LevelManager;
import Level.Levels;
import Level.Script;
import Level.ScriptState;
import Screens.PlayLevelScreen;

// script for talking to tree with hole in it
public class ChangeLevelScript extends Script {

  private Levels toChange;

  public ChangeLevelScript(Levels level) {
    this.toChange = level;

  }

  @Override
  protected void setup() {
    lockPlayer();
  }

  @Override
  protected void cleanup() {
    map.flagManager.setFlag("changeMap");
    System.out.println("setting flag");
    hideTextbox();
    unlockPlayer();
  }

  @Override
  public ScriptState execute() {
    System.out.println("executing");
    // PlayLevelScreen.changeMap();
    PlayLevelScreen.doReload = true;
    LevelManager.setLevel(toChange);
    return ScriptState.COMPLETED;
  }
}