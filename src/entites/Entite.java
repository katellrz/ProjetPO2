package entites;
//import map.Point;

import Librairies.Point;
import Librairies.StdDraw;
import Map.Case;
import outils.Omnicient;

import java.awt.Color;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


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
        this.position = Position;
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
                return Color.BLACK;
        }
    }


    protected double tempsDepuisDerniereAttaque = 0.0; // Chronométrage des attaques
    protected LocalTime derniereAttaque = LocalTime.now(); // Chronométrage des attaques

    public abstract void attaquer();

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

    public List<Entite> Aportee(List<Entite> monstres, double portee) {
        if (monstres == null || monstres.isEmpty()) {
            return new ArrayList<>(); // Retourne une liste vide si aucun monstre
        }
    
        List<Entite> monstresAportee = new ArrayList<>();
        for (Entite m : monstres) {
            if (m.getPosition().distance(this.position) / Omnicient.getSize() <= portee) {
                monstresAportee.add(m);
            }
        }
        return monstresAportee;
    }

    public Enemi PlusAvancer(List<Enemi> monstres) {
        if(!monstres.isEmpty()) {
            Enemi plusAvancer = monstres.get(0);
            for (Enemi m : monstres) {
                if (m.getCurrentIndex() > plusAvancer.getCurrentIndex()) {
                    plusAvancer = m;
                } else if (m.getCurrentIndex() == plusAvancer.getCurrentIndex()) {
                    List<Case> chemin = Omnicient.getChemin();
                    if (m.getPosition().distance(chemin.get(m.getCurrentIndex() + 1).getCenterCase()) <
                        plusAvancer.getPosition().distance(chemin.get(plusAvancer.getCurrentIndex() + 1).getCenterCase())) {
                        plusAvancer = m;
                    }
                }
            }
            return plusAvancer;
        }else{
            return null;
        }
    }

    public Enemi PlusProche(List<Enemi> monstres) {
        if(!monstres.isEmpty()) {
            Enemi plusProche = monstres.get(0);
            for (Enemi m : monstres) {
                if (m.getPosition().distance(this.position) < plusProche.getPosition().distance(this.position)) {
                    plusProche = m;
                }
            }
            return plusProche;
        }else{
            return null;
        }
    }

    public void afficheattaque(Enemi e) {
        if(e == null){
            return;
        }else{
            StdDraw.setPenColor(Color.RED);
            StdDraw.line(this.position.getX(), this.position.getY(), e.getPosition().getX(), e.getPosition().getY());
        }
    }

    protected void attaqueSimple(Enemi cible) {
        System.out.println("Attaque simple");
        if (cible != null) {
            cible.setPV(cible.getPV() - this.ATK);
            System.out.println("PV de "+cible+" : " + cible.getPV());

            afficheattaque(cible);
        }
    }


    

}