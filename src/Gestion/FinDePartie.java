package Gestion;

import Librairies.StdDraw;
import java.awt.Color;
import java.awt.Font;

public class FinDePartie {

    /**
     * Affiche une fenêtre de fin de partie en cas de victoire.
     */
    public static void afficherVictoire() {
        StdDraw.clear();
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledRectangle(400, 400, 200, 100);

        // Texte de victoire
        StdDraw.setPenColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 24);
        StdDraw.setFont(font);
        StdDraw.text(400, 500, "VICTOIRE !");
        
        // Bouton Quitter
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.filledRectangle(400, 300, 80, 30);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(400, 300, 80, 30);
        StdDraw.text(400, 300, "Quitter");
        
        StdDraw.show();
        
        attendreClicVictoire();
    }

    /**
     * Affiche une fenêtre de fin de partie en cas de défaite.
     */
    public static void afficherDefaite() {
        StdDraw.clear();
        StdDraw.setPenColor(Color.RED);
        StdDraw.filledRectangle(400, 400, 250, 150);

        // Texte de défaite
        StdDraw.setPenColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 24);
        StdDraw.setFont(font);
        StdDraw.text(400, 500, "DEFAITE...");
        
        // Boutons
        afficherBouton(300, 300, "Reprendre");
        afficherBouton(500, 300, "Recommencer");
        afficherBouton(400, 200, "Quitter");

        StdDraw.show();
        
        attendreClicDefaite();
    }

    /**
     * Affiche un bouton.
     * 
     * @param x      Coordonnée X du centre du bouton.
     * @param y      Coordonnée Y du centre du bouton.
     * @param texte  Texte du bouton.
     */
    private static void afficherBouton(double x, double y, String texte) {
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.filledRectangle(x, y, 80, 30);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(x, y, 80, 30);
        StdDraw.text(x, y, texte);
    }

    /**
     * Attend un clic pour la fenêtre de victoire.
     */
    private static void attendreClicVictoire() {
        while (true) {
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                
                // Quitter
                if (x >= 320 && x <= 480 && y >= 270 && y <= 330) {
                    System.exit(0);
                }
            }
        }
    }

    /**
     * Attend un clic pour la fenêtre de défaite.
     */
    private static void attendreClicDefaite() {
        while (true) {
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                
                // Reprendre
                if (x >= 220 && x <= 380 && y >= 270 && y <= 330) {
                    System.out.println("Reprendre le niveau...");
                    // Implémentez ici la logique pour reprendre le niveau
                    break;
                }
                // Recommencer
                else if (x >= 420 && x <= 580 && y >= 270 && y <= 330) {
                    System.out.println("Recommencer depuis le début...");
                    // Implémentez ici la logique pour recommencer le jeu
                    break;
                }
                // Quitter
                else if (x >= 320 && x <= 480 && y >= 170 && y <= 230) {
                    System.exit(0);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        // Exemple de test
        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 800);
        StdDraw.setYscale(0, 800);
        
        afficherVictoire();  // Pour tester la victoire
        afficherDefaite();   // Pour tester la défaite
    }
}

