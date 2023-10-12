package ui.Textbox;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import ui.Container.CenterContainer;
import ui.Container.UIContainer;
import ui.SpriteFont.SpriteFont;

// Represents the game's textbox
// will display the text it is given to its textQueue
// each String in the textQueue will be displayed in the textbox, and hitting the interact key will cycle between additional Strings in the queue
// use the newline character in a String in the textQueue to break the text up into a second line if needed
public class Textbox extends UIContainer {
    protected boolean isActive;

    private Queue<String> textQueue = new LinkedList<String>();
    private SpriteFont text;
    private KeyLocker keyLocker = new KeyLocker();
    private Key interactKey = Key.SPACE;
    private Key interactKey2 = Key.UP;
    private Key interactKey3 = Key.DOWN;

    private int marginX = 20;

    private CenterContainer centerContainer;

    public Textbox(int x, int y, int width, int height) {
        super(x, y, width, height);
        centerContainer = new CenterContainer();
        centerContainer.setfillType(FillType.FILL_PARENT);
        text = new SpriteFont("", 0, 0, "Arial", 30, Color.black);
        text.setDrawParsedLines(true);
        centerContainer.addComponent(text);
        addComponent(centerContainer);
    }

    public void addText(String text) {
        if (textQueue.isEmpty()) {
            keyLocker.lockKey(interactKey);
        }
        textQueue.add(text);
    }

    public void addText(String[] text) {
        if (textQueue.isEmpty()) {
            keyLocker.lockKey(interactKey);
        }
        for (String textItem : text) {
            textQueue.add(textItem);
        }
    }

    // returns whether the textQueue is out of items to display or not
    // useful for scripts to know when to complete
    public boolean isTextQueueEmpty() {
        return textQueue.isEmpty();
    }

    public void update() {
        // if textQueue has more text to display and the interact key button was pressed
        // previously, display new text
        if (!textQueue.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
            String next = textQueue.peek();

            // if camera is at bottom of screen, text is drawn at top of screen instead of
            // the bottom like usual
            // to prevent it from covering the player
            text.setText(next);

        }
        // if interact key is pressed, remove the current text from the queue to prepare
        // for the next text item to be displayed
        if (Keyboard.isKeyDown(interactKey) && !keyLocker.isKeyLocked(interactKey)) {
            keyLocker.lockKey(interactKey);
            textQueue.poll();
        } else if (Keyboard.isKeyUp(interactKey)) {
            keyLocker.unlockKey(interactKey);
        }

        super.update();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        // if camera is at bottom of screen, textbox is drawn at top of screen instead
        // of the bottom like usual
        // to prevent it from covering the player
        graphicsHandler.drawFilledRectangleWithBorder(getXAbs() + marginX, getYAbs(), getWidth() - 2*marginX, getHeight(), Color.white,
        Color.black, 2);
        if (!text.getText().isBlank()) {
            centerContainer.draw(graphicsHandler);
        }
        
    }

    public boolean isActive() {
        // System.out.println("Ais " + this.isActive);
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
        System.out.println(this.isActive);
    }

    public void setInteractKey(Key interactKey) {
        this.interactKey = interactKey;
    }
    public void setInteractKey2(Key interactKey2) {
        this.interactKey2 = interactKey2;
    }
    public void setInteractKey3(Key interactKey3) {
        this.interactKey3 = interactKey3;
    }

}
