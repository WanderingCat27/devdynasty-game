package Tilesets;

import java.time.format.TextStyle;
import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import Level.TileType;
import Level.Tileset;

public class CaveTileset extends Tileset{
    


    public CaveTileset(){
        super(ImageLoader.load("CaveTileset.png"), 16, 16, 3);
    }



    @Override
    public ArrayList<MapTileBuilder> defineTiles()
    {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        Frame floorFrame = new FrameBuilder(getSubImage(0, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder floorTile = new MapTileBuilder(floorFrame);

        mapTiles.add(floorTile);


        Frame ceilingFrameRight = new FrameBuilder(getSubImage(0, 1))
                .withScale(tileScale)
                .build();
                
        MapTileBuilder ceilingTileRight = new MapTileBuilder(ceilingFrameRight)
            .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(ceilingTileRight);


        Frame ceilingFrameLeft = new FrameBuilder(getSubImage(0, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder ceilingTileLeft = new MapTileBuilder(ceilingFrameLeft)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(ceilingTileLeft);


        Frame caveRockFrame = new FrameBuilder(getSubImage(0, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder caveRockTile = new MapTileBuilder(caveRockFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(caveRockTile);


        Frame wallFrame = new FrameBuilder(getSubImage(1, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder wallTile = new MapTileBuilder(wallFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(wallTile);


        Frame rockHoleFrame = new FrameBuilder(getSubImage(1, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder rockHoleTile = new MapTileBuilder(rockHoleFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(rockHoleTile);


        Frame redRockFrame = new FrameBuilder(getSubImage(1, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder redRockTile = new MapTileBuilder(redRockFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(redRockTile);


        Frame greenRockFrame = new FrameBuilder(getSubImage(1, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder greenRockTile = new MapTileBuilder(greenRockFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(greenRockTile);



        Frame blueRockFrame = new FrameBuilder(getSubImage(2, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder blueRockTile = new MapTileBuilder(blueRockFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(blueRockTile);


        Frame redRockWallFrame = new FrameBuilder(getSubImage(2, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder redRockWallTile = new MapTileBuilder(redRockWallFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(redRockWallTile);

        Frame greenRockWallFrame = new FrameBuilder(getSubImage(2, 2))
                .withScale(tileScale)
                .build();

        MapTileBuilder greenRockWallTile = new MapTileBuilder(greenRockWallFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(greenRockWallTile);


        Frame blueRockWallFrame = new FrameBuilder(getSubImage(2, 3))
                .withScale(tileScale)
                .build();

        MapTileBuilder blueRockWallTile = new MapTileBuilder(blueRockWallFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(blueRockWallTile);


        Frame doorLightOffFrame = new FrameBuilder(getSubImage(3, 0))
                .withScale(tileScale)
                .build();

        MapTileBuilder doorLightOffTile = new MapTileBuilder(doorLightOffFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(doorLightOffTile);


        Frame doorLightOnFrame = new FrameBuilder(getSubImage(3, 1))
                .withScale(tileScale)
                .build();

        MapTileBuilder doorLightOnTile = new MapTileBuilder(doorLightOnFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(doorLightOnTile);





        return mapTiles;
    }
}
