package Screens;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Inventory;
import GameObject.Item;
import Level.FlagManager;
import Level.LevelManager;
import Level.Map;
import Level.SoundPlayer;
import ui.Container.Anchor;
import ui.Container.PositioningContainer;
import ui.Container.UIContainer.FillType;
import ui.Slider.Slider;

// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen {
  protected ScreenCoordinator screenCoordinator;
  // protected Sprite hud;
  protected static Inventory inventory;
  protected PlayLevelScreenState playLevelScreenState;
  protected WinScreen winScreen;
  protected CombatScreen combatScreen;
  protected FlagManager flagManager;
  protected Item sword;
  public static boolean doReload = false;
  protected PauseScreen pauseScreen;
  protected PositioningContainer sliderContainer;
  protected Slider volumeSlider;

  protected KeyLocker keyLocker = new KeyLocker();
  protected static Key ESC = Key.ESC;

  public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
    this.screenCoordinator = screenCoordinator;

    // setup state
    flagManager = new FlagManager();
    flagManager.addFlag("hasLostBall", false);
    flagManager.addFlag("hasTalkedToWalrus", false);
    flagManager.addFlag("hasTalkedToDinosaur", false);
    flagManager.addFlag("hasFoundBall", false);
    flagManager.addFlag("hasTalkedToDino2", false);
  }

  public void initialize() {
    // setup player
    // this.player = new PlayerPlayer(map.getPlayerStartPosition().x,
    // map.getPlayerStartPosition().y);
    this.playLevelScreenState = PlayLevelScreenState.RUNNING;

    // setup inventory
    LevelManager.getCurrentLevel().getMap().setFlagManager(flagManager);

    inventory = new Inventory("noSelectionHUD.png", "oneSlotHUD.png", "twoSlotHUD.png", "threeSlotHUD.png",
        "fourSlotHUD.png", LevelManager.getCurrentLevel().getMap(), LevelManager.getCurrentLevel().getPlayer());

    winScreen = new WinScreen(this);

    LevelManager.getCurrentLevel().getMap().soundPlayer.play();
    SoundPlayer.musicPlaying = true;

    // dont re-initialize slider

    // if (volumeSlider == null) {
    // // Create the volume slider
    // volumeSlider = new Slider(0, 0, 200, 0, 100);
    // volumeSlider.setValue(volumeSlider.getMax());
    // volumeSlider.addChangeListener(() -> {
    // this.soundPlayer.setVolume((int) volumeSlider.getValue());
    // System.out.println(volumeSlider.getValue());
    // });
    // // position at top of screen and anchor objects to their top center
    // sliderContainer = new PositioningContainer(Anchor.TOP_CENTER);
    // sliderContainer.setfillType(FillType.FILL_SCREEN);
    // sliderContainer.setAnchorChildren(true);

    // sliderContainer.addComponnent(volumeSlider);
    // }
    // this.soundPlayer.setVolume((int) volumeSlider.getValue());

    pauseScreen = new PauseScreen(this, LevelManager.getCurrentLevel().getMap().soundPlayer);
    if (volumeSlider == null) {
      // Create the volume slider
      volumeSlider = new Slider(0, 0, 200, 0, 100);
      volumeSlider.setValue(volumeSlider.getMax());
      volumeSlider.addChangeListener(() -> {
        LevelManager.getCurrentLevel().getMap().soundPlayer.setVolume((int) volumeSlider.getValue());
      });
      // position at top of screen and anchor objects to their top center
      sliderContainer = new PositioningContainer(Anchor.TOP_CENTER);
      sliderContainer.setfillType(FillType.FILL_SCREEN);
      sliderContainer.setAnchorChildren(true);

      sliderContainer.addComponent(volumeSlider);
    }
  }

  public void update() {
    if (doReload) {
      LevelManager.getCurrentLevel().getMap().soundPlayer.pause();
      initialize();
      doReload = false;

    }

    if (LevelManager.getCurrentLevel().getMap().getFlagManager().isFlagSet("hasTalkedToDino2")) {
      screenCoordinator.setGameState(GameState.COMBAT);
      System.out.println("Combat mode");
    }

    if (Keyboard.isKeyDown(ESC) && !keyLocker.isKeyLocked(ESC)) {
      keyLocker.lockKey(ESC);
      if (playLevelScreenState == PlayLevelScreenState.RUNNING)
        this.playLevelScreenState = PlayLevelScreenState.PAUSED;
      else
        resumeLevel();

    }

    if (keyLocker.isKeyLocked(ESC) && Keyboard.isKeyUp(ESC))
      keyLocker.unlockKey(ESC);

    // based on screen state, perform specific actions
    switch (playLevelScreenState) {
      // if level is "running" update player and map to keep game logic for the
      // platformer level going
      case RUNNING:
        LevelManager.getCurrentLevel().update();
        break;
      // if level has been completed, bring up level cleared screen
      case LEVEL_COMPLETED:
        winScreen.update();
        break;
      case PAUSED:
        pauseScreen.update();
        break;
    }
    // sliderContainer.update();
  }

  public void draw(GraphicsHandler graphicsHandler) {
    // based on screen state, draw appropriate graphics
    switch (playLevelScreenState) {
      case RUNNING:
        Inventory.keyCheck();
        LevelManager.getCurrentLevel().drawMap(graphicsHandler);
        inventory.drawHud(graphicsHandler);
        break;
      case LEVEL_COMPLETED:
        winScreen.draw(graphicsHandler);
        break;
      case PAUSED:
        pauseScreen.draw(graphicsHandler);
        break;
    }
    // sliderContainer.draw(graphicsHandler);
  }

  public PlayLevelScreenState getPlayLevelScreenState() {
    return playLevelScreenState;
  }

  public void resetLevel() {
    initialize();
  }

  public SoundPlayer getSoundPlayer() {
    return getMap().soundPlayer;
  }

  public Map getMap() {
    return LevelManager.getCurrentLevel().getMap();
  }

  public void resumeLevel() {
    this.playLevelScreenState = PlayLevelScreenState.RUNNING;
  }

  public void goBackToMenu() {
    screenCoordinator.setGameState(GameState.MENU);
  }

  // This enum represents the different states this screen can be in
  private enum PlayLevelScreenState {
    RUNNING, LEVEL_COMPLETED, PAUSED;
  }
}
