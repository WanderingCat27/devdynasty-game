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
import GameObject.Inventory;

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
    private float enemyScale;
    private Inventory inventory;
    private boolean showInventory;


    


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
        inventory = playLevelScreen.getInventory();
        
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
        inventory = playLevelScreen.getInventory();
        initialize();
    }


    

    public void initialize(){
        combatSoundPlayer = new SoundPlayer(GameWindow.getGameWindow(), "Resources/Audio/combat.wav");
        LevelManager.getCurrentLevel().getSoundPlayer().pause();
        LevelManager.getCurrentLevel().getPlayer().stopSound(); // stops walking sound
        fightImage = ImageLoader.load("fight_button.png");
        runImage = ImageLoader.load("run_button.png");
        bagImage = ImageLoader.load("bag_button.png");
        youWinImage = ImageLoader.load("GameOver.png");
        youWinPopup = new Sprite(youWinImage, currScreenWidth/2 - 100, currScreenHeight/2 - 100, 5f);
        showInventory = false;
        if(currScreenHeight > Config.GAME_WINDOW_HEIGHT){
            scale = 3.45f;
            enemyScale = 18f;
        }else{
            scale = 2.3f;
            enemyScale = 10f;
        }
        

        enemyImage = ImageLoader.loadSubImage("EvilCowboy.png", Colors.MAGENTA, 0, 0, 14, 19);
        enemy = new Sprite(enemyImage, centerContainer.getWidth()-200, centerContainer.getHeight()-400, enemyScale);
        gameOver = false;
        System.out.println(ScreenManager.getScreenHeight() + " " + ScreenManager.getScreenWidth());
        System.out.println(lastScreenHeight + " " + lastScreenWidth);

        
        
        
        
        background = new CombatMap();

        textbox = new TextboxHandler(background);

        isInitialized = true;


        runButton = new SpriteButton(555, currScreenHeight-(runImage.getHeight()), scale, runImage, new Runnable() { //555, 462

            
            @Override
            public void run(){
                playLevelScreen.resumeLevel();
                gameOver = true;
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
                showInventory = !showInventory;
            }
            
            
        });

        returnButton = new TextButton(currScreenWidth/2, currScreenWidth/2, (int)(100*scale), (int)(50*scale), Color.gray, "Return to game", new Font("Comic Sans", Font.PLAIN, 20), Color.WHITE ,new Runnable() {

            
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
        returnButton.setXOrigin(currScreenWidth/2 - (returnButton.getWidth()/2));
        returnButton.setYOrigin(currScreenHeight/2 - (returnButton.getHeight()/2));
        enemy.setX(currScreenWidth/2 + (float)(50*scale));
        enemy.setY(currScreenHeight/2 - (float)(75*scale));
        youWinPopup.setX(currScreenWidth/2 - (youWinPopup.getWidth()/2));
        youWinPopup.setY(currScreenHeight/2 -(youWinPopup.getHeight()*2));

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
                enemy.setScale(10f);
                youWinPopup.setScale(2.3f);
            }else if(currScreenHeight > lastScreenHeight){
                bagButton.scaleSprite(1.5f);
                runButton.scaleSprite(1.5f);
                fightButton.scaleSprite(1.5f);
                enemy.setScale(18f);
                youWinPopup.setScale(5f);
            }
            
        }
        lastScreenHeight = currScreenHeight;
        lastScreenWidth = currScreenWidth;

        
        background.update(null);

        if(!healthZero()){
            centerContainer.update();
        }else{
            returnButton.update();
            checkButtons();
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
        if(showInventory){
            inventory.drawHud(graphicsHandler);
        }
        

    }

    public void pauseMusic() {
        combatSoundPlayer.pause();
        System.out.println("pausing music");
    }
    
}
