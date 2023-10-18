package ui.Button;

import java.awt.Color;

import Engine.GraphicsHandler;

public class SolidRectButton extends AbstractButton {
  protected Color defColor, color;

  // for extending classes to do their own thing with variables

  public SolidRectButton(int x, int y, int width, int height, Color color, Runnable onClick) {
    super(x, y, width, height, onClick);
    this.defColor = color;
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
    g.drawFilledRectangle(getXAbs(), getYAbs(), getWidth(), getHeight(), color);
    super.draw(g);
  }
}