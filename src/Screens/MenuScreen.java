package Screens;

import java.awt.Color;
import java.awt.Font;

import Button.TextButton;
import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;

// This is the class for the main menu screen
public class MenuScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;

    protected TextButton playButton;
    protected TextButton creditButton;

    public MenuScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {

        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        playButton = new TextButton(175, 119, 200, 60, new Color(2, 48, 71), "Play", new Font("Comic Sans", Font.BOLD, 30), new Color(255, 183, 3), new Runnable() {

            @Override
            public void run() {
                screenCoordinator.setGameState(GameState.LEVEL);

            }

        });

        playButton.getSpriteFont().setOutlineThickness(3);
        playButton.getSpriteFont().setOutlineColor(Color.black);

        creditButton = new TextButton(175, 219, 200, 60, new Color(251, 133, 0), "Credits", new Font("Comic Sans", Font.PLAIN, 30), new Color(33, 158, 188), new Runnable() {

            @Override
            public void run() {
                screenCoordinator.setGameState(GameState.CREDITS);

            }

        });
        creditButton.getSpriteFont().setOutlineThickness(3);
        creditButton.getSpriteFont().setOutlineColor(new Color(2, 48, 71));
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(null);
        playButton.update();
        creditButton.update();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);

        playButton.draw(graphicsHandler);
        creditButton.draw(graphicsHandler);
    }
}
