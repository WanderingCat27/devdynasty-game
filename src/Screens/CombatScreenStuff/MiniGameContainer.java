package Screens.CombatScreenStuff;

import ui.Container.Anchor;
import ui.Container.PositioningContainer;

public abstract class MiniGameContainer extends PositioningContainer {

  protected boolean isStopped;
  protected long secStop;

  public MiniGameContainer() {
    super(Anchor.BOTTOM_LEFT);
    this.setAnchorChildren(true);
    this.setfillType(FillType.FILL_SCREEN);

  }

  public abstract void start();

  public abstract void setChildrenHeight(int height);

  public abstract float getScore();


  protected void stop() {
    if (isStopped)
      return;
    isStopped = true;
    secStop = (long) (System.currentTimeMillis() / 1000);
  }

  public boolean isGameAwaitingFinish() {
    return isStopped && !isGameOver();
  }

  public boolean isGameOver() {
    // isStopped and some delay so you can see where you got it
    return isStopped && ((System.currentTimeMillis() / 1000) - 1) > secStop;
  }

}
