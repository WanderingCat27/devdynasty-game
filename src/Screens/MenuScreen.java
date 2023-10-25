package Screens;

import Engine.Config;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import Engine.ScreenManager;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.SpriteSheet;
import Level.LevelManager;
import Level.Map;
import Maps.TitleScreenMap;
import ui.Button.AnimatedSpriteButton;
import ui.Container.Anchor;
import ui.Container.CenterContainer;
import ui.Container.PositioningContainer;
import ui.Container.UIContainer.FillType;

// This is the class for the main menu screen
public class MenuScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;

    protected AnimatedSpriteButton playButton, creditButton, controlsButton;
    protected CenterContainer centerContainer;

    public MenuScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {

        background = new TitleScreenMap();
        background.setCenterCamera();

        centerContainer = new CenterContainer();
        centerContainer.setfillType(FillType.FILL_SCREEN);
        centerContainer.setAnchorChildren(false);

         playButton = new AnimatedSpriteButton(0, -40, 7, new SpriteSheet(ImageLoader.loadAllowTransparent("start_button.png"), 64, 32), () ->{
            screenCoordinator.setGameState(GameState.LEVEL);
            LevelManager.initStartMap();

          });

          playButton.setAnchor(Anchor.BOTTOM_CENTER);
        

          PositioningContainer container = new PositioningContainer(Anchor.TOP_CENTER);
          container.setYOrigin(20);
          container.setAnchorChildren(false);
          container.setfillType(FillType.NONE);
        controlsButton = new AnimatedSpriteButton(-20,0, 4f, new SpriteSheet(ImageLoader.loadAllowTransparent("controls_button.png"), 64, 24), () ->{
            screenCoordinator.setGameState(GameState.CONTROLS);
            });
            controlsButton.setAnchor(Anchor.TOP_RIGHT);
        
          
           creditButton = new AnimatedSpriteButton(20, 0, 4, new SpriteSheet(ImageLoader.loadAllowTransparent("credits_button.png"), 64, 24), () ->{
                screenCoordinator.setGameState(GameState.CREDITS);
            });

            creditButton.setAnchor(Anchor.TOP_LEFT);
       
        centerContainer.addComponent(playButton);
        container.addComponent(creditButton);
        container.addComponent(controlsButton);        
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
        playButton.scale(scale);
        creditButton.scale(scale);
        controlsButton.scale(scale);

    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        // graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), new Color(0, 191, 163));

        centerContainer.draw(graphicsHandler);
    }
}
