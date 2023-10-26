package Engine;


import GameObject.Rectangle;
import java.awt.Point;


/*
 * The game engine uses this class to start off the cascading Screen updating/drawing
 * The idea is an external class should be allowed to set its own Screen to this class's currentScreen variable,
 * and then that class can handle coordinating which Screen to show.
 */
public class ScreenManager {
    private Screen currentScreen;
    private static Rectangle screenBounds = new Rectangle(0, 0, 0, 0);

    public void initialize(Rectangle screenBounds) {
        ScreenManager.screenBounds = screenBounds;
        setCurrentScreen(new DefaultScreen());
    }

    public Screen getCurrentScreen() {
      return this.currentScreen;
    }

    // attach an external Screen class here for the ScreenManager to start calling its update/draw cycles
    public void setCurrentScreen(Screen screen) {
        screen.initialize();
        this.currentScreen = screen;
    }

    public void update() {
        currentScreen.update();
    }

    public static boolean mouseIsInBounds(Point p){
        if(p.x < screenBounds.getX() && p.x > 0){
            if(p.y < screenBounds.getY() && p.y > 0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    



    public void draw(GraphicsHandler graphicsHandler) {
        currentScreen.draw(graphicsHandler);
    }

    // gets width of currentScreen -- can be called from anywhere in an application
    public static int getScreenWidth() {
        return screenBounds.getWidth();
    }

    // gets height of currentScreen -- can be called from anywhere in an application
    public static int getScreenHeight() {
        return screenBounds.getHeight();
    }

    // gets bounds of currentScreen -- can be called from anywhere in an application
    public static Rectangle getScreenBounds() {
        return screenBounds;
    }

    public static void resize(int width, int height) {
        screenBounds.setHeight(height);
        screenBounds.setWidth(width);
    }
}
