package ui.SpriteUI;

import java.awt.Color;

import Engine.GraphicsHandler;
import ui.Container.UIContainer;

public class SolidSpriteUI extends UIContainer{
  private Color color;
  private int outlineThickness = 0;
  private Color outlineColor;

  public SolidSpriteUI(int x, int y, int width, int height, Color color) {
    super(x, y, width, height);
    this.color = color;

  }

  public SolidSpriteUI(int x, int y, int width, int height, Color color, Color outlineColor, int outlineThickness) {
    this(x, y, width, height, color);
    this.outlineThickness = outlineThickness;
    this.outlineColor = outlineColor;

  }

  @Override
  public void draw(GraphicsHandler g) {
    if(this.outlineThickness == 0)
    g.drawFilledRectangle(getXAbs(), getYAbs(), getWidth(), getHeight(), color);
    else
    g.drawFilledRectangleWithBorder(getXAbs(), getYAbs(), getWidth(), getHeight(), color, outlineColor, outlineThickness);
    
    super.draw(g);
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public int getOutlineThickness() {
    return outlineThickness;
  }

  public void setOutlineThickness(int outlineThickness) {
    this.outlineThickness = outlineThickness;
  }

  public Color getOutlineColor() {
    return outlineColor;
  }

  public void setOutlineColor(Color outlineColor) {
    this.outlineColor = outlineColor;
  }

  
  
}
