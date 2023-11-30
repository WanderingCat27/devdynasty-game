package Engine;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Utils.ImageUtils;
import Utils.Point;

import javax.swing.*;

/*
 * The JFrame that holds the GamePanel
 * Just does some setup and exposes the gamePanel's screenManager to allow an external class to setup their own content and attach it to this engine.
 */
public class GameWindow {
  private static JFrame gameWindow;
  private GamePanel gamePanel;

  public GameWindow() {
    gameWindow = new JFrame("Game");
    gamePanel = new GamePanel();
    gamePanel.setFocusable(true);
    gamePanel.requestFocusInWindow();
    gameWindow.setContentPane(gamePanel);
    gameWindow.setResizable(true);
    gameWindow.setSize(Config.GAME_WINDOW_WIDTH, Config.GAME_WINDOW_HEIGHT);
    gameWindow.setLocationRelativeTo(null);
    gameWindow.setMinimumSize(new Dimension(Config.GAME_WINDOW_WIDTH, Config.GAME_WINDOW_HEIGHT));
    gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
    gameWindow.setVisible(true);
    MyWindowFocusListener listener = new MyWindowFocusListener();
    gameWindow.addWindowFocusListener(listener);
    gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // it'd be nice if this actually worked more than 1/3rd
    gameWindow.setTitle("Time Dynasties");

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image image = ImageUtils.scaleImage(ImageLoader.loadAllowTransparent("cursor.png"), 1.5f);
    Cursor c = toolkit.createCustomCursor(image, new java.awt.Point(gamePanel.getX(),
        gamePanel.getY()), "custom_cursor");
    gamePanel.setCursor(c);

    gamePanel.setupGame();
  }

  // triggers the game loop to start as defined in the GamePanel class
  public void startGame() {
    gamePanel.startGame();
  }

  public ScreenManager getScreenManager() {
    return gamePanel.getScreenManager();
  }

  public static JFrame getGameWindow() {
    return gameWindow;
  }
}
