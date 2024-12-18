package Map;

import java.awt.Color;

import Librairies.Point;
import Librairies.StdDraw;
import Outil.Omnicient;

public class Case {

    public enum Casetype {DECOR, CONSTRUCTIBLE, ROUTE, BASE, SPAWN}

    protected Casetype type;
    protected Color couleur;
    protected int rows;
    protected int cols;
    protected Point centre;
    protected char lettre;


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

    public int getRows() {
        return rows;
    }


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

        if(SourisCliqueCase()) couleur = Color.YELLOW;//Si la case est cliquer
        //dessin de la case
        StdDraw.setPenColor(couleur);
        StdDraw.filledSquare(this.centre.getX(), this.centre.getY(), Omnicient.getSize() / 2.0);

        

        //dessine le tour de la case et en couleur si clique
        couleur = Color.BLACK;
        StdDraw.setPenColor(couleur);
        StdDraw.square(this.centre.getX(), this.centre.getY(), (Omnicient.getSize() / 2.0));

        StdDraw.show();
    }


    // Vérifier si la souris est au-dessus de cette case
    /* public boolean Sourissurvole(double CentreX, double CentreY, double size) {
        return StdDraw.mouseX() > CentreX - size / 2.0 && StdDraw.mouseX() < CentreX + size / 2.0 && StdDraw.mouseY() > CentreY - size / 2.0 && StdDraw.mouseY() < CentreY + size / 2.0;
    } */

    @Override
    public String toString() {
        return "Case [type=" + type + ", couleur=" + couleur + ", rows=" + rows + ", cols=" + cols + " position "+centre.toString()+"]";
    }


    /**
     * Pour une case dit si la souris clique dans cette case 
     * 
     * @return true ou flase 
     */
    public boolean SourisCliqueCase() {
        if (StdDraw.isMousePressed()) { // Détecte un clic
            double mouseX = StdDraw.mouseX(); // Récupère la position X de la souris
            double mouseY = StdDraw.mouseY(); // Récupère la position Y de la souris
            // Vérifie si la souris est dans les limites de cette case
            System.out.println("Clic détecté sur la case : " + this.toString());
            return mouseX > this.centre.getX() - Omnicient.getSize() / 2.0 && 
                   mouseX < this.centre.getX() + Omnicient.getSize() / 2.0 &&
                   mouseY > this.centre.getY() - Omnicient.getSize() / 2.0 &&
                   mouseY < this.centre.getY() + Omnicient.getSize() / 2.0;
        }
        return false;
    }

}
