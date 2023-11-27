package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.Clip;

import Engine.Config;
import Engine.GameWindow;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import Engine.ScreenManager;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Sprite;
import GameObject.SpriteSheet;
import Items.BossItems.Crystal;
import Items.BossItems.Metal;
import Level.Level;
import Level.LevelManager;
import Level.Map;
import Level.MapEntityStatus;
import Level.NPC;
import Level.SoundPlayer;
import Level.TextboxHandler;
import Maps.CombatMap;
import ui.Button.SolidRectButton;
import ui.Button.SpriteButton;
import ui.Button.TextButton;
import java.awt.image.BufferedImage;
import Level.TextboxHandler;
import java.util.Random;
import GameObject.Sprite;
import Screens.PlayLevelScreen;
import Screens.CombatScreenStuff.DodgeFightGameContainer;
import Screens.CombatScreenStuff.FightGameContainer;
import Screens.CombatScreenStuff.FinalFightGameContainer;
import Screens.CombatScreenStuff.MiniGameContainer;
import Scripts.NewMap.SwordScript;
import Utils.Colors;
import Utils.ImageUtils;
import Utils.Point;
import Level.NPC;
import Maps.NewMap;
import ui.Container.Anchor;
import ui.Container.CenterContainer;
import ui.Container.PositioningContainer;
import ui.Container.UIContainer;
import ui.Container.UIContainer.FillType;
import ui.SpriteUI.HealthBar;
import ui.SpriteUI.SolidSpriteUI;
import ui.SpriteUI.SpriteUI;
import NPCs.EvilCowboy;
import GameObject.Inventory;
import GameObject.Item;
import ui.Textbox.Textbox;
import ui.SpriteFont.SpriteFont;

public class CombatScreen extends Screen {

  protected ScreenCoordinator screencoordinator;
  protected PlayLevelScreen playLevelScreen;
  protected Map background;

  private boolean isInitialized;
  private boolean gameOver;

  private double attackAnimationAngle = 0;
  private BufferedImage enemyImage;

  private enum SCREENSTATE {
    TEXTBOX, FIGHTGAME, INVENTORY, USE_ITEM, RUN
  }

  private SCREENSTATE screenState = SCREENSTATE.TEXTBOX;

  private int enemyHealth;
  private int playerHealth;
  private int inventoryIndex;
  private final int initialTextboxHeight = 130;
  private final int initialFontSize = 30;
  private float prevScaleX, prevScaleY;
  private boolean playerWin;
  private boolean playerTurn;

  protected Textbox textbox;
  protected SpriteUI enemy;
  protected SpriteUI youWinPopup;
  protected HealthBar enemyHealthBar;
  protected HealthBar playerHealthBar;
  protected SpriteFont enemyHealthText;
  protected SpriteFont playerHealthText;
  protected NPC npc;
  public static SoundPlayer combatSoundPlayer;
  public static SoundPlayer combatSoundFXPlayer, weakSoundPlayer, strongSoundPlayer, bagSoundPlayer, runSoundPlayer;

  SpriteButton bagButton;

  protected PositioningContainer textBoxContainer;
  protected UIContainer fightContainer;
  protected PositioningContainer winContainer;
  private PositioningContainer bagContainer;
  private PositioningContainer runContainer;
  private PositioningContainer useItemContainer;
  private MiniGameContainer miniGameContainer;
  private boolean[] usedItems;
  private boolean awaitingAttack = false;

  public CombatScreen(PlayLevelScreen playLevelScreen) {
    this.playLevelScreen = playLevelScreen;
    // this.npc = new NPC(3, 13f, 19f, new
    // SpriteSheet(ImageLoader.load("EvilCowboy.png"), 14, 19), "STAND_DOWN");
    enemyHealth = 20;

  }

  public CombatScreen(PlayLevelScreen playLevelScreen, NPC enemy) { // Add NPC parameter to know Enemy
    this(playLevelScreen);
    this.npc = enemy;
    enemyHealth = npc.getHealth();
    playerHealth = 20;
    initialize();
  }

