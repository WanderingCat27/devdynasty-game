package ui.SpriteUI;

import java.awt.image.BufferedImage;

import Engine.GraphicsHandler;
import Utils.ImageUtils;
import ui.Container.UIContainer;

public class SpriteUI extends UIContainer {

  BufferedImage originalImage, image;

  public SpriteUI(int x, int y, BufferedImage image) {
    super(x, y);
    this.image = image;
    this.originalImage = image;
    setfillType(FillType.NONE);
    updateDim();
  }

  public SpriteUI(int x, int y, BufferedImage image, float scale) {
    this(x, y, ImageUtils.scaleImage(image, scale));

  }

  private void updateDim() {
    setWidth(image.getWidth());
    setHeight(image.getHeight());
  }

  public void setImage(BufferedImage image) {
    this.image = image;
    updateDim();
  }

  @Override
  public void draw(GraphicsHandler g) {
    g.drawImage(image, getXAbs(), getYAbs());
  }

  public void scale(float scale) {
    setImage(ImageUtils.scaleImage(originalImage, scale));
  }

}
