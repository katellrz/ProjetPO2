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

    public static void PlacerTour(){
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();    
        if(DetectionZone(x, y).equals("Zone Boutique")){
            if((x<849&&x>721)&&(y<546&&y>596)){
                Case c = null;
                while(c==null){
                    c = DetectionSourisCase(StdDraw.mouseX(), StdDraw.mouseX());
                }
                if(c.getType()==Case.Casetype.CONSTRUCTIBLE){//TODO completer avec la money
                    Tour t = new Archer(c.getCenterCase());
                    Omnicient.SavetoOmni(t);
                }else{
                    //Interface.MessageErrCaseNonConstructible();
                }
            }
        }else if((x<987&&x>859)&&(y<546&&y>596)){
            Case c = null;
            while(c==null){
                c = DetectionSourisCase(StdDraw.mouseX(), StdDraw.mouseX());
            }
            if(c.getType()==Case.Casetype.CONSTRUCTIBLE){//TODO completer avec la money
                Tour t = new WindCaster(c.getCenterCase());
                Omnicient.SavetoOmni(t);
            }else{
                //Interface.MessageErrCaseNonConstructible();
            }
        }else if((x<849&&x>721)&&(y<486&&y>536)){
            Case c = null;
            while(c==null){
                c = DetectionSourisCase(StdDraw.mouseX(), StdDraw.mouseX());
            }
            if(c.getType()==Case.Casetype.CONSTRUCTIBLE){//TODO completer avec la money
                Tour t = new WaterCaster(c.getCenterCase());
                Omnicient.SavetoOmni(t);
            }else{
                //Interface.MessageErrCaseNonConstructible();
            }
        }else if((x<987&&x>859)&&(y<486&&y>536)){
            Case c = null;
            while(c==null){
                c = DetectionSourisCase(StdDraw.mouseX(), StdDraw.mouseX());
            }
            if(c.getType()==Case.Casetype.CONSTRUCTIBLE){//TODO completer avec la money
                Tour t = new EarthCaster(c.getCenterCase());
                Omnicient.SavetoOmni(t);
            }else{
                //Interface.MessageErrCaseNonConstructible();
            }
        }else if((x<849&&x>721)&&(y<476&&y>426)){
            Case c = null;
            while(c==null){
                c = DetectionSourisCase(StdDraw.mouseX(), StdDraw.mouseX());
            }
            if(c.getType()==Case.Casetype.CONSTRUCTIBLE){//TODO completer avec la money
                Tour t = new FireCaster(c.getCenterCase());
                Omnicient.SavetoOmni(t);
            }else{
                //Interface.MessageErrCaseNonConstructible();
            }
        }
    }


    public void afficheTour(double tailleCase) {
        // Dessine la tour
        Color tourColor = this.getColor();
        StdDraw.setPenColor(tourColor);
        StdDraw.filledCircle(position.getX(), position.getY(), tailleCase / 4.0); // Ajuster la taille selon besoin
    
        this.afficherVieT();
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