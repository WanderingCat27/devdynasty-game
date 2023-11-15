package Screens;

import java.awt.Color;

import Engine.Config;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Engine.ScreenManager;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.SpriteSheet;
import Level.LevelManager;
import Level.Map;
import Maps.WinMap;
import ui.Button.AnimatedSpriteButton;
import ui.Container.Anchor;
import ui.Container.CenterContainer;
import ui.Container.PositioningContainer;
import ui.Container.UIContainer.FillType;
import ui.SpriteFont.SpriteFont;

// This class is for the win level screen
public class WinScreen extends Screen {
    protected SpriteFont winMessage;
    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected PlayLevelScreen playLevelScreen;
     protected ScreenCoordinator screenCoordinator;
    protected Map background;

    protected AnimatedSpriteButton creditButton, controlsButton;
    protected CenterContainer centerContainer;

    public WinScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {

        background = new WinMap();
        background.setCenterCamera();

        centerContainer = new CenterContainer();
        centerContainer.setfillType(FillType.FILL_SCREEN);
        centerContainer.setAnchorChildren(false);
        screenCoordinator = new ScreenCoordinator();
        

          PositioningContainer container = new PositioningContainer(Anchor.TOP_CENTER);
          container.setYOrigin(20);
          container.setAnchorChildren(false);
          container.setfillType(FillType.NONE);
            controlsButton = new AnimatedSpriteButton(-20,20, 5f, new SpriteSheet(ImageLoader.loadAllowTransparent("second_controls_button.png"), 64, 24), () ->{
            screenCoordinator.setGameState(GameState.CONTROLS);
            });
            controlsButton.setAnchor(Anchor.TOP_RIGHT);
        
          
           creditButton = new AnimatedSpriteButton(20, 20, 5, new SpriteSheet(ImageLoader.loadAllowTransparent("credits_button.png"), 64, 24), () ->{
                screenCoordinator.setGameState(GameState.CREDITS);
                System.out.println("executed");
            });

            creditButton.setAnchor(Anchor.TOP_LEFT);
           
                centerContainer.addComponent(creditButton);      

                        centerContainer.addComponent(controlsButton);      

        centerContainer.addComponent(container);
    }


    private float getScaleFactor() {
      return Math.max(1, Math.min((float)ScreenManager.getScreenHeight() / Config.GAME_WINDOW_HEIGHT, (float)ScreenManager.getScreenWidth() / Config.GAME_WINDOW_WIDTH));
    }


    public void update() {
        // update background map (to play tile animations)
        background.update(null);
        centerContainer.update();
        float scale = getScaleFactor();
        creditButton.scale(scale);
        controlsButton.scale(scale);

    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        // graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), new Color(0, 191, 163));

        centerContainer.draw(graphicsHandler);
    }
/*
    @Override
    public void initialize() {
        winMessage = new SpriteFont("You win!", 350, 239, "Comic Sans", 30, Color.white);
        instructions = new SpriteFont("Press Enter to play again or Space to go back to the main menu", 120, 279,"Comic Sans", 20, Color.white);
        keyLocker.lockKey(Key.SPACE);
        keyLocker.lockKey(Key.ENTER);
    }

    @Override
    public void update() {
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (Keyboard.isKeyUp(Key.ENTER)) {
            keyLocker.unlockKey(Key.ENTER);
        }

        // if enter is pressed, reset level. if space is pressed, go back to main menu
        if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
            playLevelScreen.goBackToMenu();
        } else if (Keyboard.isKeyDown(Key.ENTER) && !keyLocker.isKeyLocked(Key.ENTER)) {
            playLevelScreen.resetLevel();
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        winMessage.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }

*/
}
