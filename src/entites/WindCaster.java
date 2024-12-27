package entites;

import java.awt.Color;

import Librairies.Point;
import Librairies.StdDraw;

public class WindCaster extends Tour {
    public WindCaster(Point position) {
        super(position);
        this.PV=30;
        this.ATK=5;
        this.ATKSpeed=1.5;
        this.Range=6;
        this.element=Element.WIND;
        this.Cost=50;
        this.MaxPV = PV;
    }

    public void afficheTour(int size) {
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.filledCircle(position.getX(), position.getY(), size / 2);
    }



}
