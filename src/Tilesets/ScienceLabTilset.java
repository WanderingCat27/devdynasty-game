package Tilesets;


import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.Map;
import Level.TileType;
import Level.Tileset;

public class ScienceLabTilset extends Tileset
{
    public ScienceLabTilset()
    {
        super(ImageLoader.load("ScienceLabTileset.png"), 16, 16,3);
    } 
    
    @Override
    public ArrayList<MapTileBuilder> defineTiles()
    {
        //basic floor tile
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        Frame floorFrame = new FrameBuilder(getSubImage(0, 0))
            .withScale(tileScale)
            .build();
        
        MapTileBuilder floorTile = new MapTileBuilder(floorFrame);
        mapTiles.add(floorTile);
        
        //bottom border tile
        Frame bottomBorderFrame = new FrameBuilder(getSubImage(0, 1))
            .withScale(tileScale)
            .build();
        MapTileBuilder bottomBorderTile = new MapTileBuilder(bottomBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(bottomBorderTile);

        //Right border tile
        Frame rightBorderFrame = new FrameBuilder(getSubImage(0, 2))
            .withScale(tileScale)
            .build();
        MapTileBuilder rightBorderTile = new MapTileBuilder(rightBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(rightBorderTile);

        //left border tile
        Frame leftBorderFrame = new FrameBuilder(getSubImage(0, 3))
            .withScale(tileScale)
            .build();
        MapTileBuilder leftBorderTile = new MapTileBuilder(leftBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(leftBorderTile);

        //top border tile
        Frame topBorderFrame = new FrameBuilder(getSubImage(0, 4))
            .withScale(tileScale)
            .build();
        MapTileBuilder topBorderTile = new MapTileBuilder(topBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(topBorderTile);

        //top right corner tile
        Frame topRightCornerFrame = new FrameBuilder(getSubImage(2, 2))
            .withScale(tileScale)
            .build();
        MapTileBuilder topRightCornerTile = new MapTileBuilder(topRightCornerFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(topRightCornerTile);

        //top left corner tile
        Frame topLeftCornerFrame = new FrameBuilder(getSubImage(3, 2))
            .withScale(tileScale)
            .build();
        MapTileBuilder topLeftCornerTile = new MapTileBuilder(topLeftCornerFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(topLeftCornerTile);

        //bottom right corner tile
        Frame bottomRightCornerFrame = new FrameBuilder(getSubImage(2, 4))
            .withScale(tileScale)
            .build();
        MapTileBuilder bottomRightCornerTile = new MapTileBuilder(bottomRightCornerFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(bottomRightCornerTile);

        //other corner tile
        Frame otherCornerFrame = new FrameBuilder(getSubImage(2, 3))
            .withScale(tileScale)
            .build();
        MapTileBuilder otherCornerTile = new MapTileBuilder(otherCornerFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(otherCornerTile);

        //bottom left corner tile
        Frame bottomLeftCornerFrame = new FrameBuilder(getSubImage(3, 4))
            .withScale(tileScale)
            .build();
        MapTileBuilder bottomLeftCornerTile = new MapTileBuilder(bottomLeftCornerFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(bottomLeftCornerTile);

        //top right corner tile
        Frame topRightCornerFrame2 = new FrameBuilder(getSubImage(4, 4))
            .withScale(tileScale)
            .build();
        MapTileBuilder topRightCornerTile2 = new MapTileBuilder(topRightCornerFrame2)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(topRightCornerTile2);

        //top left corner tile
        Frame topLeftCornerFrame2 = new FrameBuilder(getSubImage(5, 4))
            .withScale(tileScale)
            .build();
        MapTileBuilder topLeftCornerTile2 = new MapTileBuilder(topLeftCornerFrame2)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(topLeftCornerTile2);


        //Wall left border
        Frame wallLeftBorderFrame = new FrameBuilder(getSubImage(1, 0))
            .withScale(tileScale)
            .build();
        MapTileBuilder wallLeftBorderTile = new MapTileBuilder(wallLeftBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(wallLeftBorderTile);

        //Wall right border
        Frame wallRightBorderFrame = new FrameBuilder(getSubImage(1, 2))
            .withScale(tileScale)
            .build();
        MapTileBuilder wallRightBorderTile = new MapTileBuilder(wallRightBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(wallRightBorderTile);

        //Middle wall border
        Frame middleWallBorderFrame = new FrameBuilder(getSubImage(1, 1))
            .withScale(tileScale)
            .build();
        MapTileBuilder middleWallBorderTile = new MapTileBuilder(middleWallBorderFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(middleWallBorderTile);

        //Fire extinguisher
        Frame fireExtinguisherFrame = new FrameBuilder(getSubImage(2, 0))
            .withScale(tileScale)
            .build();
        MapTileBuilder fireExtinguisherTile = new MapTileBuilder(fireExtinguisherFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(fireExtinguisherTile);

        //Lights
        Frame[] lightFrames = new Frame[]
        {
            new FrameBuilder(getSubImage(1, 3), 65)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(1, 4), 65)
                .withScale(tileScale)
                .build()
        };
        MapTileBuilder lightTile = new MapTileBuilder(lightFrames)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(lightTile);

        //Computer
        Frame computerFrame = new FrameBuilder(getSubImage(2, 1))
            .withScale(tileScale)
            .withBounds(0, 6, 9, 9)
            .build();
        MapTileBuilder computerTile = new MapTileBuilder(floorFrame)
            .withTopLayer(computerFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(computerTile);

        Frame secondComputerFrame = new FrameBuilder(getSubImage(3, 1))
            .withScale(tileScale)
            .withBounds(2, 0, 16, 16)
            .build();
        MapTileBuilder secondComputerTile = new MapTileBuilder(floorFrame)
            .withTopLayer(secondComputerFrame)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(secondComputerTile);

        //black tile
        Frame blackTile = new FrameBuilder(getSubImage(3, 0))
            .withScale(tileScale)
            .build();
        MapTileBuilder blackTileBuilder = new MapTileBuilder(blackTile)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(blackTileBuilder);

        //final border tile
        Frame finalBorderTile = new FrameBuilder(getSubImage(3, 3))
            .withScale(tileScale)
            .build();
        MapTileBuilder finalBorderTileBuilder = new MapTileBuilder(finalBorderTile)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(finalBorderTileBuilder);

        return mapTiles;
        
    }
}
