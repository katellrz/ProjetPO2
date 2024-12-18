package entites;

import Map.Case;
import Librairies.Point;
import Librairies.StdDraw;

import static outils.Omnicient.*;

import java.awt.Color;
import java.util.List;

public class Enemi extends Entite {
    private double Speed;
    private int Reward;
    private Point position;
    public int currentIndex;

    // Constructeur de la classe Enemi
    public Enemi(int PV, int ATK, double ATKSpeed, int Range, Element Element, double Speed, int Reward) {
        super(PV, ATK, ATKSpeed, Range, Element);
        this.Speed = Speed;
        this.Reward = Reward;
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

        if (currentIndex >= path.size() - 1) { // On arrète si le monstre a atteint la fin du chemin
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
        
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledCircle(position.getX(), position.getY(), 20);
        StdDraw.show();
        
    }

    
}