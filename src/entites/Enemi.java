package entites;

import Map.Case;
import outils.Omnicient;
import Librairies.Point;
import Librairies.StdDraw;

import static outils.Omnicient.*;

import java.awt.Color;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

public abstract class Enemi extends Entite {
    protected double Speed;
    protected int Reward;
    protected int currentIndex;//TODO en publique jsp pas pourquoi peut poser problème

    // Constructeur de la classe Enemi
    public Enemi() {
        super();
        this.position= getChemin().get(0).getCenterCase();
        this.currentIndex = 0;
    }



    // Getters et setters
    public double getSpeed() {
        return Speed;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setSpeed(int Speed) {
        this.Speed = Speed;
    }

    public int getReward() {
        return Reward;
    }

    public void setReward(int Reward) {
        this.Reward = Reward;
    }

    
    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public void setX(double x){
            this.position.setX(x);
    }

    public void setY(double x){
            this.position.setY(x);
    }

    @Override
    public void estMort() {
        if (PV <= 0) {
            System.out.println("L'ennemi est mort !");
            Omnicient.removeEnemi(this);
        }
    }

    public void avance(){

        List<Case>path = getChemin();//On recupere le chemin dans la classe omniciente

        Case d = path.get(path.size()-1);
        Case ad =path.get(path.size()-2);

        double PF = Math.sqrt((Math.pow((d.getCenterX()-ad.getCenterX()),2))+(Math.pow((d.getCenterY()-ad.getCenterY()),2)))/2;

        Point A = null;

        if(d.getCenterX()==ad.getCenterX()){
            A = new Point(d.getCenterX(),d.getCenterY()+PF);
        }else{
            A = new Point(d.getCenterX()+PF,d.getCenterY());
        }

        if (this.position.equals(A)||currentIndex>=path.size()-1){ // On arrète si le monstre a atteint la fin du chemin
            System.out.println("Le monstre est arrivé à la base !");
            return;
        }

        // Position centrale de la prochaine case 
        Case nextCase = path.get(currentIndex + 1);
        Point target = new Point(nextCase.getCenterX(), nextCase.getCenterY());// Position cible (centre de la case)

        
        // Calcul du vecteur de déplacement
        double dx = target.getX() - position.getX();
        double dy = target.getY() - position.getY();

        double distance = Math.sqrt(dx * dx + dy * dy);

        
        if (distance <= Speed) {
            // Atteint la cible
            position.setX(target.getX());
            position.setY(target.getY());
            currentIndex++; // Passe à la prochaine case
        } else {
            // déplace le monstre en fonction  à sa vitesse
            double ratio = Speed / distance; // Proportion du déplacement
            this.setX(position.getX() + dx * ratio);
            this.setY(position.getY() + dy * ratio);
        }
    }

    public void apparait(){

        //System.out.println("L'ennemi apparaît : " + this);
        
        StdDraw.setPenColor(this.getColorByElement());
        StdDraw.filledCircle(position.getX(), position.getY(), 5);
        StdDraw.show();     
        
        //this.afficherVieE();
        
    }

    public void afficherVieE() {

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

    protected LocalTime derniereAttaque = LocalTime.now();
    protected double tempsDepuisDerniereAttaque = 0.0;

    public abstract void attaquer();

    protected boolean peutAttaquer() {
        Duration d = Duration.between(derniereAttaque, LocalTime.now());
        tempsDepuisDerniereAttaque = d.toMillis();
        if (tempsDepuisDerniereAttaque >= ATKSpeed) {
            tempsDepuisDerniereAttaque = 0.0;
            derniereAttaque = LocalTime.now();
            return true;
        }
        return false;
    }

    protected Tour PlusProche(List<Tour> tours) {
        Tour plusProche = tours.get(0);
        for (Tour t : tours) {
            if (t.getPosition().distance(this.position) < plusProche.getPosition().distance(this.position)) {
                plusProche = t;
            }
        }
        return plusProche;
    }

    protected Tour MoinsDePV(List<Tour> tours) {
        return tours.stream().min(Comparator.comparingInt(Tour::getPV)).orElse(null);
    }

    public void afficheattaque(Tour t) {
        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.line(this.position.getX(), this.position.getY(), t.getPosition().getX(), t.getPosition().getY());
    }

    
}