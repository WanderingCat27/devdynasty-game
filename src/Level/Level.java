package Level;

import Engine.GraphicsHandler;
import GameObject.Item;
import Players.PlayerAsh;
import Utils.Direction;
import Utils.Point;

public class Level {
  

    private final Map map;
    private final Player player;

    public Level(Map map) {
      this.map = map;
      this.player = new PlayerAsh(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
      map.soundPlayer.pause();
      this.player.setMap(map);
      Point playerStartPosition = map.getPlayerStartPosition();
      this.player.setLocation(playerStartPosition.x, playerStartPosition.y);

      this.player.setFacingDirection(Direction.DOWN);

      // define/setup map
      // map.setFlagManager(flagManager);
      map.setAdjustCamera();

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

    public Map getMap() {
      return map;
    }


    public SoundPlayer getSoundPlayer() {
      return map.soundPlayer;
    }

  }