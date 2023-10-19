package Engine;

import java.util.HashSet;

// This class can be used to keep track of "locked" and "unlocked" keys based on the class
// For example, it's often useful to "lock" a button if pressed down until its been released, since holding down a button will continually count as a "button press".
// This way, that "button press" will only be counted once per press.
// This class does NOT do anything to the buttonboard class to prevent a button from actually being detected -- that is not advisable as multiple classes may be detecting button presses separately
public class ButtonLocker {
    private HashSet<Integer> lockedButtons = new HashSet<>();

    // lock a button
    public void lockButton(int button) {
        lockedButtons.add(button);
    }

    // unlock a button
    public void unlockButton(int button) {
        lockedButtons.remove(button);
    }

    // check if a button is currently locked
    public boolean isButtonLocked(int button) {
        return lockedButtons.contains(button);
    }
}