  public void initialize() {
    gameOver = false;
    playerWin = false;
    playerTurn = true;
    isInitialized = true;
    usedItems = new boolean[4];
    playerHealth = 50;

    fightContainer = new UIContainer(0, 0) {

      @Override
      public int getHeight() {
        return ScreenManager.getScreenHeight() - textbox.getHeight();
      }
    };
    fightContainer.setfillType(FillType.FILL_SCREEN);

    textBoxContainer = new PositioningContainer(Anchor.BOTTOM_LEFT);
    textBoxContainer.setAnchorChildren(true);
    textBoxContainer.setfillType(FillType.FILL_SCREEN);

    winContainer = new PositioningContainer(Anchor.TOP_CENTER);
    winContainer.setfillType(FillType.FILL_SCREEN);
    winContainer.setAnchorChildren(false);

    runContainer = new PositioningContainer(Anchor.BOTTOM_CENTER);
    runContainer.setfillType(FillType.FILL_SCREEN);
    runContainer.setAnchorChildren(false);

    useItemContainer = new PositioningContainer(Anchor.BOTTOM_CENTER);
    useItemContainer.setAnchorChildren(true);
    useItemContainer.setfillType(FillType.FILL_SCREEN);

    if (LevelManager.getCurrentLevel() == LevelManager.SALOON_INSIDE || LevelManager.getCurrentLevel() == LevelManager.OCHOUSE)
      miniGameContainer = new FightGameContainer(initialTextboxHeight);
    else if (LevelManager.getCurrentLevel() == LevelManager.PREHISTORIC)
      miniGameContainer = new DodgeFightGameContainer(initialTextboxHeight);
    else 
      miniGameContainer = new FinalFightGameContainer(initialTextboxHeight);

    // sound
    combatSoundPlayer = new SoundPlayer(GameWindow.getGameWindow(), "Resources/Audio/combat.wav");
    combatSoundPlayer.setVolume((int) PauseScreen.volume);
    LevelManager.getCurrentLevel().getSoundPlayer().pause();
    LevelManager.getCurrentLevel().getPlayer().stopSound(); // stops walking sound

    // sound effects
    // enemy attack sound
    combatSoundFXPlayer = new SoundPlayer(GameWindow.getGameWindow(), "Resources/Audio/punch1.wav");
    combatSoundPlayer.setVolume((int) PauseScreen.volume);
    combatSoundFXPlayer.pause();
    // player weak attack
    weakSoundPlayer = new SoundPlayer(GameWindow.getGameWindow(), "Resources/Audio/weakPunch.wav");
    weakSoundPlayer.setVolume((int) PauseScreen.volume);
    weakSoundPlayer.pause();
    // player strong attack
    strongSoundPlayer = new SoundPlayer(GameWindow.getGameWindow(), "Resources/Audio/strongPunch.wav");
    strongSoundPlayer.setVolume((int) PauseScreen.volume);
    strongSoundPlayer.pause();
    // run
    runSoundPlayer = new SoundPlayer(GameWindow.getGameWindow(), "Resources/Audio/buttonClick.wav");
    runSoundPlayer.setVolume((int) PauseScreen.volume);
    runSoundPlayer.pause();
    // bag
    bagSoundPlayer = new SoundPlayer(GameWindow.getGameWindow(), "Resources/Audio/bag.wav");
    bagSoundPlayer.setVolume((int) PauseScreen.volume);
    bagSoundPlayer.pause();

    // images
    youWinPopup = new SpriteUI(0, -40, ImageLoader.load("winPopup.png"), 5f) {
      @Override
      public int getyOff() {
        return textbox.getHeight() + 50;
      };
    };
    winContainer.addComponent(youWinPopup);
    BufferedImage enemyImage = ImageLoader.loadSubImage(npc.getPathToImage(), Colors.MAGENTA, 0, 0, 14, 19);
    this.enemyImage = enemyImage;
    enemy = new SpriteUI(0, 0, enemyImage, 15);
    enemy.setAnchor(Anchor.BOTTOM_CENTER);
    enemyHealthBar = new HealthBar((ScreenManager.getScreenWidth()/2)-100, 30, 200, 30, Colors.LIGHT_GREEN, Colors.BLACK, enemyHealth);
    CenterContainer enemyContainer = new CenterContainer();
    enemyContainer.setfillType(FillType.FILL_PARENT);
    enemyContainer.setAnchorChildren(false);
    enemyContainer.addComponent(enemy);
    fightContainer.addComponent(enemyContainer);

    youWinPopup.setAnchor(Anchor.TOP_CENTER);
    winContainer.addComponent(youWinPopup);

    playerHealthText = new SpriteFont(""+playerHealth, 225, -270, "Comic Sans", 30, Color.white);
    enemyHealthText = new SpriteFont(""+enemyHealth, (ScreenManager.getScreenWidth()/2)+115, 25, "Comic Sans", 30, Color.white);
    // map background
    background = new CombatMap();

    float buttonScale = 2.67f;
    SpriteButton runButton = new SpriteButton(0, 0, buttonScale, ImageLoader.load("RunButtonNew.png"), () -> {
      if (miniGameContainer.isGameAwaitingFinish())
        return;
      if (screenState == SCREENSTATE.RUN)
        screenState = SCREENSTATE.TEXTBOX;
      else {
        runSoundPlayer.clip.setMicrosecondPosition(0);
        runSoundPlayer.play();
        screenState = SCREENSTATE.RUN;
      }
    });

    SpriteButton fightButton = new SpriteButton(0, 0, buttonScale, ImageLoader.load("FightButtonNew.png"),
        new Runnable() { // 330, 374

          @Override
          public void run() {
            if (healthZero() || screenState == SCREENSTATE.FIGHTGAME || awaitingAttack)
              return;
            screenState = SCREENSTATE.FIGHTGAME;
            miniGameContainer.start();
          }

        });

    bagButton = new SpriteButton(0, 0, buttonScale, ImageLoader.load("BagButtonNew.png"),
        new Runnable() { // 555, 374

          @Override
          public void run() {
            if (miniGameContainer.isGameAwaitingFinish())
              return;
            if (screenState == SCREENSTATE.INVENTORY)
              screenState = SCREENSTATE.TEXTBOX;
            else {
              screenState = SCREENSTATE.INVENTORY;
              bagSoundPlayer.clip.setMicrosecondPosition(0);
              bagSoundPlayer.play();
            }
          }

        });
    playerHealthBar = new HealthBar(10, -275, 200, 30, Colors.LIGHT_GREEN, Colors.BLACK, playerHealth);
    
    PositioningContainer left = new PositioningContainer(Anchor.BOTTOM_LEFT);
    left.setAnchorChildren(true);
    left.setfillType(FillType.FILL_PARENT);
    left.addComponent(fightButton);
    left.addComponent(playerHealthBar);
    left.addComponent(playerHealthText);

    PositioningContainer middle = new PositioningContainer(Anchor.BOTTOM_CENTER);
    middle.setfillType(FillType.FILL_PARENT);
    middle.addComponent(bagButton);
    middle.setAnchorChildren(true);
    PositioningContainer right = new PositioningContainer(Anchor.BOTTOM_RIGHT);
    right.setfillType(FillType.FILL_PARENT);
    right.addComponent(runButton);
    right.setAnchorChildren(true);

    fightContainer.addComponent(left);
    fightContainer.addComponent(middle);
    fightContainer.addComponent(right);

    // textbox
    textbox = new Textbox(0, 0, 300, initialTextboxHeight);
    textbox.setIsActive(true);
    textbox.addText("What will you do?");
    textbox.setfillTypeX(FillType.FILL_SCREEN);
    textbox.getSpriteFont().setFontSize(initialFontSize);
    textBoxContainer.addComponent(textbox);

    TextButton returnButton = new TextButton(0, 0, 300, 150,
        Color.GRAY, "Return", new Font("Comic Sans", Font.PLAIN, 30), Color.WHITE, () -> endCombat()) {
      @Override
      public int getWidth() {
        return ScreenManager.getScreenWidth();
      }

      @Override
      public int getHeight() {
        return textbox.getHeight();
      }
    };

    returnButton.setAnchor(Anchor.TOP_CENTER);
    winContainer.addComponent(returnButton);

    TextButton returnButtonRUN = new TextButton(0, 0, 300, 100,
        new Color(50, 75, 237), "Are you sure?", new Font("Comic Sans", Font.PLAIN, 30), Color.WHITE,
        () -> endCombat()) {
      @Override
      public int getWidth() {
        return ScreenManager.getScreenWidth();
      }

      @Override
      public int getHeight() {
        return textbox.getHeight();
      }
    };
    returnButtonRUN.setAnchor(Anchor.BOTTOM_CENTER);
    runContainer.addComponent(returnButtonRUN);

    createBagContainer();

    TextButton useItemButton = new TextButton(0, 0, 300, 150, new Color(242, 196,
        12), "Use Item?",
        new Font("Comic Sans", Font.BOLD, 40), Color.WHITE, () -> {
          usedItems[inventoryIndex] = true;
          createBagContainer(); // update bag since item is removed
          screenState = SCREENSTATE.TEXTBOX;

          // Items need a name field or smth to identify them as
          // this kinda works for now since the class name will be similar to its name
          // but not final
          if (Inventory.get(inventoryIndex).getClass().getName() == "Items.RedPotion") {
            textbox.setText("25 health regenerated");
            playerHealth += 25;
          } else {
            textbox.setText("You used: " + Inventory.get(inventoryIndex).getClass().getName());
          }

        }) {
      public int getHeight() {
        return textbox.getHeight();
      };
    };
    useItemButton.setfillTypeX(FillType.FILL_SCREEN);
    useItemContainer.addComponent(useItemButton);

  }

