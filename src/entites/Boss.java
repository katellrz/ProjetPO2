package entites;

public class Boss extends Enemi {
    
    public Boss() {
        this.PV = 150;
        this.ATK = 100;
        this.ATKSpeed = 10.;
        this.Range = 2;
        this.element = Element.FIRE;
        this.Speed = 0.5;
        this.Reward = 100;
        this.PVmax = PV;
    }

    @Override
    public int getMaxPV() {
        return PVmax;
    }   
}

