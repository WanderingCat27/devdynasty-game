package Level;

import GameObject.GameObject;
import Utils.Direction;
import Utils.Point;

// This class has methods to check if a game object has collided with a map entity (map tile, enhanced map tile, npc, or trigger if applicable)
// it is used by the game object class to determine if and where a collision occurred
public class MapCollisionHandler {

    public static MapCollisionCheckResult getAdjustedPositionAfterCollisionCheckX(GameObject gameObject, Map map, Direction direction) {
        int numberOfTilesToCheck = Math.max(gameObject.getBounds().getHeight() / map.getTileset().getScaledSpriteHeight(), 1);
        float edgeBoundX = direction == Direction.LEFT ? gameObject.getBounds().getX1() : gameObject.getBounds().getX2();
        Point tileIndex = map.getTileIndexByPosition(edgeBoundX, gameObject.getBounds().getY1());
        MapEntity entityCollidedWith = null;
        for (int j = -1; j <= numberOfTilesToCheck + 1; j++) {
            MapTile mapTile = map.getMapTile(Math.round(tileIndex.x), Math.round(tileIndex.y + j));
            if (mapTile != null && hasCollidedWithMapEntity(gameObject, mapTile, direction)) {
                entityCollidedWith = mapTile;
                float adjustedPositionX = gameObject.getX();
                if (direction == Direction.RIGHT) {
                    float boundsDifference = gameObject.getX2() - gameObject.getBoundsX2();
                    adjustedPositionX = mapTile.getBoundsX1() - gameObject.getWidth() + boundsDifference;
                } else if (direction == Direction.LEFT) {
                    float boundsDifference = gameObject.getBoundsX1() - gameObject.getX();
                    adjustedPositionX = (mapTile.getBoundsX2() + 1) - boundsDifference;
                }
                return new MapCollisionCheckResult(new Point(adjustedPositionX, gameObject.getY()), entityCollidedWith);
            }
        }

        for (EnhancedMapTile enhancedMapTile : map.getActiveEnhancedMapTiles()) {
            if (!gameObject.equals(enhancedMapTile) && hasCollidedWithMapEntity(gameObject, enhancedMapTile, direction)) {
                entityCollidedWith = enhancedMapTile;
                float adjustedPositionX = gameObject.getX();
                if (direction == Direction.RIGHT) {
                    float boundsDifference = gameObject.getX2() - gameObject.getBoundsX2();
                    adjustedPositionX = enhancedMapTile.getBoundsX1() - gameObject.getWidth() + boundsDifference;
                } else if (direction == Direction.LEFT) {
                    float boundsDifference = gameObject.getBoundsX1() - gameObject.getX();
                    adjustedPositionX = (enhancedMapTile.getBoundsX2() + 1) - boundsDifference;
                }
                return new MapCollisionCheckResult(new Point(adjustedPositionX, gameObject.getY()), entityCollidedWith);
            }
        }

        for (NPC npc : map.getActiveNPCs()) {
            if (!gameObject.equals(npc) && hasCollidedWithMapEntity(gameObject, npc, direction)) {
                entityCollidedWith = npc;
                float adjustedPositionX = gameObject.getX();
                if (direction == Direction.RIGHT) {
                    float boundsDifference = gameObject.getX2() - gameObject.getBoundsX2();
                    adjustedPositionX = npc.getBoundsX1() - gameObject.getWidth() + boundsDifference;
                } else if (direction == Direction.LEFT) {
                    float boundsDifference = gameObject.getBoundsX1() - gameObject.getX();
                    adjustedPositionX = (npc.getBoundsX2() + 1) - boundsDifference;
                }
                return new MapCollisionCheckResult(new Point(adjustedPositionX, gameObject.getY()), entityCollidedWith);
            }
        }

        if (gameObject.isAffectedByTriggers()) {
            for (Trigger trigger : map.getActiveTriggers()) {
                if (!gameObject.equals(trigger) && trigger.exists() && hasCollidedWithMapEntity(gameObject, trigger, direction)) {
                    entityCollidedWith = trigger;
                    float adjustedPositionX = gameObject.getX();
                    if (direction == Direction.RIGHT) {
                        float boundsDifference = gameObject.getX2() - gameObject.getBoundsX2();
                        adjustedPositionX = trigger.getBoundsX1() - gameObject.getWidth() + boundsDifference;
                    } else if (direction == Direction.LEFT) {
                        float boundsDifference = gameObject.getBoundsX1() - gameObject.getX();
                        adjustedPositionX = (trigger.getBoundsX2() + 1) - boundsDifference;
                    }
                    return new MapCollisionCheckResult(new Point(adjustedPositionX, gameObject.getY()), entityCollidedWith);
                }
            }
        }

        // no collision occurred
        return new MapCollisionCheckResult(null, null);
    }

