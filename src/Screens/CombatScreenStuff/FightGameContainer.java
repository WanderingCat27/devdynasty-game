package Screens.CombatScreenStuff;

import java.awt.Color;
import java.awt.Font;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import Engine.Config;
import Engine.GraphicsHandler;
import Engine.Key;
import Engine.Keyboard;
import Engine.Screen;
import Engine.ScreenManager;
import ui.Button.TextButton;
import ui.Container.Anchor;
import ui.Container.CenterContainer;
import ui.Container.PositioningContainer;
import ui.SpriteUI.SolidSpriteUI;

public class FightGameContainer extends PositioningContainer {

  TextButton stopButton;
  SolidSpriteUI bar, greenCenter, perfectCenter, ticker;

  float speed = 10f;
  boolean isStopped = false;
  long secStop;
  float step;

  public FightGameContainer(int height) {
    super(Anchor.BOTTOM_LEFT);
    this.setAnchorChildren(true);
    this.setfillType(FillType.FILL_SCREEN);

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
    greenCenter = new SolidSpriteUI(0, 0, 200, height, Color.ORANGE) {
      @Override
      public int getWidth() {
        return (int) (((float) ScreenManager.getScreenWidth() / Config.GAME_WINDOW_WIDTH) * 200);

      }
    };
    greenCenter.setfillTypeY(FillType.FILL_PARENT);

    perfectCenter = new SolidSpriteUI(0, 0, 200, height, Color.GREEN) {
      @Override
      public int getWidth() {
        return (int) (((float) ScreenManager.getScreenWidth() / Config.GAME_WINDOW_WIDTH) * 30);

      }
    };
    perfectCenter.setfillTypeY(FillType.FILL_PARENT);

    centerContainer.addComponent(greenCenter);
    centerContainer.addComponent(perfectCenter);
    bar.addComponent(centerContainer);
    bar.addComponent(ticker);

    addComponent(bar);
    addComponent(stopButton);

  }

  public void setChildrenHeight(int height) {
    bar.setHeight(height);
    stopButton.setHeight(height);
  }

  public void start() {
    isStopped = false;
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

  private void stop() {
    if (isStopped)
      return;
    isStopped = true;
    secStop = (long) (System.currentTimeMillis() / 1000);
  }

  public float getScore() {
    return ((40 - Utils.MathUtils.clamp(Math.abs(step - 50) - 1, 0, 20))) / 40f;

  }

  public boolean isGameOver() {
    // isStopped and some delay so you can see where you got it
    return isStopped && ((System.currentTimeMillis() / 1000) - 1) > secStop;
  }


}
