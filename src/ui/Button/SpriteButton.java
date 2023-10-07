package ui.Button;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import Engine.GraphicsHandler;
import GameObject.Sprite;
import Utils.Point;

public class SpriteButton extends ClickableRect {
    Sprite sprite;
    protected Point origin;

    public SpriteButton(int x, int y, float scale, BufferedImage spriteImage, Runnable onClick) {
        super(x, y, x, y, null, onClick);
        spriteImage = scaleImage(spriteImage, scale);
        super.setXOrigin(x);
        super.setYOrigin(y);
        this.origin = new Point(x, y);
        setWidth(spriteImage.getWidth());
        setHeight(spriteImage.getHeight());
        this.onClick = onClick;

        this.sprite = new Sprite(spriteImage, x, y);
    }

    // Not my code, taken from: 
    // https://stackoverflow.com/questions/11271329/converting-image-to-bufferedimage
    protected BufferedImage scaleImage(BufferedImage image, float  scale) {
        int w = (int)(image.getWidth() * scale);
        int h = (int)(image.getHeight() * scale);
        BufferedImage scaledImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return scaleOp.filter(image, scaledImage);
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

    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void draw(GraphicsHandler g) {
        this.sprite.draw(g);
    }

}
