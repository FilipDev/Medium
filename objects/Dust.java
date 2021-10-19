package medium.objects;

import medium.Medium;

public class Dust extends Particle {

    public Dust() {
        super(Particle.Type.DUST);
    }

    @Override
    public void rule() {
        this.setAcceleration(0, 2);
        super.rule();
    }

    @Override
    public String getName() {
        return "Dust";
    }

    public void pop() {
        for (int x = 0; x < getSizeX(); x++) {
            for (int y = 0; y < getSizeY(); y++) {
                this.getSuperForm().add(new Dust());
            }
        }
    }
}
