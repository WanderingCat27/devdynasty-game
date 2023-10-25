package Screens;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.Random;

import Engine.Config;
import Engine.GameWindow;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import Engine.ScreenManager;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Sprite;
import GameObject.SpriteSheet;
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
import Utils.Colors;
import Level.NPC;
import Maps.NewMap;
import ui.Container.Anchor;
import ui.Container.CenterContainer;
import ui.Container.UIContainer.FillType;
import NPCs.EvilCowboy;

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
    private int lastScreenWidth, lastScreenHeight, currScreenHeight, currScreenWidth;
    private boolean screenChanged;
    protected EvilCowboy evilCowboy;
    private boolean gameOver;
    private double imageScale;


    


    public CombatScreen(PlayLevelScreen playLevelScreen){
        this.playLevelScreen = playLevelScreen;
        //this.npc = new NPC(3, 13f, 19f, new SpriteSheet(ImageLoader.load("EvilCowboy.png"), 14, 19), "STAND_DOWN");
        health = 20;
        centerContainer = new CenterContainer();
        centerContainer.setfillType(FillType.FILL_SCREEN);
        lastScreenHeight = centerContainer.getHeight();
        lastScreenWidth = centerContainer.getWidth();
        currScreenHeight = lastScreenHeight;
        currScreenWidth = lastScreenWidth;
        
    }

    public CombatScreen(PlayLevelScreen playLevelScreen, NPC enemy){ // Add NPC parameter to know Enemy
        this.playLevelScreen = playLevelScreen;
        this.npc = enemy;
        health = 20;
        centerContainer = new CenterContainer();
        centerContainer.setfillType(FillType.FILL_SCREEN);
        lastScreenHeight = centerContainer.getHeight();
        lastScreenWidth = centerContainer.getWidth();
        currScreenHeight = lastScreenHeight;
        currScreenWidth = lastScreenWidth;
        initialize();
    }


    

    public void initialize(){
        combatSoundPlayer = new SoundPlayer(GameWindow.getGameWindow(), "Resources/Audio/combat.wav");
        LevelManager.getCurrentLevel().getSoundPlayer().pause();
        fightImage = ImageLoader.load("fight_button.png");
        runImage = ImageLoader.load("run_button.png");
        bagImage = ImageLoader.load("bag_button.png");
        youWinImage = ImageLoader.load("you_win.png");
        youWinPopup = new Sprite(youWinImage, 100, 0);
        enemyImage = ImageLoader.loadSubImage("EvilCowboy.png", Colors.MAGENTA, 0, 0, 14, 19);
        enemy = new Sprite(enemyImage, 400, 100, 10);
        gameOver = false;
        System.out.println(ScreenManager.getScreenHeight() + " " + ScreenManager.getScreenWidth());
        System.out.println(lastScreenHeight + " " + lastScreenWidth);

        if(currScreenHeight > Config.GAME_WINDOW_HEIGHT){
            scale = 3.45f;
        }else{
            scale = 2.3f;
        }
        
        
        
        background = new CombatMap();

        textbox = new TextboxHandler(background);

        isInitialized = true;


        runButton = new SpriteButton(555, currScreenHeight-(runImage.getHeight()), scale, runImage, new Runnable() { //555, 462

            
            @Override
            public void run(){
                System.out.println("Run");
                System.out.println(currScreenHeight +" "+ currScreenHeight);
            }
            
        });



        fightButton = new SpriteButton(330, 374, scale, fightImage, new Runnable() { //330, 374

            
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


        

        bagButton = new SpriteButton(555, 374, scale, bagImage, new Runnable() { //555, 374

            
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
                    playLevelScreen.resumeLevel();
                    gameOver = true;
                }
            }
            
            
        });

        centerContainer.addComponent(fightButton);
        centerContainer.addComponent(bagButton);
        centerContainer.addComponent(runButton);

        System.out.println(fightButton.getXAbs());
        

    }

    protected NPC getNPC(int npcId) {
        for (NPC npc : LevelManager.getCurrentLevel().getMap().getNPCs()) {
            if (npc.getId() == npcId) {
                return npc;
            }
        }
        return null;
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

    public boolean gameOver(){
        return gameOver;
    }

    private void fullscreen(){
        fightButton.scaleSprite(1.5f);
        runButton.scaleSprite(1.5f);
        bagButton.scaleSprite(1.5f);
    }

    private void reverseFullscreen(){
        fightButton.scaleSprite(-1.5f);
        runButton.scaleSprite(-1.5f);
        bagButton.scaleSprite(-1.5f);
    }


    private void checkButtons(){ //Makes sure the buttons are relative to the screen
        runButton.setXOrigin(currScreenWidth/2-(runButton.getWidth())/2);
        runButton.setYOrigin(currScreenHeight/2-(runButton.getHeight())/2);
        bagButton.setXOrigin(runButton.getXOrigin());
        bagButton.setYOrigin(runButton.getYOrigin()-runButton.getHeight());
        fightButton.setXOrigin(bagButton.getXOrigin()-bagButton.getWidth());
        fightButton.setYOrigin(bagButton.getYOrigin());

    }



    public void update(){

        currScreenHeight = centerContainer.getHeight();
        currScreenWidth = centerContainer.getWidth();
        checkButtons(); 
        if(currScreenHeight != lastScreenHeight){
            if(currScreenHeight < lastScreenHeight){
                bagButton.scaleSprite(2/3f);
                runButton.scaleSprite(2/3f);
                fightButton.scaleSprite(2/3f);
            }else if(currScreenHeight > lastScreenHeight){
                bagButton.scaleSprite(1.5f);
                runButton.scaleSprite(1.5f);
                fightButton.scaleSprite(1.5f);
            }
            
        }
        lastScreenHeight = currScreenHeight;
        lastScreenWidth = currScreenWidth;

        
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
