package medium.objects;

import java.awt.Color;
import java.util.Arrays;

public abstract class Particle extends Form {
    
    public Particle(Type type) {
        this(null, type);
    }

    public Particle(Form superForm, Type type) {
        super(1, 1, superForm);
        this.type = type;
        this.color = new Color[getSizeX()][getSizeY()];
        for (int x = 0; x < getSizeX(); x++) {
            for (int y = 0; y < getSizeY(); y++) {
                this.color[x][y] = type.getColor();
            }
        }
    }

    private Type type;
    private Color[][] color;
    private double ddx = 0.0;
    private double ddy = 0.0;
    private double dx = 0.0;
    private double dy = 0.0;
    private double x = 0.0;
    private double y = 0.0;

    public Type getType() {
        return type;
    }

    @Override
    public Color[][] getOutput() {
        return color;
    }

    @Override
    public void check(Form form) {
    }

    @Override
    public boolean set(int x, int y) {
        return this.set((double) x, (double) y);
    }

    public boolean set(double x, double y) {
        if (super.set((int) x, (int) y)) {
            this.x = x;
            this.y = y;
            return true;
        }
        return false;
    }

    public void setVelocity(double velX, double velY) {
        this.dx = velX;
        this.dy = velY;
    }

    public void setAcceleration(double accX, double accY) {
        this.ddx = accX;
        this.ddy = accY;
    }

    /**
     * The default rule is to simply change the position and velocity.
     * Should be called at the end of methods that override it.
     */
    public void rule() {
        this.dx += this.ddx;
        this.dy += this.ddy;
        this.x += this.dx;
        this.y += this.dy;
        if (!set(this.x, this.y)) {
            this.ddx *= -1;
            this.ddy *= -1;
            this.dx *= -1;
            this.dy *= -1;
            this.x += this.dx;
            this.y += this.dy;
        }
    }

    @Override
    public void setSize(int x, int y) {
        super.setSize(x, y);
        this.color = new Color[getSizeX()][getSizeY()];
        for (int i = 0; i < getSizeX(); i++) {
            for (int j = 0; j < getSizeY(); j++) {
                this.color[i][j] = type.getColor();
            }
        }
    }

    @Override
    public int getX() {
        return (int) this.x;
    }

    @Override
    public int getY() {
        return (int) this.y;
    }

    public double getAccelerationY() {
        return ddy;
    }

    public double getVelocityY() {
        return dy;
    }

    public enum Type {
        DUST(45, 35, 71);

        private final Color color;

        private Type(int h, int s, int b) {
            this.color = Color.getHSBColor(h, s, b);
        }

        public Color getColor() {
            return color;
        }
    }
}
