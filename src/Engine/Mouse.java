package Engine;

import java.awt.Point;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.event.MouseInputListener;


public class Mouse {
    public static final int LEFT_MOUSE_BUTTON = 1;
    public static final int RIGHT_MOUSE_BUTTON = 2;
    public static final int MIDDLE_MOUSE_BUTTON = 3; 
    
    private static Point mouseLocation;

    private static final HashMap<Integer, Boolean> buttonDown = new HashMap<>();

    private static final MouseInputListener mouseListener = new MouseInputListener() {
        @Override
        public void mouseMoved(java.awt.event.MouseEvent e) {
            mouseLocation = e.getPoint();
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            buttonDown.put(e.getButton(), true);
        }
        
        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
            buttonDown.put(e.getButton(), false);
        }
        
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {}

        

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {}

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {}

        @Override
        public void mouseDragged(java.awt.event.MouseEvent e) {}

    };

    // prevents instantiation of this class
    private Mouse() {}

    public static Point getMouseLoction() {
        if (mouseLocation == null) return new Point(0, 0);
        else return mouseLocation;
    }

    // returns the MouseListener of this class
    public static MouseListener getMouseListener() {
        return mouseListener;
    }

    // returns if a mouse button is currently being pressed
    public static boolean isButtonDown(int button) {
        return buttonDown.getOrDefault(button, false);
    }

    // returns if a mouse button is currently not being pressed
    public static boolean isButtonUp(int button) {
        return !isButtonDown(button);
    }


}
