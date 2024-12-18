package entites.enemies;

import entites.Enemi;
import map.Point;


public class Boss extends Enemi {
    private String field;

    // Constructeur
    public Boss(int PV, int ATK, int ATKSpeed, int Range, Element Element, double Speed, int Reward) {
        super(PV, ATK, ATKSpeed, Range, Element, Speed, Reward);
    }

    // Getter et setter
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

}