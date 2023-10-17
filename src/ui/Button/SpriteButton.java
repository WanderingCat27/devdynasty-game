package ui.Button;

import java.awt.image.BufferedImage;

import Utils.ImageUtils;
import Utils.Point;
import ui.SpriteUI.SpriteUI;

public class SpriteButton extends ClickableRect {
    SpriteUI sprite;
    protected Point origin;

    public SpriteButton(int x, int y, float scale, BufferedImage spriteImage, Runnable onClick) {
        super(x, y, x, y, null, onClick);
        spriteImage = ImageUtils.resizeImage(spriteImage, (int) (scale * spriteImage.getWidth()), (int) (scale *spriteImage.getHeight()));
        super.setXOrigin(x);
        super.setYOrigin(y);
        this.origin = new Point(x, y);
        setWidth(spriteImage.getWidth());
        setHeight(spriteImage.getHeight());
        this.onClick = onClick;

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
        return sprite;
    }

}
