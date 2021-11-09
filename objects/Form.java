package medium.objects;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import medium.Medium;
import medium.misc.Location;

import java.awt.Color;

public abstract class Form {
    
    private Location location = new Location(0, 0);
    private int sizeX, sizeY;
    private String id;
    private Form superForm = null;
    private final Set<Form> subForms = new HashSet<>();
    private final Set<Form> subFormsToRemove = new HashSet<>();

    public Form(int sizeX, int sizeY, String id, Form superForm, Form... subForms) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.output = new Color[sizeX][sizeY];
        this.superForm = superForm;
        this.id = id;

        Collections.addAll(this.subForms, subForms);
        for (Form form : subForms) {
            form.superForm = this;
        }
    }

    public void update() {
        for (Form subForm : this.subForms) {
            subForm.update();
        }
        for (Form subForm : subFormsToRemove) {
            subForms.remove(subForm);
        }

        this.rule();
    }

    public void removeSubForm(Form form) {
        this.subFormsToRemove.add(form);
    }

    public abstract void rule();

    public boolean isPartOf(Form form) {
        return superForm.subForms.contains(form);
    }

    private Color[][] output;
    private int precalcEpoch = 0;

    public Color[][] getOutput() {
        if (precalcEpoch == Medium.EPOCH) {
            return output;
        }

        output = new Color[sizeX][sizeY];

        for (Form subForm : subForms) {
            for (int x = 0; x < subForm.sizeX; x++) {
                for (int y = 0; y < subForm.sizeY; y++) {
                    output[x + subForm.getX()][y + subForm.getY()] = subForm.getOutput()[x][y];
                }
            }
        }

        precalcEpoch = Medium.EPOCH;

        return output;
    }

    public void add(Form form) {
        this.subForms.add(form);
        form.superForm = this;
    }
    
    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getX() {
        return location.getX();
    }

    public int getY() {
        return location.getY();
    }

    public boolean setX(int x) {
        return setPosition(x, getY());
    }

    public boolean setX(double x) {
        return setPosition(x, getY());
    }

    public boolean setY(double y) {
        return setPosition(getX(), y);
    }

    public boolean setY(int y) {
        return setPosition(getX(), y);
    }

    public Location getLocation() {
        return location;
    }

    public boolean setPosition(int x, int y) {
        return this.setPosition((double) x, (double) y);
    }

    public boolean setPosition(double x, double y) {
        if (superForm.isWithin((int) x, (int) y)) {
            this.location = new Location(x, y);
            updateSuper();
            return true;
        }
        return false;
    }

    public boolean setPosition(Location loc) {
        return setPosition(loc.getX(), loc.getY());
    }

    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }

    protected void setSize(int x, int y) {
        if (superForm.isWithin(this.getX(), this.getY(), x, y)) {
            this.sizeX = x;
            this.sizeY = y;
        }
    }

    public void updateSuper() {
        superForm.check(this);
    }

    protected void check(Form form) {}

    public boolean isWithin(Form form) {
        return isWithin(form.getX(), form.getY())
                && isWithin(form.getX()  + form.sizeX, form.getY())
                && isWithin(form.getX(),form.getY() + form.sizeY)
                && isWithin(form.getX() + form.sizeX, form.getY() + form.sizeY);
    }


    public boolean isWithin(int x, int y, int sizeX, int sizeY) {
        return isWithin(x, y)
                && isWithin(x  + sizeX, y)
                && isWithin(x,y + sizeY)
                && isWithin(x + sizeX, y + sizeY);
    }

    public boolean isWithin(int x, int y) {
        return x >= this.getX()
                && x <= this.getY() + this.sizeX - 1
                && y >= this.getX()
                && y <= this.getY() + this.sizeY - 1;
    }

    public Form getSubForm(String id) {
        for (Form subForm : this.subForms) {
            if (subForm.getID().equals(id)) {
                return subForm;
            }
        }
        return null;
    }

    public Form getBottomFormAt(int x, int y, Class<? extends Form>... bottomFormTypes) {
        Form target = null;
        if (getSubForms().size() == 0) {
            for (Class<? extends Form> bottomFormType : bottomFormTypes) {
                if (bottomFormType.isInstance(this)) {
                    if (isWithin(x, y)) {
                        target = this;
                    }
                }
            }
        } else {
            for (Form subForm : getSubForms()) {
                Form bottomForm = subForm.getBottomFormAt(x, y, bottomFormTypes);
                if (bottomForm != null) {
                    target = bottomForm;
                }
            }
        }
        return target;
    }

    public abstract String getName();

    public String getID() {
        return this.id;
    }

    protected Set<Form> getSubForms() {
        return subForms;
    }

    public Form getSuperForm() {
        return superForm;
    }
}
