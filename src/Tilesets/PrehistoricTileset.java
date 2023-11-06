package Tilesets;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.TileType;
import Level.Tileset;

public class PrehistoricTileset extends Tileset {
  public PrehistoricTileset() {
    super(ImageLoader.load("dinoTileset.png"), 16, 16, 3);
  }

  @Override
  public ArrayList<MapTileBuilder> defineTiles() {
    ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

   // SpriteSheet spriteSheet2 = new SpriteSheet(ImageLoader.load("desert.png"), 16, 16);

    // desert grass
    // Frame desertGrass = new FrameBuilder(spriteSheet2.getSubImageNoOffset(6, 0))
    // .withScale(tileScale)
    // .build();

    // stone with grass
    Frame stonyGrass = new FrameBuilder(getSubImage(0, 0))
        .withScale(tileScale)
        .build();
    MapTileBuilder stonyGrassTile = new MapTileBuilder(stonyGrass);

    mapTiles.add(stonyGrassTile);

    // top left rock
    Frame topLeftFrame = new FrameBuilder(getSubImage(0, 1))
        .withScale(tileScale)
        .build();
    MapTileBuilder topLeftTile = new MapTileBuilder(topLeftFrame);
    mapTiles.add(topLeftTile);

    Frame[] flowerFrames = new Frame[] {
        new FrameBuilder(getSubImage(1, 2), 65)
            .withScale(tileScale)
            .build(),
        new FrameBuilder(getSubImage(1, 3), 65)
            .withScale(tileScale)
            .build(),
        new FrameBuilder(getSubImage(1, 2), 65)
            .withScale(tileScale)
            .build(),
        new FrameBuilder(getSubImage(1, 4), 65)
            .withScale(tileScale)
            .build()
    };

    MapTileBuilder flowerTile = new MapTileBuilder(flowerFrames);

    mapTiles.add(flowerTile);

    // top right rock
    Frame topRight = new FrameBuilder(getSubImage(0, 2))
        .withScale(tileScale)
        .build();
    MapTileBuilder topRightTile = new MapTileBuilder(topRight);
    ;

    mapTiles.add(topRightTile);

    // bottom left rock
    Frame bottomLeft = new FrameBuilder(getSubImage(0, 3))
        .withScale(tileScale)
        .build();
    MapTileBuilder bottomLeftTile = new MapTileBuilder(bottomLeft);

    mapTiles.add(bottomLeftTile);

    // bottom right rock
    Frame bottomRight = new FrameBuilder(getSubImage(0, 4))
        .withScale(tileScale)
        .build();
    MapTileBuilder bottomRightTile = new MapTileBuilder(bottomRight);

    mapTiles.add(bottomRightTile);

    // hard rock
    Frame hardRock = new FrameBuilder(getSubImage(1, 0))
        .withScale(tileScale)
        .build();
    MapTileBuilder hardRockTile = new MapTileBuilder(hardRock)
    .withTileType(TileType.NOT_PASSABLE);

    mapTiles.add(hardRockTile);

     // fire
    Frame fire = new FrameBuilder(getSubImage(1, 1))
        .withScale(tileScale)
        .build();
    MapTileBuilder fireTile = new MapTileBuilder(fire)
    .withTileType(TileType.NOT_PASSABLE);

    mapTiles.add(fireTile);

     // path 1
    Frame path1 = new FrameBuilder(getSubImage(2, 0))
        .withScale(tileScale)
        .build();
    MapTileBuilder path1Tile = new MapTileBuilder(path1);

    mapTiles.add(path1Tile);

     // path 2
    Frame path2 = new FrameBuilder(getSubImage(2, 1))
        .withScale(tileScale)
        .build();
    MapTileBuilder path2tile = new MapTileBuilder(path2);
    mapTiles.add(path2tile);

    // upper log
    Frame upperLog = new FrameBuilder(getSubImage(2, 2))
        .withScale(tileScale)
        .build();
    MapTileBuilder upperLogTile = new MapTileBuilder(upperLog);
    mapTiles.add(upperLogTile);

    // lower log
    Frame lowerLog = new FrameBuilder(getSubImage(3, 2))
        .withScale(tileScale)
        .build();
    MapTileBuilder lowerLogTile = new MapTileBuilder(lowerLog);
    mapTiles.add(lowerLogTile);

    // pond top left
    Frame pondTL = new FrameBuilder(getSubImage(2, 3))
        .withScale(tileScale)
        .build();
    MapTileBuilder pondTLTile = new MapTileBuilder(pondTL);
    mapTiles.add(pondTLTile);

    // pond top right
    Frame pondTR = new FrameBuilder(getSubImage(2, 4))
        .withScale(tileScale)
        .build();
    MapTileBuilder pondTRTile = new MapTileBuilder(pondTR);
    mapTiles.add(pondTRTile);

    // pond bottom left
    Frame pondBL = new FrameBuilder(getSubImage(3, 3))
        .withScale(tileScale)
        .build();
    MapTileBuilder pondBLTile = new MapTileBuilder(pondBL);
    mapTiles.add(pondBLTile);

    // pond bottom right
    Frame pondBR = new FrameBuilder(getSubImage(3, 4))
        .withScale(tileScale)
        .build();
    MapTileBuilder pondBRTile = new MapTileBuilder(pondBR);
    mapTiles.add(pondBRTile);

    // plain dirt
    Frame dirt = new FrameBuilder(getSubImage(3, 0))
        .withScale(tileScale)
        .build();
    MapTileBuilder dirtTile = new MapTileBuilder(dirt);
    mapTiles.add(dirtTile);

     // alt dirt
    Frame altDirt = new FrameBuilder(getSubImage(3, 1))
        .withScale(tileScale)
        .build();
    MapTileBuilder altDirtTile = new MapTileBuilder(altDirt);
    mapTiles.add(altDirtTile);

     // alt rock bottom left
    Frame altRockBL = new FrameBuilder(getSubImage(4, 1))
        .withScale(tileScale)
        .build();
    MapTileBuilder altRockBLTile = new MapTileBuilder(altRockBL);
    mapTiles.add(altRockBLTile);

     // alt rock bottom right
    Frame altRockBR = new FrameBuilder(getSubImage(4, 2))
        .withScale(tileScale)
        .build();
    MapTileBuilder altRockBRTile = new MapTileBuilder(altRockBR);
    mapTiles.add(altRockBRTile);


    return mapTiles;
  }

}
