package medium;

import medium.objects.Form;
import medium.objects.media.ExcitableMedium;
import medium.objects.particles.Dust;
import medium.objects.interactables.Button;

public class Main {
    
    public static void main(String[] args) {
        Medium medium = new Medium(400, 400);
        Display display = new Display(medium, 2);

        Dust dust = new Dust("dust");
        medium.add(dust);
        dust.setPosition(200, 0);
        dust.setVelocity(-2, 4);
        dust.setSize(4, 4);
        /*
        Button button = new Button(50, 50, "lol");
        //medium.add(button);
        button.setPosition(100, 100);*/

        //ExcitableMedium excitable = new ExcitableMedium(medium.getSizeX(), medium.getSizeY(), "excitable");
        ExcitableMedium excitable = new ExcitableMedium(50, 50, "excitable");
        for (int i = -4; i < 5; i++) {
            for (int j = -4; j < 5; j++) {
                excitable.setValue(25 + i, 25 + j, 255);
            }
        }
        ExcitableMedium excitable2 = new ExcitableMedium(50, 50, "excitable2");
        for (int i = -1; i < 2; i++) {
            for (int j = -4; j < 5; j++) {
                excitable2.setValue(25 + i, 25 + j, 255);
            }
        }
        medium.add(excitable);
        medium.add(excitable2);

        excitable2.setPosition(50, 50);

        //excitable.setPosition(2, 2);

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
            if (Medium.EPOCH % 2 == 0)
                medium.update();

            if (Medium.EPOCH % 5 == 0) {
                Form excitable2 = Medium.getMedium().getSubForm("excitable2");
                excitable2.setPosition(excitable2.getX() + 1, excitable2.getY() + 1);
            }

            /*if (Medium.EPOCH == 10 && false) {
                Dust dust = new Dust("dust2");
                medium.add(dust);
                dust.setVelocity(-3, 4);
                dust.setPosition(200, 0);
                dust.setSize(4, 4);
            }*/

            long postLoop = System.currentTimeMillis();

            if (!pause) display.update();
            java.awt.Point location = display.getMousePosition();

            long postDisplay = System.currentTimeMillis();

            //System.out.println(Medium.EPOCH + ": display: " + (postDisplay - postLoop) + " loop: " + (postLoop - preTime));

            long latency = System.currentTimeMillis() - preTime;
            //System.out.println(latency);
            //if (latency > 0) {
                Thread.sleep(Math.max(0, 60 - (latency)));
            //}
        }
    }
}
