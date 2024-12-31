package Librairies;


/**
 * Représente un point dans un plan à deux dimensions avec des coordonnées {@code x} et {@code y}.
 * Cette classe fournit des méthodes pour accéder et modifier les coordonnées, 
 * calculer la distance entre deux points et comparer deux points.
 */
public class Point {

    private double x;
    private double y;
    /**
     * Constructeur de la classe {@code Point}.
     * Initialise un point avec les coordonnées spécifiées {@code x} et {@code y}.
     * 
     * @param x la coordonnée x du point.
     * @param y la coordonnée y du point.
     */

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    /**
     * Retourne une représentation sous forme de chaîne de caractères des coordonnées du point.
     * 
     * @return une chaîne représentant les coordonnées du point sous le format "(x=..., y=...)".
     */

    @Override
    public String toString() {
        return " (x=" + x + ", y=" + y + ")";
    }

    

    /**
     * Compare ce point avec un autre point pour vérifier s'ils ont les mêmes coordonnées.
     * 
     * @param B le point à comparer avec le point courant.
     * @return {@code true} si les deux points ont les mêmes coordonnées, {@code false} sinon.
     */
    public boolean equals(Point B){
        return this.x==B.getX()&&this.y==B.getY();
    }


     /**
     * Calcule la distance euclidienne entre ce point et un autre point spécifié.
     * 
     * @param position le point avec lequel calculer la distance.
     * @return la distance entre ce point et le point spécifié sous forme d'entier.
     */
    public int distance(Point position) {
        return (int) Math.sqrt(Math.pow(this.x - position.getX(), 2) + Math.pow(this.y - position.getY(), 2));        
    }

}
