package entites;

import java.awt.Color;
import java.awt.Font;

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

    public static void afficheTourBoutique(int Money){

        if(Money<50){
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(923, 571, 64, 25);
        }else{
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(923, 571, 64, 25);
        }
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(923, 571, 64, 25);
        StdDraw.filledCircle(883, 571, 15);
        Font font1 = new Font("Arial", Font.PLAIN, 20);
        StdDraw.setFont(font1);
        StdDraw.text(805, 581, "Archer");
            

        Font font = new Font("Arial", Font.PLAIN, 10); // Arial, taille 20
        StdDraw.setFont(font);
        StdDraw.text(805, 561, "PV : 30    ATK : 5");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(745, 571, "20");

    }

    @Override
    public int getMaxPV() {
        return MaxPV;
    }



}
