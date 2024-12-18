package Gestion;


//IMPORT____________________________________
import Librairies.StdDraw;
import Map.Carte;

import java.awt.Color;
import Librairies.Point;

// _________________________________________

public abstract class Interface{


    /**
     * Fonctionn qui a pour role d'afficher le canvas (fentre de notre jeux)
     */
    public static void AfficheInterface(){

        StdDraw.setCanvasSize(1024, 720);
        StdDraw.setXscale(-12, 1012);
        StdDraw.setYscale(-10, 710);
        StdDraw.enableDoubleBuffering();
        StdDraw.show();
       
    }

    /**
     * Fonction qui a pour role d'afficher les chose qui ne varie jamais (zone de jeux, coeur, piece...)
     */
    public static void AfficheStatique(){
        AfficheCadreMAP();

        AfficheCadrePLAYER();
        AfficheCoeur();
        AffichePiece();

        AfficheCadreLEVEL();

        AfficheCadreBOUTIQUE();

        StdDraw.show();

    }

    /**
     * Fonction qui a pour role d'afficher les elemnt suceptible de changer durant le jeux 
     */
    public static void AfficheDynamique(String nom){
        System.out.println("ici");
        afficheCarte(nom);

        
        
    }
    

    

    //ZONE MAP_____________________________________________________________________________________________________
    
    public static void AfficheCadreMAP(){

        Point center = new Point(350,350);
        Point halfDist = new Point(350, 350);

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    }

    public static void afficheCarte (String nom){
        System.out.println("la");
        Carte c = new Carte(nom);
        System.out.println(c.toString());
        c.afficheCarte();
        System.out.println("tj la ");

        StdDraw.show();
    }
    
    //ZONE PLAYER__________________________________________________________________________________________________

    public static void AfficheCadrePLAYER(){
        
        Point center = new Point(856,641);
        Point halfDist = new Point(144, 25);

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    }

    public static void AfficheCoeur(){

        int halfHeight = 20;
        Point center = new Point(972, 641);//TODO verifier la valeur de x
    
        StdDraw.setPenColor(new Color(223, 75, 95));
        double[] listX = new double[]
        {
            center.getX(),
            center.getX()- halfHeight,
            center.getX()- halfHeight,
            center.getX()- 0.66 * halfHeight,
            center.getX()- 0.33 * halfHeight,
            center.getX(),
            center.getX() + 0.33 * halfHeight,
            center.getX() + 0.66 * halfHeight,
            center.getX() + halfHeight,
            center.getX() + halfHeight,
        };
        double[] listY = new double[]
        {
            center.getY()- halfHeight,
            center.getY(),
            center.getY() + 0.5 * halfHeight,
            center.getY() + halfHeight,
            center.getY() + halfHeight,
            center.getY() + 0.5 * halfHeight,
            center.getY() + halfHeight,
            center.getY() + halfHeight,
            center.getY() + 0.5 * halfHeight,
            center.getY(),
        };
        StdDraw.filledPolygon(listX, listY);

    }

    public static void AffichePiece(){

        Point centerC = new Point(740, 641);
        int radius = 20;

        StdDraw.setPenColor(new Color(212, 175,55));
        StdDraw.filledCircle(centerC.getX(), centerC.getY(), radius);
        StdDraw.setPenColor(new Color(192, 192,192));
        StdDraw.filledCircle(centerC.getX(), centerC.getY(), 0.7 * radius);
    }

    //ZONE LEVEL___________________________________________________________________________________________________

    public static void AfficheCadreLEVEL(){
        
        Point center = new Point(856,688);
        Point halfDist = new Point(144, 12);

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    }

    //ZONE BOUTIQUE________________________________________________________________________________________________
    
    public static void AfficheCadreBOUTIQUE(){
        
        Point center = new Point(856,303);
        Point halfDist = new Point(144, 303);

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    }
}