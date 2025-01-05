package entites;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import Gestion.Joueur;
import Librairies.Point;
import Librairies.StdDraw;
import entites.Entite.Element;
import outils.Omnicient;

public class PoisonCaster extends Tour {

    private static Color couleur = new Color(242, 211, 0);;

    public PoisonCaster(Point position) {
        super(50, 1, 2, 5, Element.WIND, position, 80);
    }

    @Override
    public Color getColor() {
        return couleur;
    }

    public static void afficheTourBoutique(int Money) {
        if (Money < 70) {
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(923, 391, 64, 25);
        } else {
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(923, 391, 64, 25);
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(923, 391, 64, 25);
        StdDraw.setPenColor(couleur);
        StdDraw.filledCircle(883, 391, 15);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font1 = new Font("Arial", Font.PLAIN, 20);
        StdDraw.setFont(font1);
        StdDraw.text(940, 401, "PoisonCaster");

        Font font = new Font("Arial", Font.PLAIN, 10);
        StdDraw.setFont(font);
        StdDraw.text(943, 381, "PV : 50    ATK : 1");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(883, 391, "80");
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
                    //TODO Corriger Poison caster
                    Omnicient.SavetoOmni(new Empoisoner(cible));

                }
            }
        }
    }

}
