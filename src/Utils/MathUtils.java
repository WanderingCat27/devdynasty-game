package Utils;

// class to store some longer than normal math logic for reusability purposes
public class MathUtils {
    public static float getRemainder(float number) {
        return number > 0 ? (float)Math.abs(number - Math.floor(number)) : (float)Math.abs(number - Math.ceil(number));
    }

    public static int clamp(int val, int min, int max) {
        return Math.min(max, Math.max(val, min));
    
    }

    public static boolean isInBounds(java.awt.Point p, int x, int y, int width, int height) {
        return p.x > x && p.x < x + width && p.y > y && p.y < y + height;
    }
}

