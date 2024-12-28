package entites;

import java.awt.Color;
import java.awt.Font;

import Librairies.Point;
import Librairies.StdDraw;

public class EarthCaster extends Tour {

    private static Color couleur = new Color(139,69,19);

    public EarthCaster(Point position) {
        super(position);
        this.PV=50;
        this.ATK=7;
        this.ATKSpeed=0.5;
        this.Range=2.5;
        this.element=Element.EARTH;
        this.Cost=100;
        this.MaxPV = PV;
        
    }

    public void afficheTour(int size) {
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.filledCircle(position.getX(), position.getY(), size / 2);
    }

    public static void afficheTourBoutique(int Money){

        if(Money<100){
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(923, 511, 64, 25);
        }else{
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(923, 511, 64, 25);
        }
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(923, 511, 64, 25);
        StdDraw.setPenColor(couleur);
        StdDraw.filledCircle(883, 511, 15);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font1 = new Font("Arial", Font.PLAIN, 17);
        StdDraw.setFont(font1);
        StdDraw.text(940, 521, "EarthCaster");
            

        Font font = new Font("Arial", Font.PLAIN, 10); // Arial, taille 20
        StdDraw.setFont(font);
        StdDraw.text(943, 501, "PV : 50    ATK : 7");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(883, 511, "100");

    }

    @Override
    public int getMaxPV() {
        return MaxPV;
    }
}
