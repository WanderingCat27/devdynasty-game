package Tilesets;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.Map;
import Level.MapTile;
import Level.TileType;
import Level.Tileset;

public class SaloonTileset extends Tileset
{
    public SaloonTileset()
    {
        super(ImageLoader.load("saloon_tileset.png"), 16, 16,3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles()
    {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        //FLoor tile
        Frame floorFrame = new FrameBuilder(getSubImage(0, 0))
            .withScale(tileScale)
            .build();
        
        MapTileBuilder floorTile = new MapTileBuilder(floorFrame);
        mapTiles.add(floorTile);

        //Bottom border tile
        Frame bottomBorderFrame = new FrameBuilder(getSubImage(1, 0))
            .withScale(tileScale)
            .build();
        MapTileBuilder bottomBorderTile = new MapTileBuilder(bottomBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(bottomBorderTile);

        //Top border tile
        Frame topBorderFrame = new FrameBuilder(getSubImage(1, 3))
            .withScale(tileScale)
            .build();
        MapTileBuilder topBorderTile = new MapTileBuilder(topBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(topBorderTile);

        //Left border tile
        Frame leftBorderFrame = new FrameBuilder(getSubImage(1, 1))
            .withScale(tileScale)
            .build();
        MapTileBuilder leftBorderTile = new MapTileBuilder(leftBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(leftBorderTile);

        //right border frame
        Frame rightBorderFrame = new FrameBuilder(getSubImage(1, 2))
            .withScale(tileScale)
            .build();
        MapTileBuilder rightBorderTile = new MapTileBuilder(rightBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(rightBorderTile);

        //top left corner tile
        Frame topLeftCornerFrame = new FrameBuilder(getSubImage(0, 1))
            .withScale(tileScale)
            .build();
        MapTileBuilder topLeftCornerTile = new MapTileBuilder(topLeftCornerFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(topLeftCornerTile);

        //top right corner frame
        Frame topRightCornerFrame = new FrameBuilder(getSubImage(0, 2))
            .withScale(tileScale)
            .build();
        MapTileBuilder topRightCornerTile = new MapTileBuilder(topRightCornerFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(topRightCornerTile);

        //bottom left corner frame
        Frame bottomLeftCornerFrame = new FrameBuilder(getSubImage(0, 3))
            .withScale(tileScale)
            .build();
        MapTileBuilder bottomLeftCornerTile = new MapTileBuilder(bottomLeftCornerFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(bottomLeftCornerTile);

        //bottom right corner frame
        Frame bottomRightCornerFrame = new FrameBuilder(getSubImage(2, 0))
            .withScale(tileScale)
            .build();
        MapTileBuilder bottomRightCornerTile = new MapTileBuilder(bottomRightCornerFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(bottomRightCornerTile);

        //black tile
        Frame blackFrame = new FrameBuilder(getSubImage(2, 1))
            .withScale(tileScale)
            .build();
        MapTileBuilder blackTile = new MapTileBuilder(blackFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(blackTile);

        //left bar border
        Frame leftBarBorderFrame = new FrameBuilder(getSubImage(2, 2))
            .withScale(tileScale)
            .build();
        MapTileBuilder leftBarBorderTile = new MapTileBuilder(leftBarBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(leftBarBorderTile);

        //right bar border
        Frame rightBarBorderFrame = new FrameBuilder(getSubImage(2, 3))
            .withScale(tileScale)
            .build();
        MapTileBuilder rightBarBorderTile = new MapTileBuilder(rightBarBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(rightBarBorderTile);

        //middle bar border
        Frame middleBarBorderFrame = new FrameBuilder(getSubImage(3, 0))
            .withScale(tileScale)
            .build();
        MapTileBuilder middleBarBorderTile = new MapTileBuilder(middleBarBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(middleBarBorderTile);

        //final left bar border
        Frame finalLeftBarBorderFrame = new FrameBuilder(getSubImage(3, 1))
            .withScale(tileScale)
            .build();
        MapTileBuilder finalLeftBarBorderTile = new MapTileBuilder(finalLeftBarBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(finalLeftBarBorderTile);

        //final right bar border
        Frame finalRightBarBorderFrame = new FrameBuilder(getSubImage(3, 2))
            .withScale(tileScale)
            .build();
        MapTileBuilder finalRightBarBorderTile = new MapTileBuilder(finalRightBarBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(finalRightBarBorderTile);

        //final middle bar border
        Frame finalMiddleBarBorderFrame = new FrameBuilder(getSubImage(3, 3))
            .withScale(tileScale)
            .build();
        MapTileBuilder finalMiddleBarBorderTile = new MapTileBuilder(finalMiddleBarBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(finalMiddleBarBorderTile);
        
        return mapTiles;

    }
    
}
