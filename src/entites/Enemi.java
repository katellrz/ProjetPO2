package entites;

import Librairies.Point;
import Librairies.StdDraw;
import Map.Case;
import java.awt.Color;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import outils.Omnicient;
import static outils.Omnicient.*;

/**
 * Classe abstraite représentant un ennemi dans le jeu.
 * Cette classe contient les propriétés et comportements de base d'un ennemi,
 * y compris son mouvement, sa vitesse, sa récompense, et ses interactions avec d'autres entités.
 */

public abstract class Enemi extends Entite {
    protected double Speed;
    protected int Reward;
    protected int currentIndex;//TODO en publique jsp pas pourquoi peut poser problème

    /**
     * Constructeur de la classe Enemi.
     * Initialise la position de l'ennemi au centre de la première case du chemin
     * et définit l'index actuel à 0.
     */
    public Enemi(int PV, int ATK, double ATKSpeed, int Range, Element Element, int Reward, double Speed) {
        super(PV, ATK, ATKSpeed, Range, Element,Omnicient.getSpawn().getCenterCase());
        this.Reward = Reward;
        this.Speed = Speed;
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

    
     /**
     * Définit la vitesse de déplacement de l'ennemi.
     * 
     * @param Speed La nouvelle vitesse de l'ennemi.
     */

    public void setSpeed(int Speed) {
        this.Speed = Speed;
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


     /**
     * Définit la récompense donnée lorsque l'ennemi est vaincu.
     * 
     * @param Reward La nouvelle récompense de l'ennemi.
     */
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

     /**
     * Déplace l'ennemi en suivant le chemin prédéfini.
     */

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

    /**
     * Affiche l'ennemi sur la carte.
     */
    public void apparait(){

        //System.out.println("L'ennemi apparaît : " + this);
        
        StdDraw.setPenColor(this.getColorByElement());
        StdDraw.filledSquare(position.getX(), position.getY(), 5);
        StdDraw.show();     
        
        this.afficherVieE();
        
    }

    /**
     * Affiche la barre de vie de l'ennemi.
     */
    public void afficherVieE() {
        if (this.getPosition() == null ) {
            System.err.println("Erreur : position invalide pour " + this);
            return;
        }else if (this.PVmax <= 0){
            System.err.println("Erreur : PV max invalide pour " + this);
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



    protected LocalTime derniereAttaque = LocalTime.now();
    protected double tempsDepuisDerniereAttaque = 0.0;

    public abstract void attaquer();

      /**
     * Vérifie si l'ennemi peut attaquer.
     * 
     * @return  true si l'ennemi peut attaquer, sinon  false.
     */
    protected boolean peutAttaquer() {
        Duration d = Duration.between(derniereAttaque, LocalTime.now());
        tempsDepuisDerniereAttaque = d.toMillis();
        if (tempsDepuisDerniereAttaque >= ATKSpeed*1000) {
            tempsDepuisDerniereAttaque = 0.0;
            derniereAttaque = LocalTime.now();
            return true;
        }
        return false;
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
        return plusProche;
    }

     /**
     * Retourne la tour avec le moins de points de vie.
     * 
     * @param tours La liste des tours.
     * @return La tour avec le moins de PV.
     */

    protected Tour MoinsDePV(List<Tour> tours) {
        return tours.stream().min(Comparator.comparingInt(Tour::getPV)).orElse(null);
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

    
}