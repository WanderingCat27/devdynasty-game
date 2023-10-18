package ui.Button;

import java.awt.Point;

import Engine.Mouse;
import ui.Container.UIContainer;

public abstract class AbstractButton extends UIContainer {
  protected boolean pressed = false;
  protected Runnable onClick;

  public AbstractButton(int x, int y, int width, int height, Runnable onClick) {
    super(x, y, width, height);
    this.onClick = onClick;
  }

  protected abstract void whileNotHovering();

  protected abstract void whileHovering();

  protected abstract void whilePressed();

  protected boolean isMouseOver() {
    Point p = Mouse.getMouseLoction();
    return Utils.MathUtils.isInBounds(p, getXAbs(), getYAbs(), getWidth(), getHeight());
  }

  @Override
  public void update() {
    if (isMouseOver() && Mouse.isButtonDown(Mouse.LEFT_MOUSE_BUTTON) && !pressed) {
      // activate only once per click
      pressed = true;
      this.onClick.run();
    }
    // prime for another press
    if (Mouse.isButtonUp(Mouse.LEFT_MOUSE_BUTTON))
      pressed = false;

    if (pressed)
      whilePressed();
    else if (isMouseOver()) // not pressed but hovering
      whileHovering();
    else // not pressed or hovering
      whileNotHovering();
  }


}
