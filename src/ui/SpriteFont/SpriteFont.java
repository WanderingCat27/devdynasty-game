package ui.SpriteFont;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import Engine.GraphicsHandler;
import ui.Container.UIContainer;

// This class represents a sprite font, which is graphic text (text drawn to the screen as if it were an image)
public class SpriteFont extends UIContainer{
	protected String text;
	protected Font font;
	protected Color color;
	protected Color outlineColor;
	protected float outlineThickness = 1f;

	public SpriteFont(String text, int x, int y, String fontName, int fontSize, Color color) {
		this(text, x, y,new Font(fontName, Font.PLAIN, fontSize), color);
	}

	public SpriteFont(String text, int x, int y, Font font, Color color) {
		super(x, y, 0, 0);

		this.text = text;
		this.font = font;
		this.color = color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getText() {
		return text;
	}

	public Font getFont() { return font; }

	public void setText(String text) {
		this.text = text;
	}

	public void setFontName(String fontName) {
		this.font = new Font(fontName, this.font.getStyle(), this.font.getSize());
	}

	public void setFontStyle(int fontStyle) {
		this.font = new Font(font.getFontName(), fontStyle, this.font.getSize());
	}

	public void setFontSize(int size) {
		this.font = new Font(font.getFontName(), this.font.getStyle(), size);
	}

	public void setFont(Font font) { this.font = font; }

	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}

	public void setOutlineThickness(float outlineThickness) {
		this.outlineThickness = outlineThickness;
	}

	

	public void setLocation(int x, int y) {
		setXOrigin(x);
		setYOrigin(y);
	}

	public void moveX(int dx) {
		setXOrigin(getXOrigin() + dx);
	}

	public void moveY(int dy) {
		setYOrigin(getYOrigin() + dy);
	}

	public void moveRight(int dx) {
		moveX(dx);
	}

	public void moveLeft(int dx) {
		moveX(-dx);
	}

	public void moveDown(int dy) {
		moveY(dy);
	}

	public void moveUp(int dy) {
		moveY(-dy);
	}

	private int getAscent(Graphics2D graphics) {
		FontMetrics fm = graphics.getFontMetrics(font);
		return fm.getAscent();
	}

	public void draw(GraphicsHandler graphicsHandler) {
		int ascent = getAscent(graphicsHandler.getGraphics());
		if (outlineColor != null && !outlineColor.equals(color)) {
			graphicsHandler.drawStringWithOutline(text, getXAbs(), getYAbs() + ascent, font, color, outlineColor, outlineThickness);
		} else {
			graphicsHandler.drawString(text, getXAbs(), getYAbs() + ascent, font, color);
		}

		super.draw(graphicsHandler);
	}

	// this can be called instead of regular draw to have the text drop to the next line in graphics space on a new line character
	public void drawWithParsedNewLines(GraphicsHandler graphicsHandler, int gapBetweenLines) {
		int ascent = getAscent(graphicsHandler.getGraphics());
		int drawLocationY = Math.round(getYAbs()) + ascent;
		for (String line: text.split("\n")) {
			if (outlineColor != null && !outlineColor.equals(color)) {
				graphicsHandler.drawStringWithOutline(line, getXAbs(), drawLocationY, font, color, outlineColor, outlineThickness);
			} else {
				graphicsHandler.drawString(line, getXAbs(), drawLocationY, font, color);
			}
			drawLocationY += font.getSize() + gapBetweenLines;
		}

		super.draw(graphicsHandler);
	}

	@Override
	public int getWidth() {
		return getTextDimensions().width;
	}

	@Override
	public int getHeight() {
		return getTextDimensions().height;
	}

	// method is deprecated, but the alternative method requires a
	// "FontRendererContext" which is unclear how to create or find or anything
	public Dimension getTextDimensions() {
		FontMetrics metric = Toolkit.getDefaultToolkit().getFontMetrics(font);
		return new Dimension(metric.stringWidth(text), metric.getHeight());
	}
}
