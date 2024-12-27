package entites;

import Librairies.Point;
import Librairies.StdDraw;

public class Archer extends Tour{

    public Archer(Point position){
        super(position);
        this.PV=30;
        this.ATK=5;
        this.ATKSpeed=1;
        this.Range=2;
        this.element=Element.NONE;
        this.Cost=20;       
    }

    @Override
    public int getMaxPV() {
        return MaxPV;
    }

    public static void afficheTourBoutique(int Money){

        if(Money>=20){
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(785, 571, 64, 25);
        }
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(785, 571, 64, 25);
        StdDraw.filledCircle(745, 571, 20);
        StdDraw.text(805, 581, "Archer");
        StdDraw.text(1400.1, 650, "20");


    }




}