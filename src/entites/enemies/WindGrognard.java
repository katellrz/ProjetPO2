package entites.enemies;

import entites.Enemi;
import map.Point;

public class WindGrognard extends Enemi {
    private int resistanceAuVent;

    
    public WindGrognard(int PV, int ATK, int ATKSpeed, int Range, Element Element, int Speed, int Reward) {
        super(PV, ATK, ATKSpeed, Range, Element, Speed, Reward);
    }

    public int getResistanceAuVent() {
        return resistanceAuVent;
    }

    public void setResistanceAuVent(int resistanceAuVent) {
        this.resistanceAuVent = resistanceAuVent;
    }

    
    public void augmenterVitesse() {
        System.out.println("Augmenter la vitesse grâce à la résistance au vent !");
    }
}
