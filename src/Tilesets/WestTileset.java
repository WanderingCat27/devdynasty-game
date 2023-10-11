package Tilesets;
import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.MapTile;
import Level.TileType;
import Level.Tileset;

public class WestTileset extends Tileset
{
    public WestTileset()
    {
        super(ImageLoader.load("westTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles()
    {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        //yellow-ish grass
        Frame westGrass = new FrameBuilder(getSubImage(0, 0))
            .withScale(tileScale)
            .build();
        MapTileBuilder westGrassTile = new MapTileBuilder(westGrass);

        mapTiles.add(westGrassTile);

        //dirt
        Frame dirtFrame = new FrameBuilder(getSubImage(0, 1))
            .withScale(tileScale)
            .build();
        MapTileBuilder dirtTile = new MapTileBuilder(dirtFrame);
        mapTiles.add(dirtTile);

        Frame[] cactusFrames = new Frame[] {
            new FrameBuilder(getSubImage(0, 2), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(0, 3), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(0, 2), 65)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(0, 4), 65)
                    .withScale(tileScale)
                    .build()
        };

    MapTileBuilder cactusTile = new MapTileBuilder(cactusFrames);

    mapTiles.add(cactusTile);

    // top of big cactus
    Frame cactusTop = new FrameBuilder(getSubImage(1, 0))
        .withScale(tileScale)
        .build();
    MapTileBuilder cactusTopTile = new MapTileBuilder(cactusTop)
        .withTileType(TileType.NOT_PASSABLE);;

    mapTiles.add(cactusTopTile);
    
    // bottom of big cactus
    Frame cactusBottom = new FrameBuilder(getSubImage(2, 0))
        .withScale(tileScale)
        .build();
    MapTileBuilder cactusBottomTile = new MapTileBuilder(cactusBottom)
        .withTileType(TileType.NOT_PASSABLE);


    mapTiles.add(cactusBottomTile);

    // sand
    Frame sand = new FrameBuilder(getSubImage(2, 1))
        .withScale(tileScale)
        .build();
    MapTileBuilder sandTile = new MapTileBuilder(sand);

    mapTiles.add(sandTile);

    // tumbleweed
    Frame tumbleweed = new FrameBuilder(getSubImage(1, 1))
        .withScale(tileScale)
        .build();
    MapTileBuilder tumbleweedTile = new MapTileBuilder(tumbleweed);

    mapTiles.add(tumbleweedTile);

        return mapTiles;
    }


}
