package entites;

import Librairies.Point;
import Librairies.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import Gestion.Joueur;
import outils.Omnicient;

public class IceCaster extends Tour {

    private static Color couleur = new Color(173, 216, 230);

    public IceCaster(Point position) {
        super(40, 1, 2, 5, Element.WATER, position, 70);
    }

    @Override
    public Color getColor() {
        return couleur;
    }

    public static void afficheTourBoutique(int Money) {
        if (Money < 70) {
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(785, 391, 64, 25);
        } else {
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(785, 391, 64, 25);
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(785, 391, 64, 25);
        StdDraw.setPenColor(couleur);
        StdDraw.filledCircle(745, 391, 15);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font1 = new Font("Arial", Font.PLAIN, 20);
        StdDraw.setFont(font1);
        StdDraw.text(805, 401, "IceCaster");

        Font font = new Font("Arial", Font.PLAIN, 10);
        StdDraw.setFont(font);
        StdDraw.text(805, 381, "PV : 40    ATK : 1");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(745, 391, "70");
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
