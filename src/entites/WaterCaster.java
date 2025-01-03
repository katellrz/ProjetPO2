package entites;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import Gestion.Joueur;
import Librairies.Point;
import Librairies.StdDraw;
import outils.Omnicient;

public class WaterCaster extends Tour {

    private static Color couleur = new Color(24, 108, 151);

    public WaterCaster(Point position) {
        super(30, 3, 1, 4, Element.WATER, position, 50);
        this.PV=30;
        this.ATK=3;
        this.ATKSpeed=1;
        this.Range=4;
        this.element=Element.WATER;
        this.Cost=50;

    }

    @Override
    public Color getColor() {
        return couleur;
    }

    public static void afficheTourBoutique(int Money){

        if(Money<50){
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(785, 511, 64, 25);
        }else{
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(785, 511, 64, 25);
        }
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(785, 511, 64, 25);
        StdDraw.setPenColor(couleur);
        StdDraw.filledCircle(745, 511, 15);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font1 = new Font("Arial", Font.PLAIN, 17);
        StdDraw.setFont(font1);
        StdDraw.text(805, 521, "WaterCaster");
            

        Font font = new Font("Arial", Font.PLAIN, 10); // Arial, taille 20
        StdDraw.setFont(font);
        StdDraw.text(805, 501, "PV : 30    ATK : 3");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(745, 511, "50");

    }

    @Override
    public void attaquer(Joueur Joueur) {
        if (peutAttaquer()) {
            List<Enemi> cibles = MonstreAportee(Omnicient.getPositionMonstre(), this.Range);
            if (cibles == null || !cibles.isEmpty()) {
                Enemi cible = PlusAvancer(cibles);
                if (cible != null) {
                    attaqueSimple(cible, Joueur);
                    afficheattaque(cible);
                }
            }
        }
    }

    


}
