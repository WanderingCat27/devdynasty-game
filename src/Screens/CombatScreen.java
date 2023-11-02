package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.Random;

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
import Level.Level;
import Level.LevelManager;
import Level.Map;
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
import Utils.Colors;
import Utils.ImageUtils;
import Level.NPC;
import Maps.NewMap;
import ui.Container.Anchor;
import ui.Container.CenterContainer;
import ui.Container.PositioningContainer;
import ui.Container.UIContainer;
import ui.Container.UIContainer.FillType;
import ui.SpriteUI.SolidSpriteUI;
import ui.SpriteUI.SpriteUI;
import NPCs.EvilCowboy;
import GameObject.Inventory;
import GameObject.Item;
import ui.Textbox.Textbox;

public class CombatScreen extends Screen {

  protected ScreenCoordinator screencoordinator;
  protected PlayLevelScreen playLevelScreen;
  protected Map background;

  private boolean isInitialized;
  private boolean gameOver;

  private enum SCREENSTATE {
    TEXTBOX, INVENTORY, USE_ITEM, RUN
  }

  private SCREENSTATE screenState = SCREENSTATE.TEXTBOX;

  private int health;
  private int inventoryIndex;
  private final int initialTextboxHeight = 130;
  private final int initialFontSize = 30;
  private float prevScaleX, prevScaleY;
  private boolean playerWin;

  protected Textbox textbox;
  protected SpriteUI enemy;
  protected SpriteUI youWinPopup;
  protected NPC npc;
  public SoundPlayer combatSoundPlayer;

  SpriteButton bagButton;

  protected PositioningContainer textBoxContainer;
  protected UIContainer fightContainer;
  protected CenterContainer winContainer;
  private PositioningContainer bagContainer;
  private CenterContainer runContainer;
  private PositioningContainer useItemContainer;
 
  public CombatScreen(PlayLevelScreen playLevelScreen) {
    this.playLevelScreen = playLevelScreen;
    // this.npc = new NPC(3, 13f, 19f, new
    // SpriteSheet(ImageLoader.load("EvilCowboy.png"), 14, 19), "STAND_DOWN");
    health = 20;

  }

  public CombatScreen(PlayLevelScreen playLevelScreen, NPC enemy) { // Add NPC parameter to know Enemy
    this(playLevelScreen);
    this.npc = enemy;
    health = 20;

    initialize();
  }

  public void initialize() {
    gameOver = false;
    playerWin = false;
    isInitialized = true;

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

    winContainer = new CenterContainer();
    winContainer.setfillType(FillType.FILL_SCREEN);
    winContainer.setAnchorChildren(false);

    runContainer = new CenterContainer();
    runContainer.setfillType(FillType.FILL_SCREEN);
    runContainer.setAnchorChildren(false);

    useItemContainer = new PositioningContainer(Anchor.BOTTOM_CENTER);
    useItemContainer.setAnchorChildren(true);
    useItemContainer.setfillType(FillType.FILL_SCREEN);

    // sound
    combatSoundPlayer = new SoundPlayer(GameWindow.getGameWindow(), "Resources/Audio/combat.wav");
    LevelManager.getCurrentLevel().getSoundPlayer().pause();
    LevelManager.getCurrentLevel().getPlayer().stopSound(); // stops walking sound

    // images
    youWinPopup = new SpriteUI(0, -40, ImageLoader.load("GameOver.png"), 5);
    winContainer.addComponent(youWinPopup);
    BufferedImage enemyImage = ImageLoader.loadSubImage("EvilCowboy.png", Colors.MAGENTA, 0, 0, 14, 19);
    enemy = new SpriteUI(0, 0, enemyImage, 15);
    enemy.setAnchor(Anchor.BOTTOM_CENTER);
    CenterContainer enemyContainer = new CenterContainer();
    enemyContainer.setfillType(FillType.FILL_PARENT);
    enemyContainer.setAnchorChildren(false);
    enemyContainer.addComponent(enemy);
    fightContainer.addComponent(enemyContainer);

    youWinPopup.setAnchor(Anchor.BOTTOM_CENTER);
    winContainer.addComponent(youWinPopup);

    // map background
    background = new CombatMap();

    float buttonScale = 2.67f;
    SpriteButton runButton = new SpriteButton(0, 0, buttonScale, ImageLoader.load("run_button.png"), () -> {
      if (screenState == SCREENSTATE.RUN)
        screenState = SCREENSTATE.TEXTBOX;
      else
        screenState = SCREENSTATE.RUN;
    });

    SpriteButton fightButton = new SpriteButton(0, 0, buttonScale, ImageLoader.load("fight_button.png"),
        new Runnable() { // 330, 374

          @Override
          public void run() {
            screenState = SCREENSTATE.TEXTBOX;
            if (health > 0) {
              System.out.println("Attacked");
              health -= 10;
              System.out.println("Health: " + health);
              textbox.setText("You did 10 damage." + "\n\nEnemy Health: " + health);

            }
          }

        });

    bagButton = new SpriteButton(0, 0, buttonScale, ImageLoader.load("bag_button.png"),
        new Runnable() { // 555, 374

          @Override
          public void run() {
            if (screenState == SCREENSTATE.INVENTORY)
              screenState = SCREENSTATE.TEXTBOX;
            else
              screenState = SCREENSTATE.INVENTORY;
          }

        });

    PositioningContainer left = new PositioningContainer(Anchor.BOTTOM_LEFT);
    left.setAnchorChildren(true);
    left.setfillType(FillType.FILL_PARENT);
    left.addComponent(fightButton);

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
        Color.gray, "Return to game", new Font("Comic Sans", Font.PLAIN, 20), Color.WHITE, () -> endCombat());

