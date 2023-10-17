package ui.Button;

import java.awt.image.BufferedImage;

import Engine.GraphicsHandler;
import GameObject.Sprite;
import Utils.ImageUtils;
import Utils.Point;
import ui.SpriteUI.SpriteUI;

public class SpriteButton extends AbstractButton {
  protected Sprite sprite;
  protected BufferedImage normalImg, hoveredImg, clickedImage;
  protected Point origin;
  

  public SpriteButton(int x, int y, float scale, BufferedImage spriteImage, Runnable onClick) {
    super(x, y, x, y, onClick);
    spriteImage = ImageUtils.resizeImageNearestNeighbor(spriteImage, (int) (scale * spriteImage.getWidth()),
        (int) (scale * spriteImage.getHeight()));
    super.setXOrigin(x);
    super.setYOrigin(y);
    this.origin = new Point(x, y);
    setWidth(spriteImage.getWidth());
    setHeight(spriteImage.getHeight());

    this.normalImg = spriteImage;
    this.hoveredImg = ImageUtils.deepCopy(spriteImage);
    this.clickedImage = ImageUtils.deepCopy(spriteImage);
    ImageUtils.darken(this.hoveredImg);
    ImageUtils.brighten(this.clickedImage);
    this.addComponent(new SpriteUI(0, 0, spriteImage));
  }

  // if you manually set width and height you might want to center the hitbox of
  // the button to the center of the sprite image because the imaage has white
  // space around it
  // you dont want to click the whitespace
  public void centerClickBox() {
    // get differences between sprite dimensions and button dimensions
    int heightDiff = this.sprite.getHeight() - getHeight();
    int widthDiff = this.sprite.getWidth() - getWidth();

    // offset by half the diff
    super.setXOrigin((int) origin.x + widthDiff / 2);
    super.setYOrigin((int) origin.y + heightDiff / 2);
  }

  public SpriteUI getSprite() {
    return ((SpriteUI) this.components.get(0));
  }

  @Override
  public void whileHovering() {
    this.sprite.setImage(hoveredImg);
  }

  @Override
  public void whileNotHovering() {
    this.sprite.setImage(normalImg);

  }

  @Override
  public void whilePressed() {
    this.sprite.setImage(clickedImage);
  }

  @Override
  public void draw(GraphicsHandler g) {
    this.sprite.draw(g);
  }

}
