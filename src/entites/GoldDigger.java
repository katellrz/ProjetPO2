package entites;

import Gestion.Joueur;
import Librairies.Point;
import Librairies.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import static outils.Omnicient.getPositionMonstre;

/**
 * cette Classe represente  une tour de type GoldDigger,
 * La GoldDigger est une tour de type terre specialisée dans l'attaque des ennemis à portée,
 * tout en permettant au joueur de gagner de l'argent supplémentaire  a chaque attaque  qui est réussie
 */

public class GoldDigger extends Tour {

    private static Color couleur = new Color(0, 167, 15);
     /**
     * Constructeur de la classe GoldDigger

     * @param position la position de la tour sur la carte.
     */

    public GoldDigger(Point position) {
        super(20, 1, 2, 10, Element.EARTH, position, 20);
    }



    /**
     * Affiche la GoldDigger dans la boutique de tours avec ses caractéristiques,
     * La couleur change en fonction de l'argent disponible du joueur
     *
     * @param Money l'argent actuel du joueur
     */
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

    /**
     * methode attaque Permet à la GoldDigger d'attaquer les ennemis à portée
     * Identifie l'ennemi le plus proche parmi ceux à portée, applique des dégâts,
     * et augmente l'argent du joueur de 1 pour chaque attaque réussie
     *
     * @param Joueur le joueur associé (utilisé pour la gestion des gains d'argent).
     */
    @Override
    public void attaquer(Joueur Joueur) {
        if(this.peutAttaquer()){
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
}
