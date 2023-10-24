package Tilesets;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.TileType;
import Level.Tileset;

public class WestTileset extends Tileset {
  public WestTileset() {
    super(ImageLoader.load("westTileset.png"), 16, 16, 3);
  }

  @Override
  public ArrayList<MapTileBuilder> defineTiles() {
    ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

    SpriteSheet spriteSheet2 = new SpriteSheet(ImageLoader.load("desert.png"), 16, 16);

    Frame desertSand = new FrameBuilder(spriteSheet2.getSubImageNoOffset(6, 1))
        .withScale(tileScale)
        .build();

    // desert grass
    // Frame desertGrass = new FrameBuilder(spriteSheet2.getSubImageNoOffset(6, 0))
    // .withScale(tileScale)
    // .build();

    // yellow-ish grass
    Frame westGrass = new FrameBuilder(getSubImage(0, 0))
        .withScale(tileScale)
        .build();
    MapTileBuilder westGrassTile = new MapTileBuilder(westGrass);

    mapTiles.add(westGrassTile);

    // dirt
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
        .withTileType(TileType.NOT_PASSABLE);
    ;

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

    // desert sand

    MapTileBuilder desertTile = new MapTileBuilder(desertSand);
    mapTiles.add(desertTile);
    // desert grass add

    MapTileBuilder desertGrassTile = new MapTileBuilder(westGrass);
    mapTiles.add(desertGrassTile);

    // saloon roof
    for (int col = 0; col < 4; col++) {
      Frame saloon = new FrameBuilder(spriteSheet2.getSubImageNoOffset(0, col))
          .withScale(tileScale)
          .build();
      MapTileBuilder saloonTile = new MapTileBuilder(desertSand)
          .withTileType(TileType.NOT_PASSABLE)
          .withTopLayer(saloon);
      mapTiles.add(saloonTile);
    }

    // saloon
    for (int row = 1; row < 6; row++) {
      for (int col = 0; col < 4; col++) {
        Frame saloon = new FrameBuilder(spriteSheet2.getSubImageNoOffset(row, col))
            .withScale(tileScale)
            .build();
        MapTileBuilder saloonTile = new MapTileBuilder(saloon);
        // if (col == 4 && row == 1)
        // saloonTile.withTileType(TileType.PASSABLE); // porch entrance is enterable
        // else
        saloonTile.withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(saloonTile);
      }
    }

    // mini buildings
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 3; col++) {
        Frame building = new FrameBuilder(spriteSheet2.getSubImageNoOffset(row, col + 5))
            .withScale(tileScale)
            .build();
        MapTileBuilder buildingTile;
        if (row % 2 == 0)
          buildingTile = new MapTileBuilder(desertSand)
              .withTileType(TileType.NOT_PASSABLE)
              .withTopLayer(building);
        else
          buildingTile = new MapTileBuilder(building)
              .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(buildingTile);
      }
    }

    // cactus
    Frame cactus2 = new FrameBuilder(spriteSheet2.getSubImageNoOffset(7, 1))
        .withScale(tileScale)
        .build();
    MapTileBuilder cactusTile2 = new MapTileBuilder(desertSand)
        .withTopLayer(cactus2);
    mapTiles.add(cactusTile2);

    // tree
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        Frame tree = new FrameBuilder(spriteSheet2.getSubImageNoOffset(row + 5, col + 4))
            .withScale(tileScale)
            .build();
        MapTileBuilder treeTile = new MapTileBuilder(westGrass)
            .withTopLayer(tree);
        mapTiles.add(treeTile);
      }
    }

    // base
    Frame treeBase = new FrameBuilder(spriteSheet2.getSubImageNoOffset(6, 7))
        .withScale(tileScale)
        .build();
    MapTileBuilder treeBaseTile = new MapTileBuilder(westGrass)
        .withTopLayer(treeBase);
        mapTiles.add(treeBaseTile);
    // trunk
    Frame treeTrunk = new FrameBuilder(spriteSheet2.getSubImageNoOffset(7, 7))
        .withScale(tileScale)
        .build();
    MapTileBuilder treeTrunkTile = new MapTileBuilder(westGrass)
        .withTopLayer(treeTrunk)
        .withTileType(TileType.NOT_PASSABLE);
        mapTiles.add(treeTrunkTile);


    return mapTiles;
  }

}
