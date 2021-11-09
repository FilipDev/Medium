package medium.objects.interactables;

import medium.objects.Form;

import java.awt.event.MouseEvent;

public abstract class ClickableForm extends Form {

    public ClickableForm(int sizeX, int sizeY, String id, Form superForm, Form... subForms) {
        super(sizeX, sizeY, id, superForm, subForms);
    }

    private boolean isClicked = false;

    public void setClicked(MouseEvent mouseEvent, boolean clicked) {
        if (clicked) {
            isClicked = true;
            onClick(mouseEvent);
        } else {
            isClicked = false;
        }
    }

    public boolean isClicked() {
        return isClicked;
    }

    public abstract void onClick(MouseEvent mouseEvent);
}
