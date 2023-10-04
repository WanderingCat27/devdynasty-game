package Button;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;

import Engine.GraphicsHandler;
import GameObject.Sprite;
import SpriteFont.SpriteFont;

public class TextButton extends ClickableRect {

    SpriteFont spriteFont;

    private static Canvas metricCanvas = new Canvas();
    public TextButton(int x, int y, int width, int height, Color color, String text, Font font, Color textColor, Runnable onClick) {
        super(x, y, width, height, color, onClick);
        // method is deprecated, but the alternative method requires a "FontRendererContext" which is unclear how to find
        
        Dimension textDim = getTextDimensions(font, text);
        int spriteX = x+width/2 - textDim.width / 2;
        int spriteY = y + height/2 - textDim.height/2;
        this.spriteFont = new SpriteFont(text, spriteX, spriteY , font, textColor);
    }

    public SpriteFont getSpriteFont() {
        return spriteFont;
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
