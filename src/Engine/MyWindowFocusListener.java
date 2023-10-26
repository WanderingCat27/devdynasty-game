package Engine;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


public class MyWindowFocusListener implements WindowFocusListener {
    @Override
    public void windowGainedFocus(WindowEvent e) {
        // Your application window has gained focus (become active).
        System.out.println("Window gained focus");
        Mouse.mouseOnScreen = true;
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        // Your application window has lost focus (is not active).
        System.out.println("Window lost focus");
        Mouse.mouseOnScreen = false;
        
    }
}

