package Screens.CombatScreenStuff;

import ui.SpriteUI.SolidSpriteUI;
import java.awt.Color;
import java.security.SecureRandom;
import java.util.Timer;
import java.util.TimerTask;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.Keyboard;

public class FinalFightGameContainer extends MiniGameContainer {

  private final int MAX_ROUNDS = 10;
  SolidSpriteUI bg;

  int[] xValues;
  int curr = 0;
  int xPos = 2;
  float speed = 1f;
  int round;

  float score = 10;

  Color boxColor = Color.GREEN;
  private static SecureRandom random = new SecureRandom();

  public FinalFightGameContainer(int height) {
    bg = new SolidSpriteUI(0, 0, 0, height, Color.GRAY);
    bg.setfillTypeX(FillType.FILL_PARENT);
    addComponent(bg);

  }

  @Override
  public void update() {
    if (Keyboard.isKeyDown(Key.A) && Keyboard.isKeyUp(Key.D) && getBoxX() > getBoxWidth() / 2)
      xPos--;
    else if (Keyboard.isKeyDown(Key.D) && Keyboard.isKeyUp(Key.A) && getBoxX() < (getWidth() - getBoxWidth() / 2))
      xPos++;

    checkColliding();
  }

  private void checkColliding() {
    int x = getBoxX();
    int beamX = xValues[indexAt()];
    int width = getBoxWidth();
    if (x >= beamX && x <= beamX + width) {
      if (indexAt() == curr)
        boxColor = Color.RED;
      else
        boxColor = Color.ORANGE;
    } else {
      boxColor = Color.GREEN;
    }

  }

  private int indexAt() {
    System.out.println(getWidth() / xValues.length);
    return getBoxX() / (getWidth() / xValues.length);
  }

  private int getBoxX() {
    return (int) (xPos * (speed * bg.getWidth() / 100.0));
  }

  @Override
  public void draw(GraphicsHandler g) {
    super.draw(g);
    int w = getBoxWidth();
    for (int index = 0; index < xValues.length; index++)
      if (xValues[index] != 0)
        g.drawFilledRectangle(getXAbs() - drawPosition.getXOffset(this) + xValues[index],
            -drawPosition.getYOffset(this) - bg.getHeight(), w, bg.getHeight(),
            index != curr ? Color.YELLOW : Color.RED);

    g.drawFilledRectangle((int) (getXAbs() - drawPosition.getXOffset(this) + getBoxX() - w / 2),
        -drawPosition.getYOffset(this) - w, w, w, boxColor);

  }

  private int getBoxWidth() {
    return getWidth() / 20;

  }

  @Override
  public void start() {
    xValues = new int[5];
    round = 0;
    score = 10;
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
      @Override
      public void run() {
        round++;
        int bottom = curr * getWidth() / xValues.length;
        int top = (curr + 1) * getWidth() / xValues.length;
        xValues[curr] = random.nextInt(bottom, top);
        curr = (curr + 1) % xValues.length;
        if (round >= MAX_ROUNDS)
          stop();
      }
    };
    timer.schedule(timerTask, 0, 200);
  }

  @Override
  public void setChildrenHeight(int height) {
    bg.setHeight(height);
  }

  @Override
  public float getScore() {
    return score;
  }

}
