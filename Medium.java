package medium;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import medium.objects.*;
import medium.objects.interactables.Button;
import medium.objects.interactables.ClickableForm;
import medium.objects.media.ExcitableMedium;
import medium.objects.particles.Particle;

public class Medium extends Form {

    public static final Color DEFAULT_COLOR = Color.BLACK;

    public static Medium medium;

    public static int EPOCH = 0;

    public static boolean flag = false;

    public static Medium getMedium() {
        return medium;
    }

    public Medium(int sizeX, int sizeY) {
        super(sizeX, sizeY, "Medium", null);
        medium = this;
    }

    @Override
    public void rule() {
        EPOCH++;
    }

    @Override
    public void check(Form form) {
        if (!isWithin(form)) {
            //this.removeSubForm(form);
        }
    }

    public boolean doesCollide(Form form, int x, int y) {
        for (int xi = x; xi < x + form.getSizeX(); xi++) {
            for (int yi = y; yi < y + form.getSizeY(); yi++) {
                if (isWithin(xi, yi, form.getSizeX(), form.getSizeY())) {
                    if (xi == x || xi == x + getSizeX() || yi == y || yi == y + getSizeY()) {
                        Form bottomForm = getBottomFormAt(x, y, Particle.class, ExcitableMedium.class);
                        if (bottomForm != form) {
                            return bottomForm != null;
                        }
                    }
                } else {
                    return true;
                }
            }
        }
        /*Form bottomForm = getBottomFormAt(x, y, Particle.class);
        if (bottomForm != form) {
            //Form bottomForm2 = null;
            Form bottomForm2 = getBottomFormAt(x, y, ExcitableMedium.class);
            /*
            Form button = getSubForm("lol");
            if (button != null && !((Button) button).getFlag()) {
                System.out.println(((Button) button).getFlag());
                bottomForm2 = getBottomFormAt(x, y, medium.objects.interactables.Button.class);
            }*/
            //return !isWithin(x, y, form.getSizeX(), form.getSizeY()) || (bottomForm != null || bottomForm2 != null);

        //}
        return false;
    }

    private ClickableForm clickedForm;

    public void processClick(MouseEvent mouseEvent, boolean clicked) {
        if (clicked) {
            Point mousePosition = Display.display.getMousePosition();
            if (mousePosition == null) return;
            Form bottomForm = getBottomFormAt((int) mousePosition.getX(), (int) mousePosition.getY(), ClickableForm.class);
            ClickableForm clickableForm = ((ClickableForm) bottomForm);
            if (clickableForm != null ) {
                clickableForm.setClicked(mouseEvent, true);
                clickedForm = clickableForm;
            }
        } else {
            if (clickedForm != null) clickedForm.setClicked(mouseEvent, false);
            clickedForm = null;
        }
    }

    @Override
    public Color[][] getOutput() {
        return super.getOutput();
    }

    @Override
    public String getName() {
        return "medium";
    }
}
