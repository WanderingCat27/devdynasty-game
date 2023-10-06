package Button;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;

import Engine.GraphicsHandler;
import SpriteFont.SpriteFont;

public class TextButton extends ClickableRect {

    SpriteFont spriteFont;
    Dimension textDimensions;

    private static Canvas metricCanvas = new Canvas();
    public TextButton(int x, int y, int width, int height, Color color, String text, Font font, Color textColor, Runnable onClick) {
        super(x, y, width, height, color, onClick);
        // method is deprecated, but the alternative method requires a "FontRendererContext" which is unclear how to find
        
        this.textDimensions = getTextDimensions(font, text);
        this.spriteFont = new SpriteFont(text, 0, 0, font, textColor);
        updateSpritePos();
    }

    public SpriteFont getSpriteFont() {
        return spriteFont;
    }

    public void updateSpritePos() {
        this.spriteFont.setX(getX() + width/2 - textDimensions.width / 2);
        this.spriteFont.setY(getY() + height/2 - textDimensions.height / 2);
        
    }

    @Override
    public void update() {
        super.update();
        // if relative center, needs to 
        if(super.relativeCenter)
            updateSpritePos(); 
    }

    public static Dimension getTextDimensions(Font font, String text) {
        FontMetrics metric = Toolkit.getDefaultToolkit().getFontMetrics(font);
        return new Dimension(metric.stringWidth(text), metric.getHeight());
    }



    @Override
    public void draw(GraphicsHandler g) {        
        super.draw(g);
        spriteFont.draw(g);        
    }
}
