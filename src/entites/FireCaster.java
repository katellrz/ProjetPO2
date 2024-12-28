package entites;

import java.awt.Font;
import java.awt.Color;

import Librairies.Point;
import Librairies.StdDraw;

public class FireCaster extends Tour{

    private static Color couleur = new Color(255, 142, 97);

    public FireCaster(Point position){
        super(position);
        this.PV=30;
        this.ATK=10;
        this.ATKSpeed=0.5;
        this.Range=2.5;
        this.element=Element.FIRE;
        this.Cost=100;       
    }

    @Override
    public int getMaxPV() {
        return MaxPV;
    }

    @Override
    public Color getColor() {
        return couleur;
    }

    public static void afficheTourBoutique(int Money){

        if(Money<100){
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(785, 451, 64, 25);
        }else{
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(785, 451, 64, 25);
        }
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(785, 451, 64, 25);
        StdDraw.setPenColor(couleur);
        StdDraw.filledCircle(745, 451, 15);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font1 = new Font("Arial", Font.PLAIN, 17);
        StdDraw.setFont(font1);
        StdDraw.text(805, 461, "FireCaster");
            

        Font font = new Font("Arial", Font.PLAIN, 10); // Arial, taille 20
        StdDraw.setFont(font);
        StdDraw.text(805, 441, "PV : 30    ATK : 10");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(745, 451, "100");

    }


}
