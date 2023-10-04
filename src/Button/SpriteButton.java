package Button;

import Engine.GraphicsHandler;
import GameObject.Sprite;
import SpriteFont.SpriteFont;
import Utils.Point;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class SpriteButton extends ClickableRect {
    Sprite sprite;
    protected Point origin;

    public SpriteButton(int x, int y, float scale, BufferedImage spriteImage, Runnable onClick) {
        spriteImage = scaleImage(spriteImage, scale);
        this.x = x;
        this.y = y;
        this.origin = new Point(x, y);
        this.width = spriteImage.getWidth();
        this.height = spriteImage.getHeight();
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

    // manually set the click hitbox width
    public void setWidth(int width) {
        this.width = width;
    }

    // manually set the click hitbox height
    public void setHeight(int height) {
        this.height = height;
    }

    // if you manually set width and height you might want to center the hitbox of
    // the button to the center of the sprite image because the imaage has white
    // space around it
    // you dont want to click the whitespace
    public void centerClickBox() {
        // get differences between sprite dimensions and button dimensions
        int heightDiff = this.sprite.getHeight() - this.height;
        int widthDiff = this.sprite.getWidth() - this.width;

        // offset by half the diff
        this.x = (int) origin.x + widthDiff / 2;
        this.y = (int) origin.y + heightDiff / 2;
    }

    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void draw(GraphicsHandler g) {
        this.sprite.draw(g);
    }

}
