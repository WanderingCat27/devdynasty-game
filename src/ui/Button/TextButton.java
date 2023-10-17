package ui.Button;

import java.awt.Color;
import java.awt.Font;

import ui.Container.CenterContainer;
import ui.SpriteFont.SpriteFont;

public class TextButton extends SolidRectButton {

    SpriteFont spriteFontRef;

    public TextButton(int x, int y, int width, int height, Color color, String text, Font font, Color textColor, Runnable onClick) {
        super(x, y, width, height, color, onClick);
    
        this.spriteFontRef = new SpriteFont(text, 0, 0, font, textColor);
        CenterContainer centerContainer = new CenterContainer();
        centerContainer.addComponnent(this.spriteFontRef);
        addComponnent(centerContainer);
    }

    public SpriteFont getSpriteFont() {
        return this.spriteFontRef;
    }
}
