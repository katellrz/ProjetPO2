package entites;

import Librairies.Point;
import Librairies.StdDraw;
import java.awt.Font;

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

        if(Money<20){
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(785, 571, 64, 25);
        }else{
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(785, 571, 64, 25);
        }
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(785, 571, 64, 25);
        StdDraw.filledCircle(745, 571, 15);
        Font font1 = new Font("Arial", Font.PLAIN, 20);
        StdDraw.setFont(font1);
        StdDraw.text(805, 581, "Archer");
            

        Font font = new Font("Arial", Font.PLAIN, 10); // Arial, taille 20
        StdDraw.setFont(font);
        StdDraw.text(805, 561, "PV : 30    ATK : 5");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(745, 571, "20");

    }




}