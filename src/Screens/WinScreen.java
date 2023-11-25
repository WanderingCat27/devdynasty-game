package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

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
import Utils.Colors;
import ui.Button.AnimatedSpriteButton;
import ui.Container.Anchor;
import ui.Container.CenterContainer;
import ui.Container.PositioningContainer;
import ui.Container.UIContainer.FillType;
import ui.SpriteFont.SpriteFont;
import ui.SpriteUI.SpriteUI;

// This class is for the win level screen
public class WinScreen extends Screen {
    protected SpriteFont winMessage;
    protected SpriteFont instructions;
    protected SpriteUI youWinPopup;
    protected SpriteUI winPopup;

    protected SpriteUI createdBy, team, names, thanks;

    protected KeyLocker keyLocker = new KeyLocker();
    protected PlayLevelScreen playLevelScreen;
     protected ScreenCoordinator screenCoordinator;
    protected Map background;

    protected AnimatedSpriteButton creditButton, controlsButton;
    protected CenterContainer centerContainer;
    protected CenterContainer winContainer, creditsContainer;
    protected int creditState;
    protected SpriteFont continueLabel;

    public WinScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {

        creditState = 0;



        background = new WinMap();
        background.setCenterCamera();

        centerContainer = new CenterContainer();

        centerContainer.setfillType(FillType.FILL_SCREEN);
        centerContainer.setAnchorChildren(false);
        screenCoordinator = new ScreenCoordinator();

        winContainer = new CenterContainer();
        winContainer.setfillType(FillType.FILL_SCREEN);
        winContainer.setAnchorChildren(false);

        creditsContainer = new CenterContainer();
        creditsContainer.setfillType(FillType.FILL_SCREEN);
        creditsContainer.setAnchorChildren(false);
        

          PositioningContainer container = new PositioningContainer(Anchor.BOTTOM_CENTER);
          PositioningContainer container2 = new PositioningContainer(Anchor.BOTTOM_CENTER);
          
          container.setYOrigin(300);
          container.setAnchorChildren(false);
          container.setfillType(FillType.NONE);

            continueLabel = new SpriteFont("Click to Continue", 0, 121, new Font("Monospaced", Font.PLAIN, 20),
            Color.ORANGE);
            


         /*    controlsButton = new AnimatedSpriteButton(-20,20, 5f, new SpriteSheet(ImageLoader.loadAllowTransparent("second_controls_button.png"), 64, 24), () ->{
            screenCoordinator.setGameState(GameState.CONTROLS);
            });
            controlsButton.setAnchor(Anchor.TOP_RIGHT); */
        
          
                

        youWinPopup = new SpriteUI(0, 0, ImageLoader.load("you.png"), 13f);
        youWinPopup.setAnchor(Anchor.BOTTOM_RIGHT);
        winContainer.addComponent(youWinPopup);
        
        winPopup = new SpriteUI(0, 0, ImageLoader.load("win.png"), 13f);
        winPopup.setAnchor(Anchor.BOTTOM_LEFT);
        winContainer.addComponent(winPopup);


        createdBy = new SpriteUI(0, 0, ImageLoader.load("createdBy.png"), 8f);
        createdBy.setAnchor(Anchor.BOTTOM_CENTER);

        team = new SpriteUI(0, 0, ImageLoader.load("team.png"), 5f);
        team.setAnchor(Anchor.TOP_CENTER);

        names = new SpriteUI(0, 0, ImageLoader.load("names.png"), 6f);
        names.setAnchor(Anchor.CENTER);

        thanks = new SpriteUI(0, 0, ImageLoader.load("thanks.png"), 6f);
        thanks.setAnchor(Anchor.CENTER);
        //creditsContainer.addComponent(team);

            creditButton = new AnimatedSpriteButton(20, 20, 5, new SpriteSheet(ImageLoader.loadAllowTransparent("credits_button.png"), 64, 24), () ->{
                runCredits();
                System.out.println("executed");
            });

            
           
                centerContainer.addComponent(creditButton);
                creditButton.setAnchor(Anchor.TOP_CENTER);
                continueLabel.setAnchor(Anchor.CENTER);
                continueLabel.setYOrigin(200);
                centerContainer.addComponent(container);
                centerContainer.addComponent(container2);


    }


    private void runCredits() {
        
        if (creditState < 3) {
            creditButton.setHeight(ScreenManager.getScreenHeight());
            creditButton.setWidth(ScreenManager.getScreenWidth());
            creditState++;
        }
        else {
            creditState = 0;
            creditButton.setHeight(64);
            creditButton.setWidth(24);
        }
            handleCredits(creditState);
    }

    private void handleCredits(int creditState) {
        switch (creditState) {
            case 0:
                creditsContainer.removeComponent(thanks);
                creditsContainer.removeComponent(continueLabel);
                break;
            case 1:
                creditsContainer.addComponent(createdBy);
                creditsContainer.addComponent(team);
                creditsContainer.addComponent(continueLabel);
                break;
            case 2:
                creditsContainer.removeComponent(createdBy);
                creditsContainer.removeComponent(team);
                creditsContainer.addComponent(names);
                break;
            case 3:
                creditsContainer.removeComponent(names);
                creditsContainer.addComponent(thanks);
                break;
        }
    }

    private float getScaleFactor() {
      return Math.max(1, Math.min((float)ScreenManager.getScreenHeight() / Config.GAME_WINDOW_HEIGHT, (float)ScreenManager.getScreenWidth() / Config.GAME_WINDOW_WIDTH));
    }


    public void update() {
        // update background map (to play tile animations)
        background.update(null);
        if (creditState > 0) {
            creditsContainer.update();
        } else {
            creditButton.scale(1);
        }
        winContainer.update();
        centerContainer.update();
        float scale = getScaleFactor();
       // creditButton.scale(scale);
       // controlsButton.scale(scale);
        youWinPopup.scale(scale);
        winPopup.scale(scale);
        createdBy.scale(scale);
        team.scale(scale);
        

    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        centerContainer.draw(graphicsHandler);
        // graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), new Color(0, 191, 163));
        winContainer.draw(graphicsHandler);
        if (creditState > 0) {
            creditsContainer.draw(graphicsHandler);
            SpriteUI x = null;
            switch (creditState) {
                case 1:
                    x = createdBy;
                    break;
                case 2:
                    x = names;
                    break;
                case 3:
                    x = thanks;
                    break;
            }
            graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Colors.CORNFLOWER_BLUE);
            System.out.println(names.getYAbs());
            creditsContainer.draw(graphicsHandler);
        }
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
