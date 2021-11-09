package medium.objects.media;

import medium.objects.Form;

import java.awt.*;

public class ExcitableMedium extends Form {

    private float[][] values;
    private Color[][] colors;

    public ExcitableMedium(int sizeX, int sizeY, String id) {
        super(sizeX, sizeY, id, null);

        values = new float[sizeX][sizeY];
        colors = new Color[sizeX][sizeY];
    }

    public void setValue(int x, int y, float value) {
        this.values[x][y] = value;
    }

    @Override
    public void rule() {
        float[][] newValues = new float[getSizeX()][getSizeY()];
        for (int x = 0; x < getSizeX(); x++) {
            for (int y = 0; y < getSizeY(); y++) {
                float value = values[x][y];
                for (int xi = -1; xi < 2; xi++) {
                    for (int yi = -1; yi < 2; yi++) {
                            if (isWithin(x + xi + getX(), y + yi + getY())) {
                                if (!(xi == 0 && yi == 0)) {
                                    newValues[x + xi][y + yi] += value / 8.0F;
                                }
                            }
                    }
                }
                //newValues[x][y] = value / 8.0F;
            }
        }
        values = newValues;
        for (int x = 0; x < getSizeX(); x++) {
            for (int y = 0; y < getSizeY(); y++) {
                colors[x][y] = Color.getHSBColor(0, 0, values[x][y] * 255.0F);
            }
        }
    }

    @Override
    public String getName() {
        return "excitable_medium";
    }

    @Override
    protected void check(Form form) {

    }

    @Override
    public Color[][] getOutput() {
        return colors;
    }

    public Color getColor(float value) {
        return Color.BLACK;
    }
}
