package ui.Button;

import java.awt.image.BufferedImage;

import GameObject.SpriteSheet;
import Utils.ImageUtils;
import Utils.Point;
import ui.SpriteUI.SpriteUI;

public class AnimatedSpriteButton extends AbstractButton {
  protected SpriteUI spriteUI;
  protected BufferedImage normalImg, hoveredImg, clickedImage;
  protected Point origin;

  public AnimatedSpriteButton(int x, int y, float scale, SpriteSheet spriteSheet, Runnable onClick) {
    super(x, y, x, y, onClick);
    normalImg = scaleImage(spriteSheet.getSubImageNoOffset(0, 0), scale);
    hoveredImg = scaleImage(spriteSheet.getSubImageNoOffset(0, 1), scale);
    clickedImage = scaleImage(spriteSheet.getSubImageNoOffset(0, 2), scale);
    // ImageUtils.brighten(this.hoveredImg);


    super.setXOrigin(x);
    super.setYOrigin(y);
    this.origin = new Point(x, y);
    setWidth(normalImg.getWidth());
    setHeight(normalImg.getHeight());

    this.spriteUI = new SpriteUI(0, 0, normalImg);
    this.addComponent(this.spriteUI);
  }


  public static BufferedImage scaleImage(BufferedImage spriteImage, float scale) {
    return ImageUtils.resizeImageNearestNeighbor(spriteImage, (int) (scale * spriteImage.getWidth()),
        (int) (scale * spriteImage.getHeight()));
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

}
