package Button;

import java.awt.Color;
import java.awt.Point;

import Engine.GraphicsHandler;
import Engine.Mouse;

public class ClickableRect {
    protected int width, height, x, y;
    protected Color defColor, color;
    protected boolean pressed = false;
    protected Runnable onClick;

    // for extending classes to do their own thing with variables
    protected ClickableRect() {

    }

    public ClickableRect(int x, int y, int width, int height, Color color, Runnable onClick) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
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
        return p.x > x && p.x < x + width && p.y > y && p.y < y + height;
    }

    public void draw(GraphicsHandler g) {
        if (pressed)
            color = defColor.darker();
        else if (isMouseOver()) // not pressed but hovering
            color = defColor.brighter();
        else // not pressed or hovering
            color = defColor;

        g.drawFilledRectangle(Math.round(x), Math.round(y), Math.round(width), Math.round(height), color);
    }
}