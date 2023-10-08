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
    protected BufferedImage runImage;
    protected BufferedImage bagImage;
    private float scale;

    



    public CombatScreen(ScreenCoordinator screenCoordinator){
        this.screencoordinator = screenCoordinator;
    }


    
    public void initialize(){

        scale = 2.3f;
        fightImage = ImageLoader.load("fight_button.png");
        runImage = ImageLoader.load("run_button.png");
        bagImage = ImageLoader.load("bag_button.png");

        background = new CombatMap();
        background.setAdjustCamera(false);

        fightButton = new SpriteButton(330, 374, scale, fightImage, new Runnable() {

            @Override
            public void run(){
                System.out.println("Fight");

            }
        });

        runButton = new SpriteButton(555, 374, scale, runImage, new Runnable() {

            @Override
            public void run(){
                System.out.println("Run");

            }
        });

        bagButton = new SpriteButton(555, 462, scale, bagImage, new Runnable() {

            @Override
            public void run(){
                System.out.println("Bag");
            }
        });


    }

    public void update(){
        background.update(null);
        fightButton.update();
        runButton.update();
        bagButton.update();

    }

    public void draw(GraphicsHandler graphicsHandler){
        background.draw(graphicsHandler);
        fightButton.draw(graphicsHandler);
        runButton.draw(graphicsHandler);
        bagButton.draw(graphicsHandler);

    }
    
}
