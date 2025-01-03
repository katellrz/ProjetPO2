package entites;

import Librairies.Point;
import Librairies.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.util.Comparator;
import java.util.List;

import Gestion.Joueur;
import outils.Omnicient;

/**
 *  EarthCaster est une Classe représentant une tour de type EarthCaster.
 * L'EarthCaster est une tour élémentaire terrestre spécialisée dans l'attaque
 * de groupe, infligeant des dégâts aux ennemis proches de la cible principale.
 */

public class EarthCaster extends Tour {

    private static Color couleur = new Color(139,69,19);

    /**
     * Constructeur de la classe EarthCaster.
     * Initialise une tour avec des caractéristiques spécifiques, notamment sa portée,
     * ses points de vie, ses dégâts et son coût.
     *
     * @param position La position de la tour sur le plateau.
     */

    public EarthCaster(Point position) {
        super(50, 7, 0.5, 2.5, Element.EARTH, position, 100);        
    }


    /**
     * Retourne la couleur associée à l'EarthCaster.
     *
     * @return La couleur de la tour EarthCaster.
     */
    @Override
    public Color getColor() {
        return couleur;
    }

    /**
     * Affiche la tour EarthCaster dans la boutique.
     * La tour est affichée différemment en fonction de l'argent disponible.
     *
     * @param Money La quantité d'argent disponible pour l'achat.
     */

    public static void afficheTourBoutique(int Money){

        if(Money<100){
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(923, 511, 64, 25);
        }else{
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(923, 511, 64, 25);
        }
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(923, 511, 64, 25);
        StdDraw.setPenColor(couleur);
        StdDraw.filledCircle(883, 511, 15);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font1 = new Font("Arial", Font.PLAIN, 17);
        StdDraw.setFont(font1);
        StdDraw.text(940, 521, "EarthCaster");
            

        Font font = new Font("Arial", Font.PLAIN, 10); // Arial, taille 20
        StdDraw.setFont(font);
        StdDraw.text(943, 501, "PV : 50    ATK : 7");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(883, 511, "100");

    }

     /**
     * Permet à l'EarthCaster d'attaquer les ennemis à sa portée.
     * Cette méthode sélectionne l'ennemi avec le plus de PV comme cible principale,
     * puis inflige des dégâts à tous les ennemis proches de cette cible principale.
     */

    @Override
    public void attaquer(Joueur Joueur) {
        if (peutAttaquer()) {
            List<Enemi> cibles = MonstreAportee(Omnicient.getPositionMonstre(), this.Range);
            if (cibles == null || !cibles.isEmpty()) {
                Enemi cible = cibles.stream().max(Comparator.comparingInt(Enemi::getPV)).orElse(null);
                if (cible != null) {
                    for (Enemi m : cibles) {
                        if (m.getPosition().distance(cible.getPosition()) <= 1.0) {
                            m.setPV(m.getPV() - this.ATK);
                        }
                    }
                    afficheattaque(cible);
                }
            }
        }
    }

}
