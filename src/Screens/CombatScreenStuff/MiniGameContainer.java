package Screens.CombatScreenStuff;

import java.awt.Color;

import Engine.GraphicsHandler;
import Engine.Key;
import ui.Container.Anchor;
import ui.Container.PositioningContainer;
import ui.Textbox.Textbox;

public abstract class MiniGameContainer extends PositioningContainer {

  protected boolean isStopped;
  protected long secStop;
  protected String[] tutorialStrings;
  protected boolean isTutorialOver = false;

  private Textbox tutorialTextbox;
  private PositioningContainer bottom;

  public MiniGameContainer(String... tutorialStrings) {
    super(Anchor.BOTTOM_LEFT);
    this.setAnchorChildren(true);
    this.setfillType(FillType.FILL_SCREEN);
    this.tutorialStrings = tutorialStrings;

    tutorialTextbox = new Textbox(0, 0, 0, 0) {
      @Override
      public int getHeight() {
        return textboxHeight();
      }
    };
    tutorialTextbox.setfillTypeX(FillType.FILL_SCREEN);
    bottom = new PositioningContainer(Anchor.BOTTOM_LEFT);
    bottom.setAnchorChildren(true);
    bottom.setfillType(FillType.FILL_SCREEN);
    bottom.addComponent(tutorialTextbox);

  }

  public void init() {
    if (!hasCompletedTutorial()) {
      System.out.println("starting tutorial");
      tutorialTextbox.addText("Press 'enter' to continue");
      tutorialTextbox.addText(tutorialStrings);
      // replay tutorials message
      tutorialTextbox.addText(
          "To view tutorial again press escape click 'replay tutorials', \nthen restart fight \n(to restart click run then speak to npc again)");
      tutorialTextbox.setIsActive(true);
      tutorialTextbox.setInteractKey(Key.ENTER);
      tutorialTextbox.getSpriteFont().setFontSize(32);
      tutorialTextbox.next();
    } else {
      isTutorialOver = true;
      start();
    }

  }

  public abstract boolean hasCompletedTutorial();

  protected abstract void start();

  public abstract void setChildrenHeight(int height);

  public abstract int textboxHeight();

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

  @Override
  public void update() {
    if (!isTutorialOver) {
      bottom.update();
      if (tutorialTextbox.isTextQueueEmpty()) {
        System.out.println("ended tutorial");
        isTutorialOver = true;
        start();
      }

    } else
      super.update();

  }

  @Override
  public void draw(GraphicsHandler g) {
    if (!isTutorialOver) {
      bottom.draw(g);
    } else
      super.draw(g);
  }
}
