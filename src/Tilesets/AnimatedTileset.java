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

public class AnimatedTileset extends Tileset
{
    public AnimatedTileset()
    {
        super(ImageLoader.load("AnimatedPixelSheet.png"), 32, 32, 2);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles()
    {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();
        //bush
        Frame bushFrame = new FrameBuilder(getSubImage(0, 0))
            .withScale(tileScale)
            .build();
        MapTileBuilder bushTile = new MapTileBuilder(bushFrame);
        mapTiles.add(bushTile);

        //grass
        Frame grassFrame = new FrameBuilder(getSubImage(0, 1))
            .withScale(tileScale)
            .build();
        MapTileBuilder grassTile = new MapTileBuilder(grassFrame);
        mapTiles.add(grassTile);

        //flower
        Frame[] flowerFrames = new Frame[]
        {
            new FrameBuilder(getSubImage(0, 2),65)
                .withScale(tileScale)
                .build(),
            new FrameBuilder(getSubImage(0, 3),65)
                .withScale(tileScale)
                .build()
        };

        MapTileBuilder flowerTile = new MapTileBuilder(flowerFrames);
        mapTiles.add(flowerTile);
        return mapTiles;
    }
}
