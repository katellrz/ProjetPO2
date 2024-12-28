import Librairies.StdDraw;
import entites.Joueur;
import java.awt.Color;

public class Main {
    public static void main(String[] args) {
    
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0, 1000);

       
        Joueur joueur = new Joueur(100, 200, 856, 641);

        while (true) {
            StdDraw.clear();

        
            StdDraw.setPenColor(new Color(192, 192, 192)); 
            StdDraw.filledCircle(joueur.getX(), joueur.getY(), 25); // Cercle pour représenter le joueur

            
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.text(100, 950, "Vie: " + joueur.getVie());
            StdDraw.text(300, 950, "Argent: " + joueur.getArgent());

            //  perte de vie avec la barre d'espace
            if (StdDraw.isKeyPressed(32)) { // Barre d'espace pour tester
                joueur.perdreVie(1);
            }

         
            StdDraw.show();
            StdDraw.pause(20);
        }
    }
}