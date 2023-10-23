package Screens;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.Random;

import Engine.GameWindow;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import Engine.ScreenManager;
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
import java.awt.image.BufferedImage;
import Level.TextboxHandler;
import java.util.Random;
import GameObject.Sprite;
import Screens.PlayLevelScreen;
import Level.NPC;
import Maps.NewMap;
import ui.Container.CenterContainer;
import ui.Container.UIContainer.FillType;

public class CombatScreen extends Screen{
    
    protected ScreenCoordinator screencoordinator;
    protected PlayLevelScreen playLevelScreen;
    protected Map background;
    protected SpriteButton fightButton;
    protected SpriteButton runButton;
    protected SpriteButton bagButton;
    protected TextButton returnButton;
    protected BufferedImage fightImage, runImage, bagImage, youWinImage, enemyImage;
    protected TextboxHandler textbox;
    protected Sprite youWinPopup;
    protected Sprite enemy;
    private float scale;
    private boolean action;
    private int health;
    private Random rand;
    private boolean isInitialized;
    protected NPC npc;
    protected CenterContainer centerContainer;
    public SoundPlayer combatSoundPlayer;
    private int startScreenWidth, startScreenHeight, currScreenHeight, currScreenWidth;
    private boolean screenChanged;
    


    



    public CombatScreen(ScreenCoordinator screenCoordinator){ // Add NPC parameter to know Enemy
        this.screencoordinator = screenCoordinator;
        health = 20;
        rand = new Random(15);
        centerContainer = new CenterContainer();
        centerContainer.setfillType(FillType.FILL_SCREEN);
        startScreenHeight = ScreenManager.getScreenHeight();
        startScreenWidth = ScreenManager.getScreenHeight();
        currScreenHeight = startScreenHeight;

    }

    // public void setNPC(NPC npc){
    //     this.npc = npc;
    // }

    

    public void initialize(){
        combatSoundPlayer = new SoundPlayer(GameWindow.getGameWindow(), "Resources/Audio/combat.wav");
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

        //fightButton.scaleSprite(5f);


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

        centerContainer.addComponent(fightButton);
        centerContainer.addComponent(bagButton);
        centerContainer.addComponent(runButton);

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

    private void fullscreen(){
        fightButton.scaleSprite(1.5f);
        runButton.scaleSprite(1.5f);
        bagButton.scaleSprite(1.5f);
    }

    private void notFullscreen(){
        fightButton.scaleSprite(-1.5f);
        runButton.scaleSprite(-1.5f);
        bagButton.scaleSprite(-1.5f);
    }


    public void update(){

        currScreenHeight = ScreenManager.getScreenHeight();
        if(currScreenHeight != startScreenHeight){
            screenChanged = true;
        }

        background.update(null);
        if(!healthZero()){
            centerContainer.update();
        }else{
            returnButton.update();
            LevelManager.getCurrentLevel().getSoundPlayer().pause();
        }

        // if(screenChanged){
        //     if(currScreenHeight > startScreenHeight){
                
        //     }else if(currScreenHeight <= startScreenHeight){
        //         fightButton.scaleSprite(-2f);
        //     }
        //     startScreenHeight = currScreenHeight;
        //     screenChanged = false;
        // }
            

        
    }

    public void draw(GraphicsHandler graphicsHandler){
        background.draw(graphicsHandler);
        // fightButton.draw(graphicsHandler);
        // runButton.draw(graphicsHandler);
        // bagButton.draw(graphicsHandler);
        centerContainer.draw(graphicsHandler);
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
