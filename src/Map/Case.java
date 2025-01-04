package Map;

import static outils.Omnicient.getSize;

import java.awt.Color;

import Librairies.Point;
import Librairies.StdDraw;
import outils.Omnicient;


/**
 * La classe Case représente une case individuelle dans la carte du jeu 
 * Elle contient des informations sur son type, sa couleur, sa position, et 
 * ses dimensions. Elle offre des fonctionnalités pour déterminer 
 * les interactions avec la souris et pour afficher visuellement la case.
 */

public class Case {

    public enum Casetype {DECOR, CONSTRUCTIBLE, ROUTE, BASE, SPAWN}

    protected Casetype type;
    protected Color couleur;
    protected int rows;
    protected int cols;
    protected Point centre;
    protected char lettre;
    protected boolean sel = false;

/**
     * Constructeur pour initialiser 
     *
     * @param c  Lettre representant le type de la case.
     * @param rows  Ligne de la case dans la grille.
     * @param cols  Colonne de la case dans la grille.
     * @param centerX Coordonnée X du centre de la case.
     * @param centerY Coordonnée Y du centre de la case.
     */
    public Case(char c, int rows, int cols,int centerX,int centerY){
        this.lettre=c;
        this.type = TypedeCase(c);
        this.couleur = SetCouleur(c);
        this.rows = rows;
        this.cols = cols;
        this.centre= new Point(centerX, centerY);
    }


//GETTEUR & SETTEUR

    public char getLettre(){
        return lettre;
    }

    public Casetype getType() {
        return type;
    }

    public Color getCouleur() {
        return couleur;
    }

     /**
     * Récupere la ligne de la case dans la grille.
     * 
     * @return Numéro de la ligne.
     */
    public int getRows() {
        return rows;
    }


    /**
     * Récupere la colonne de la case dans la grille.
     * 
     * @return Numéro de la colonne.
     */
    public int getCols() {
        return cols;
    }

    
    public double getCenterX() {
        return centre.getX();
    }

    public double getCenterY() {
        return centre.getY();
    }

    public Point getCenterCase(){
        return this.centre;
    }
    /**
     * Definit si la case est sélectionnée.
     * 
     * @param sel Booleen indiquant si la case est sélectionnée.
     */

    public void setSel(boolean sel) {
        this.sel = sel;
    }

//Fonction de chaque case 

    /**
     * Cette fontion permet de determiner le type de la case selon la lettrer noté sur le document ressource de ap
     * @param casetype correspond a la lettre lu par les fonction précédentes 
     * @return un parmatre de type Casetype, ce type est definis dans un énum 
     */
    public Casetype TypedeCase(char casetype){
        switch (casetype) {
            case 'S' :
                return Casetype.SPAWN;
                
            case 'B' :
                return Casetype.BASE;
                
            case 'R' :
                return Casetype.ROUTE;
                
            case 'C' :
                return Casetype.CONSTRUCTIBLE;
                
            case 'X':
                return Casetype.DECOR;
                
            default:
                return null;
        }
    }

    /**
     * Cette fontion permet de determiner la couleur de la case selon la lettre noté sur le document ressource de map
     * @param casetype correspond a la lettre lu par les fonction précédentes 
     * @return un parmatre de type Color 
     */
    public Color SetCouleur(char casetype){
        switch (casetype) {
            case 'S' :
                return Color.RED;
                
            case 'B' :
                return Color.ORANGE;
                
            case 'R' :
                return new Color(194,178,128);
                
            case 'C' :
                return Color.LIGHT_GRAY;
                
            case 'X':
                return new Color(11,102,35);
                
            default:
                return Color.WHITE;
                
        }
    }

    /**
     * Cette fontion permet d'afficher chaque case indépendament si la case est cliquer alors la cose devien jaune 
     * @param size correspond a 
     * @return un parmatre de type Color 
     */
    public void afficheCase(){
        Color couleur = this.couleur;//couleur de base 

        if(this.contains(StdDraw.mouseX(), StdDraw.mouseY(), getSize())&&StdDraw.isMousePressed()) couleur = Color.YELLOW;//Si la case est cliquer
        //dessin de la case
        StdDraw.setPenColor(couleur);
        StdDraw.filledSquare(this.centre.getX(), this.centre.getY(), Omnicient.getSize() / 2.0);

        couleur = Color.BLACK;
        StdDraw.setPenColor(couleur);
        StdDraw.square(this.centre.getX(), this.centre.getY(), (Omnicient.getSize() / 2.0));

    }


    // Vérifier si la souris est au-dessus de cette case
    /* public boolean Sourissurvole(double CentreX, double CentreY, double size) {
        return StdDraw.mouseX() > CentreX - size / 2.0 && StdDraw.mouseX() < CentreX + size / 2.0 && StdDraw.mouseY() > CentreY - size / 2.0 && StdDraw.mouseY() < CentreY + size / 2.0;
    } */

    /**
     *
     *
     * @return Reprsentation textuelle de la case.
     */

    @Override
    public String toString() {
        return "Case [type=" + type + ", couleur=" + couleur + ", rows=" + rows + ", cols=" + cols + " position "+centre.toString()+"]";
    }

/**
     * Vérifie si un point donne se trouve dans les limites de la case.
     *
     * @param mouseX  Coordonnée X de la souris.
     * @param mouseY  Coordonnée Y de la souris.
     * @param tailleCase Taille de la case.
     * @return True si le point est dans la case, false sinon
     */
    public boolean contains(double mouseX, double mouseY, double tailleCase) {
        return mouseX >= centre.getX() - tailleCase / 2 && mouseX <= centre.getX() + tailleCase / 2 && mouseY >= centre.getY() - tailleCase / 2
                && mouseY <= centre.getY() + tailleCase / 2;
    }

    
}
