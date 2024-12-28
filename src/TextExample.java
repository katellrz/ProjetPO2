
import java.awt.Font;

import Librairies.StdDraw;

public class TextExample {
    public static void main(String[] args) {
        // Définir la taille du canevas
        StdDraw.setCanvasSize(800, 600);

        // Définir l'échelle du graphique
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);

        // Créer une police avec une taille spécifique
        Font font = new Font("Arial", Font.PLAIN, 10); // Arial, taille 20
        StdDraw.setFont(font);

        // Dessiner le texte
        StdDraw.text(50, 50, "Texte de taille 20");

        // Changer la taille de la police
        Font largerFont = new Font("Arial", Font.BOLD, 5);
        StdDraw.setFont(largerFont);

        // Dessiner un autre texte avec une police différente
        StdDraw.text(50, 70, "Texte de taille 40");
    }
}
