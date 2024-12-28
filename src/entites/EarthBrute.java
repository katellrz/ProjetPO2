package entites;

public class EarthBrute extends Enemi {
    
    public EarthBrute() {
        this.PV = 30;
        this.ATK = 5;
        this.ATKSpeed = 1;
        this.Range = 3;
        this.element = Element.EARTH;
        this.Speed = 1;
        this.Reward = 3;
        this.PVmax = PV;
    }

    @Override
    public int getMaxPV() {
        return PVmax;
    }  
}