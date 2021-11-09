package medium.objects.interactables;

import medium.Display;
import medium.Medium;
import medium.misc.OutputTools;
import medium.objects.Form;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Button extends ClickableForm {

    private boolean hovering = false;
    private boolean flag = false;

    public Button(int sizeX, int sizeY, String id) {
        super(sizeX, sizeY, id, null);
    }

    @Override
    public void rule() {
        Point mousePosition = Display.display.getMousePosition();
        hovering = mousePosition != null && isWithin(mousePosition.x, mousePosition.y);
    }

    @Override
    protected void check(Form form) {

    }

    @Override
    public String getName() {
        return "button";
    }

    @Override
    public Color[][] getOutput() {
        if (isClicked()) {
            return OutputTools.fillBrightness(new Color[getSizeX()][getSizeY()], 1.0);
        } else {
            return OutputTools.fillBrightness(new Color[getSizeX()][getSizeY()], hovering ? 0.7 : 0.2);
        }
    }

    @Override
    public void onClick(MouseEvent mouseEvent) {
        flag = !flag;
    }

    public boolean getFlag() {
        return flag;
    }
}
