package Level;

import Engine.GraphicsHandler;
import Engine.Key;
import ui.Anchor;
import ui.Container.PositioningContainer;
import ui.Container.UIContainer.FillType;
import ui.Textbox.Textbox;

// Represents the game's textbox
// will display the text it is given to its textQueue
// each String in the textQueue will be displayed in the textbox, and hitting the interact key will cycle between additional Strings in the queue
// use the newline character in a String in the textQueue to break the text up into a second line if needed
public class MapTextboxHandler {
    private Map map;

    private PositioningContainer positioningContainer;

    private Textbox textbox;

    public MapTextboxHandler(Map map) {
        this.textbox = new Textbox(0, 0, 800, 100);
        this.positioningContainer = new PositioningContainer(Anchor.BOTTOM_CENTER);
        this.positioningContainer.setfillType(FillType.FILL_SCREEN);
        this.positioningContainer.setAnchorChildren(true);
        this.positioningContainer.addComponent(textbox);
        this.map = map;
    }

    public Textbox getTextbox() {
        return textbox;
    }

    public void update() {
        // if camera at bottom of map draw from top of screen to prevent covering player
        if (!map.getCamera().isAtBottomOfMap()) {
            this.positioningContainer.setDrawPosition(Anchor.BOTTOM_CENTER);
        } else {
            this.positioningContainer.setDrawPosition(Anchor.TOP_CENTER);
        }

        this.positioningContainer.update();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        this.positioningContainer.draw(graphicsHandler);
    }

    // map textbox methods to this class for ease of use
    // positioning textbox so cannot extend textbox cause drawing from a container

    public void addText(String text) {
        textbox.addText(text);
    }

    public void addText(String[] text) {
        textbox.addText(text);
    }

    public boolean isTextQueueEmpty() {
        return textbox.isTextQueueEmpty();
    }

    public boolean isActive() {
        return textbox.isActive();
    }

    public void setIsActive(boolean isActive) {
        textbox.setIsActive(isActive);
    }

    public void setInteractKey(Key interactKey) {
        textbox.setInteractKey(interactKey);
    }

}
