package Screens;

import java.awt.Color;
import java.awt.Font;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Sprite;
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

public class ControlsScreen extends Screen {
  protected ScreenCoordinator screenCoordinator;
  protected AnimatedSpriteButton exitButton;
  protected SpriteFont controlsLabel;
  protected PositioningContainer posContainer;
  protected SolidSpriteUI background;

  private static Color textColor = new Color(255, 118, 118);

  public ControlsScreen(ScreenCoordinator screenCoordinator) {
    this.screenCoordinator = screenCoordinator;
  }

  @Override
  public void initialize() {
    background = new SolidSpriteUI(0, 0, 0, 0, new Color(169, 150, 236));
    background.setfillType(FillType.FILL_SCREEN);
    posContainer = new PositioningContainer(Anchor.TOP_CENTER);
    posContainer.setAnchorChildren(true);
    posContainer.setfillType(FillType.FILL_SCREEN);
    // posContainer.addComponent(new SpriteUI(0, 0,
    // ImageUtils.createFilledImage(800, 500, new Color(0, 0, 0, 100))));

    // controls label
    controlsLabel = new SpriteFont("Controls", 0, 7, new Font("Comic Sans", Font.BOLD, 40), textColor);
     controlsLabel.setOutlineColor(Color.BLACK);
    controlsLabel.setOutlineThickness(5);
    posContainer.addComponent(controlsLabel);

    // movement label
    SpriteFont movementLabel = new SpriteFont("Movement", -250, 80, new Font("Comic Sans", Font.BOLD, 30),
        textColor);
    movementLabel.setOutlineColor(Color.BLACK);
    movementLabel.setOutlineThickness(5);
        posContainer.addComponent(movementLabel);

    // movement controls
    SpriteFont movementControls = new SpriteFont("W-A-S-D", 250, 80, new Font("Comic Sans", Font.BOLD, 30),
        textColor);
         movementControls.setOutlineColor(Color.BLACK);
    movementControls.setOutlineThickness(5);
    posContainer.addComponent(movementControls);

    // interact label
    SpriteFont interactLabel = new SpriteFont("Interact/Pick-Up Items", -230, 150,
        new Font("Comic Sans", Font.BOLD, 30), textColor);
    interactLabel.setOutlineColor(Color.BLACK);
    interactLabel.setOutlineThickness(5);
    posContainer.addComponent(interactLabel);

    // interact controls
    SpriteFont interactControls = new SpriteFont("Return", 250, 150, new Font("Comic Sans", Font.BOLD, 30),
        textColor);
    interactControls.setOutlineColor(Color.BLACK);
    interactControls.setOutlineThickness(5);
    posContainer.addComponent(interactControls);

    // pause label
    SpriteFont pauseLabel = new SpriteFont("Pause", -250, 220, new Font("Comic Sans", Font.BOLD, 30),
        textColor);
    pauseLabel.setOutlineColor(Color.BLACK);
    pauseLabel.setOutlineThickness(5);
    posContainer.addComponent(pauseLabel);

    // pause controls
    SpriteFont pauseControls = new SpriteFont("ESC", 250, 220, new Font("Comic Sans", Font.BOLD, 30),
        textColor);
    pauseControls.setOutlineColor(Color.BLACK);
    pauseControls.setOutlineThickness(5);
    posContainer.addComponent(pauseControls);

    // inventory label
    SpriteFont inventoryLabel = new SpriteFont("Inventory", -250, 290, new Font("Comic Sans", Font.BOLD, 30),
        textColor);
    inventoryLabel.setOutlineColor(Color.BLACK);
    inventoryLabel.setOutlineThickness(5);
    posContainer.addComponent(inventoryLabel);

    // inventory controls
    SpriteFont inventoryControls = new SpriteFont("1-2-3-4-`", 250, 290, new Font("Comic Sans", Font.BOLD, 30),
        textColor);
    inventoryControls.setOutlineColor(Color.BLACK);
    inventoryControls.setOutlineThickness(5);
    posContainer.addComponent(inventoryControls);

    // drop label
    SpriteFont dropLabel = new SpriteFont("Drop Item", -250, 360, new Font("Comic Sans", Font.BOLD, 30),
        textColor);
    dropLabel.setOutlineColor(Color.BLACK);
    dropLabel.setOutlineThickness(5);
    posContainer.addComponent(dropLabel);

    // drop controls
    SpriteFont dropControls = new SpriteFont("R", 250, 360, new Font("Comic Sans", Font.BOLD, 30), textColor);
    dropControls.setOutlineColor(Color.BLACK);
    dropControls.setOutlineThickness(5);
    posContainer.addComponent(dropControls);

    // this will be the exit button
    this.exitButton = new AnimatedSpriteButton(10, 10, 5,
        new SpriteSheet(ImageLoader.loadAllowTransparent("menu_button.png"), 32, 16), () -> {
          screenCoordinator.setGameState(GameState.MENU);

        });

  }

  @Override
  public void update() {
    background.update();
    exitButton.update();
  }

  @Override
  public void draw(GraphicsHandler graphicsHandler) {
    background.draw(graphicsHandler);
    posContainer.draw(graphicsHandler);
    exitButton.draw(graphicsHandler);
  }

}
