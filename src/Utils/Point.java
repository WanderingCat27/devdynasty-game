package Utils;

// Represents a Point on a 2D plane, has some "point math" methods
public class Point {
    public final float x;
    public final float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point add(Point point) {
        return new Point(x + point.x, y + point.y);
    }

    public Point addX(int x) {
        return new Point(this.x + x, this.y);
    }

    public Point addY(int y) {
        return new Point(this.x, this.y + y);
    }

    public Point subtract(Point point) {
        return new Point(x - point.x, y - point.y);
    }

    public Point subtractX(int x) {
        return new Point(this.x - x, this.y);
    }

    public Point subtractY(float y) {
        return new Point(this.x, this.y - y);
    }

    public int getX() { return (int) x; }
    public int getY() { return (int) y; }

    public String toString() { return String.format("(%s, %s)", this.x, this.y); }
}