  private void createBagContainer() {
    bagContainer = new PositioningContainer(Anchor.BOTTOM_LEFT);
    bagContainer.setAnchorChildren(true);
    bagContainer.setfillType(FillType.FILL_SCREEN);

    for (int count = 0; count < 4; count++) {
      final int c = count;
      // if nothing in that slot draw a blank rect
      System.out.println(usedItems[count]);
      if (Inventory.itemsInInventorySprites.size() - 1 < count || usedItems[count]) {
        bagContainer.addComponent(new SolidSpriteUI(0, 0, 0, 0, Color.GRAY) {
          private final int index = c;

          @Override
          public int getWidth() {
            return ScreenManager.getScreenWidth() / 4;
          }

          @Override
          public int getXOrigin() {
            return index * ScreenManager.getScreenWidth() / 4;
          }
        });
        continue;
      }
      Runnable run = new Runnable() {
        private final int index = c;

        @Override
        public void run() {
          inventoryIndex = index;
          screenState = SCREENSTATE.USE_ITEM;
        }
      };

      SolidRectButton slot = new SolidRectButton(0, 0, 0, initialTextboxHeight, Color.WHITE, run, 10) {
        private final int index = c;

        @Override
        public int getWidth() {
          return ScreenManager.getScreenWidth() / 4;
        }

        @Override
        public int getXOrigin() {
          return index * ScreenManager.getScreenWidth() / 4;
        }
      };

      CenterContainer container = new CenterContainer();
      container.addComponent(new SpriteUI(0, 0, Inventory.itemsInInventorySprites.get(count).getImage(), 10));
      slot.addComponent(container);

      bagContainer.addComponent(slot);

    }
    bagContainer.children().forEach((b) -> {
      b.setHeight((int) (initialTextboxHeight * getYScaleFactor()));
    });

  }

