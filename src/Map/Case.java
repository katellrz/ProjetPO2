package Map;

import static outils.Omnicient.getSize;

import java.awt.Color;

import Librairies.Point;
import Librairies.StdDraw;
import outils.Omnicient;

public class Case {

    public enum Casetype {DECOR, CONSTRUCTIBLE, ROUTE, BASE, SPAWN}

    protected Casetype type;
    protected Color couleur;
    protected int rows;
    protected int cols;
    protected Point centre;
    protected char lettre;
    protected boolean sel = false;


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

    @Override
    public String toString() {
        return "Case [type=" + type + ", couleur=" + couleur + ", rows=" + rows + ", cols=" + cols + " position "+centre.toString()+"]";
    }


    public boolean contains(double mouseX, double mouseY, double tailleCase) {
        return mouseX >= centre.getX() - tailleCase / 2 && mouseX <= centre.getX() + tailleCase / 2 && mouseY >= centre.getY() - tailleCase / 2
                && mouseY <= centre.getY() + tailleCase / 2;
    }

    /**
     * Pour une case dit si la souris clique dans cette case 
     * 
     * @return true ou flase 
     */

     /* boolean dejaClique = false;
     boolean etatPrecedentSouris = false;
     
     public boolean SourisCliqueCase(double mouseX, double mouseY) {
        boolean etatActuelSouris = StdDraw.isMousePressed();
    
        // Vérifie si la souris est pressée, que ce n'est pas un clic maintenu, et si la souris est bien dans cette case
        if (etatActuelSouris && !etatPrecedentSouris && this.contains(mouseX, mouseY, Omnicient.getSize())) {
            System.out.println("Clic détecté sur la case : " + this.toString());
            etatPrecedentSouris = etatActuelSouris;
            return true; // Indique qu'un clic valide a eu lieu
        }
    
        // Réinitialise l'état précédent de la souris
        etatPrecedentSouris = etatActuelSouris;
        return false; // Aucun clic détecté
    } */
    

    
}
