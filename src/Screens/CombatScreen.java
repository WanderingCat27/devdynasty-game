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
import ui.Button.SpriteButton;
import java.awt.image.BufferedImage;
import Level.Textbox;
import java.util.Random;
import GameObject.Sprite;


public class CombatScreen extends Screen{
    
    protected ScreenCoordinator screencoordinator;
    protected Map background;
    protected SpriteButton fightButton;
    protected SpriteButton runButton;
    protected SpriteButton bagButton;
    protected BufferedImage fightImage;
    protected BufferedImage runImage;
    protected BufferedImage bagImage;
    protected BufferedImage youWinImage;
    protected Textbox textbox;
    protected Sprite youWinPopup;
    private float scale;
    private boolean action;
    private int health;
    private Random rand;

    



    public CombatScreen(ScreenCoordinator screenCoordinator){ // Add NPC parameter to know Enemy
        this.screencoordinator = screenCoordinator;
        health = 20;
        rand = new Random(15);
    }


    
    public void initialize(){

        scale = 2.3f;
        fightImage = ImageLoader.load("fight_button.png");
        runImage = ImageLoader.load("run_button.png");
        bagImage = ImageLoader.load("bag_button.png");
        youWinImage = ImageLoader.load("you_win.png");
        youWinPopup = new Sprite(youWinImage, 100, 0);
        background = new CombatMap();

        textbox = new Textbox(background);

        fightButton = new SpriteButton(330, 374, scale, fightImage, new Runnable() {

            
            @Override
            public void run(){
                if(health > 0){
                    System.out.println("Fight");
                    health -= 10;
                    System.out.println("Health: " + health);
                }
            }
            
        });


        runButton = new SpriteButton(555, 462, scale, runImage, new Runnable() {

            
            @Override
            public void run(){
                System.out.println("Run");
            }
            
        });

        bagButton = new SpriteButton(555, 374, scale, bagImage, new Runnable() {

            
            @Override
            public void run(){
                System.out.println("Bag");
            }
            
            
        });

        

    }

    public boolean healthZero(){
        if(health <= 0){
            return true;
        }
        return false;
    }


    public void update(){
        background.update(null);
        if(!healthZero()){
            fightButton.update();
            runButton.update();
            bagButton.update();
        }
        



    }

    public void draw(GraphicsHandler graphicsHandler){
        background.draw(graphicsHandler);
        fightButton.draw(graphicsHandler);
        runButton.draw(graphicsHandler);
        bagButton.draw(graphicsHandler);
        
        if(healthZero()){
            youWinPopup.draw(graphicsHandler);
            //PlayLevelScreen.goToCombat();
        }

    }
    
}
