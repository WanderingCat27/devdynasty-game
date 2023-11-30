package Screens;

import java.awt.Color;

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
import ui.SpriteUI.SolidSpriteUI;
import ui.SpriteUI.SpriteUI;

// This is the class for the main menu screen
public class MenuScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected SolidSpriteUI solidBg;
    protected SpriteUI title;

    protected AnimatedSpriteButton playButton, creditButton, controlsButton;
    protected CenterContainer centerContainer;
    protected PositioningContainer titleContainer;

    public MenuScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {

        background = new TitleScreenMap();
        background.setCenterCamera();
        solidBg = new SolidSpriteUI(0, 0, 0, 0, new Color(149, 133, 241));
        solidBg.setfillType(FillType.FILL_SCREEN);

        titleContainer = new PositioningContainer(Anchor.TOP_CENTER);
        titleContainer.setAnchorChildren(true);
        titleContainer.setfillType(FillType.FILL_SCREEN);
        title = new SpriteUI(0, 10, ImageLoader.loadAllowTransparent("title.png"), 5);
        titleContainer.addComponent(title);

        centerContainer = new CenterContainer() {
          @Override
          public int getYAbs() {
            return (int) (title.getHeight()*1.4);
          }
          @Override
          public int getHeight() {
            return ScreenManager.getScreenHeight() - title.getHeight();
          }
        };
        centerContainer.setfillType(FillType.FILL_SCREEN);
        centerContainer.setAnchorChildren(false);

         playButton = new AnimatedSpriteButton(0, -10, 7.5f, new SpriteSheet(ImageLoader.loadAllowTransparent("start_button.png"), 64, 32), () ->{
            screenCoordinator.setGameState(GameState.LEVEL);
            LevelManager.initStartMap();

          });

          playButton.setAnchor(Anchor.BOTTOM_CENTER);
        

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
            });

            creditButton.setAnchor(Anchor.TOP_LEFT);
       
        centerContainer.addComponent(playButton);      
                centerContainer.addComponent(creditButton);      

                        centerContainer.addComponent(controlsButton);      

        centerContainer.addComponent(container);
    }


    private float getScaleFactor() {
      return Math.max(1, Math.min((float)ScreenManager.getScreenHeight() / Config.GAME_WINDOW_HEIGHT, (float)ScreenManager.getScreenWidth() / Config.GAME_WINDOW_WIDTH));
    }


    public void update() {
        // update background map (to play tile animations)
        solidBg.update();
        centerContainer.update();
        titleContainer.update();
        float scale = getScaleFactor();
        playButton.scale(scale);
        creditButton.scale(scale);
        controlsButton.scale(scale);
        title.scale(scale);


    }

    public void draw(GraphicsHandler graphicsHandler) {
        solidBg.draw(graphicsHandler);
        // graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), new Color(0, 191, 163));

        centerContainer.draw(graphicsHandler);
        titleContainer.draw(graphicsHandler);
    }
}
