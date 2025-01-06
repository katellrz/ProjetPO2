package entites;

import Librairies.Point;
import Librairies.StdDraw;
import Map.Case;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Gestion.Joueur;
import outils.Omnicient;
import static outils.Omnicient.*;

/**
 * Classe abstraite representant un ennemi dans le jeu
 * Cette classe contient les propriétés et comportements de base d'un ennemi,
 * y compris son mouvement, sa vitesse, sa récompense, et ses interactions avec d'autres entités.
 */

public abstract class Enemi extends Entite {
    protected double Speed;
    protected int Reward;
    protected int currentIndex;//TODO en publique jsp pas pourquoi peut poser problème
    public boolean buffer;
    protected final double ATKSpeedNonBuffer;

    /**
     * Constructeur de la classe Enemi.
     * Initialise la position de l'ennemi au centre de la première case du chemin
     * et définit l'index actuel à 0.
     */
    public Enemi(int PV, int ATK, double ATKSpeed, int Range, Element Element, int Reward, double Speed) {
        
        super(PV, ATK, ATKSpeed, Range, Element,new Point(getChemin().get(0).getCenterCase().getX(),getChemin().get(0).getCenterCase().getY()));
        this.Reward = Reward;
        this.Speed = Speed;
        this.ATKSpeedNonBuffer = ATKSpeed;
    }

    /**
     * Permet de gerer les effet des ennemie de type buffer
     */
    public void effetBuffer(){
        if (buffer&&ATKSpeed==ATKSpeedNonBuffer){
            System.out.println("agit");
            this.ATKSpeed = this.ATKSpeed*1.5;
        }else if (!buffer&&ATKSpeed!=ATKSpeedNonBuffer){
            ATKSpeed = ATKSpeedNonBuffer;
        }
    }

    // Getters et setters

     /**
     * Obtient la vitesse de déplacement de l'ennemi.
     * 
     * @return La vitesse de l'ennemi.
     */
    public double getSpeed() {
        return Speed;
    }

    
    public void setSpeed(double speed) {
        Speed = speed;
    }



    /**
     * Obtient l'index actuel sur le chemin.
     * 
     * @return L'index actuel.
     */

    public int getCurrentIndex() {
        return currentIndex;
    }


    
/**
     * Obtient la récompense donnée lorsque l'ennemi est vaincu.
     * 
     * @return La récompense de l'ennemi.
     */
    public int getReward() {
        return Reward;
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



     /**
     * Déplace l'ennemi en suivant le chemin prédéfini
     */

     public void avance(Joueur Joueur) {
        List<Case> path = Omnicient.getChemin();
        if (currentIndex >= path.size() - 1 || this.position.equals(Omnicient.getBase().getCenterCase())) {
            System.out.println("L'ennemi est arrivé à la base");
            return;
        }

        Case nextCase = path.get(currentIndex + 1);
        Point target = new Point(nextCase.getCenterX(), nextCase.getCenterY());
        double dx = target.getX() - position.getX();
        double dy = target.getY() - position.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance <= Speed) {
            position.setX(target.getX());
            position.setY(target.getY());
            currentIndex++;
        } else {
            double ratio = Speed / distance;
            this.position.setX(position.getX() + dx * ratio);
            this.position.setY(position.getY() + dy * ratio);
        }
    }

    /**
     * Affiche l'ennemi sur la carte
     */
    public void apparait(){

        //System.out.println("L'ennemi apparaît : " + this);
        
        StdDraw.setPenColor(this.color);
        StdDraw.filledSquare(position.getX(), position.getY(), 5);
        StdDraw.show();     
        
        this.afficherVieE();
        
    }


    private void afficherVieE() {
        double largeurActuelle = Math.max(0, (double) this.getPV() / this.PVmax * 50);
        double x = this.getPosition().getX();
        double y = this.getPosition().getY() - 25;

        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.filledRectangle(x, y, 50 / 2, 7 / 2);
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledRectangle(x - (50 - largeurActuelle) / 2, y, largeurActuelle / 2, 7 / 2);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(x, y, 50 / 2, 7 / 2);

        StdDraw.show();
    }


