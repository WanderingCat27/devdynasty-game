package Level;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import Engine.GraphicsHandler;
import GameObject.Item;
import Players.PlayerAsh;
import Utils.Direction;
import Utils.Point;

public abstract class Level {

  protected Map map;
  private Player player;
  private Point playerPosition;
  private ArrayList<Item> itemsSaved;
  private ArrayList<Trigger> triggersSaved;
  private ArrayList<NPC> npcsSaved;

  protected abstract Map getMapInstance();

  protected void unload() {
    this.playerPosition = new Point(player.getX(), player.getY());
    this.itemsSaved = this.map.getItems();
    this.triggersSaved = this.map.getTriggers();
    this.npcsSaved = this.map.getNPCs();
    if (getSoundPlayer() != null) {
      getSoundPlayer().pause();
      getSoundPlayer().dispose();
    }
      this.player = null;
    this.map = null;
  }

  public void load() {
    this.map = getMapInstance();
    if (playerPosition == null) {
      playerPosition = this.map.getPlayerStartPosition();
    }
    if(GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasDroppedCrystalOff"))
    {
      this.player = new PlayerAsh(playerPosition.x, playerPosition.y);
    }
    else if(GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasChangedCostume"))
    {
      this.player = new PlayerAsh(playerPosition.x, playerPosition.y, "STAND_RIGHT");
    }
    else
    {
      this.player = new PlayerAsh(playerPosition.x, playerPosition.y);
    }
    // if(GlobalFlagManager.FLAG_MANAGER.isFlagSet("evilCowboyDefeated") && GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasDroppedCrystalOff")) {
    //   this.player = new PlayerAsh(playerPosition.x, playerPosition.y, "STAND_RIGHT");
    // } else if(GlobalFlagManager.FLAG_MANAGER.isFlagSet("hasChangedCostume")) {
    //   this.player = new PlayerAsh(playerPosition.x, playerPosition.y);
    // }
    // else
    // {
    //   this.player = new PlayerAsh(playerPosition.x, playerPosition.y);
    // }
    //this.player = new PlayerAsh(playerPosition.x, playerPosition.y);
    if (map.soundPlayer != null)
      map.soundPlayer.pause();
    this.player.setMap(map);

    this.player.setFacingDirection(Direction.DOWN);

    // define/setup map
    // map.setFlagManager(flagManager);
    // map.setAdjustCamera();

    // let pieces of map know which button to listen for as the "interact" button
    map.getTextboxHandler().getTextbox().setInteractKey(player.getInteractKey());

    // setup map scripts to have references to the map and player
    for (MapTile mapTile : map.getMapTiles()) {
      if (mapTile.getInteractScript() != null) {
        mapTile.getInteractScript().setMap(map);
        mapTile.getInteractScript().setPlayer(player);
      }
    }

    if (itemsSaved != null)
      map.items = itemsSaved;
    for (Item item : map.getItems()) {
      item.setMap(this.map);
      if (item.getInteractScript() != null) {
        item.getInteractScript().setMap(map);
        item.getInteractScript().setPlayer(player);
      }
    }

    if (npcsSaved != null)
      map.npcs = npcsSaved;
    for (NPC npc : map.getNPCs()) {
      npc.setMap(this.map);
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

    if (itemsSaved != null)
      map.triggers = triggersSaved;
    for (Trigger trigger : map.getTriggers()) {
      trigger.setMap(this.map);
      if (trigger.getTriggerScript() != null) {
        trigger.getTriggerScript().setMap(map);
        trigger.getTriggerScript().setPlayer(player);
      }
    }

  }


  public void update() {
    player.update();
    map.update(player);
  }

  public void drawMap(GraphicsHandler graphicsHandler) {
    map.draw(this.player, graphicsHandler);
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public Map getMap() {
    return map;
  }

  public SoundPlayer getSoundPlayer() {
    return map.soundPlayer;
  }

}