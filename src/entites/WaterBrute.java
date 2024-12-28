package entites;

public class WaterBrute extends Enemi {
    
    public WaterBrute() {
        this.PV = 30;
        this.ATK = 5;
        this.ATKSpeed = 1;
        this.Range = 3;
        this.element = Element.WATER;
        this.Speed = 1;
        this.Reward = 3;

    }

    @Override
    public int getMaxPV() {
        return PVmax;
    }  
}