  public void enemyAttack() {
    screenState = SCREENSTATE.TEXTBOX;
    textbox.setText("Enemy's turn");
    System.out.println("Running Enemy Attack");
    Timer timer = new Timer();
    TimerTask gameDelay = new TimerTask() {
      @Override
      public void run() {
        combatSoundFXPlayer.clip.setMicrosecondPosition(0);
        combatSoundFXPlayer.play();
        attackAnimation();
        awaitingAttack = false;
        int damage = (int)(Math.random()*12)+4;
        playerHealth -= damage;
        playerHealthBar.update(playerHealth);
        playerHealthText.setText(""+playerHealth);
        textbox.setText("Enemy did " + damage + " damage" + "\nYour Health: " + playerHealth + "\nWhat will you do?");
        timer.cancel();
      }

    };
    timer.schedule(gameDelay, 2000, 2000);
    playerTurn = true;

  }

  public void update() {
    background.update(null);
    if (playerTurn && playerAlive()) {
      switch (screenState) {
        case INVENTORY:
          bagContainer.update();
          break;
        case RUN:
          runContainer.update();
          break;
        case USE_ITEM:
          useItemContainer.update();
          break;
        case FIGHTGAME:
          miniGameContainer.update();
          if (miniGameContainer.isGameOver()) {
            screenState = SCREENSTATE.TEXTBOX;
            System.out.println("Attacked");
            int damage = (int) (miniGameContainer.getScore() * 10 + .5f);
            if (damage > enemyHealth) {
              enemyHealth = 0;
            } else {
              enemyHealth -= damage;
            }

            Timer timer = new Timer();
            TimerTask gameDelay = new TimerTask() {
              @Override
              public void run() {
                if (damage >= 8) {
                  strongSoundPlayer.clip.setMicrosecondPosition(0);
                  strongSoundPlayer.play();
                } else {
                  weakSoundPlayer.clip.setMicrosecondPosition(0);
                  weakSoundPlayer.play();
                }
                System.out.println("Health: " + playerHealth);
                if (healthZero()) {
                  textbox.setText("You did " + damage + " damage." + "\n\nYou have defeated the Enemy!");
                } else {
                  textbox.setText("You did " + damage + " damage." + "\n\nEnemy Health: " + enemyHealth);
                }
                timer.cancel();
              }

            };
            timer.schedule(gameDelay, 0, 3000);
            enemyHealthBar.update(enemyHealth);
            enemyHealthText.setText(""+enemyHealth);
            if (!healthZero()) {
              playerTurn = false;
            }

          }
          break;
        default:
          textBoxContainer.update();
          break;
      }
    } else {
      Timer timer = new Timer();
      awaitingAttack = true;
      TimerTask delay = new TimerTask() {
        @Override
        public void run() {
          enemyAttack();
          timer.cancel();
        }
      };
      timer.schedule(delay, 2000, 2000);
      playerTurn = true;

    }

    if (healthZero()) {
      winContainer.update();
    } else if (!playerAlive()) {
      textbox.setText("You lose");
      winContainer.update();
      playerWin = false;
    } else {
      if(playerTurn)
        fightContainer.update();
    }
    // scale items that should scale
    scaleAll();

  }

