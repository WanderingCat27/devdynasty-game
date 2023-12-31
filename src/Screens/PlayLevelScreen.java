package Screens;

import javax.sound.sampled.Clip;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Mouse;
import Engine.Screen;
import Engine.ScreenManager;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Inventory;
import GameObject.Item;
import Items.BossItems.Crystal;
import Level.FlagManager;
import Level.GlobalFlagManager;
import Level.Level;
import Level.LevelManager;
import Level.Map;
import Level.SoundPlayer;
import Level.Trigger;
import Maps.ScienceLabMap;
import Maps.Future.FutureMap;
import Maps.WildWest.WildWestMap;
import NPCs.EvilCowboy;
import Scripts.ChangeLevelByString;
import Scripts.ChangeLevelScript;
import Scripts.ScienceLab.CheckDroppedItemScript;
import Scripts.ScienceLab.ItemTableScript;
import Scripts.ScienceLab.PickUpItemScript;
import Utils.Point;
import ui.Container.Anchor;
import ui.Container.PositioningContainer;
import ui.Container.UIContainer.FillType;
import ui.Slider.Slider;
import Level.NPC;

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
  protected NPC currEnemy;
  protected EvilCowboy evilCowboy;
  protected KeyLocker keyLocker = new KeyLocker();
  protected static Key ESC = Key.ESC;
  public static float currentVolume;
  public static float currentWalkVolume;
  protected boolean activeCombat = false;
  protected boolean move = true;

  public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
    this.screenCoordinator = screenCoordinator;
    // setup state
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToCowboy", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToCaveman", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasLostBall", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToWalrus", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToDinosaur", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasFoundBall", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToDino2", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToScientist", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToOldCowboy", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToOldCowboyTwice", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasBeatCowboy", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("evilCowboyDefeated", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("cavemanDefeated", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("win", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasPickedUpCrystal", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasDroppedCrystalOff", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToCB3", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToCaveman2", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToBlueDino", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("acceptedCBquest", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToBat", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("batDefeated", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("jacketPickedUp", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasChangedCostume", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("swordPickedUp", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("dinoDefeated", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("swordSpawn", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasDroppedMetalOff", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasPickedUpMetal", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToRobotEnemy", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("robotEnemyDefeated", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToSecurityRobot", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("securityRobotDefeated", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasDroppedChipOff", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasPickedUpChip", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToRobotOne", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToRobotTwo", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("hasTalkedToRobotThree", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("returnedToLab", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("tasksCompleted", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("redJewelCorrect", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("greenJewelCorrect", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("blueJewelCorrect", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("allJewelsCorrect", false);
    GlobalFlagManager.FLAG_MANAGER.addFlag("flashlightPickedUp", false);

    this.currentVolume = 100;
    this.currentWalkVolume = 100;
    pauseScreen = new PauseScreen(this, LevelManager.getCurrentLevel().getMap().soundPlayer,
        LevelManager.getCurrentLevel().getPlayer().getWalkingSoundPlayer());
  }

  public void initialize() {
    // setup player
    // this.player = new PlayerPlayer(map.getPlayerStartPosition().x,
    // map.getPlayerStartPosition().y);
    this.playLevelScreenState = PlayLevelScreenState.RUNNING;

    inventory = new Inventory("noSelectionHUD.png", "oneSlotHUD.png", "twoSlotHUD.png", "threeSlotHUD.png",
        "fourSlotHUD.png", LevelManager.getCurrentLevel().getMap(), LevelManager.getCurrentLevel().getPlayer());

    winScreen = new WinScreen(this);
    if (combatScreen == null)
      combatScreen = new CombatScreen(this);

    if (LevelManager.getCurrentLevel().getSoundPlayer() != null) {
      LevelManager.getCurrentLevel().getSoundPlayer().setVolume((int) currentVolume);
      LevelManager.getCurrentLevel().getSoundPlayer().clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    SoundPlayer.musicPlaying = true;

  }

  public void update() {
    if (doReload) {
      if (LevelManager.getCurrentLevel().getSoundPlayer() != null)
        LevelManager.getCurrentLevel().getSoundPlayer().pause();
      initialize();
      doReload = false;
    }

    if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToCowboy") && !GlobalFlagManager.FLAG_MANAGER.isFlagSet("evilCowboyDefeated")) {
      runCombat(LevelManager.getCurrentLevel().getMap().getNPCById(4), "hasTalkedToCowboy", "evilCowboyDefeated");
    }
    if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToBlueDino") && !GlobalFlagManager.FLAG_MANAGER.isFlagSet("dinoDefeated")) {
      runCombat(LevelManager.getCurrentLevel().getMap().getNPCById(3), "hasTalkedToBlueDino", "dinoDefeated");
    }
    if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToBat") && !GlobalFlagManager.FLAG_MANAGER.isFlagSet("batDefeated"))
      runCombat(LevelManager.getCurrentLevel().getMap().getNPCById(6), "hasTalkedToBat", "batDefeated");

    if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToSecurityRobot") && !GlobalFlagManager.FLAG_MANAGER.isFlagSet("securityRobotDefeated"))
      runCombat(LevelManager.getCurrentLevel().getMap().getNPCById(2), "hasTalkedToSecurityRobot", "securityRobotDefeated");

    if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("evilCowboyDefeated")){
        if(LevelManager.getCurrentLevel() == LevelManager.LAB && GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasDroppedCrystalOff")) {
          LevelManager.getCurrentLevel().getMap().getNPCById(2).setInteractScript(new ChangeLevelByString("prehistoric"));
        }
        else if(LevelManager.getCurrentLevel() == LevelManager.LAB && ItemTableScript.itemsOnTable.size() == 0)
        {
          LevelManager.getCurrentLevel().getMap().getNPCById(2).setInteractScript(new CheckDroppedItemScript());
        }
        //LevelManager.getCurrentLevel().getMap().getNPCById(2).setIsHidden(false);
        if(LevelManager.getCurrentLevel() == LevelManager.WILDWEST)
        {
          LevelManager.getCurrentLevel().getMap().getNPCById(6).setIsHidden(false);
          if(GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasPickedUpCrystal"))
          {
            LevelManager.getCurrentLevel().getMap().getNPCById(6).setInteractScript(new ChangeLevelScript(LevelManager.LAB));
          }
          else
          {
            LevelManager.getCurrentLevel().getMap().getNPCById(6).setInteractScript(new PickUpItemScript());
          }
      }
    }

    if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToRobotEnemy") && !GlobalFlagManager.FLAG_MANAGER.isFlagSet("robotEnemyDefeated")) {
        runCombat(LevelManager.getCurrentLevel().getMap().getNPCById(9), "hasTalkedToRobotEnemy", "robotEnemyDefeated");
        // System.out.println("ran robot combat");
      }

    if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToRobotOne")) {
      GlobalFlagManager.FLAG_MANAGER.setFlag("tasksCompleted");
    }
   
   
      if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("robotEnemyDefeated")){
        if(LevelManager.getCurrentLevel() == LevelManager.LAB && GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasDroppedChipOff")) {
          LevelManager.getCurrentLevel().getMap().getNPCById(9).setInteractScript(new ChangeLevelByString("floor3"));
        }
        else if(LevelManager.getCurrentLevel() == LevelManager.LAB && ItemTableScript.itemsOnTable.size() == 2)
        {
          LevelManager.getCurrentLevel().getMap().getNPCById(9).setInteractScript(new CheckDroppedItemScript());
        }
        if(LevelManager.getCurrentLevel() == LevelManager.FUTURE){
          LevelManager.getCurrentLevel().getMap().getNPCById(2).setIsHidden(false);
          if(GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasPickedUpChip"))
          {
            LevelManager.getCurrentLevel().getMap().getNPCById(2).setInteractScript(new ChangeLevelScript(LevelManager.LAB));
          }
          else
          {
            LevelManager.getCurrentLevel().getMap().getNPCById(2).setInteractScript(new PickUpItemScript());
          }
      }
      
    }
    if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasTalkedToCaveman") && !GlobalFlagManager.FLAG_MANAGER.isFlagSet("cavemanDefeated")) {
      runCombat(LevelManager.getCurrentLevel().getMap().getNPCById(5), "hasTalkedToCaveman", "cavemanDefeated");
      // System.out.println("ran caveman combat");
    }

    if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("dinoDefeated") && LevelManager.getCurrentLevel() == LevelManager.PREHISTORIC && !GlobalFlagManager.FLAG_MANAGER.isFlagSet("swordSpawn")) {
      LevelManager.getCurrentLevel().getMap().getItemById(2).setIsHidden(false);
      GlobalFlagManager.FLAG_MANAGER.setFlag("swordSpawn");
    }

    if(GlobalFlagManager.FLAG_MANAGER.isFlagSet("cavemanDefeated")){
        if(LevelManager.getCurrentLevel() == LevelManager.LAB && GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasDroppedMetalOff")) {
          LevelManager.getCurrentLevel().getMap().getNPCById(2).setInteractScript(new ChangeLevelByString("future"));
        }
        else if(LevelManager.getCurrentLevel() == LevelManager.LAB && ItemTableScript.itemsOnTable.size() == 1)
        {
          LevelManager.getCurrentLevel().getMap().getNPCById(2).setInteractScript(new CheckDroppedItemScript());
        }
        if(LevelManager.getCurrentLevel() == LevelManager.PREHISTORIC){
          LevelManager.getCurrentLevel().getMap().getNPCById(2).setIsHidden(false);
          if(GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasPickedUpMetal"))
          {
            LevelManager.getCurrentLevel().getMap().getNPCById(2).setInteractScript(new ChangeLevelScript(LevelManager.LAB));
          }
          else
          {
            LevelManager.getCurrentLevel().getMap().getNPCById(2).setInteractScript(new PickUpItemScript());
          }
      }
      
    }

    if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("returnedToLab") && LevelManager.getCurrentLevel() == LevelManager.LAB) {
      if (move == true) {
          LevelManager.getCurrentLevel().getMap().getNPCById(3).setLocation((LevelManager.getCurrentLevel().getMap().getNPCById(2).getLocation().getX() - 100), LevelManager.getCurrentLevel().getMap().getNPCById(2).getLocation().getY());
          System.out.println("moving");
          move = false;
      }
        
    }


    if(LevelManager.getCurrentLevel() == LevelManager.CAVE){

      if(GlobalFlagManager.FLAG_MANAGER.isFlagSet("redJewelCorrect") && GlobalFlagManager.FLAG_MANAGER.isFlagSet("greenJewelCorrect") && GlobalFlagManager.FLAG_MANAGER.isFlagSet("blueJewelCorrect")){
        GlobalFlagManager.FLAG_MANAGER.setFlag("allJewelsCorrect");
        System.out.println("Jewels correct");
        LevelManager.getCurrentLevel().getMap().getItemById(2).setIsHidden(true);
        LevelManager.getCurrentLevel().getMap().getItemById(3).setIsHidden(false);
      }

      if(GlobalFlagManager.FLAG_MANAGER.isFlagSet("redJewelCorrect")){
        System.out.println("Red Correct");
      }

      if(GlobalFlagManager.FLAG_MANAGER.isFlagSet("greenJewelCorrect")){
        System.out.println("Green Correct");
      }

      if(GlobalFlagManager.FLAG_MANAGER.isFlagSet("blueJewelCorrect")){
        System.out.println("Blue Correct");
      }

      
    }

    if (GlobalFlagManager.FLAG_MANAGER.isFlagSet("win")) {
      playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
    }

    if (Keyboard.isKeyDown(ESC) && !keyLocker.isKeyLocked(ESC)) {
      keyLocker.lockKey(ESC);
      if (playLevelScreenState == PlayLevelScreenState.RUNNING || playLevelScreenState == PlayLevelScreenState.COMBAT)
        this.playLevelScreenState = PlayLevelScreenState.PAUSED;
      else
        resumeLevel();
    }
    if (keyLocker.isKeyLocked(ESC) && Keyboard.isKeyUp(ESC)) {
      keyLocker.unlockKey(ESC);
    }
    

    // based on screen state, perform specific actions
    switch (playLevelScreenState) {
      // if level is "running" update player and map to keep game logic for the
      // platformer level going
      case RUNNING:
        LevelManager.getCurrentLevel().update();
        LevelManager.getCurrentLevel().getPlayer().getWalkingSoundPlayer().setVolume((int) currentWalkVolume);
        break;
      // if level has been completed, bring up level cleared screen
      case LEVEL_COMPLETED:
        winScreen.update();
        break;
      case PAUSED:
        pauseScreen.update();
        break;
      case COMBAT:
        if (combatScreen.isInitialized()) {
          combatScreen.update();
        } else {
          combatScreen = new CombatScreen(this, currEnemy);
          combatScreen.update();
          activeCombat = true;
        }
        break;

    }
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
      case COMBAT:
        combatScreen.draw(graphicsHandler);
    }

    // print tile location mouse is over, to find tiles to place entities on

    // if (Mouse.isButtonDown(Mouse.LEFT_MOUSE_BUTTON) &&
    // LevelManager.getCurrentLevel() != null) {
    // Map map = LevelManager.getCurrentLevel().getMap();
    // float x = map.getCamera().getX();
    // float y = map.getCamera().getY() - map.getCamera().getHeight() + 21;
    // map.getTileByPosition(Mouse.getMouseLoction().x,
    // Mouse.getMouseLoction().y).getLocation();
    // System.out.println(x + " " + y);
    // }

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
    getSoundPlayer().play();
    System.out.println("playing music");
    if(activeCombat && !combatScreen.gameOver()){
      this.playLevelScreenState = PlayLevelScreenState.COMBAT;
      System.out.println("resume combat");
    } else {
      this.playLevelScreenState = PlayLevelScreenState.RUNNING;
      System.out.println("resume level");
    }
    
  }

  public void setCurrentVolume(float volume) {
    this.currentVolume = volume;
  }

  public static float getCurrentVolume() {
    return currentVolume;
  }

  public void setCurrentWalkVolume(float volume) {
    this.currentWalkVolume = volume;
  }

  public static float getCurrentWalkVolume() {
    return currentWalkVolume;
  }

  public void goBackToMenu() {
    screenCoordinator.setGameState(GameState.MENU);
  }

  public Inventory getInventory() {
    return inventory;
  }

  private void runCombat(NPC npc, String flagName, String defeatedFlagName) {
    if (!combatScreen.gameOver() ) {
      currEnemy = npc;
      if (this.playLevelScreenState != PlayLevelScreenState.PAUSED) {
        this.playLevelScreenState = PlayLevelScreenState.COMBAT;
      }
      activeCombat = true;
      
    } else if (!combatScreen.playerWin() && combatScreen.gameOver()) {
      GlobalFlagManager.FLAG_MANAGER.unsetFlag(flagName);
      activeCombat = false;
      this.playLevelScreenState = PlayLevelScreenState.RUNNING;
      combatScreen = new CombatScreen(null);
    } else {
        GlobalFlagManager.FLAG_MANAGER.setFlag(defeatedFlagName);
        System.out.println(defeatedFlagName);
        activeCombat = false;
        combatScreen = new CombatScreen(null);
        this.playLevelScreenState = PlayLevelScreenState.RUNNING;
    }

  }

  // This enum represents the different states this screen can be in
  private enum PlayLevelScreenState {
    RUNNING, LEVEL_COMPLETED, PAUSED, COMBAT;
  }
}
