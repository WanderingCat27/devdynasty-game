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

public class TestTileset extends Tileset
{
    public TestTileset()
    {
        super(ImageLoader.load("PixelSheetTest.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles()
    {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        //flower
        Frame flowerFrame = new FrameBuilder(getSubImage(0, 0))
            .withScale(tileScale)
            .build();
        MapTileBuilder flowerTile = new MapTileBuilder(flowerFrame);

        mapTiles.add(flowerTile);

        //grass with dark patches
        Frame darkGrassFrame = new FrameBuilder(getSubImage(0, 1))
            .withScale(tileScale)
            .build();
        MapTileBuilder darkGrassTile = new MapTileBuilder(darkGrassFrame);
        mapTiles.add(darkGrassTile);

        //grass with light patches
        Frame lightGrassFrame = new FrameBuilder(getSubImage(0, 2))
            .withScale(tileScale)
            .build();
        MapTileBuilder lightGrassTile = new MapTileBuilder(lightGrassFrame);
        mapTiles.add(lightGrassTile);

        return mapTiles;
    }
}