  public boolean playerAlive() {
    return playerHealth > 0;
  }

  public void draw(GraphicsHandler graphicsHandler) {
    background.draw(graphicsHandler);

    switch (screenState) {
      case INVENTORY:
        bagContainer.draw(graphicsHandler);
        break;
      case RUN:
        runContainer.draw(graphicsHandler);
        break;
      case USE_ITEM:
        useItemContainer.draw(graphicsHandler);
        break;
      case FIGHTGAME:
        miniGameContainer.draw(graphicsHandler);
        break;
      default:
        textBoxContainer.draw(graphicsHandler);
        break;
    }

    if (healthZero())
      winContainer.draw(graphicsHandler);
    else
      fightContainer.draw(graphicsHandler);
      enemyHealthBar.draw(graphicsHandler);
      enemyHealthText.draw(graphicsHandler);
      
  }

  private void attackAnimation()
  {
       double radius = 40; // Adjust the radius as needed
       double speed = 2 * Math.PI / 60.0; // One full circle in one second at 60 fps

       for (int frame = 0; frame < 60; frame++) {
           // Update the enemy's position for each frame
           enemy.setX((int) (0 + radius * Math.cos(speed * frame)));
           enemy.setY((int) (0 + radius * Math.sin(speed * frame)));

           // Render or do other necessary actions here

           // Sleep to achieve 60 fps (assuming this method is called in a loop)
           try {
               Thread.sleep(7); // Adjust sleep time as needed
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }

       // After the animation, return to the original location
       enemy.setX(0);
       enemy.setY(0);
  }

  protected void scaleAll() {
    float scaleX = getXScaleFactor();
    if (prevScaleX != scaleX) {
      // scale x
      prevScaleX = scaleX;
      fightContainer.children().forEach((b) -> {
        if (b.children().get(0) instanceof SpriteButton)
          ((SpriteButton) b.children().get(0)).scaleSprite(scaleX);
      });
    }

    float scaleY = getYScaleFactor();
    if (prevScaleY != scaleY) {
      // scale y
      prevScaleY = scaleY;
      youWinPopup.scale(scaleY);
      textbox.setHeight((int) (initialTextboxHeight * scaleY));
      miniGameContainer.setChildrenHeight((int) (initialTextboxHeight * scaleY));
      textbox.getSpriteFont().setFontSize((int) (initialFontSize * scaleY));

      bagContainer.children().forEach((b) -> {
        b.setHeight((int) (initialTextboxHeight * scaleY));
      });
    }
  }

  private float getXScaleFactor() {
    return Math.max(1, (float) ScreenManager.getScreenWidth() / Config.GAME_WINDOW_WIDTH);
  }

  private float getYScaleFactor() {
    return Math.max(1, (float) ScreenManager.getScreenHeight() / Config.GAME_WINDOW_HEIGHT);
  }

  protected NPC getNPC(int npcId) {
    for (NPC npc : LevelManager.getCurrentLevel().getMap().getNPCs()) {
      if (npc.getId() == npcId) {
        return npc;
      }
    }
    return null;
  }

  public boolean isInitialized() {
    return isInitialized;
  }

  public boolean healthZero() {
    return enemyHealth <= 0;
  }

  // the item name in this case is just the name of the class, for example,
  // the crystal class is called "Crystal"
  private void spawnWinningNPC(String itemName) {
    // as we add more items, just add more cases
    if (itemName.toLowerCase().equals("crystal")) {
      NPC crystal = new Crystal(10, LevelManager.getCurrentLevel().getPlayer().getLocation());
      LevelManager.getCurrentLevel().getMap().addNPC(crystal);
      crystal.setMap(LevelManager.getCurrentLevel().getMap());
      crystal.getInteractScript().setIsActive(true);
      crystal.getInteractScript().setMap(LevelManager.getCurrentLevel().getMap());
    } else if (itemName.toLowerCase().equals("metal")) {
      NPC metal = new Metal(11, LevelManager.getCurrentLevel().getPlayer().getLocation());
      LevelManager.getCurrentLevel().getMap().addNPC(metal);
      metal.setMap(LevelManager.getCurrentLevel().getMap());
      metal.getInteractScript().setIsActive(true);
      metal.getInteractScript().setMap(LevelManager.getCurrentLevel().getMap());
    }
  }

  public boolean gameOver() {
    return gameOver;
  }

  public boolean playerWin() {
    return playerWin;
  }

  public void pauseMusic() {
    combatSoundPlayer.pause();
    LevelManager.getCurrentLevel().getSoundPlayer().play();
    System.out.println("pausing combat music");
  }

  private void endCombat() {
    gameOver = true;
    pauseMusic();
    playLevelScreen.resumeLevel();
    LevelManager.getCurrentLevel().getSoundPlayer().play();
    if (healthZero() && playerAlive()) {
      playerWin = true;
      System.out.println(Arrays.toString(usedItems));
      for (int i = usedItems.length - 1; i >= 0; i--) {
        if (usedItems[i])
          Inventory.remove(i);
        usedItems[i] = false;
      }
      // spawn item
      if (LevelManager.getCurrentLevel() == LevelManager.SALOON_INSIDE) {
        spawnWinningNPC("crystal");
      } else if (LevelManager.getCurrentLevel() == LevelManager.PREHISTORIC){
        spawnWinningNPC("metal");
      }
      System.out.println("Spawning a new item");
    } else {
      playerWin = false;
    }
    isInitialized = false;
  }

}
