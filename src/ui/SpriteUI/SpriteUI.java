package ui.SpriteUI;

import java.awt.image.BufferedImage;

import Engine.GraphicsHandler;
import ui.Container.UIContainer;

public class SpriteUI extends UIContainer {


  BufferedImage image;

  public SpriteUI(int x, int y, BufferedImage image) {
    super(x, y);
    this.image = image;

    updateDim();
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

  
  

  
  
}
