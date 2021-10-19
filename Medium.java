package medium;

import java.awt.Color;

import medium.objects.*;

public class Medium extends Form {

    public static final Color DEFAULT_COLOR = Color.BLACK;

    public static Medium medium;

    public static int EPOCH = 0;

    public static Medium getMedium() {
        return medium;
    }

    public Medium(int sizeX, int sizeY) {
        super(sizeX, sizeY, null);
        medium = this;
    }

    @Override
    public void rule() {
        EPOCH++;

        /*for (int x = 0; x < this.getSizeX(); x++) {
            for (int y = 0; y < this.getSizeY(); y++) {
                this.getOutput()[x][y] =
            }
        }*/
    }

    @Override
    public void check(Form form) {
        if (!isWithin(form)) {
            this.getSubForms().remove(form);
        }
    }

    @Override
    public Color[][] getOutput() {
        return super.getOutput();
    }

    @Override
    public String getName() {
        return "Medium";
    }
}
