package medium.objects.other;

import medium.objects.Form;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Tracker extends Form {

    private double[] data;
    private int delay;

    public Tracker(int sizeX, int sizeY, Form superForm, Form... subForms) {
        super(sizeX, sizeY, "tracker", superForm, subForms);
    }

    public void setData(String values, String splitter, int delay) {
        String[] split = values.split("\n");
        for (int i = 0; i < split.length; i++) {
            data[i] = Integer.parseInt(split[i]);
        }
        this.delay = delay;
    }

    public void importData(File file, int frameRate) throws FileNotFoundException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
    }

    public int getInput() {
        return 0;
    }

    private long lastExecute = 0;
    private int iteration;

    @Override
    public void rule() {
        /*if (System.currentTimeMillis()) {
            this.setSize((int) data[iteration % data.length] * 2 + 1, data[iteration % data.length]);
            lastExecute = System.currentTimeMillis();
        }*/
    }

    @Override
    protected void check(Form form) {

    }

    @Override
    public String getName() {
        return null;
    }
}
