package medium.objects;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import medium.Medium;

import java.awt.Color;

public abstract class Form {
    
    private int x, y, sizeX, sizeY;
    private Form superForm = null;
    private Set<Form> subForms = new HashSet<>();

    public Form(int sizeX, int sizeY, Form superForm, Form... subForms) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.output = new Color[sizeX][sizeY];
        this.superForm = superForm;

        Collections.addAll(this.subForms, subForms);
        for (Form form : subForms) {
            form.superForm = this;
        }
    }

    public void update() {
        for (Form subForm : this.subForms) {
            subForm.update();
        }

        this.rule();
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
                    output[x + subForm.x][y + subForm.y] = subForm.getOutput()[x][y];
                }
            }
        }

        /**
         *
         *
         * for (int x = 0; x < sizeX; x++) {
            for (int y = 0; x < sizeY; y++) {
                if (output[x][y] == null) output[x][y] = Medium.DEFAULT_COLOR;
            }
        }**/

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
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean setX(int x) {
        return set(x, y);
    }

    public boolean setY(int y) {
        return set(x, y);
    }

    public boolean set(int x, int y) {
        if (superForm.isWithin(x, y)) {
            this.x = x;
            this.y = y;
            updateSuper();
            return true;
        }
        return false;
    }

    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }

    protected void setSize(int x, int y) {
        this.sizeX = x;
        this.sizeY = y;
    }

    public void updateSuper() {
        superForm.check(this);
    }

    protected abstract void check(Form form);

    public boolean isWithin(Form form) {
        return isWithin(form.x, form.y)
                && isWithin(form.x  + form.sizeX, form.y)
                && isWithin(form.x,form.y + form.sizeY)
                && isWithin(form.x + form.sizeX, form.y + form.sizeY);
    }

    public boolean isWithin(int x, int y) {
        return x >= this.x
                && x <= this.x + this.sizeX
                && y >= this.y
                && y <= this.y + this.sizeY;
    }

    public abstract String getName();

    protected Set<Form> getSubForms() {
        return subForms;
    }

    public Form getSuperForm() {
        return superForm;
    }
}