    public static MapCollisionCheckResult getAdjustedPositionAfterCollisionCheckY(GameObject gameObject, Map map, Direction direction) {
        int numberOfTilesToCheck = Math.max(gameObject.getBounds().getWidth() / map.getTileset().getScaledSpriteWidth(), 1);
        float edgeBoundY = direction == Direction.UP ? gameObject.getBounds().getY() : gameObject.getBounds().getY2();
        Point tileIndex = map.getTileIndexByPosition(gameObject.getBounds().getX1(), edgeBoundY);
        MapEntity entityCollidedWith = null;
        for (int j = -1; j <= numberOfTilesToCheck + 1; j++) {
            MapTile mapTile = map.getMapTile(Math.round(tileIndex.x) + j, Math.round(tileIndex.y));
            if (mapTile != null && hasCollidedWithMapEntity(gameObject, mapTile, direction)) {
                entityCollidedWith = mapTile;
                float adjustedPositionY = gameObject.getY();
                if (direction == Direction.DOWN) {
                    float boundsDifference = gameObject.getY2() - gameObject.getBoundsY2();
                    adjustedPositionY = mapTile.getBoundsY1() - gameObject.getHeight() + boundsDifference;
                } else if (direction == Direction.UP) {
                    float boundsDifference = gameObject.getBoundsY1() - gameObject.getY();
                    adjustedPositionY = (mapTile.getBoundsY2() + 1) - boundsDifference;
                }
                return new MapCollisionCheckResult(new Point(gameObject.getX(), adjustedPositionY), entityCollidedWith);
            }
        }

        for (EnhancedMapTile enhancedMapTile : map.getActiveEnhancedMapTiles()) {
            if (!gameObject.equals(enhancedMapTile) && hasCollidedWithMapEntity(gameObject, enhancedMapTile, direction)) {
                entityCollidedWith = enhancedMapTile;
                float adjustedPositionY = gameObject.getY();
                if (direction == Direction.DOWN) {
                    float boundsDifference = gameObject.getY2() - gameObject.getBoundsY2();
                    adjustedPositionY = enhancedMapTile.getBoundsY1() - gameObject.getHeight() + boundsDifference;
                } else if (direction == Direction.UP) {
                    float boundsDifference = gameObject.getBoundsY1() - gameObject.getY();
                    adjustedPositionY = (enhancedMapTile.getBoundsY2() + 1) - boundsDifference;
                }
                return new MapCollisionCheckResult(new Point(gameObject.getX(), adjustedPositionY), entityCollidedWith);
            }
        }

        for (NPC npc : map.getActiveNPCs()) {
            if (!gameObject.equals(npc) && hasCollidedWithMapEntity(gameObject, npc, direction)) {
                entityCollidedWith = npc;
                float adjustedPositionY = gameObject.getY();
                if (direction == Direction.DOWN) {
                    float boundsDifference = gameObject.getY2() - gameObject.getBoundsY2();
                    adjustedPositionY = npc.getBoundsY1() - gameObject.getHeight() + boundsDifference;
                } else if (direction == Direction.UP) {
                    float boundsDifference = gameObject.getBoundsY1() - gameObject.getY();
                    adjustedPositionY = (npc.getBoundsY2() + 1) - boundsDifference;
                }
                return new MapCollisionCheckResult(new Point(gameObject.getX(), adjustedPositionY), entityCollidedWith);
            }
        }

        if (gameObject.isAffectedByTriggers()) {
            for (Trigger trigger : map.getActiveTriggers()) {
                if (!gameObject.equals(trigger) && trigger.exists() && hasCollidedWithMapEntity(gameObject, trigger, direction)) {
                    entityCollidedWith = trigger;
                    float adjustedPositionY = gameObject.getY();
                    if (direction == Direction.DOWN) {
                        float boundsDifference = gameObject.getY2() - gameObject.getBoundsY2();
                        adjustedPositionY = trigger.getBoundsY1() - gameObject.getHeight() + boundsDifference;
                    } else if (direction == Direction.UP) {
                        float boundsDifference = gameObject.getBoundsY1() - gameObject.getY();
                        adjustedPositionY = (trigger.getBoundsY2() + 1) - boundsDifference;
                    }
                    return new MapCollisionCheckResult(new Point(gameObject.getX(), adjustedPositionY), entityCollidedWith);
                }
            }
        }

        // no collision occurred
        return new MapCollisionCheckResult(null, null);
    }

    // based on tile type, perform logic to determine if a collision did occur with an intersecting tile or not
    private static boolean hasCollidedWithMapEntity(GameObject gameObject, MapEntity mapEntity, Direction direction) {
        if (mapEntity instanceof MapTile) {
            MapTile mapTile = (MapTile)mapEntity;
            switch (mapTile.getTileType()) {
                case PASSABLE:
                    return false;
                case NOT_PASSABLE:
                    return gameObject.intersects(mapTile);
                default:
                    return false;
            }
        }
        else {
            return mapEntity.intersects(gameObject);
        }
    }
}
