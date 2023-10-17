package Screens;

import java.awt.Color;
import java.awt.Font;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import ui.Button.TextButton;
import ui.Container.CenterContainer;
import ui.Container.UIContainer.FillType;

// This is the class for the main menu screen
public class MenuScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;


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
        TextButton playButton = new TextButton(-150, -100, 300, 90, new Color(2, 48, 71), "Play", new Font("Comic Sans", Font.BOLD, 30), new Color(255, 183, 3), new Runnable() {

            @Override
            public void run() {
                screenCoordinator.setGameState(GameState.LEVEL);

            }

        });

        playButton.getSpriteFont().setOutlineThickness(3);
        playButton.getSpriteFont().setOutlineColor(Color.black);

        TextButton creditButton = new TextButton(-150, 50, 300, 90, new Color(251, 133, 0), "Credits", new Font("Comic Sans", Font.PLAIN, 30), new Color(33, 158, 188), new Runnable() {

            @Override
            public void run() {
                screenCoordinator.setGameState(GameState.CREDITS);

            }

        });
        creditButton.getSpriteFont().setOutlineThickness(3);
        creditButton.getSpriteFont().setOutlineColor(new Color(2, 48, 71));

        centerContainer.addComponent(playButton);
        centerContainer.addComponent(creditButton);        
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(null);
        centerContainer.update();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);

        centerContainer.draw(graphicsHandler);
    }
}
