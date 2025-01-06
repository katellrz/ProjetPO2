package entites;

import Gestion.Joueur;
import Librairies.Point;
import Librairies.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import outils.Omnicient;

/**
 * La classe PoisonCaster represente une tour spécialisée dans l'attaque 
 * avec des capacités de poison , Cette tour peut attaquer des ennemis à 
 * portée et les empoisonner.
 */

public class PoisonCaster extends Tour {

    /**
     * La couleur utilisée pour représenter cette tour dans l'interface graphique.
     */
 private static Color couleur = new Color(242, 211, 0);

  /**
     * Constructeur de la classe PoisonCaster.
     *
     * @param position La position de la tour sur le terrain.
     */

    public PoisonCaster(Point position) {
        super(50, 1, 2, 5, Element.WIND, position, 80);
    }


    /**
     * Affiche la representation graphique de la tour dans la boutique.
     *
     * @param Money Le montant d'argent du joueur pour determiner si la tour 
     *              peut être achetée
     */

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
        Font font1 = new Font("Arial", Font.PLAIN, 15);
        StdDraw.setFont(font1);
        StdDraw.text(940, 401, "PoisonCaster");

        Font font = new Font("Arial", Font.PLAIN, 10);
        StdDraw.setFont(font);
        StdDraw.text(943, 381, "PV : 50    ATK : 1");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(883, 391, "80");
    }

     /**
     * cette methode Gere l'attaque de la tour ,Si un ennemi est à portée, il est attaqué 
     * et puis empoisonnée
     *
     * @param Joueur L'objet representant le joueur pour appliquer les effets de l'attaque
     */

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
                    System.out.println("Nouvel ennemi empoisoner : "+cible);

                }
            }
        }
    }

}
