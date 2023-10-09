package Screens;

import Engine.GraphicsHandler;
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

import Maps.TestMap;
import Maps.WildWestMap;

import Players.Cat;

import Players.PlayerAsh;

import Players.PlayerPlayer;
import Utils.Direction;
import Utils.Point;
import Engine.GameWindow;

// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected static Map map;
    protected Player player;
    //protected Sprite hud;
    protected Inventory inventory;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected FlagManager flagManager;
    protected Item sword;
    public static boolean doReload = false;


        // sound for level
        protected SoundPlayer soundPlayer;
        protected String soundPath;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    Map mapType = new NewMap();
    public static Map changeMapType = new WildWestMap();

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("hasLostBall", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasFoundBall", false);

        // define/setup map
        this.map = mapType;
        map.setFlagManager(flagManager);
        map.setAdjustCamera();

        // setup player
        //this.player = new PlayerPlayer(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player = new PlayerAsh(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        Point playerStartPosition = map.getPlayerStartPosition();
        this.player.setLocation(playerStartPosition.x, playerStartPosition.y);
        this.playLevelScreenState = PlayLevelScreenState.RUNNING;
        this.player.setFacingDirection(Direction.DOWN);

        //setup inventory
        this.inventory = new Inventory("noSelectionHUD.png", "oneSlotHUD.png", "twoSlotHUD.png", "threeSlotHUD.png", "fourSlotHUD.png",this.map,this.player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // setup map scripts to have references to the map and player
        for (MapTile mapTile : map.getMapTiles()) {
            if (mapTile.getInteractScript() != null) {
                mapTile.getInteractScript().setMap(map);
                mapTile.getInteractScript().setPlayer(player);
            }
        }

        for(Item item : map.getItems())
        {
            if(item.getInteractScript() != null)
            {
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
        this.soundPath = this.map.soundPath;
        System.out.println("Current song file path is " + this.soundPath);
        SoundPlayer.musicPlaying = true;
        this.soundPlayer = new SoundPlayer(GameWindow.getGameWindow(),this.soundPath);
                
    }

    public void update() {
        if (doReload) {
            System.out.println("initialized");
            mapType = new WildWestMap();
            this.inventory.setMap(changeMapType);
            soundPlayer.pause();
            System.out.println("pausing");
            initialize();
            doReload = false;
        }
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
        }

        
    }
    public static void changeMap() {
        System.out.println("test changing map");
        map = new WildWestMap();
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
        }
    }

    public static Map getMap() {
        Map currentMap = map;
        return currentMap;
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
        RUNNING, LEVEL_COMPLETED
    }
}
