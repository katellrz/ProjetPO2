package entites;

import Librairies.Point;
import Librairies.StdDraw;
import outils.Omnicient;

import java.awt.Color;
import java.awt.Font;

import java.util.List;

public class Archer extends Tour{

    private static Color couleur = new Color(107, 106, 105);

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
    public Color getColor() {
        return couleur;
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
        StdDraw.setPenColor(couleur);
        StdDraw.filledCircle(745, 571, 15);
        StdDraw.setPenColor(StdDraw.BLACK);
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
    public void attaquer() {
        if (peutAttaquer()) {
            List<Enemi> cibles = MonstreAportee(Omnicient.getPositionMonstre(), this.Range);
            if (!cibles.isEmpty()) {
                Enemi cible = PlusAvancer(cibles);
                if (cible != null) {
                    attaqueSimple(cible);
                    afficheattaque(cible);
                }
            }
        }
    }


}