package entites;
//import map.Point;

import Librairies.Point;
import java.awt.Color;


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

    //private Point Position;
    

    // Constructeur de la classse Entite
     public Entite(int PV, int ATK, double ATKSpeed, double Range, Element Element, Point Position) {
        this.PV = PV;
        this.ATK = ATK;
        this.ATKSpeed = ATKSpeed;
        this.Range = Range;
        this.element = Element;
        this.PVmax = PV;
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
      /**
     * Définit les points de vie actuels de l'entité.
     * Si les PV sont inferieurs ou egaux à zero, la méthode estMort() est appelée.
     * 
     * @param PV Les nouveaux points de vie.
     */

    public void setPV(int PV) {
        this.PV = PV;
        estMort();
    }

      /**
     * Méthode abstraite appelee lorsque l'entité meurt.
     * la methode doit  etre implémentee  par les sous-classes.
     */

    public abstract void estMort();
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

   /*public Point getPosition() {
        return Position;
    }

    public void setPosition(Point Position) {
        this.Position = Position;
    }*/

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
                return Color.BLACK;
        }
    }
}