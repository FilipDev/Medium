package medium;

import medium.objects.Dust;

public class Main {
    
    public static void main(String[] args) {
        Medium medium = new Medium(400, 400);
        Display display = new Display(medium, 2);

        Dust dust = new Dust();
        medium.add(dust);
        dust.set(200, 0);
        dust.setSize(4, 4);

        try {
            loop(medium, display);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean pause = false;

    public static void loop(Medium medium, Display display) throws InterruptedException {
        while (true) {
            Medium.EPOCH++;
            long preTime = System.currentTimeMillis();

            medium.update();

            if (!pause) display.draw(display.getFrame());
            java.awt.Point location = display.getMousePosition();
           
            long latency = System.currentTimeMillis() - preTime;
            Thread.sleep(Math.max(0, 50 - (latency)));
        }
    }
}