    returnButton.setAnchor(Anchor.TOP_CENTER);
    winContainer.addComponent(returnButton);

    TextButton returnButtonRUN = new TextButton(0, 0, 300, 100,
        Color.gray, "Are you sure?", new Font("Comic Sans", Font.PLAIN, 20), Color.WHITE, () -> endCombat());
    returnButtonRUN.setAnchor(Anchor.BOTTOM_CENTER);
    runContainer.addComponent(returnButtonRUN);

    createBagContainer();

    useItemContainer.addComponent(new TextButton(0, 0, 300, 150, Color.CYAN, "Use Item?",
        new Font("Comic Sans", Font.BOLD, 20), Color.MAGENTA, () -> {
          Item item =Inventory.remove(inventoryIndex);
          System.out.println(
              "used item in inventory at index " + inventoryIndex + " : " + item);
          createBagContainer(); // update bag since item is removed
          screenState = SCREENSTATE.TEXTBOX;

          // Items need a name field or smth to identify them as
          // this kinda works for now since the class name will be similar to its name
          // but not final
          textbox.setText("You used: " + item.getClass().getName());
        }));

  }

  private void createBagContainer() {
    bagContainer = new PositioningContainer(Anchor.BOTTOM_LEFT);
    bagContainer.setAnchorChildren(true);
    bagContainer.setfillType(FillType.FILL_SCREEN);

    bagContainer.addComponent(new SolidSpriteUI(0, 0, 100, 100, Color.GREEN));

    for (int count = 0; count < 4; count++) {
      final int c = count;
      // if nothing in that slot draw a blank rect
      if (Inventory.itemsInInventorySprites.size() - 1 < count) {
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

  public void update() {
    background.update(null);

    switch (screenState) {
      case INVENTORY:
        bagContainer.update();
        ;
        break;
      case RUN:
        runContainer.update();
        break;
      case USE_ITEM:
        useItemContainer.update();
      default:
        textBoxContainer.update();
        break;
    }

    if (healthZero())
      winContainer.update();
    else
      fightContainer.update();

    // scale items that should scale
    scaleAll();

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
      default:
        textBoxContainer.draw(graphicsHandler);
        break;
    }

    if (healthZero())
      winContainer.draw(graphicsHandler);
    else
      fightContainer.draw(graphicsHandler);

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
      textbox.setHeight((int) (initialTextboxHeight * scaleY));
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
    return health <= 0;
  }

  public boolean gameOver() {
    return gameOver;
  }

  public boolean playerWin(){
    return playerWin;
  }

  public void pauseMusic() {
    combatSoundPlayer.pause();
    LevelManager.getCurrentLevel().getSoundPlayer().play();
    System.out.println("pausing combat music");
  }

  private void endCombat() {
    pauseMusic();
    playLevelScreen.resumeLevel();
    LevelManager.getCurrentLevel().getSoundPlayer().play();
    gameOver = true;
    if(healthZero()){
      playerWin = true;
    }else{
      playerWin = false;
    }
    
  }

}
