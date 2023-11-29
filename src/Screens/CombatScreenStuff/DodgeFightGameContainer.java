package Screens.CombatScreenStuff;

import java.awt.Color;
import java.util.Arrays;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Utils.MathUtils;
import ui.SpriteUI.SolidSpriteUI;

public class DodgeFightGameContainer extends MiniGameContainer {

  protected SolidSpriteUI box, bg;
  protected KeyLocker keyLocker = new KeyLocker();
  protected boolean onRoof = false;
  protected boolean[] points;
  protected boolean[] collided;

  float speed = 2.0f;

  int xPos = 0;
  int collisions = 0;

  public DodgeFightGameContainer(int height) {
    box = new SolidSpriteUI(0, 0, 50, 50, Color.WHITE) {
      @Override
      public int getHeight() {
        return bg.getHeight() / 2;
      }

      @Override
      public int getWidth() {
        return getHeight();
      }

      @Override
      public int getYOrigin() {
        return onRoof ? -bg.getHeight() + getHeight() : 0;
      }
    };

    bg = new SolidSpriteUI(0, 0, 0, height, Color.GRAY);
    bg.setfillTypeX(FillType.FILL_PARENT);
    addComponent(bg);
    addComponent(box);
  }

  protected void generateRandomObstacles() {
    points = new boolean[10];
    collided = new boolean[10];
    for (int index = 0; index < points.length; index++)
      points[index] = Math.random() < 0.5; // generate random true/false 50:50
  }

  @Override
  public void start() {
    generateRandomObstacles();
    xPos = 0;
    isStopped = false;
  }

  @Override
  public void update() {
    if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
      keyLocker.lockKey(Key.SPACE);
      onRoof = !onRoof;
    } else if (Keyboard.isKeyUp(Key.SPACE) && keyLocker.isKeyLocked(Key.SPACE)) {
      keyLocker.unlockKey(Key.SPACE);
    }

    if (!isStopped)
      xPos++;
    if (getObstacleX(11) <= -5 && !isStopped) {
      System.out.println("stopping");
      isStopped = true;
      System.out.println(Arrays.toString(collided));
    }
    if (isColliding()) {
      collided[getClosestIndex()] = true;
    }
  }

  private boolean isColliding() {
    return xPos > 32 && getClosestIndex() < 10 && getObstacleX(getClosestIndex()) >= 0
        && onRoof == points[MathUtils.clamp(getClosestIndex(), 0, 9)];
  }

  private int getClosestIndex() {
    int w = box.getHeight();
    return (int) ((w + (w * xPos / (10/speed)) - (w * 8)) / (w * 4));
  }

  @Override
  public void draw(GraphicsHandler g) {
    super.draw(g);
    int w = box.getHeight();
    for (int index = 0; index < points.length; index++)
      g.drawFilledRectangle(getObstacleX(index),
          points[index] ? bg.getYAbs() : bg.getYAbs() + bg.getHeight() - w, w, w,
          collided[index] ? Color.RED : Color.BLACK);

    g.drawFilledRectangle(getObstacleX(11), bg.getYAbs(), 2000, bg.getHeight(), Color.white);
  }

  protected int getObstacleX(int index) {
    int w = box.getWidth();
    return (int) ((w * 8 + index * w * 4) - (w * xPos / (10/speed)));
  }

  @Override
  public void setChildrenHeight(int height) {
    bg.setHeight(height);

  }

  @Override
  public float getScore() {
    int score = 10;
    for (boolean c : collided)
      if (c)
        score--;
    return Math.max(score, 3);
  }

}
