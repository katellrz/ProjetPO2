package entites;

import Map.Case;
import outils.Omnicient;
import Librairies.Point;
import Librairies.StdDraw;

import static outils.Omnicient.*;

import java.awt.Color;
import java.util.List;

public class Enemi extends Entite {
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

        // Dessine la barre de vie
        StdDraw.setPenColor(Color.RED); 
        double lifePercentage = (double) getPV() / this.PVmax;
        double barWidth = Omnicient.getSize() * 0.4; // Largeur de la barre proportienelle a la taille de la bare 
        double barHeight = Omnicient.getSize() * 0.05; // Hauteur de la barre proportionelle a la taille de  la bar 
        double barX = position.getX() - barWidth / 2.0; //Permet de placer laa bare au dessus de l'enemie centrer
        double barY = position.getY() + Omnicient.getSize() / 6.0;// distance en tre le cante de l'enemi et la bare

        //StdDraw.filledRectangle(barX + barWidth * lifePercentage / 2.0, barY, barWidth * lifePercentage / 2.0, barHeight / 2.0);

        // Dessine le contour de la barre de vie
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(position.getX(), barY, barWidth / 2.0, barHeight / 2.0);
        
        
    }

    
}