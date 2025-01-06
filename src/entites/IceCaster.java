package entites;

import Gestion.Joueur;
import Librairies.Point;
import Librairies.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import outils.Omnicient;


/**
 * cette Classe represente une tour de type IceCaster,
 * IceCaster est une tour élémentaire de type eau capable d'attaquer les ennemis à portée 
 * tout en ralentissant leur vitesse et leur cadence d'attaque en fonction de leurs PV restants
 */

public class IceCaster extends Tour {

    private static Color couleur = new Color(6, 0, 160);

     /**
     * Constructeur de la classe IceCaster.
     * Initialise une tour IceCaster 
     * - PV : 40
     * - Dégâts : 1
     * - Vitesse d'attaque : 2
     * - Portée : 5
     * - Élément : WATER
     * - Position : elle est specifier au moment de l'instanciation
     * - Coût : 70
     *
     * @param position la position de la tour sur la carte
     */

    public IceCaster(Point position) {
        super(40, 1, 2, 5, Element.WATER, position, 70);
    }

     /**
     * cette methode affiche l'IceCaster dans la boutique de tours avec ses caractéristiques,
     * La couleur change en fonction de l'argent disponible du joueur
     *
     * @param Money l'argent actuel du joueur.
     */
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

     /**
     * cette methode Permet à l'IceCaster d'attaquer les ennemis à portée
     * Identifie l'ennemi le plus avancé parmi ceux à portée, applique des degâts,
     * reduit leur vitesse de deplacement et leur cadence d'attaque proportionnellement
     *  a  leurs PV restants.
     *
     * @param Joueur le joueur associé et utilisé dans les interactions de combat
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
                    cible.setSpeed(cible.getPV()*0.7);
                    cible.setATKSpeed(cible.getPV()*0.7);
                }
            }
        }
    }
}
