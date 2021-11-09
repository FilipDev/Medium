package medium.misc;

public class Location {

    private final double x, y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public double getFineX() {
        return x;
    }

    public double getFineY() {
        return y;
    }

    public Location setX(double x) {
        return new Location(x, getFineY());
    }

    public Location setY(double x) {
        return new Location(x, getFineY());
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Location multiply(double m) {
        return new Location(x * m, y * m);
    }
}
