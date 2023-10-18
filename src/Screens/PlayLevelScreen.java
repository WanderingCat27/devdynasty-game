package Screens;

import Engine.GameWindow;
import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Inventory;
import GameObject.Item;
import Level.EnhancedMapTile;
import Level.FlagManager;
import Level.Map;
import Level.MapTile;
import Level.NPC;
import Level.Player;
import Level.SoundPlayer;
import Level.Trigger;
import Maps.NewMap;
import Players.PlayerAsh;
import Utils.Direction;
import Utils.Point;
import ui.Container.Anchor;
import ui.Container.PositioningContainer;
import ui.Container.UIContainer.FillType;
import ui.Slider.Slider;
import Game.GameState;
import Engine.Keyboard;


// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    public static Map map = new NewMap();
    protected Player player;
    // protected Sprite hud;
    protected static Inventory inventory;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected CombatScreen combatScreen;
    protected FlagManager flagManager;
    protected Item sword;
    public static boolean doReload = false;
    protected PauseScreen pauseScreen;
    protected PositioningContainer sliderContainer;
    protected Slider volumeSlider;

    protected KeyLocker keyLocker = new KeyLocker();
    protected static Key ESC = Key.ESC;

    // sound for level
    protected SoundPlayer soundPlayer;
    protected String soundPath;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        
        if (soundPlayer != null) {
            soundPlayer.dispose();
        }

        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("hasLostBall", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasFoundBall", false);
        flagManager.addFlag("hasTalkedToDino2", false);

        // define/setup map
        map.setFlagManager(flagManager);
        map.setAdjustCamera();

        // setup player
        // this.player = new PlayerPlayer(map.getPlayerStartPosition().x,
        // map.getPlayerStartPosition().y);
        this.player = new PlayerAsh(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        Point playerStartPosition = map.getPlayerStartPosition();
        this.player.setLocation(playerStartPosition.x, playerStartPosition.y);
        this.playLevelScreenState = PlayLevelScreenState.RUNNING;
        this.player.setFacingDirection(Direction.DOWN);

        // setup inventory
        inventory = new Inventory("noSelectionHUD.png", "oneSlotHUD.png", "twoSlotHUD.png", "threeSlotHUD.png",
                "fourSlotHUD.png", map, this.player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // setup map scripts to have references to the map and player
        for (MapTile mapTile : map.getMapTiles()) {
            if (mapTile.getInteractScript() != null) {
                mapTile.getInteractScript().setMap(map);
                mapTile.getInteractScript().setPlayer(player);
            }
        }

        for (Item item : map.getItems()) {
            if (item.getInteractScript() != null) {
                item.getInteractScript().setMap(map);
                item.getInteractScript().setPlayer(player);
            }
        }

        for (NPC npc : map.getNPCs()) {
            if (npc.getInteractScript() != null) {
                npc.getInteractScript().setMap(map);
                npc.getInteractScript().setPlayer(player);
            }
        }
        for (EnhancedMapTile enhancedMapTile : map.getEnhancedMapTiles()) {
            if (enhancedMapTile.getInteractScript() != null) {
                enhancedMapTile.getInteractScript().setMap(map);
                enhancedMapTile.getInteractScript().setPlayer(player);
            }
        }
        for (Trigger trigger : map.getTriggers()) {
            if (trigger.getTriggerScript() != null) {
                trigger.getTriggerScript().setMap(map);
                trigger.getTriggerScript().setPlayer(player);
            }
        }

        winScreen = new WinScreen(this);
        
        

        System.out.println(SoundPlayer.musicPlaying);
        this.soundPath = map.soundPath;
        System.out.println("Current song file path is " + this.soundPath);
        SoundPlayer.musicPlaying = true;
        this.soundPlayer = new SoundPlayer(GameWindow.getGameWindow(), this.soundPath);

        // dont re-initialize slider

        // if (volumeSlider == null) {
        //     // Create the volume slider
        //     volumeSlider = new Slider(0, 0, 200, 0, 100);
        //     volumeSlider.setValue(volumeSlider.getMax());
        //     volumeSlider.addChangeListener(() -> {
        //         this.soundPlayer.setVolume((int) volumeSlider.getValue());
        //         System.out.println(volumeSlider.getValue());
        //     });
        //     // position at top of screen and anchor objects to their top center
        //     sliderContainer = new PositioningContainer(Anchor.TOP_CENTER);
        //     sliderContainer.setfillType(FillType.FILL_SCREEN);
        //     sliderContainer.setAnchorChildren(true);

        //     sliderContainer.addComponnent(volumeSlider);
        // }
        // this.soundPlayer.setVolume((int) volumeSlider.getValue());

        pauseScreen = new PauseScreen(this, this.soundPlayer);
        if (volumeSlider == null) {
            // Create the volume slider
            volumeSlider = new Slider(0, 0, 200, 0, 100);
            volumeSlider.setValue(volumeSlider.getMax());
            volumeSlider.addChangeListener(() -> {
                this.soundPlayer.setVolume((int) volumeSlider.getValue());
                System.out.println(volumeSlider.getValue());
            });
            // position at top of screen and anchor objects to their top center
            sliderContainer = new PositioningContainer(Anchor.TOP_CENTER);
            sliderContainer.setfillType(FillType.FILL_SCREEN);
            sliderContainer.setAnchorChildren(true);

            sliderContainer.addComponent(volumeSlider);
        }
    }

    public void update() {
        if (doReload) {
            System.out.println("initialized");
            soundPlayer.pause();
            System.out.println("pausing");
            initialize();
            doReload = false;
            
        }

        if(map.getFlagManager().isFlagSet("hasTalkedToDino2")){
                screenCoordinator.setGameState(GameState.COMBAT);
                System.out.println("Combat mode");
        }

        if(Keyboard.isKeyDown(ESC) && !keyLocker.isKeyLocked(ESC))
        {
            keyLocker.lockKey(ESC);
            this.playLevelScreenState = PlayLevelScreenState.PAUSED;
        }
        

        
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the
            // platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
            case PAUSED:
                pauseScreen.update();
                if(Keyboard.isKeyUp(ESC))
                {
                    keyLocker.unlockKey(ESC);
                }
                break;
        }
        //sliderContainer.update();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
                Inventory.keyCheck();
                map.draw(player, graphicsHandler);
                inventory.drawHud(graphicsHandler);
                break;
            case LEVEL_COMPLETED:
                winScreen.draw(graphicsHandler);
                break;
            case PAUSED:
                pauseScreen.draw(graphicsHandler);
                break;
        }
        //sliderContainer.draw(graphicsHandler);
    }
    
    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    public SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }

    public Map getMap() {
        return map;
    }

    public void resumeLevel()
    {
        this.playLevelScreenState = PlayLevelScreenState.RUNNING;
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED, PAUSED;
    }
}
