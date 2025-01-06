package entites;

import Librairies.Point;
import Librairies.StdDraw;

import static outils.Omnicient.getPositionMonstre;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import Gestion.Joueur;

public class GoldDigger extends Tour {

    private static Color couleur = new Color(0, 167, 15);

    public GoldDigger(Point position) {
        super(20, 1, 2, 10, Element.EARTH, position, 20);
    }

    @Override
    public Color getColor() {
        return couleur;
    }

    public static void afficheTourBoutique(int Money) {
        if (Money < 20) {
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(785, 331, 64, 25);
        } else {
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(785, 331, 64, 25);
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(785, 331, 64, 25);
        StdDraw.setPenColor(couleur);
        StdDraw.filledCircle(745, 331, 15);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font1 = new Font("Arial", Font.PLAIN, 17);
        StdDraw.setFont(font1);
        StdDraw.text(805, 341, "GoldDigger");

        Font font = new Font("Arial", Font.PLAIN, 10);
        StdDraw.setFont(font);
        StdDraw.text(805, 321, "PV : 20    ATK : 1");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(745, 331, "20");
    }

    @Override
    public void attaquer(Joueur Joueur) {
        List<Enemi> monstres = getPositionMonstre();
        if (monstres == null || !monstres.isEmpty()) {
            List<Enemi> cibles = this.MonstreAportee(monstres, this.Range);
            if (cibles == null || !cibles.isEmpty()) {
                Enemi cible = this.PlusProche(cibles);
                if (cible != null) {
                    attaqueSimple(cible, Joueur);
                    afficheattaque(cible);
                    Joueur.gagnerArgent(1);
                }
            }
        }
        
    }
}
