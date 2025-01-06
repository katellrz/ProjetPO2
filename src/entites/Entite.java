package entites;
//import map.Point;

import Librairies.Point;
import java.awt.Color;
import java.time.Duration;
import java.time.LocalTime;



/**
 * Classe abstraite representant une entite dans le jeu.
 * Une entite peut avoir différents éléments (FEU, EAU, TERRE, VENT) et des attributs
 * comme les points de vie, l'attaque, la vitesse d'attaque, la portée et sa position.
 */


public abstract class Entite {

     /**
     * Énumération représentant les éléments possibles pour une entité.
     */

    public enum Element{
        NONE,WIND,FIRE,WATER,EARTH
    }

    protected int PV;
    protected int ATK;
    protected double ATKSpeed;
    protected double Range;
    protected Element element;
    protected Point position;
    protected final int PVmax;
    protected final Color color;

    //private Point Position;
    

    // Constructeur de la classse Entite
     public Entite(int PV, int ATK, double ATKSpeed, double Range, Element Element, Point Position) {
        this.PV = PV;
        this.ATK = ATK;
        this.ATKSpeed = ATKSpeed;
        this.Range = Range;
        this.element = Element;
        this.PVmax = PV;
        this.position = Position;
        this.color = getColorByElement();
    } 
    // Getters et setters


    

    /**
     * Obtient les points de vie actuels de l'entité.
     * 
     * @return Les points de vie actuels.
     */
    public int getPV() {
        return PV;
    }



      public void setATKSpeed(double aTKSpeed) {
        ATKSpeed = aTKSpeed;
    }




    /**
     * Définit les points de vie actuels de l'entité.
     * Si les PV sont inferieurs ou egaux à zero, la méthode estMort() est appelée.
     * 
     * @param PV Les nouveaux points de vie.
     */

    public void setPV(int PV) {
        this.PV = PV;
        
    }

    

      /**
     * Méthode abstraite appelee lorsque l'entité meurt.
     * la methode doit  etre implémentee  par les sous-classes.
     */
/**
 * 
 * Getters et setters
 */
    public int getATK() {
        return ATK;
    }

    public double getATKSpeed() {
        return ATKSpeed;
    }

    public double getRange() {
        return Range;
    }

    public Element getElement() {
        return element;
    }

    public Point getPosition() {
        return position;
    }

    public int getPVmax() {
        return PVmax;
    }

    /**
     * 
     * 
     * @return La couleur correspondant à l'élément de l'entité.
     */
    protected Color getColorByElement() {
        switch (this.element) {
            case FIRE:
                return new Color(184, 22, 1);
            case EARTH:
                return new Color(0, 167, 15);
            case WATER:
                return new Color(6, 0, 160);
            case WIND:
                return new Color(242, 211, 0);
            default:
                return Color.GRAY;
        }
    }    

    protected LocalTime derniereAttaque = LocalTime.now();
    protected double tempsDepuisDerniereAttaque = 0.0;

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
}