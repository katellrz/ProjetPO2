package entites;

import Librairies.Point;
import Librairies.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import Gestion.Joueur;
import outils.Omnicient;

/**
 * Classe représentant une tour de type FireCaster.
 * La FireCaster est une tour qui utilise l'élément FEU pour attaquer les ennemis
 * dans une certaine portee. Elle est caractérisée par ses PV, ATK, vitesse d'attaque,
 * portée, coût et couleur spécifique.
 */

public class FireCaster extends Tour{

    private static Color couleur = new Color(255, 142, 97);

    public FireCaster(Point position) {
        super(30, 10, 0.5, 2.5, Element.FIRE, position, 100);
    }



    /**
     * Obtient la couleur spécifique de la FireCaster.
     * 
     * @return La couleur de la FireCaster.
     */
    @Override
    public Color getColor() {
        return couleur;
    }

     /**
     * Affiche les informations de la FireCaster dans la boutique.
     * 
     * @param Money Le montant d'argent disponible du joueur.
     *              Si le joueur n'a pas assez d'argent, le bouton sera grisé.
     */

    public static void afficheTourBoutique(int Money){

        if(Money<100){
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(785, 451, 64, 25);
        }else{
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(785, 451, 64, 25);
        }
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(785, 451, 64, 25);
        StdDraw.setPenColor(couleur);
        StdDraw.filledCircle(745, 451, 15);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font1 = new Font("Arial", Font.PLAIN, 17);
        StdDraw.setFont(font1);
        StdDraw.text(805, 461, "FireCaster");
            

        Font font = new Font("Arial", Font.PLAIN, 10); // Arial, taille 20
        StdDraw.setFont(font);
        StdDraw.text(805, 441, "PV : 30    ATK : 10");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(745, 451, "100");

    }

    /**
     * Attaque les ennemis à portée de la FireCaster.
     * Les ennemis proches de la cible principale (distance <= 0.75) subissent
     * également des dégâts.
     */

    @Override
    public void attaquer(Joueur Joueur) {
        if (peutAttaquer()) {
            List<Enemi> cibles = MonstreAportee(Omnicient.getPositionMonstre(), this.Range);
            if (cibles == null || !cibles.isEmpty()) {
                Enemi cible = PlusProche(cibles);
                if (cible != null) {
                    this.attaqueSimple(cible, Joueur);
                    this.attaqueCollateral(cible,0.75, Joueur);
                }
            }
        }
    }


}
