package Gestion;

import Librairies.StdDraw;
import java.awt.Color;
import java.awt.Font;

public class FinDePartie {

    public FinDePartie(){}

    /**
     * Affiche une fenêtre de fin de partie en cas de victoire.
     */
    public void afficherVictoire() {
        StdDraw.clear();
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledRectangle(400, 400, 200, 200);

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
    public boolean afficherDefaite() {
        StdDraw.clear();
        StdDraw.setPenColor(Color.RED);
        StdDraw.filledRectangle(400, 400, 250, 150);

        // Texte de défaite
        StdDraw.setPenColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 24);
        StdDraw.setFont(font);
        StdDraw.text(400, 500, "DEFAITE...");
        
        // Boutons
        afficherBouton(300, 300, "Quitter");
        afficherBouton(500, 300, "Retenter le niveau ");
        

        StdDraw.show();
        
        return attendreClicDefaite();
    }

    /**
     * Affiche un bouton.
     * 
     * @param x      Coordonnée X du centre du bouton.
     * @param y      Coordonnée Y du centre du bouton.
     * @param texte  Texte du bouton.
     */
    private void afficherBouton(double x, double y, String texte) {
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.filledRectangle(x, y, 80, 30);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(x, y, 80, 30);
        StdDraw.text(x, y, texte);
    }

    /**
     * Attend un clic pour la fenêtre de victoire.
     */
    private void attendreClicVictoire() {
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
    private boolean attendreClicDefaite() {
        while (true) {
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                
                // Quitter
                if (x >= 220 && x <= 380 && y >= 270 && y <= 330) {
                    System.exit(0);
                    return false;
                }
                // Recommencer
                else if (x >= 420 && x <= 580 && y >= 270 && y <= 330) {
                    
                    return true;
                }
            }
        }
    }
    
}

