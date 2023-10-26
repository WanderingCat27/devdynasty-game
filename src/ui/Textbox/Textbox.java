package ui.Textbox;

import java.awt.Color;
import java.awt.Font;
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
  private CenterContainer centerContainer;

  public Textbox(int x, int y, int width, int height) {
    super(x, y, width, height);
    text = new SpriteFont("", 0, 0, new Font("Arial", Font.PLAIN, 30), Color.BLACK);
    text.setDrawParsedLines(true);
    text.setParentWidth(10);
    centerContainer = new CenterContainer();
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

  @Override
  public void update() {
    // if textQueue has more text to display and the interact key button was pressed
    // previously, display new text
    if ((!textQueue.isEmpty() && keyLocker.isKeyLocked(interactKey))
        || (!textQueue.isEmpty() && keyLocker.isKeyLocked(interactKey2))
        || (!textQueue.isEmpty() && keyLocker.isKeyLocked(interactKey3))) {
      String next = textQueue.peek();

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

    if (Keyboard.isKeyDown(interactKey2) && !keyLocker.isKeyLocked(interactKey2)) {
      keyLocker.lockKey(interactKey2);
      textQueue.poll();
    } else if (Keyboard.isKeyUp(interactKey2)) {
      keyLocker.unlockKey(interactKey2);
    }

    if (Keyboard.isKeyDown(interactKey3) && !keyLocker.isKeyLocked(interactKey3)) {
      keyLocker.lockKey(interactKey3);
      textQueue.poll();
    } else if (Keyboard.isKeyUp(interactKey3)) {
      keyLocker.unlockKey(interactKey3);
    }

  }

  @Override
  public void draw(GraphicsHandler graphicsHandler) {
    // if camera is at bottom of screen, textbox is drawn at top of screen instead
    // of the bottom like usual
    // to prevent it from covering the player
    if (isActive) {
      graphicsHandler.drawFilledRectangleWithBorder(getXAbs(), getYAbs(), getWidth(), getHeight(), Color.white,
          Color.black, 2);

      super.draw(graphicsHandler);
    }
  }

  public boolean isActive() {
    return isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
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

  public SpriteFont getSpriteFont() {
    return this.text;
  }

}
