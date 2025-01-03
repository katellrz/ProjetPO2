package entites;

import Librairies.Point;
import Librairies.StdDraw;
import java.awt.Color;
import java.awt.Font;

import Gestion.Joueur;

public class GoldDigger extends Tour {

    private static Color couleur = new Color(255, 215, 0);

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
        Font font1 = new Font("Arial", Font.PLAIN, 20);
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
        // GoldDigger génère de l'argent pour le joueur
        Joueur.gagnerArgent(1);
    }
}
