package Gestion;

// IMPORTS ____________________________________
import Librairies.StdDraw;
import Map.Carte;
import entites.Archer;
import entites.EarthCaster;
import entites.FireCaster;
import entites.GoldDigger;
import entites.IceCaster;
import entites.WaterCaster;
import entites.WindCaster;
import java.awt.Color;
import java.awt.Font;

import Librairies.Point;

// _________________________________________

public abstract class Interface {

    /**
     * Fonction qui a pour rôle d'afficher le canvas (fenêtre de notre jeu).
     */
    public static void AfficheInterface() {
        StdDraw.setCanvasSize(1024, 720);
        StdDraw.setXscale(-12, 1012);
        StdDraw.setYscale(-10, 710);
        StdDraw.enableDoubleBuffering();
    }

    /**
     * Fonction qui affiche les éléments statiques (zone de jeu, cœur, pièce, etc.).
     */
    public static void AfficheStatique() {
        AfficheCadreMAP();
        AfficheCadrePLAYER();
        AfficheCoeur();
        AffichePiece();
        AfficheCadreLEVEL();
        AfficheCadreBOUTIQUE();
    }

    /**
     * Fonction qui affiche les éléments dynamiques du jeu.
     */
    public static void AfficheDynamique(Carte map, int Money, int vie, int currentLevel, int totalLevels, int currentWave, int totalWaves) {
        map.afficheCarte();
        Archer.afficheTourBoutique(Money);
        WindCaster.afficheTourBoutique(Money);
        WaterCaster.afficheTourBoutique(Money);
        EarthCaster.afficheTourBoutique(Money);
        FireCaster.afficheTourBoutique(Money);
        IceCaster.afficheTourBoutique(Money);
        GoldDigger.afficheTourBoutique(Money);
        BouttonTriche();
        afficherProgression(currentLevel, totalLevels, currentWave, totalWaves);
    }

    //ZONE MAP_____________________________________________________________________________________________________
    public static void AfficheCadreMAP() {
        Point center = new Point(350, 350);
        Point halfDist = new Point(350, 350);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    }

    //ZONE PLAYER__________________________________________________________________________________________________
    public static void AfficheCadrePLAYER() {
        Point center = new Point(856, 641);
        Point halfDist = new Point(144, 25);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    }

    public static void AfficheCoeur() {
        int halfHeight = 20;
        Point center = new Point(972, 641);
        StdDraw.setPenColor(new Color(223, 75, 95));
        double[] listX = {
            center.getX(),
            center.getX() - halfHeight,
            center.getX() - halfHeight,
            center.getX() - 0.66 * halfHeight,
            center.getX() - 0.33 * halfHeight,
            center.getX(),
            center.getX() + 0.33 * halfHeight,
            center.getX() + 0.66 * halfHeight,
            center.getX() + halfHeight,
            center.getX() + halfHeight,
        };
        double[] listY = {
            center.getY() - halfHeight,
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

    public static void AffichePiece() {
        Point centerC = new Point(740, 641);
        int radius = 20;
        StdDraw.setPenColor(new Color(212, 175, 55));
        StdDraw.filledCircle(centerC.getX(), centerC.getY(), radius);
        StdDraw.setPenColor(new Color(192, 192, 192));
        StdDraw.filledCircle(centerC.getX(), centerC.getY(), 0.7 * radius);
    }

    //ZONE LEVEL____________________________________________________________________________________________________
    public static void AfficheCadreLEVEL() {
        Point center = new Point(856, 688);
        Point halfDist = new Point(144, 12);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    }

     /**
     * Affiche le niveau et la vague actuels ainsi que leur nombre total.
     *
     * @param currentLevel  Le numéro du niveau actuel.
     * @param totalLevels   Le nombre total de niveaux.
     * @param currentWave   Le numéro de la vague actuelle.
     * @param totalWaves    Le nombre total de vagues pour le niveau actuel.
     */
    public static void afficherProgression(int currentLevel, int totalLevels, int currentWave, int totalWaves) {
        // Position de l'affichage
        double x = 100; // Coordonnée X pour le texte
        double y = 700; // Coordonnée Y pour le texte

        // Couleur de fond pour rendre le texte plus lisible
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.filledRectangle(x + 50, y + 20, 100, 30);

        // Couleur et style de texte
        StdDraw.setPenColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 14);
        StdDraw.setFont(font);

        // Texte d'affichage
        StdDraw.text(x, y + 30, "Niveau : " + currentLevel + " / " + totalLevels);
        StdDraw.text(x, y, "Vague : " + currentWave + " / " + totalWaves);
    }

    //ZONE BOUTIQUE________________________________________________________________________________________________
    public static void AfficheCadreBOUTIQUE() {
        Point center = new Point(856, 303);
        Point halfDist = new Point(144, 303);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), halfDist.getX(), halfDist.getY());
    }

    public static void MessageErrCaseNonConstructible() {
        Point center = new Point(856, 303);
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.filledRectangle(center.getX(), center.getY(), 40, 15);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(center.getX(), center.getY(), 40, 15);
        StdDraw.text(500, 500, "Case non constructible");
        StdDraw.show();
        StdDraw.pause(1000);
    }

    public static void BouttonTriche() {
        Point centerVie = new Point(784, 25);
        Point centerArgent = new Point(928, 25);
        Point halfDist = new Point(72, 25);
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.filledRectangle(centerVie.getX(), centerVie.getY(), halfDist.getX(), halfDist.getY());
        StdDraw.filledRectangle(centerArgent.getX(), centerArgent.getY(), halfDist.getX(), halfDist.getY());
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(centerVie.getX(), centerVie.getY(), halfDist.getX(), halfDist.getY());
        StdDraw.rectangle(centerArgent.getX(), centerArgent.getY(), halfDist.getX(), halfDist.getY());
        StdDraw.text(centerVie.getX(), centerVie.getY(), "Vie + 100");
        StdDraw.text(centerArgent.getX(), centerArgent.getY(), "Argent + 1000");
    }
}
