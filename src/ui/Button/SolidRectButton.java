package ui.Button;

import java.awt.Color;

import Engine.GraphicsHandler;

public class SolidRectButton extends AbstractButton {
  protected Color defColor, color;
  protected int borderThickness = 0;

  // for extending classes to do their own thing with variables

  public SolidRectButton(int x, int y, int width, int height, Color color, Runnable onClick) {
    super(x, y, width, height, onClick);
    this.defColor = color;
  }

  public SolidRectButton(int x, int y, int width, int height, Color color, Runnable onClick, int borderThickness) {
    this(x, y, width, height, color, onClick);
    this.borderThickness = borderThickness;
  }

  @Override
  protected void whileNotHovering() {
    color = defColor;
  }

  @Override
  protected void whileHovering() {
    color = defColor.brighter();
  }

  @Override
  protected void whilePressed() {
    color = defColor.darker();
  }

  @Override
  public void draw(GraphicsHandler g) {
    if (borderThickness == 0)
      g.drawFilledRectangle(getXAbs(), getYAbs(), getWidth(), getHeight(), color);
    else
      g.drawFilledRectangleWithBorder(getXAbs(), getYAbs(), getWidth(), getHeight(), color, Color.BLACK, borderThickness);

    super.draw(g);
  }
}