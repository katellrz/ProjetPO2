package entites;

import static outils.Omnicient.getPositionMonstre;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import Gestion.Joueur;
import Librairies.Point;
import Librairies.StdDraw;

public class RailGun extends Tour {

     private static Color couleur = new Color(184, 22, 1);
    
    /**
     * Constructeur de la classe PoisonCaster.
     *
     * @param position La position de la tour sur le terrain.
     */

    public RailGun(Point position) {
        super(20, 1, 0, 0, Element.FIRE, position, 150);
    }

    

    /**
     * Retourne la couleur représentant la tour.
     *
     * @return La couleur de la tour (bleu).
     */

    @Override
    public Color getColor() {
        return couleur;
    }

    /**
     * Affiche la representation graphique de la tour dans la boutique.
     *
     * @param Money Le montant d'argent du joueur pour determiner si la tour 
     *              peut être achetée
     */

    public static void afficheTourBoutique(int Money) {
        if (Money < 150) {
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(923, 451, 64, 25);
        } else {
            StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.filledRectangle(923, 451, 64, 25);
        }

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(923, 451, 64, 25);
        StdDraw.setPenColor(couleur);
        StdDraw.filledCircle(883, 451, 15);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font1 = new Font("Arial", Font.PLAIN, 20);
        StdDraw.setFont(font1);
        StdDraw.text(940, 461, "RailGun");

        Font font = new Font("Arial", Font.PLAIN, 10);
        StdDraw.setFont(font);
        StdDraw.text(943, 441, "PV : 20    ATK : 1");

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(883, 451, "150");
    }

        /**
     * cette methode Gere l'attaque de la tour ,Si un ennemi est à portée, il est attaqué 
     * et puis empoisonnée
     *
     * @param Joueur L'objet representant le joueur pour appliquer les effets de l'attaque
     */

    @Override
    public void attaquer(Joueur Joueur) {
        //il n'attaque pas 
    }

    public void attaqueClick(){

        System.out.println("arrive la ");
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();

        List<Enemi> e = getPositionMonstre();

        if(! (e.isEmpty()&&e==null)){
            Enemi cible = e.get(0);
            double distance = new Point ( x, y ).distance(cible.getPosition());

            for(Enemi m : e ){
                double distance2 = new Point ( x, y ).distance(cible.getPosition());
                if(distance>distance2){
                    cible = m;
                    distance = distance2;
                }
                                
            }
            attaqueSimple(cible, null);
            System.out.println(" cible toucher par RailGun "+cible);
        }
    }

}
    


