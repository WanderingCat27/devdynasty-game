package ui.Button;

import java.awt.Point;

import Engine.ButtonLocker;
import Engine.Mouse;
import ui.Container.UIContainer;

public abstract class AbstractButton extends UIContainer {
  protected boolean pressed = false;
  protected Runnable onClick;
  protected ButtonLocker buttonLocker = new ButtonLocker();

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


  // logic for button click and state
  @Override
  public void update() {
    if (isMouseOver()) {
      if (Mouse.isButtonDown(Mouse.LEFT_MOUSE_BUTTON) && !buttonLocker.isButtonLocked(Mouse.LEFT_MOUSE_BUTTON)) {
        pressed = true;
      }
    } else { // if mouse is moved away while pressing cancel press
      pressed = false;
    }

    if (isMouseOver())
      if (pressed) 
        whilePressed(); // pressed and hovering
      else
        whileHovering(); // not pressed but hovering
    else
      whileNotHovering(); // normal state


    // lock button so mouse clicks only register once
    if (!buttonLocker.isButtonLocked(Mouse.LEFT_MOUSE_BUTTON) && Mouse.isButtonDown(Mouse.LEFT_MOUSE_BUTTON)) {
      buttonLocker.lockButton(Mouse.LEFT_MOUSE_BUTTON);
    } else if (buttonLocker.isButtonLocked(Mouse.LEFT_MOUSE_BUTTON) && Mouse.isButtonUp(Mouse.LEFT_MOUSE_BUTTON)) {
      buttonLocker.unlockButton(Mouse.LEFT_MOUSE_BUTTON); 

      // run button logic on mouse released if button is still pressed
      if (pressed) {
        System.out.println("Clicked");
        if (onClick != null)
          this.onClick.run();
      }

      pressed = false;
    }
  }

}
