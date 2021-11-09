package medium.misc;

import java.awt.*;

public class OutputTools {

    public static Color[][] fillBrightness(Color[][] in, double value) {
        for (int x = 0; x < in.length; x++) {
            for (int y = 0; y < in[0].length; y++) {
                in[x][y] = new Color((int) (value * 255.0), (int) (value * 255.0), (int) (value * 255.0));
            }
        }
        return in;
    }

}
