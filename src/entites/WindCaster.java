package entites;

import Librairies.Point;
import Librairies.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import Gestion.Joueur;
import outils.Omnicient;

/**
 * Représente une tour de type WindCaster dans le jeu.
 * Hérite de la classe Tour
 * 
 * La tour WindCaster possède des caractéristiques spécifiques comme des points de vie (PV),
 * une attaque (ATK), une vitesse d'attaque (ATKSpeed), une portée (Range), un coût (Cost),
 * et un élément associé vent.
 * 
 * Elle est également capable d'attaquer les ennemis dans sa portée, en ciblant les ennemis les plus avancés,
 * et dispose d'une méthode pour l'affichage dans la boutique.
 
 */

public class WindCaster extends Tour {

    private static Color couleur = new Color(167, 194, 200);


    public WindCaster(Point position) {
        super(30, 5, 1.5, 6, Element.WIND, position, 50);
    }

    @Override
    public Color getColor() {
        return couleur;
    }

    
     /**
     * Affiche la représentation de la tour WindCaster dans la boutique du jeu.
     * La couleur du bouton et l'activation du bouton dépendent de la quantité d'argent disponible.
     * 
     * @param Money Le montant d'argent du joueur.
     */

    public static void afficheTourBoutique(int Money){

        if(Money<50){
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(923, 571, 64, 25);
        }else{
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(923, 571, 64, 25);
        }
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(923, 571, 64, 25);
        StdDraw.setPenColor(couleur);
        StdDraw.filledCircle(883, 571, 15);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font1 = new Font("Arial", Font.PLAIN, 17);
        StdDraw.setFont(font1);
        StdDraw.text(940, 581, "WindCaster");
            

        Font font = new Font("Arial", Font.PLAIN, 10); // Arial, taille 20
        StdDraw.setFont(font);
        StdDraw.text(943, 561, "PV : 30    ATK : 5");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(883, 571, "50");

    }



     /**
     * Effectue l'attaque de la tour WindCaster en ciblant les ennemis dans sa portée.
     * La tour attaque l'ennemi le plus avancé parmi les cibles dans sa portée.
     * Si la cible existe, la méthode `attaqueSimple` est appelée pour infliger les dégâts à l'ennemi.
     */
    @Override
    public void attaquer(Joueur Joueur) {
        if (peutAttaquer()) {
            List<Enemi> cibles = MonstreAportee(Omnicient.getPositionMonstre(), this.Range);
            if (cibles == null || !cibles.isEmpty()) {
                Enemi cible = PlusProche(cibles);
                if (cible != null) {
                    attaqueSimple(cible, Joueur);
                    afficheattaque(cible);
                }
            }
        }
    }
}
