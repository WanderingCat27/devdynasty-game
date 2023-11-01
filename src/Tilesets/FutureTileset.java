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

    return mapTiles;
  }

  private ArrayList<MapTileBuilder> multiFlippedFrames(java.awt.image.BufferedImage image, ImageEffect... effects) {

    ArrayList<MapTileBuilder> frames = new ArrayList<>();

    for (ImageEffect e : effects) {
      frames.add(createFrame(image, e, TileType.PASSABLE));
    }

    return frames;
  }

  private MapTileBuilder createFrame(java.awt.image.BufferedImage image, ImageEffect effect, TileType passable) {
    return new MapTileBuilder(new FrameBuilder(image)
        .withScale(tileScale)
        .withImageEffect(effect)
        .build()).withTileType(passable);
  }

  private MapTileBuilder createFrame(java.awt.image.BufferedImage image) {
    return new MapTileBuilder(new FrameBuilder(image)
        .withScale(tileScale)
        .build());
  }

}
