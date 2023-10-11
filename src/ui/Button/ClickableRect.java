package ui.Button;

import java.awt.Color;
import java.awt.Point;

import Engine.GraphicsHandler;
import Engine.Mouse;
import ui.Container.UIContainer;

public class ClickableRect extends UIContainer{
    protected Color defColor, color;
    protected boolean pressed = false;
    protected Runnable onClick;


    // for extending classes to do their own thing with variables

    public ClickableRect(int x, int y, int width, int height, Color color, Runnable onClick) {
        super(x, y, width, height);
        this.defColor = color;
        this.onClick = onClick;
    }

    public void update() {

        if (isMouseOver() && Mouse.isButtonDown(Mouse.LEFT_MOUSE_BUTTON) && !pressed) {
            // activate only once per click
            pressed = true;
            this.onClick.run();
        }
        // prime for another press
        if (Mouse.isButtonUp(Mouse.LEFT_MOUSE_BUTTON))
            pressed = false;

    }
    protected boolean isMouseOver() {
        Point p = Mouse.getMouseLoction();
        return Utils.MathUtils.isInBounds(p, getXAbs(), getYAbs(), getWidth(), getHeight());
    }

    public void draw(GraphicsHandler g) {
        if (pressed)
            color = defColor.darker();
        else if (isMouseOver()) // not pressed but hovering
            color = defColor.brighter();
        else // not pressed or hovering
            color = defColor;

        g.drawFilledRectangle(getXAbs(), getYAbs(), getWidth(), getHeight(), color);
        super.draw(g);
    }
}