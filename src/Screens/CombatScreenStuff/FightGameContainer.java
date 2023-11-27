package Screens.CombatScreenStuff;

import java.awt.Color;
import java.awt.Font;
import java.security.SecureRandom;

import Engine.Config;
import Engine.Key;
import Engine.Keyboard;
import Engine.ScreenManager;
import ui.Button.TextButton;
import ui.Container.Anchor;
import ui.Container.CenterContainer;
import ui.Container.PositioningContainer;
import ui.SpriteUI.SolidSpriteUI;

public class FightGameContainer extends MiniGameContainer {

  TextButton stopButton;
  SolidSpriteUI bar, yellowCenter, perfectCenter, ticker;
  private static SecureRandom random = new SecureRandom();
  int hitLocation;

  float speed = 10f;
  float step;

  public FightGameContainer(int height) {
    stopButton = new TextButton(0, 0, 200, height, Color.RED, "Stop", new Font("Comic Sans", Font.BOLD, 20),
        Color.GREEN, () -> stop());

    bar = new SolidSpriteUI(200, 0, 0, height, Color.GRAY) {
      @Override
      public int getWidth() {
        return super.getParentWidth() - stopButton.getWidth();
      }
    };

    ticker = new SolidSpriteUI(0, 0, 60, height, Color.PINK, Color.BLACK, 6) {
      int w = 10;

      @Override
      public int getXOrigin() {
        return (int) ((step / 100) * (bar.getWidth() - ticker.getWidth()));
      }

      @Override
      public int getWidth() {
        return (int) (((float) ScreenManager.getScreenWidth() / Config.GAME_WINDOW_WIDTH) * w);
      }

      @Override
      public void update() {
        super.update();
        if (isStopped) {
          setColor(Color.BLACK);
        } else {
          setColor(Color.PINK);
        }
      }
    };
    ticker.setAnchor(Anchor.TOP_CENTER);
    ticker.setfillTypeY(FillType.FILL_PARENT);

    CenterContainer centerContainer = new CenterContainer();
    yellowCenter = new SolidSpriteUI(0, 0, 200, height, Color.ORANGE) {
      @Override
      public int getWidth() {
        return (int) (((float) ScreenManager.getScreenWidth() / Config.GAME_WINDOW_WIDTH) * 200);

      }

      @Override
      public int getXOrigin() {
        return (int) (hitLocation/100.0 * bar.getWidth());
      }
    };
    yellowCenter.setfillTypeY(FillType.FILL_PARENT);

    perfectCenter = new SolidSpriteUI(0, 0, 200, height, Color.GREEN) {
      @Override
      public int getxOff() {
        return yellowCenter.getXAbs() + yellowCenter.getWidth()/2 - perfectCenter.getWidth()/2;
      }
      @Override
      public int getWidth() {
        return (int) (((float) ScreenManager.getScreenWidth() / Config.GAME_WINDOW_WIDTH) * 30);

      }
    };
    perfectCenter.setfillTypeY(FillType.FILL_PARENT);

    bar.addComponent(yellowCenter);
    yellowCenter.setAnchor(Anchor.TOP_CENTER);
    bar.addComponent(perfectCenter);
    // bar.addComponent(centerContainer);
    bar.addComponent(ticker);

    addComponent(bar);
    addComponent(stopButton);

  }

  @Override
  public void setChildrenHeight(int height) {
    bar.setHeight(height);
    stopButton.setHeight(height);
  }

  @Override
  public void start() {
    isStopped = false;
    hitLocation = random.nextInt(80-2+1)+2;
  }

  @Override
  public void update() {
    super.update();
    if (!isStopped) {
      step = Math.abs(((System.currentTimeMillis() % (int) (20000 * (1 / speed))) / (100 * (1 / speed))) - 100);
    }

    if (Keyboard.isKeyDown(Key.SPACE))
      stop();
  }

  
  @Override
  public float getScore() {
    return ((40 - Utils.MathUtils.clamp(Math.abs(step - hitLocation) - 2, 0, 20))) / 40f;

  }

  
}
