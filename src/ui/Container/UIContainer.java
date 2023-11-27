package ui.Container;

import java.util.ArrayList;

import Engine.GraphicsHandler;
import Engine.ScreenManager;

public class UIContainer {

  // ui has and x and a y, and xOff and yOff
  // ui is positioned based on its parent, if a ui is in space alone then it will
  // position on screen directly to x and y
  // if ui is in a container or another ui element it will position relative to
  // the ui element it is in, its parent
  private int x, y, xOff, yOff, width, height, parentWidth, parentHeight;
  protected ArrayList<UIContainer> components = new ArrayList<>(0);

  private Anchor anchor;

  private FillType fillTypeX = FillType.NONE;
  private FillType fillTypeY = FillType.NONE;

  // anchors modify the position on the object itself from which is will be drawn
  // i.e. CENTER -> (0,0) = center

  public UIContainer(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.anchor = Anchor.TOP_LEFT;
  }

  // if no width given, defaults to fill parent
  public UIContainer(int x, int y) {
    this(x, y, 0, 0);
    setfillType(FillType.FILL_PARENT);
  }

  public int getxOff() {
    return xOff;
  }

  public void setxOff(int xOff) {
    this.xOff = xOff;
  }

  public int getyOff() {
    return yOff;
  }

  public void setyOff(int yOff) {
    this.yOff = yOff;
  }

  public int getParentWidth() {
    return parentWidth;
  }

  public void setParentWidth(int parentWidth) {
    this.parentWidth = parentWidth;
  }

  public int getParentHeight() {
    return parentHeight;
  }

  public void setParentHeight(int parentHeight) {
    this.parentHeight = parentHeight;
  }

  public enum FillType {
    NONE, FILL_PARENT, FILL_SCREEN
  };

  public int getXOrigin() {
    return x;
  }

  public void setXOrigin(int x) {
    this.x = x;
  }

  public int getYOrigin() {
    return y;
  }

  public void setYOrigin(int y) {
    this.y = y;
  }

  public int getXAbs() {
    return getXOrigin() + getxOff() + anchor.getXOffset(this);
  }

  public int getYAbs() {
    return getYOrigin() + getyOff() + anchor.getYOffset(this);
  }

  public void setParentData(int xOff, int yOff, int width, int height) {
    setxOff(xOff);
    setyOff(yOff);
    setParentWidth(width);
    setParentHeight(height);
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getHeight() {
    switch (fillTypeY) {
      case FILL_SCREEN:
        return ScreenManager.getScreenHeight();
      case FILL_PARENT:
        return getParentHeight();
      default:
        return this.height;
    }

  }

  public int getWidth() {
    switch (fillTypeX) {
      case FILL_SCREEN:
        return ScreenManager.getScreenWidth();
      case FILL_PARENT:
        return getParentWidth();
      default:
        return this.width;
    }

  }

  public FillType getFillTypeX() {
    return fillTypeX;
  }

  public FillType getFillTypeY() {
    return fillTypeY;
  }

  public void setfillType(FillType fillType) {
    this.fillTypeX = fillType;
    this.fillTypeY = fillType;
  }

  public void setfillTypeX(FillType fillType) {
    this.fillTypeX = fillType;
  }

  public void setfillTypeY(FillType fillType) {
    this.fillTypeY = fillType;
  }

  public void draw(GraphicsHandler g) {
    for (UIContainer c : components) {
      // position children relative to self
      c.setParentData(getXAbs(), getYAbs(), getWidth(), getHeight());
      c.draw(g);
    }
  }

  public void update() {
    components.forEach((c) -> c.update());
  }

  public void addComponent(UIContainer comp) {
    this.components.add(comp);
  }

  public void removeComponent(UIContainer comp) {
    this.components.remove(comp);
  }

  public void setAnchor(Anchor anchor) {
    this.anchor = anchor;
  }

  public ArrayList<UIContainer> children() {
    return components;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;

  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setLocation(int x, int y) {
    setX(x);
    setY(y);
  }
}
