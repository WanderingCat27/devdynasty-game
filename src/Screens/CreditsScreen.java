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
import SpriteFont.SpriteFont;

// This class is for the credits screen
public class CreditsScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected SpriteFont creditsLabel;
    protected SpriteFont createdByLabel;
    protected TextButton exitButton;

    public CreditsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        // setup graphics on screen (background map, spritefont text)
        background = new TitleScreenMap();
        creditsLabel = new SpriteFont("Credits", 200, 7, "Times New Roman", 30, Color.white);
        createdByLabel = new SpriteFont("Created by devDynasty", 200, 121, "Times New Roman", 20, Color.white);

        this.exitButton = new TextButton(20, 20, 100, 50, Color.gray, "Menu", new Font("Comic Sans", Font.PLAIN, 20), Color.WHITE, new Runnable() {

            @Override
            public void run() {
                screenCoordinator.setGameState(GameState.MENU);
            }
            
        });
    }

    public void update() {
        background.update(null);
        exitButton.update();

    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        creditsLabel.draw(graphicsHandler);
        createdByLabel.draw(graphicsHandler);
        exitButton.draw(graphicsHandler);
    }
}
