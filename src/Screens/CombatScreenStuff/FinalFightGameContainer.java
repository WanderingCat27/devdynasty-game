package Screens.CombatScreenStuff;

import ui.SpriteUI.SolidSpriteUI;
import java.awt.Color;
import java.net.CookieHandler;
import java.security.SecureRandom;
import java.util.Timer;
import java.util.TimerTask;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Utils.MathUtils;

public class FinalFightGameContainer extends MiniGameContainer {

  // offset before first collision
  private static final int START_OFFSET = 500;
  // gap between blocks
  private static final int GAP = 400;
  // num of blocks to spawn
  private static final int MAX_ROUNDS = 10;
  // speed of blocks incoming
  private static final float SPEED = 10f;
  SolidSpriteUI bg;

  private boolean[][] pressBoxes;
  private Color[] success;
  private int curr = 0;
  private int xPos = 2;
  private int round;

  private float score = 10;

  private int prevIndex = -1;

  private KeyLocker keyLocker = new KeyLocker();

  Color boxColor = Color.GREEN;
  private static SecureRandom random = new SecureRandom();

  public FinalFightGameContainer(int height) {
    bg = new SolidSpriteUI(0, 0, 0, height, Color.GRAY);
    bg.setfillTypeX(FillType.FILL_PARENT);

    addComponent(bg);

    for (int i = 0; i < 3; i++) {
      final int ii = i;
      addComponent(new SolidSpriteUI(0, 0, 0, 0, i % 2 == 0 ? Color.DARK_GRAY : Color.GRAY) {
        final int pos = ii;

        @Override
        public int getHeight() {
          // TODO Auto-generated method stub
          return bg.getHeight() / 3;
        }

        @Override
        public int getWidth() {
          return bg.getWidth();
        }

        @Override
        public int getYAbs() {
          return bg.getYAbs() + pos * bg.getHeight() / 3;
        }
      });
    }

  }

  private int getBoxX(int i) {
    return (int) (START_OFFSET + i * GAP - (xPos * SPEED));
  }

  private int getClosestIndex() {
    return (int) (((xPos * SPEED) - (START_OFFSET - 300)) / GAP);
  }

  @Override
  public void update() {
    int index = getClosestIndex();
    if (prevIndex != index) {
      // if didnt press the key at all and should have -.5f
      if (prevIndex >= 0 && prevIndex < MAX_ROUNDS) {
        if (!keyLocker.isKeyLocked(Key.A) && pressBoxes[prevIndex][0])
          score -= .5f;
        if (!keyLocker.isKeyLocked(Key.S) && pressBoxes[prevIndex][1])
          score -= .5f;
        if (!keyLocker.isKeyLocked(Key.D) && pressBoxes[prevIndex][2])
          score -= .5f;
      }
      keyLocker.unlockKey(Key.A);
      keyLocker.unlockKey(Key.S);
      keyLocker.unlockKey(Key.D);
      success = new Color[3];
      prevIndex = index;
    }
    xPos++;
    if (index >= MAX_ROUNDS)
      stop();
    else if (index >= 0 && getBoxX(getClosestIndex()) > 0) {
      if (checkKey(Key.A))
        rowPressed(0);
      else if (checkKey(Key.S))
        rowPressed(1);
      else if (checkKey(Key.D))
        rowPressed(2);
    }

  }

  private boolean checkKey(Key key) {
    if (Keyboard.isKeyDown(key) && !keyLocker.isKeyLocked(key)) {
      keyLocker.lockKey(key);
      return true;
    }
    return false;
  }

  private boolean rowPressed(int row) {
    if (pressBoxes[getClosestIndex()][row]) { // is a correct press
      float val = 1 - getScoreClosest();
      score -= val;
      success[row] = val <= .1 ? Color.GREEN : Color.ORANGE;
      return true;
    }
    score -= 0.5f; // was an incorrect press
    success[row] = Color.RED;
    return false;

  }

  private float getScoreClosest() {
    System.out.println((50f - MathUtils.clamp(Math.abs(getBoxX(getClosestIndex()) - 125) - 50, 0, 50)));
    return (50f - MathUtils.clamp(Math.abs(getBoxX(getClosestIndex()) - 125) - 30, 0, 50)) / 50f;
  }

  @Override
  public void draw(GraphicsHandler g) {
    super.draw(g);

    for (int i = 0; i < pressBoxes.length; i++) {
      for (int j = 0; j < pressBoxes[i].length; j++)
        if (pressBoxes[i][j])
          g.drawFilledRectangle(getBoxX(i),
              bg.getYAbs() + j * bg.getHeight() / 3,
              40, bg.getHeight() / 3, getColor(j));
    }

    g.drawFilledRectangle(0, bg.getYAbs(), 300, bg.getHeight(), new Color(252, 236, 118, 100));
    g.drawFilledRectangle(125, bg.getYAbs(), 50, bg.getHeight(), new Color(252, 0, 50, 100));

    // success/failure markers
    if (keyLocker.isKeyLocked(Key.A))
      g.drawFilledRectangle(125, bg.getYAbs(), 50, bg.getHeight() / 3, success[0]);
    if (keyLocker.isKeyLocked(Key.S))
      g.drawFilledRectangle(125, bg.getYAbs() + bg.getHeight() / 3, 50, bg.getHeight() / 3, success[1]);
    if (keyLocker.isKeyLocked(Key.D))
      g.drawFilledRectangle(125, bg.getYAbs() + 2 * bg.getHeight() / 3, 50, bg.getHeight() / 3, success[2]);

  }

  private Color getColor(int i) {
    if (i == 0)
      return Color.RED;
    else if (i == 2)
      return Color.GREEN;
    else
      return Color.BLUE;
  }

  @Override
  public void start() {
    isStopped = false;
    xPos = 0;
    score = 10;
    pressBoxes = new boolean[MAX_ROUNDS][3];
    for (int i = 0; i < pressBoxes.length; i++)
      for (int j = 0; j < 3; j++)
        pressBoxes[i][j] = random.nextInt(3) == 2; // 0 - 2;

  }

  @Override
  public void setChildrenHeight(int height) {
    bg.setHeight(height);
  }

  @Override
  public float getScore() {
    return score / 10;
  }

  @Override
  public boolean isGameOver() {
    return isStopped;
  }

}
