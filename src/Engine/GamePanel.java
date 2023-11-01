package Engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Rectangle;
import Level.Level;
import Level.LevelManager;
import Screens.PlayLevelScreen;
import Utils.Colors;
import ui.SpriteFont.SpriteFont;

/*
 * This is where the game loop process and render back buffer is setup
 */
public class GamePanel extends JPanel {
  // loads Screens on to the JPanel
  // each screen has its own update and draw methods defined to handle a "section"
  // of the game.
  private ScreenManager screenManager;

  // used to draw graphics to the panel
  private GraphicsHandler graphicsHandler;

  private boolean isGamePaused = false;
  private SpriteFont pauseLabel;
  private KeyLocker keyLocker = new KeyLocker();
  private final Key pauseKey = Key.ESC;
  private Thread gameLoopProcess;

  private Key showFPSKey = Key.G;
  private SpriteFont fpsDisplayLabel;
  private boolean showFPS = false;
  private int currentFPS;
  private GameState gameState;

  private ScreenCoordinator screenCoordinatorRef;

  // The JPanel and various important class instances are setup here
  public GamePanel() {
    super();
    this.setDoubleBuffered(true);

    // attaches Keyboard class's keyListener to this JPanel
    this.addKeyListener(Keyboard.getKeyListener());

    // adds listeners for mouse events from the static Mouse class
    this.addMouseListener(Mouse.getMouseListener());
    this.addMouseMotionListener((MouseMotionListener) Mouse.getMouseListener());

    graphicsHandler = new GraphicsHandler();

    screenManager = new ScreenManager();

    pauseLabel = new SpriteFont("PAUSE", 365, 280, "Comic Sans", 24, Color.white);
    pauseLabel.setOutlineColor(Color.black);
    pauseLabel.setOutlineThickness(2.0f);

    fpsDisplayLabel = new SpriteFont("FPS", 4, 3, "Comic Sans", 12, Color.PINK);

    currentFPS = Config.TARGET_FPS;

    // this game loop code will run in a separate thread from the rest of the
    // program
    // will continually update the game's logic and repaint the game's graphics
    GameLoop gameLoop = new GameLoop(this);
    gameLoopProcess = new Thread(gameLoop.getGameLoopProcess());

    // resize screen when window resizes
    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        ScreenManager.resize(getWidth(), getHeight());
      }
    });
  }

  // this is called later after instantiation, and will initialize screenManager
  // this had to be done outside of the constructor because it needed to know the
  // JPanel's width and height, which aren't available in the constructor
  public void setupGame() {
    setBackground(Colors.BLACK);
    screenManager.initialize(new Rectangle(getX(), getY(), getWidth(), getHeight()));
  }

  // this starts the timer (the game loop is started here
  public void startGame() {
    gameLoopProcess.start();
  }

  public ScreenManager getScreenManager() {
    return screenManager;
  }

  public void setCurrentFPS(int currentFPS) {
    this.currentFPS = currentFPS;
  }

  public void update() {
    updatePauseState();
    updateShowFPSState();

    if (!isGamePaused) {
      screenManager.update();
    }

    if (Config.debugMapChangeKey && Keyboard.isKeyDown(Key.M) && !keyLocker.isKeyLocked(Key.M)) {
      keyLocker.lockKey(Key.M);
      PlayLevelScreen.doReload = true;
      
      Level level = LevelManager.LAB;

      if (LevelManager.getCurrentLevel() == LevelManager.LAB)
        level = LevelManager.WILDWEST;
      else if (LevelManager.getCurrentLevel() == LevelManager.WILDWEST)
        level = LevelManager.FUTURE;

      LevelManager.setLevel(level);

    } else if (Keyboard.isKeyUp(Key.M))
      keyLocker.unlockKey(Key.M);
  }

  private void updatePauseState() {
    // gameState =
    // ((ScreenCoordinator)screenManager.getCurrentScreen()).getGameState();
    // if (Keyboard.isKeyDown(pauseKey) && !keyLocker.isKeyLocked(pauseKey) &&
    // (gameState == GameState.LEVEL)) {
    // isGamePaused = !isGamePaused;
    // keyLocker.lockKey(pauseKey);
    // }

    // if (Keyboard.isKeyUp(pauseKey)) {
    // keyLocker.unlockKey(pauseKey);
    // }
  }

  private void updateShowFPSState() {
    if (Keyboard.isKeyDown(showFPSKey) && !keyLocker.isKeyLocked(showFPSKey)) {
      showFPS = !showFPS;
      keyLocker.lockKey(showFPSKey);
    }

    if (Keyboard.isKeyUp(showFPSKey)) {
      keyLocker.unlockKey(showFPSKey);
    }

    fpsDisplayLabel.setText("FPS: " + currentFPS);
  }

  public void draw() {
    screenManager.draw(graphicsHandler);

    // if game is paused, draw pause gfx over Screen gfx
    if (isGamePaused) {
      pauseLabel.draw(graphicsHandler);
      graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(),
          new Color(0, 0, 0, 100));
    }

    if (showFPS) {
      fpsDisplayLabel.draw(graphicsHandler);
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // every repaint call will schedule this method to be called
    // when called, it will setup the graphics handler and then call this class's
    // draw method
    graphicsHandler.setGraphics((Graphics2D) g);
    draw();
  }
}
