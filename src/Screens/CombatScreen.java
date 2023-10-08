package Screens;
import java.awt.Color;
import java.awt.Font;

import Engine.GraphicsHandler;
import Engine.Screen;
import Engine.ImageLoader;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.CombatMap;
import Button.SpriteButton;
import java.awt.image.BufferedImage;


public class CombatScreen extends Screen{
    
    protected ScreenCoordinator screencoordinator;
    protected Map background;
    protected SpriteButton fightButton;
    protected SpriteButton runButton;
    protected SpriteButton bagButton;
    protected BufferedImage fightImage;

    



    public CombatScreen(ScreenCoordinator screenCoordinator){
        this.screencoordinator = screenCoordinator;
    }


    
    public void initialize(){

        fightImage = ImageLoader.load("fight_button.png");
        background = new CombatMap();
        background.setAdjustCamera(false);

        fightButton = new SpriteButton(300, 300, 1, fightImage, new Runnable() {

            @Override
            public void run(){

            }
        });


    }

    public void update(){
        background.update(null);
        fightButton.update();

    }

    public void draw(GraphicsHandler graphicsHandler){
        background.draw(graphicsHandler);
        fightButton.draw(graphicsHandler);

    }
    
}
