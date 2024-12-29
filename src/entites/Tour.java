package entites;

import Librairies.Point;
import Librairies.StdDraw;
import Map.Case;

import static Map.DetectionSouris.*;

import java.awt.Color;
import java.util.List;

import Gestion.Interface;
import outils.Omnicient;



public abstract class Tour extends Entite {
    protected int Cost;
    protected Point position;
    protected int MaxPV;

    // Constructeur de la classe TOURS
    public Tour(Point position) {
        super();
        this.position = position;
        // this.Cost = cost;
        // this.MaxPV = PV;
    }

    // Getters et setters
    public int getCost() {
        return Cost;
    }

    public Point getPosition(){
        return position;
    }

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
    


    public void afficheTour(double tailleCase) {
        // Dessine la tour
        Color tourColor = this.getColor();
        StdDraw.setPenColor(tourColor);
        StdDraw.filledCircle(position.getX(), position.getY(), tailleCase / 4.0); // Ajuster la taille selon besoin
    
        //this.afficherVieT();
    }

    public void afficherVieT() {

        // Calculer la largeur actuelle en fonction des points de vie
        double largeurActuelle = (double)this.getPV() /this.getMaxPV() * 50;

        // Dessiner le fond de la barre (gris)
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.filledRectangle(this.getPosition().getX(),this.getPosition().getY() - 25, 50 / 2, 7 / 2);

        // Dessiner la barre de vie (verte)
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledRectangle(this.getPosition().getX() - (50 - largeurActuelle) / 2,this.getPosition().getY() - 25,
                largeurActuelle / 2, 7 / 2);

        // Contour de la barre (noir)
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(this.getPosition().getX(),this.getPosition().getX() - 25, 50 / 2, 7 / 2);

        StdDraw.show();

    }

    public abstract int getMaxPV();

    public abstract Color getColor();
     
}