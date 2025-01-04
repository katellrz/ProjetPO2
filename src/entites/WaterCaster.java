package entites;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import Gestion.Joueur;
import Librairies.Point;
import Librairies.StdDraw;
import outils.Omnicient;


/**
 * Représente une tour de type WaterCaster dans le jeu,
 * La WaterCaster est une tour élémentaire d'eau qui possède des caractéristiques spécifiques telles que
 * une faible attaque, une portée moyenne et un coût modéré.
 * Elle est capable d'attaquer les ennemis en les ciblant dans sa portée.
 */

public class WaterCaster extends Tour {

     /**
     * Couleur représentant la WaterCaster.
     */

    private static Color couleur = new Color(24, 108, 151);
     /**
     * Constructeur de la classe WaterCaster.
     * Initialise une tour WaterCaster avec des valeurs prédéfinies.
     * 
     * @param position La position de la tour sur le terrain.
     */



    public WaterCaster(Point position) {
        super(30, 3, 1, 4, Element.WATER, position, 50);
        this.PV=30;
        this.ATK=3;
        this.ATKSpeed=1;
        this.Range=4;
        this.element=Element.WATER;
        this.Cost=50;

    }

   /**
     * @return La couleur de la WaterCaster.
     */
    @Override
    public Color getColor() {
        return couleur;
    }

    /**
     * Affiche la  représentation de la WaterCaster dans la boutique,
     * Permet de visualiser la tour dans l'interface utilisateur, en indiquant si elle est
     * achetable en fonction de l'argent du joueur
     * 
     * @param Money Le montant d'argent disponible du joueur.
     */

    public static void afficheTourBoutique(int Money){

        if(Money<50){
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(785, 511, 64, 25);
        }else{
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(785, 511, 64, 25);
        }
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(785, 511, 64, 25);
        StdDraw.setPenColor(couleur);
        StdDraw.filledCircle(745, 511, 15);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font1 = new Font("Arial", Font.PLAIN, 17);
        StdDraw.setFont(font1);
        StdDraw.text(805, 521, "WaterCaster");
            

        Font font = new Font("Arial", Font.PLAIN, 10); // Arial, taille 20
        StdDraw.setFont(font);
        StdDraw.text(805, 501, "PV : 30    ATK : 3");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(745, 511, "50");

    }


     /**
     * Permet à la WaterCaster d'attaquer les ennemis à portée.
     * Si des ennemis sont dans la portee de la tour, elle choisit la cible la plus avancée,
     * lui inflige des dégâts, et affiche l'attaque.
     * 
     * @param Joueur Le joueur auquel appartient la tour.
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
