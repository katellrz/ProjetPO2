package entites;

import Librairies.Point;
import Librairies.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import outils.Omnicient;

/**
 * Classe représentant une tour de type FireCaster.
 * La FireCaster est une tour qui utilise l'élément FEU pour attaquer les ennemis
 * dans une certaine portee. Elle est caractérisée par ses PV, ATK, vitesse d'attaque,
 * portée, coût et couleur spécifique.
 */

public class FireCaster extends Tour{

    private static Color couleur = new Color(255, 142, 97);

    public FireCaster(Point position){
        super(position);
        this.PV=30;
        this.ATK=10;
        this.ATKSpeed=0.5;
        this.Range=2.5;
        this.element=Element.FIRE;
        this.Cost=100;       
    }

<<<<<<< HEAD
=======
    /**
     * Obtient les points de vie maximum de la FireCaster.
     * 
     * @return Les points de vie maximum.
     */
    @Override
    public int getMaxPV() {
        return MaxPV;
    }
>>>>>>> 5d6d35354f71ccd077c9a446355ffd45a66a445a


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
    public void attaquer() {
        if (peutAttaquer()) {
            List<Enemi> cibles = MonstreAportee(Omnicient.getPositionMonstre(), this.Range);
            if (!cibles.isEmpty()) {
                Enemi cible = PlusProche(cibles);
                if (cible != null) {
                    for (Enemi m : cibles) {
                        if (m.getPosition().distance(cible.getPosition()) <= 0.75) {
                            m.setPV(m.getPV() - this.ATK);
                        }
                    }
                    afficheattaque(cible);
                }
            }
        }
    }



}
