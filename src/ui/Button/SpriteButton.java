package ui.Button;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

import Utils.ImageUtils;
import Utils.Point;
import ui.SpriteUI.SpriteUI;

public class SpriteButton extends AbstractButton {
  protected SpriteUI spriteUI;
  protected BufferedImage originalImg, normalImg, hoveredImg, clickedImage;
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
    this.originalImg = spriteImage;
    this.hoveredImg = ImageUtils.deepCopy(spriteImage);
    this.clickedImage = ImageUtils.deepCopy(spriteImage);
    ImageUtils.darken(this.hoveredImg);
    ImageUtils.brighten(this.clickedImage);
    this.spriteUI = new SpriteUI(0, 0, spriteImage);
    this.addComponent(this.spriteUI);
  }

  // if you manually set width and height you might want to center the hitbox of
  // the button to the center of the sprite image because the imaage has white
  // space around it
  // you dont want to click the whitespace
  public void centerClickBox() {
    // get differences between sprite dimensions and button dimensions
    int heightDiff = this.spriteUI.getHeight() - getHeight();
    int widthDiff = this.spriteUI.getWidth() - getWidth();

    // offset by half the diff
    super.setXOrigin((int) origin.x + widthDiff / 2);
    super.setYOrigin((int) origin.y + heightDiff / 2);
  }

  public SpriteUI getSpriteUI() {
    return ((SpriteUI) this.components.get(0));
  }

  @Override
  public void whileHovering() {
    this.spriteUI.setImage(hoveredImg);
  }

  @Override
  public void whileNotHovering() {
    this.spriteUI.setImage(normalImg);

  }

  @Override
  public void whilePressed() {
    this.spriteUI.setImage(clickedImage);
  }

  public void scaleSprite(float scale) {
    this.normalImg = ImageUtils.resizeImageNearestNeighbor(this.originalImg, (int) (scale * originalImg.getWidth()),
        (int) (scale * originalImg.getHeight()));
    this.hoveredImg = ImageUtils.deepCopy(normalImg);
    this.clickedImage = ImageUtils.deepCopy(normalImg);
    ImageUtils.darken(this.hoveredImg);
    ImageUtils.brighten(this.clickedImage);
    setWidth(normalImg.getWidth());
    setHeight(normalImg.getHeight());
  }

  public void setImage(BufferedImage image, float scale) {
    this.originalImg = image;
    scaleSprite(scale);
    this.originalImg = this.normalImg;
  }

}
