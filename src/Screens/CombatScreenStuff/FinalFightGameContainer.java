package Screens.CombatScreenStuff;

import ui.SpriteUI.SolidSpriteUI;
import java.awt.Color;
import java.net.CookieHandler;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Utils.MathUtils;

public class FinalFightGameContainer extends MiniGameContainer {
  public static boolean HAS_COMPLETED_TUTORIAL = false;

  // offset before first collision
  private static final int START_OFFSET = 800;
  // gap between blocks
  private static final int GAP = 350;
  // num of blocks to spawn
  private static final int MAX_ROUNDS = 15;
  // speed of blocks incoming
  private static final float SPEED = 11f;
  SolidSpriteUI bg;

  enum Score {
    NONE, PERFECT, GOOD, MISS;
  }

  private boolean[][] pressBoxes;
  private Score[][] scores;
  private int xPos = 0;

  private int prevIndex = -1;

  private KeyLocker keyLocker = new KeyLocker();

  Color boxColor = Color.GREEN;
  private static SecureRandom random = new SecureRandom();

  public FinalFightGameContainer(int height) {
    super("This game is controlled using a-s-d", "The goal of the game is to press the correct \nkeys when the blocks enter the red zone.", "a is for the top row, \ns is for the middle row, and \nd is for the bottom row.", "Don't worry if you don't understand, you can always \nrestart the fight by clicking run and exiting.");
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
    super.update();
    if (!isTutorialOver)
      return;

    int index = getClosestIndex();
    if (prevIndex != index) {
      // if didnt press the key at all and should have -.5f
      if (prevIndex >= 0 && prevIndex < MAX_ROUNDS) {
        if (!keyLocker.isKeyLocked(Key.A) && pressBoxes[prevIndex][0])
          scores[prevIndex][0] = Score.MISS;
        if (!keyLocker.isKeyLocked(Key.S) && pressBoxes[prevIndex][1])
          scores[prevIndex][1] = Score.MISS;
        if (!keyLocker.isKeyLocked(Key.D) && pressBoxes[prevIndex][2])
          scores[prevIndex][2] = Score.MISS;
      }
      keyLocker.unlockKey(Key.A);
      keyLocker.unlockKey(Key.S);
      keyLocker.unlockKey(Key.D);
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
    int index = getClosestIndex();
    if (pressBoxes[index][row]) { // is a correct press
      float val = 1 - getScoreClosest();
      // good press, give score
      scores[index][row] = val <= .3 ? Score.PERFECT : Score.GOOD;
      return true;
    }
    // was an incorrect press
    scores[index][row] = Score.MISS;
    return false;

  }

  private float getScoreClosest() {
    System.out.println((50f - MathUtils.clamp(Math.abs(getBoxX(getClosestIndex()) - 125) - 50, 0, 50)));
    return (50f - MathUtils.clamp(Math.abs(getBoxX(getClosestIndex()) - 125) - 30, 0, 50)) / 50f;
  }

  @Override
  public void draw(GraphicsHandler g) {
    super.draw(g);
    if (!isTutorialOver)
      return;

    for (int i = 0; i < pressBoxes.length; i++) {
      for (int j = 0; j < pressBoxes[i].length; j++)
        if (pressBoxes[i][j])
          g.drawFilledRectangle(getBoxX(i),
              bg.getYAbs() + j * bg.getHeight() / 3,
              40, bg.getHeight() / 3, getColor(j));
    }

    g.drawFilledRectangle(0, bg.getYAbs(), 300, bg.getHeight(), new Color(252, 236, 118, 100));
    g.drawFilledRectangle(125, bg.getYAbs(), 50, bg.getHeight(), new Color(252, 0, 50, 100));

    int index = getClosestIndex();
    if (index < MAX_ROUNDS && index >= 0) {
      // success/failure markers
      if (keyLocker.isKeyLocked(Key.A))
        g.drawFilledRectangle(125, bg.getYAbs(), 50, bg.getHeight() / 3, getColor(scores[index][0]));
      if (keyLocker.isKeyLocked(Key.S))
        g.drawFilledRectangle(125, bg.getYAbs() + bg.getHeight() / 3, 50, bg.getHeight() / 3,
            getColor(scores[index][1]));
      if (keyLocker.isKeyLocked(Key.D))
        g.drawFilledRectangle(125, bg.getYAbs() + 2 * bg.getHeight() / 3, 50, bg.getHeight() / 3,
            getColor(scores[index][2]));
    }
  }

  private Color getColor(int i) {
    if (i == 0)
      return Color.RED;
    else if (i == 2)
      return Color.GREEN;
    else
      return Color.BLUE;
  }

  private Color getColor(Score score) {
    if (score == null)
      return new Color(0, 0, 0, 0);
    switch (score) {
      case PERFECT:
        return Color.GREEN;
      case GOOD:
        return Color.YELLOW;
      case MISS:
        return Color.RED;
      default:
        return new Color(0, 0, 0, 0); // transparent
    }
  }

  @Override
  public void start() {
    HAS_COMPLETED_TUTORIAL = true;

    isStopped = false;
    xPos = 0;
    pressBoxes = new boolean[MAX_ROUNDS][3];
    scores = new Score[MAX_ROUNDS][3];
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
    float max = 0;
    float total = 0;
    for (int round = 0; round < scores.length; round++) {
      for (int row = 0; row < 3; row++) {
        Score sc = scores[round][row];
        if (sc == null)
          continue;
        max++;
        switch (sc) {
          case PERFECT:
            total += 1;
            break;
          case GOOD:
            total += .75f;
            break;
        }
      }
    }

    return 10 * total / max;
  }

  @Override
  public boolean isGameOver() {
    return isStopped;
  }

  @Override
  public boolean hasCompletedTutorial() {
    return HAS_COMPLETED_TUTORIAL;
  }

  @Override
  public int textboxHeight() {
    return bg.getHeight();
  }

}
