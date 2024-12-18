package entites;

import static outils.Omnicient.*;

import java.awt.Color;
import java.util.List;

import Librairies.Point;

import entites.Tours.Archer;
import outils.Omnicient;
import Map.Case;
import Librairies.StdDraw;


public abstract class Tour extends Entite {
    protected int Cost;
    protected Point position;
    protected int MaxPV;

    // Constructeur de la classe TOURS
    public Tour(int PV, int ATK, double ATKSpeed, int Range, Element Element,int cost, Point Position) {
        super(PV, ATK, ATKSpeed, Range, Element);
        this.position = Position;
        this.Cost = cost;
        this.MaxPV = PV;
    }

    // Getters et setters
    public int getCost() {
        return Cost;
    }

    public Point getPosition(){
        return position;
    }

    public static void PlacerTour(){
        for (List<Case> c : Omnicient.getCarte()) {
            for (Case cs : c) {
                if(cs.SourisCliqueCase()){
                    Archer t = new Archer(12,1,1,1,Element.NONE,30,cs.getCenterCase());
                    SavetoOmni(t);
                }                
            }
            
        }
    }

    public void afficheTour(double tailleCase) {
        // Dessine la tour
        Color tourColor = this.getColorByElement(this.getElement());
        StdDraw.setPenColor(tourColor);
        StdDraw.filledCircle(position.getX(), position.getY(), tailleCase / 4.0); // Ajuster la taille selon besoin
        
        // Dessine la barre de vie au-dessus
        StdDraw.setPenColor(Color.RED); // Barre de vie (rouge)
        double lifePercentage = (double) getPV() / getMaxPV(); // TODO definir le max PV de chaque tours Fraction de la vie
        double barWidth = tailleCase * 0.8; // Largeur de la barre
        double barHeight = tailleCase * 0.1; // Hauteur de la barre
        double barX = position.getX() - barWidth / 2.0; // Position en X de la barre
        double barY = position.getY() + tailleCase / 2.0; // Position en Y de la barre

        StdDraw.filledRectangle(barX + barWidth * lifePercentage / 2.0, barY, barWidth * lifePercentage / 2.0, barHeight / 2.0);

        // Dessine le contour de la barre de vie
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(position.getX(), barY, barWidth / 2.0, barHeight / 2.0);
    }

    public abstract int getMaxPV();



  
}