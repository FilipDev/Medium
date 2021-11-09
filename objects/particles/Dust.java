package medium.objects.particles;

import medium.Medium;

public class Dust extends Particle {

    public Dust(String id) {
        super(id, Particle.Type.DUST);
        //setAcceleration(0, 1);
    }

    @Override
    public void rule() {
        //this.setAcceleration(0.01, 0.2);
        //setSize(getSizeX() + Medium.EPOCH % 4 - 2, getSizeY() + Medium.EPOCH % 4 - 2);
        super.rule();
    }

    @Override
    public String getName() {
        return "dust";
    }

    public void pop() {
        for (int x = 0; x < getSizeX(); x++) {
            for (int y = 0; y < getSizeY(); y++) {
                this.getSuperForm().add(new Dust(getID()));
            }
        }
    }
}
