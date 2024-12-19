package entites.enemies;

import entites.Enemi;

public class FireGrognard extends Enemi {
    
    public FireGrognard() {
        this.PV = 1;
        this.ATK = 7;
        this.ATKSpeed = 2;
        this.Range = 3;
        this.element = Element.FIRE;
        this.Speed = 2;
        this.Reward = 1;
        this.PVmax = PV;
    }
}