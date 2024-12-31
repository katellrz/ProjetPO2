package entites;

import Librairies.Point;
import Librairies.StdDraw;
import Map.Case;
import static Map.DetectionSouris.*;
import java.awt.Color;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import outils.Omnicient;




/**
 * La classe abstraite Tour représente une entité tour dans le jeu. 
 * Elle fournit les méthodes et propriétés de base pour toutes les tours.
 */
public abstract class Tour extends Entite {
    protected int Cost;

    
    /**
     * Constructeur de la classe Tour.
     * 
     * @param position La position initiale de la tour.
     */
    public Tour(int PV, int ATK, double ATKSpeed, double Range, Element Element, Point position, int cost) {
        super(PV, ATK, ATKSpeed,Range, Element, position);
        this.Cost = cost;
        
    }

    // Getters et setters
    public int getCost() {
        return Cost;
    }

    public Point getPosition(){
        return position;
    }

    @Override
    public void estMort(){
        if(this.PV <= 0){
            Omnicient.removeTour(this);
        }
    }


    /**
     * Place une tour sur une case constructible en fonction des interactions de l'utilisateur.
     */

    public static void PlacerTour() {
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();
    
        if (StdDraw.isMousePressed()) {
            if ((x < 849 && x > 721) && (y < 596 && y > 546)) { // Archer
                System.out.println("Archer sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new Archer(c.getCenterCase());
                    Omnicient.SavetoOmni(t);
                    System.out.println("Tour placée a la position : " + t.getPosition());
                }
            } else if ((x < 987 && x > 859) && (y < 596 && y > 546)) { // WindCaster
                System.out.println("WindCaster sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new WindCaster(c.getCenterCase());
                    Omnicient.SavetoOmni(t);
                }
            } else if ((x < 849 && x > 721) && (y < 536 && y > 486)) { // WaterCaster
                System.out.println("WaterCaster sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new WaterCaster(c.getCenterCase());
                    Omnicient.SavetoOmni(t);
                }
            } else if ((x < 987 && x > 859) && (y < 536 && y > 486)) { // EarthCaster
                System.out.println("EarthCaster sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new EarthCaster(c.getCenterCase());
                    Omnicient.SavetoOmni(t);
                }
            } else if ((x < 849 && x > 721) && (y < 476 && y > 426)) { // FireCaster
                System.out.println("FireCaster sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new FireCaster(c.getCenterCase());
                    Omnicient.SavetoOmni(t);
                }
            }
        }
    }

     /**
     * Attend qu'une case constructible soit sélectionnée par l'utilisateur.
     * 
     * @return une instance de Case représentant la case sélectionnée.
     */
    
    // Fonction pour attendre que l'utilisateur clique sur une case constructible
    private static Case attendreCaseConstructible() {
        Case c = null;
        while (c == null) {
            if (StdDraw.isMousePressed()) {
                c = DetectionSourisCase(StdDraw.mouseX(), StdDraw.mouseY());
                if (c != null && c.getType() == Case.Casetype.CONSTRUCTIBLE) {
                    return c;
                }
            }
        }
        return null;
    }
    
 /**
     * Affiche la tour sur l'interface graphique.
     * 
     * @param tailleCase La taille de la case pour ajuster la taille de la tour.
     */

    public void afficheTour(double tailleCase) {
        // Dessine la tour
        Color tourColor = this.getColor();
        StdDraw.setPenColor(tourColor);
        StdDraw.filledCircle(position.getX(), position.getY(), tailleCase / 4.0); // Ajuster la taille selon besoin
    
        this.afficherVieT();
    }

    public void afficherVieT() {
        if (this.getPosition() == null || this.PVmax <= 0) {
            System.err.println("Erreur : position ou PV max invalide pour " + this);
            return;
        }
    
        // Calculer la largeur actuelle en fonction des points de vie
        double largeurActuelle = Math.max(0, (double) this.getPV() / this.PVmax * 50);
    
        double x = this.getPosition().getX();
        double y = this.getPosition().getY() - 25;
    
        // Vérifier que les coordonnées sont valides
        if (Double.isInfinite(x) || Double.isNaN(x) || Double.isInfinite(y) || Double.isNaN(y)) {
            System.err.println("Erreur : coordonnées invalides pour " + this + " (x=" + x + ", y=" + y + ")");
            return;
        }
    
        // Dessiner le fond de la barre (gris)
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.filledRectangle(x, y, 50 / 2, 7 / 2);
    
        // Dessiner la barre de vie (verte)
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledRectangle(x - (50 - largeurActuelle) / 2, y, largeurActuelle / 2, 7 / 2);
    
        // Contour de la barre (noir)
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(x, y, 50 / 2, 7 / 2);
    
        StdDraw.show();
    }

    public abstract Color getColor();
     
}