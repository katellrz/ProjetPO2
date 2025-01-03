package entites;

import Librairies.Point;
import Librairies.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import Gestion.Joueur;
import outils.Omnicient;

/**
 * Classe représentant une tour de type Archer dans le jeu.
 * Cette tour possède des caractéristiques spécifiques comme ses PV, ATK, vitesse d'attaque, portée, et coût.
 */

public class Archer extends Tour{

    /**
     * Couleur spécifique de la tour Archer.
     */


    private static Color couleur = new Color(107, 106, 105);

     /**
     * Constructeur pour créer une tour Archer à une position donnée.
     *
     * @param position la position de la tour Archer.
     */
    public Archer(Point position){
        super(30, 5, 1, 2, Element.NONE, position, 20);
    }


      /**
     * Retourne la couleur spécifique de la tour Archer.
     *
     * @return la couleur de la tour.
     */

    @Override//TODO  reverifier les histoire de couleur 
    public Color getColor() {
        return couleur;
    }

    

    /**
     * Affiche l'interface graphique de la tour Archer dans la boutique.
     * Si l'utilisateur n'a pas assez d'argent, l'option est grisée.
     *
     * @param Money le montant d'argent de l'utilisateur.
     */

     public static void afficheTourBoutique(int Money) {
        if (Money < 20) {
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(785, 571, 64, 25);
        } else {
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

        Font font = new Font("Arial", Font.PLAIN, 10);
        StdDraw.setFont(font);
        StdDraw.text(805, 561, "PV : 30    ATK : 5");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(745, 571, "20");
    }

    /**
     * Attaque les ennemis à portée.
     * Sélectionne l'ennemi le plus avancé à portée et effectue une attaque simple.
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
                 }
             }
         }
     }
 }