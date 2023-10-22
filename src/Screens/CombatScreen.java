package Screens;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.Random;

import Engine.GameWindow;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Sprite;
import Level.LevelManager;
import Level.Map;
import Level.NPC;
import Level.SoundPlayer;
import Level.TextboxHandler;
import Maps.CombatMap;
import ui.Button.SpriteButton;
import ui.Button.TextButton;
import ui.Container.CenterContainer;

public class CombatScreen extends Screen{
    
    protected ScreenCoordinator screencoordinator;
    protected PlayLevelScreen playLevelScreen;
    protected Map background;
    protected SpriteButton fightButton;
    protected SpriteButton runButton;
    protected SpriteButton bagButton;
    protected TextButton returnButton;
    protected BufferedImage fightImage;
    protected BufferedImage runImage;
    protected BufferedImage bagImage;
    protected BufferedImage youWinImage;
    protected BufferedImage enemyImage;
    protected TextboxHandler textbox;
    protected Sprite youWinPopup;
    protected Sprite enemy;
    private float scale;
    private boolean action;
    private int health;
    private Random rand;
    private boolean isInitialized;
    protected NPC npc;
    private CenterContainer centerContainer;
    public SoundPlayer combatSoundPlayer;
    


    



    public CombatScreen(ScreenCoordinator screenCoordinator){ // Add NPC parameter to know Enemy
        this.screencoordinator = screenCoordinator;
        health = 20;
        rand = new Random(15);
    }

    // public void setNPC(NPC npc){
    //     this.npc = npc;
    // }

    

    public void initialize(){
        combatSoundPlayer = new SoundPlayer(GameWindow.getGameWindow(), "Resources/Audio/AmTronic_-_Caribbean_Dub.wav");
        LevelManager.getCurrentLevel().getSoundPlayer().pause();
        scale = 2.3f;
        fightImage = ImageLoader.load("fight_button.png");
        runImage = ImageLoader.load("run_button.png");
        bagImage = ImageLoader.load("bag_button.png");
        youWinImage = ImageLoader.load("you_win.png");
        enemyImage = ImageLoader.load("godzilla.png");
        youWinPopup = new Sprite(youWinImage, 100, 0);
        enemy =  new Sprite(enemyImage, 300f, 50f);

        
        background = new CombatMap();

        textbox = new TextboxHandler(background);

        isInitialized = true;

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

        returnButton = new TextButton(100, 400, 200, 100, Color.gray, "Return to game", new Font("Comic Sans", Font.PLAIN, 20), Color.WHITE ,new Runnable() {

            
            @Override
            public void run(){
                if(healthZero()){
                    pauseMusic();
                    screencoordinator.setGameState(GameState.LEVEL);
                }
            }
            
            
        });


        System.out.println(fightButton.getXAbs());
        

    }

    public boolean isInitialized(){
        return isInitialized;
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
        }else{
            returnButton.update();
            LevelManager.getCurrentLevel().getSoundPlayer().pause();
        }
        


    }

    public void draw(GraphicsHandler graphicsHandler){
        background.draw(graphicsHandler);
        fightButton.draw(graphicsHandler);
        runButton.draw(graphicsHandler);
        bagButton.draw(graphicsHandler);
        enemy.draw(graphicsHandler);
        
        if(healthZero()){
            youWinPopup.draw(graphicsHandler);
            returnButton.draw(graphicsHandler);
            //npc.draw(graphicsHandler);
        }

    }

    public void pauseMusic() {
        combatSoundPlayer.pause();
        System.out.println("pausing music");
    }
    
}