    /** permet au Enemie de recevoir des soin par le healer */

    public void recevoirSoins(int soin){
        if(PV+soin>PVmax){
            this.PV = PVmax;
        }else{
            this.PV+=soin;
        }
    }


    /**
     * permet de detecter les entité a aporter 
     * @param tours List presente sur la map
     * @param range Largeur de l'attaque
     * @return Une liste d'entite a la porter de l'entité attaquante
     */
   public List<Tour> TourAportee(List<Tour> tours, double range) {
        List<Tour> cibles = new ArrayList<>();
        for (Tour t : tours) {
            if (t.getPosition().distance(this.position) <= range*getSize()) {
                cibles.add(t);
            }
        }
        return cibles;
    }



    public List<Enemi>Aportee(List<Enemi> tours, double range) {
        List<Enemi> cibles = new ArrayList<>();
        for (Enemi t : tours) {
            if (t.getPosition().distance(this.position) <= range*getSize()) {
                cibles.add(t);
            }
        }
        return cibles;
    }

    /**
     * Retourne la tour la plus proche de l'ennemi.
     * 
     * @param tours La liste des tours.
     * @return La tour la plus proche.
     */
    protected Tour PlusProche(List<Tour> tours) {
        Tour plusProche = tours.get(0);
        for (Tour t : tours) {
            if (t.getPosition().distance(this.position) < plusProche.getPosition().distance(this.position)) {
                plusProche = t;
            }
        }
        System.out.println("plus proche : " + plusProche);
        return plusProche;
    }

     /**
     * Retourne la tour avec le moins de points de vie.
     * 
     * @param tours La liste des tours.
     * @return La tour avec le moins de PV.
     */

    protected Tour MoinsDePV(List<Tour> tours) {
        Tour Min = tours.get(0);
        for (Tour t : tours) {
            if (t.getPV() < Min.getPV()) {
                Min = t;
            }
        }
        System.out.println("moins de pv : " + Min);
        return Min;
    }

    /**
     * PErmet de detecter l'ennemie avec les mmoin de Pv 
     * @param e une liste  dans laquelle se trouve toutes les entte concerner 
     * @return l'entite avec le moin de PV
     */
    protected Enemi MoinsDePVE(List<Enemi> e) {
        Enemi Min = e.get(0);
        for (Enemi t : e) {
            if (t.getPV() < Min.getPV()) {
                Min = t;
            }
        }
        System.out.println("moins de pv : " + Min);
        return Min;
    }

     /**
     * Affiche l'attaque de l'ennemi sur une tour.
     * 
     * @param t La tour attaquée.
     */
    public void afficheattaque(Tour t) {
        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.line(this.position.getX(), this.position.getY(), t.getPosition().getX(), t.getPosition().getY());
    }


    public void attaqueSimple(Tour t, Joueur Joueur) {
        t.setPV(t.getPV() - this.ATK);
        afficheattaque(t);
        System.out.println("Tour attaquée : " + t);
    }

    /**
     * Permet de detecter les enemies qui vont avoir une attque collatéralle
     * @param t l'entiter qui est viser par l'attaque simple a partir de laquelle on calcule les degat des entités collatérales
     * @param distance la distence sur laquelle est l'attaque
     * 
     */
    public void attaqueCollateral(Tour t, double distance, Joueur Joueur) {
        for (Tour tour : Omnicient.getPositionTours()) {
            if (tour.getPosition().distance(t.getPosition()) <= distance) {
                tour.setPV(tour.getPV() - this.ATK);
                afficheAattaqueCollateral(tour, t);
            }
        }
    }

    
    /**
     * Permet de tracer des rait entre la tour viser et les tour anxe de l'attaque
     * @param t tour viser 
     * @param cible tours anexe
     */
    public void afficheAattaqueCollateral(Tour t, Tour cible) {
        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.line(t.getPosition().getX(), t.getPosition().getY(), cible.getPosition().getX(), cible.getPosition().getY());
    }


    public void attaquer(Joueur joueur){}

    
}