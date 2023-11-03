package Tilesets;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;
import Utils.ImageUtils;

public class FutureTileset extends Tileset {

  public FutureTileset() {
    super(ImageLoader.loadAllowTransparent("future_tiles.png"), 16, 16, 3);
  }

  @Override
  public ArrayList<MapTileBuilder> defineTiles() {
    ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

    // grass
    Frame grassFrame = new FrameBuilder(getSubImageNoOffset(0, 0))
        .withScale(tileScale)
        .build();

    MapTileBuilder grassTile = new MapTileBuilder(grassFrame);

    mapTiles.add(grassTile);

    // light
    // top
    Frame topOfLightFrame = new FrameBuilder(getSubImageNoOffset(0, 2))
        .withScale(tileScale)
        .build();

    MapTileBuilder topOfLightTile = new MapTileBuilder(grassFrame)
        .withTopLayer(topOfLightFrame);

    mapTiles.add(topOfLightTile);
    // bottom
    Frame bottomOfLightFrame = new FrameBuilder(getSubImageNoOffset(1, 2))
        .withScale(tileScale)
        .build();

    MapTileBuilder bottomOfLightTile = new MapTileBuilder(grassFrame)
        .withTopLayer(bottomOfLightFrame)
        .withTileType(TileType.PASSABLE);

    mapTiles.add(bottomOfLightTile);

    // pavement
    mapTiles.add(createFrame(getSubImageNoOffset(0, 1)));
    // edges
    mapTiles.addAll(multiFlippedFrames(getSubImageNoOffset(1, 1), ImageEffect.NONE, ImageEffect.FLIP_H_AND_V));
    mapTiles.addAll(multiFlippedFrames(getSubImageNoOffset(2, 1), ImageEffect.NONE, ImageEffect.FLIP_H_AND_V));

    // inner corners
    mapTiles.addAll(multiFlippedFrames(getSubImageNoOffset(1, 0), ImageEffect.NONE, ImageEffect.FLIP_HORIZONTAL,
        ImageEffect.FLIP_VERTICAL, ImageEffect.FLIP_H_AND_V));
    // outer corners
    mapTiles.addAll(multiFlippedFrames(getSubImageNoOffset(2, 0), ImageEffect.NONE, ImageEffect.FLIP_HORIZONTAL,
        ImageEffect.FLIP_VERTICAL, ImageEffect.FLIP_H_AND_V));

    // building
    mapTiles.addAll(multiFlippedFrames(getSubImageNoOffset(3, 0), TileType.NOT_PASSABLE, ImageEffect.NONE,
        ImageEffect.FLIP_H_AND_V, ImageEffect.FLIP_HORIZONTAL, ImageEffect.FLIP_VERTICAL));
    mapTiles.addAll(multiFlippedFrames(getSubImageNoOffset(3, 1), TileType.NOT_PASSABLE, ImageEffect.NONE,
        ImageEffect.FLIP_HORIZONTAL));

    for (int row = 0; row < 2; row++) {
      for (int col = 0; col < 2; col++) {
        mapTiles.add(createFrame(getSubImageNoOffset(4 + row, col), ImageEffect.NONE, TileType.NOT_PASSABLE));
      }
    }

    mapTiles
        .add(createFrame(getSubImageNoOffset(6, 0), ImageEffect.NONE, TileType.NOT_PASSABLE));
    mapTiles.addAll(multiFlippedFrames(getSubImageNoOffset(6, 2), TileType.NOT_PASSABLE, ImageEffect.NONE,
        ImageEffect.FLIP_VERTICAL));
    mapTiles
        .add(createFrame(getSubImageNoOffset(6, 1), ImageEffect.NONE, TileType.NOT_PASSABLE));
    mapTiles.addAll(multiFlippedFrames(getSubImageNoOffset(7, 0), TileType.NOT_PASSABLE, ImageEffect.NONE,
        ImageEffect.FLIP_HORIZONTAL));
    mapTiles.add(createFrame(getSubImageNoOffset(7, 1), ImageEffect.NONE, TileType.NOT_PASSABLE));
    mapTiles.addAll(multiFlippedFrames(getSubImageNoOffset(7, 2), TileType.NOT_PASSABLE, ImageEffect.FLIP_HORIZONTAL,
        ImageEffect.NONE));
    mapTiles.add(createFrame(getSubImageNoOffset(5, 2), ImageEffect.NONE, TileType.PASSABLE));

    // lab building
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 5; col++) {
        if (col == 1 || (row != 2 && col == 3))
          continue;
        if (row != 2)
          mapTiles.add(createFrame(getSubImageNoOffset(row, 3 + col), ImageEffect.NONE, TileType.NOT_PASSABLE));
        else
          mapTiles.add(createFrame(getSubImageNoOffset(row+2, 3 + col), grassFrame,ImageEffect.NONE, TileType.NOT_PASSABLE));

      }
    }
    return mapTiles;

  }

  private ArrayList<MapTileBuilder> multiFlippedFrames(java.awt.image.BufferedImage image, ImageEffect... effects) {
    return multiFlippedFrames(image, TileType.PASSABLE, effects);
  }

  private ArrayList<MapTileBuilder> multiFlippedFramesAll(java.awt.image.BufferedImage image, TileType passable) {
    return multiFlippedFrames(image, passable, ImageEffect.NONE, ImageEffect.FLIP_HORIZONTAL, ImageEffect.FLIP_H_AND_V,
        ImageEffect.FLIP_VERTICAL);
  }

  private ArrayList<MapTileBuilder> multiFlippedFrames(java.awt.image.BufferedImage image, TileType passable,
      ImageEffect... effects) {

    ArrayList<MapTileBuilder> frames = new ArrayList<>();

    for (ImageEffect e : effects) {
      frames.add(createFrame(image, e, passable));
    }

    return frames;
  }

  private MapTileBuilder createFrame(java.awt.image.BufferedImage image, ImageEffect effect, TileType passable) {
    return new MapTileBuilder(new FrameBuilder(image)
        .withScale(tileScale)
        .withImageEffect(effect)
        .build()).withTileType(passable);
  }

  private MapTileBuilder createFrame(java.awt.image.BufferedImage image, Frame bottomLayer, ImageEffect effect,
      TileType passable) {

    return new MapTileBuilder(bottomLayer)
        .withTopLayer(new FrameBuilder(image)
            .withScale(tileScale)
            .withImageEffect(effect)
            .build())
        .withTileType(passable);

  }

  private MapTileBuilder createFrame(java.awt.image.BufferedImage image) {
    return new MapTileBuilder(new FrameBuilder(image)
        .withScale(tileScale)
        .build());
  }

}
