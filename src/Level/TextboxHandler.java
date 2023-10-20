package Level;

import Engine.GraphicsHandler;
import ui.Container.Anchor;
import ui.Container.PositioningContainer;
import ui.Container.UIContainer.FillType;
import ui.Textbox.Textbox;

public class TextboxHandler {
  

  protected PositioningContainer positioningContainer;
  protected Textbox textbox;
  private Map map;

  public TextboxHandler(Map map) {
    this.map = map;
    positioningContainer = new PositioningContainer(Anchor.BOTTOM_CENTER);
    positioningContainer.setAnchorChildren(true);
    textbox = new Textbox(0, 0, 750, 100);

    positioningContainer.addComponent(textbox);
    positioningContainer.setfillType(FillType.FILL_SCREEN);
  }

  public void update() {
    positioningContainer.update();

    if(this.map.getCamera().isAtBottomOfMap()) {
      positioningContainer.setDrawAnchor(Anchor.TOP_CENTER);
    } else {
      positioningContainer.setDrawAnchor(Anchor.BOTTOM_CENTER);
    }
  }

  public void draw(GraphicsHandler g) {
    positioningContainer.draw(g);
  }

  public Textbox getTextbox() {
    return this.textbox;
  }
}