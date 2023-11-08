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

public class FutureIndoorTileset extends Tileset {

  public FutureIndoorTileset() {
    super(ImageLoader.loadAllowTransparent("reception_tiles.png"), 16, 16, 3);
  }

  @Override
  public ArrayList<MapTileBuilder> defineTiles() {
    ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

    // floor
    Frame floorFrame = new FrameBuilder(getSubImageNoOffset(0, 0))
        .withScale(tileScale)
        .build();

    MapTileBuilder floorTile = new MapTileBuilder(floorFrame);

    mapTiles.add(floorTile);

    for (int col = 0; col < 4; col++) {
      TileType pass = col == 0 ? TileType.PASSABLE : TileType.NOT_PASSABLE;
      mapTiles.add(createFrame(getSubImageNoOffset(0, 1 + col), ImageEffect.NONE, pass));
    }

    for (int col = 0; col < 5; col++) {
      mapTiles.add(createFrame(getSubImageNoOffset(1, col), floorFrame, ImageEffect.NONE, TileType.NOT_PASSABLE));
    }
    for (int col = 0; col < 3; col++)
      mapTiles.add(createFrame(getSubImageNoOffset(0, col + 5)));

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
