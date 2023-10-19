package ui.Button;

import java.awt.image.BufferedImage;

import GameObject.SpriteSheet;
import Utils.ImageUtils;
import ui.SpriteUI.SpriteUI;

public class AnimatedSpriteButton extends AbstractButton {
  protected SpriteUI spriteUI;
  protected SpriteSheet spriteSheet;
  protected BufferedImage normalImg, hoveredImg, clickedImage;
  protected float initialScale;

  public AnimatedSpriteButton(int x, int y, float scale, SpriteSheet spriteSheet, Runnable onClick) {
    super(x, y, x, y, onClick);
    this.spriteSheet = spriteSheet;
    this.initialScale = scale;

    scale(1);

    this.spriteUI = new SpriteUI(0, 0, normalImg);
    this.addComponent(this.spriteUI);
  }


  public void scale(float scale) {
    scale *= initialScale;
    normalImg = scaleImage(spriteSheet.getSubImageNoOffset(0, 0), scale);
    hoveredImg = scaleImage(spriteSheet.getSubImageNoOffset(0, 1), scale);
    clickedImage = scaleImage(spriteSheet.getSubImageNoOffset(0, 2), scale);


    setWidth(normalImg.getWidth());
    setHeight(normalImg.getHeight());
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
