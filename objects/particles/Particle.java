package medium.objects.particles;

import medium.Medium;
import medium.misc.Location;
import medium.objects.Form;

import java.awt.Color;

public abstract class Particle extends Form {
    
    public Particle(String id, Type type) {
        this(id, null, type);
    }

    public Particle(String id, Form superForm, Type type) {
        super(1, 1, id, superForm);
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
    private Location previousLocation = new Location(0, 0);

    public Type getType() {
        return type;
    }

    @Override
    public Color[][] getOutput() {
        return color;
    }

    @Override
    public boolean setPosition(double x, double y) {
        if (!Medium.getMedium().doesCollide(this, (int) x, (int) y)) {
            this.previousLocation = getLocation();
            return super.setPosition(x, y);
        }
        return false;
    }

    public Location getFarthestOpen(double velX, double velY) {
        double rate = velY / velX;
        double d = Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
        for (double i = Math.ceil(d); i >= 0; i -= 0.5) {
            double y = (rate * i + this.getY()) * (velY < 0 ? -1 : 1);
            double x = (i + this.getX()) * (velX < 0 ? -1 : 1);
            if (!Medium.getMedium().doesCollide(this, (int) x, (int) y)) {
                return new Location(x, y);
            }
        }
        return new Location(getX(), getY());
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
        boolean b = setPosition(getX() + dx, getY() + dy);
        if (!b) {
            boolean bounceX = Medium.getMedium().doesCollide(this, (int) (getX() + dx), getY());
            boolean bounceY = Medium.getMedium().doesCollide(this, getX(), (int) (getY() + dy));
            System.out.println(bounceY);
            Location farthestOpen = getFarthestOpen(this.dx, this.dy);
            setPosition(farthestOpen);
            System.out.println(farthestOpen);
            this.dx *= (bounceX ? -1 : 1);
            this.dy *= (bounceY ? -1 : 1);
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
