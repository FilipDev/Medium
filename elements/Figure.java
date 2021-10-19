package medium.elements;

import medium.objects.Form;

public class Figure extends Form {

    private Form[][] figure;

    public Figure(int sizeX, int sizeY, Form superForm, Form[][] subForms) {
        super(sizeX, sizeY, superForm, subForms);


    }

    @Override
    public void rule() {

    }

    @Override
    protected void check(Form form) {

    }

    @Override
    public String getName() {
        return null;
    }

}
