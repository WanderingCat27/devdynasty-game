package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.SpriteSheet;
import Level.Map;
import Maps.TitleScreenMap;
import Utils.ImageUtils;
import ui.Button.AnimatedSpriteButton;
import ui.Container.Anchor;
import ui.Container.PositioningContainer;
import ui.Container.UIContainer;
import ui.Container.UIContainer.FillType;
import ui.SpriteFont.SpriteFont;
import ui.SpriteUI.SolidSpriteUI;
import ui.SpriteUI.SpriteUI;

// This class is for the credits screen
public class CreditsScreen extends Screen {
  protected ScreenCoordinator screenCoordinator;
  protected SolidSpriteUI background;
  protected SpriteFont creditsLabel;
  protected SpriteFont createdByLabel;
  protected AnimatedSpriteButton exitButton;
  protected PositioningContainer posContainer;
  private static Color textColor = new Color(255, 118, 118);

  public CreditsScreen(ScreenCoordinator screenCoordinator) {
    this.screenCoordinator = screenCoordinator;
  }

  @Override
  public void initialize() {
    // setup graphics on screen (background map, spritefont text)
    background = new SolidSpriteUI(0, 0,0, 0, new Color(149, 133, 241));
    background.setfillType(FillType.FILL_SCREEN);
    posContainer = new PositioningContainer(Anchor.TOP_CENTER);
    posContainer.setAnchorChildren(true);
    posContainer.setfillType(FillType.FILL_SCREEN);
    
    // posContainer.addComponent(new SpriteUI(0, 0, ImageUtils.createFilledImage(400, 300, new Color(0, 0, 0, 100))));

    creditsLabel = new SpriteFont("Credits", 0, 7, new Font("Comic Sans", Font.BOLD, 40), textColor);
    createdByLabel = new SpriteFont("Created by devDynasty", 0, 121, new Font("Comic Sans", Font.BOLD, 30),
        textColor);
    createdByLabel.setOutlineColor(Color.BLACK);
    createdByLabel.setOutlineThickness(5);
    creditsLabel.setOutlineColor(Color.BLACK);
    creditsLabel.setOutlineThickness(5);
    posContainer.addComponent(creditsLabel);
    posContainer.addComponent(createdByLabel);
    this.exitButton = new AnimatedSpriteButton(10, 10, 5,
        new SpriteSheet(ImageLoader.loadAllowTransparent("menu_button.png"), 32, 16), () -> {
          screenCoordinator.setGameState(GameState.MENU);

        });
  }

  public void update() {
    background.update();
    exitButton.update();
  }

  public void draw(GraphicsHandler graphicsHandler) {
    background.draw(graphicsHandler);
    exitButton.draw(graphicsHandler);

    posContainer.draw(graphicsHandler);
  }
}
