package Screens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Inventory;
import GameObject.Item;
import Level.FlagManager;
import Level.LevelManager;
import Level.SoundPlayer;
import ui.Container.Anchor;
import ui.Container.PositioningContainer;
import ui.Container.UIContainer.FillType;
import ui.Slider.Slider;

// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    // protected Sprite hud;
    protected static Inventory inventory;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected CombatScreen combatScreen;
    protected FlagManager flagManager;
    protected Item sword;
    public static boolean doReload = false;


    protected PositioningContainer sliderContainer;
    protected Slider volumeSlider;


    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;

        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("hasLostBall", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasFoundBall", false);
        flagManager.addFlag("hasTalkedToDino2", false);
    }

    public void initialize() {
        // setup player
        // this.player = new PlayerPlayer(map.getPlayerStartPosition().x,
        // map.getPlayerStartPosition().y);
        this.playLevelScreenState = PlayLevelScreenState.RUNNING;

        // setup inventory 
        LevelManager.getCurrentLevel().getMap().setFlagManager(flagManager);

        inventory = new Inventory("noSelectionHUD.png", "oneSlotHUD.png", "twoSlotHUD.png", "threeSlotHUD.png",
                "fourSlotHUD.png", LevelManager.getCurrentLevel().getMap(), LevelManager.getCurrentLevel().getPlayer());

        winScreen = new WinScreen(this);
        

        LevelManager.getCurrentLevel().getMap().soundPlayer.play();
        System.out.println(SoundPlayer.musicPlaying);
        SoundPlayer.musicPlaying = true;

        // dont re-initialize slider
        if (volumeSlider == null) {
            // Create the volume slider
            volumeSlider = new Slider(0, 0, 200, 0, 100);
            volumeSlider.setValue(volumeSlider.getMax());
            volumeSlider.addChangeListener(() -> {
                LevelManager.getCurrentLevel().getMap().soundPlayer.setVolume((int) volumeSlider.getValue());
                System.out.println(volumeSlider.getValue());
            });
            // position at top of screen and anchor objects to their top center
            sliderContainer = new PositioningContainer(Anchor.TOP_CENTER);
            sliderContainer.setfillType(FillType.FILL_SCREEN);
            sliderContainer.setAnchorChildren(true);

            sliderContainer.addComponent(volumeSlider);
        }
        LevelManager.getCurrentLevel().getMap().soundPlayer.setVolume((int) volumeSlider.getValue());
    }

    public void update() {
        if (doReload) {
            System.out.println("initialized");
            LevelManager.getCurrentLevel().getMap().soundPlayer.pause();
            System.out.println("pausing");
            initialize();
            doReload = false;
            
        }

        if(LevelManager.getCurrentLevel().getMap().getFlagManager().isFlagSet("hasTalkedToDino2")){
                screenCoordinator.setGameState(GameState.COMBAT);
                System.out.println("Combat mode");
        }
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the
            // platformer level going
            case RUNNING:
                LevelManager.getCurrentLevel().update();
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                winScreen.update();
                break;


            
        }

        

        sliderContainer.update();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
                Inventory.keyCheck();
                LevelManager.getCurrentLevel().drawMap(graphicsHandler);
                inventory.drawHud(graphicsHandler);
                break;
            case LEVEL_COMPLETED:
                winScreen.draw(graphicsHandler);
                break;
        }

        sliderContainer.draw(graphicsHandler);
    }
    
    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED;
    }
}
