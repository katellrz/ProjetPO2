package entites;
//import map.Point;

import java.awt.Color;

public abstract class Entite {

    public enum Element{
        NONE,WIND,FIRE,WATER,EARTH
    }

    private int PV;
    private int ATK;
    private double ATKSpeed;
    private int Range;
    private Element element;
    //private Point Position;
    private String Icone;

    // Constructeur de la classse Entite
    public Entite(int PV, int ATK, double ATKSpeed, int Range, Element Element) {
        this.PV = PV;
        this.ATK = ATK;
        this.ATKSpeed = ATKSpeed;
        this.Range = Range;
        this.element = Element;
    }
    // Getters et setters
    public int getPV() {
        return PV;
    }

    public void setPV(int PV) {
        this.PV = PV;
    }

    public int getATK() {
        return ATK;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public double getATKSpeed() {
        return ATKSpeed;
    }

    public void setATKSpeed(int ATKSpeed) {
        this.ATKSpeed = ATKSpeed;
    }

    public int getRange() {
        return Range;
    }

    public void setRange(int Range) {
        this.Range = Range;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element Element) {
        this.element = Element;
    }

   /*public Point getPosition() {
        return Position;
    }

    public void setPosition(Point Position) {
        this.Position = Position;
    }*/

    public String getIcone() {
        return Icone;
    }

    public void setIcone(String Icone) {
        this.Icone = Icone;
    }

    protected Color getColorByElement(Element element) {
        switch (element) {
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