package Button;

import java.awt.Color;
import java.awt.Point;

import Engine.GraphicsHandler;
import Engine.Mouse;
import Engine.ScreenManager;

public class ClickableRect {
    protected int width, height;
    private int x, y;
    protected Color defColor, color;
    protected boolean pressed = false;
    protected Runnable onClick;

    protected boolean relativeCenter = false;

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

    protected int getX() {
        return relativeCenter ? ScreenManager.getScreenWidth()/2 + x -width /2: x;
    }

    protected int getY() {
        return relativeCenter ? ScreenManager.getScreenHeight()/2 + y - height/2 : y;
    }

    protected boolean isMouseOver() {
        Point p = Mouse.getMouseLoction();
        return p.x > getX() && p.x < getY() + width && p.y > getY() && p.y < getY() + height;
    }

    public void draw(GraphicsHandler g) {
        if (pressed)
            color = defColor.darker();
        else if (isMouseOver()) // not pressed but hovering
            color = defColor.brighter();
        else // not pressed or hovering
            color = defColor;

        g.drawFilledRectangle(getX(), getY(), width, height, color);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRelativeCenter(boolean relative) { 
        this.relativeCenter = relative;
    }
}