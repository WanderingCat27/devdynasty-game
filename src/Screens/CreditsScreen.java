package Screens;

import java.awt.Color;
import java.awt.Font;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.SpriteSheet;
import Level.Map;
import Maps.TitleScreenMap;
import ui.Button.AnimatedSpriteButton;
import ui.Container.Anchor;
import ui.Container.PositioningContainer;
import ui.Container.UIContainer.FillType;
import ui.SpriteFont.SpriteFont;

// This class is for the credits screen
public class CreditsScreen extends Screen {
  protected ScreenCoordinator screenCoordinator;
  protected Map background;
  protected SpriteFont creditsLabel;
  protected SpriteFont createdByLabel;
  protected AnimatedSpriteButton exitButton;
  protected PositioningContainer posContainer;

  public CreditsScreen(ScreenCoordinator screenCoordinator) {
    this.screenCoordinator = screenCoordinator;
  }

  @Override
  public void initialize() {
    // setup graphics on screen (background map, spritefont text)
    background = new TitleScreenMap();
    posContainer = new PositioningContainer(Anchor.TOP_CENTER);
    posContainer.setAnchorChildren(true);
    posContainer.setfillType(FillType.FILL_SCREEN);

    creditsLabel = new SpriteFont("Credits", 0, 7, new Font("Times New Roman", Font.BOLD, 30), Color.ORANGE);
    createdByLabel = new SpriteFont("Created by devDynasty", 0, 121, new Font("Times New Roman", Font.BOLD, 20),
        Color.ORANGE);
    posContainer.addComponent(creditsLabel);
    posContainer.addComponent(createdByLabel);
    this.exitButton = new AnimatedSpriteButton(10, 10, 3,
        new SpriteSheet(ImageLoader.loadAllowTransparent("menu_button.png"), 32, 16), () -> {
          screenCoordinator.setGameState(GameState.MENU);

        });
  }

  public void update() {
    background.update(null);
    exitButton.update();
  }

  public void draw(GraphicsHandler graphicsHandler) {
    background.draw(graphicsHandler);
    exitButton.draw(graphicsHandler);

    posContainer.draw(graphicsHandler);
  }
}
