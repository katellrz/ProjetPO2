// Gestion/Level.java
package Gestion;

import java.util.ArrayList;
import java.util.List;

import Librairies.FileExtraction;

/**
 * LEvel  reprsente  un niveau dans le jeu
 * Un niveau est compose d'une carte (map) et d'une série de vagues d'ennemis (waves)
 * Chaque niveau est défini par un fichier contenant les informations nécessaires
 */

public class Level {
     /** Le numero du niveau courant */
    private int currentlevel;

  /** La vague d'ennemis actuellement active dans le niveau */
    private Wave waves;
     /** La carte (map) associée au niveau */
    private final String map;

  /** Liste des vagues définies dans le fichier de niveau */
    private final List<String> fichier;
    /** Indice de la vague */
    private int currentWave;

    /**
     * Constructeur de la classe Level.
     * Initialise le niveau à partir d'un fichier contenant la carte et les vagues d'ennemis
     *
     * @param currentlevel le numero du niveau
     * @throws IllegalArgumentException si le fichier du niveau est invalide
     */

    public Level(int currentlevel) {
        this.currentlevel = currentlevel;
        this.fichier = FileExtraction.ExtraireFichier("resources/levels/level" + currentlevel + ".lvl");

        if (fichier.size() < 2) {
            throw new IllegalArgumentException("Fichier de niveau invalide : level" + currentlevel + ".lvl. Il doit contenir au moins une carte et une vague.");
        }

        this.map = fichier.get(0);
        //fichier.remove(map);//map est une string
        this.currentWave = 1;
        this.waves = new Wave(fichier.get(currentWave));
    }


    /**
     * Retourne le nombre total de vagues dans le niveau.
     *
     * @return le nombre de vagues 
     */
    public int NbrWaves() {
        return fichier.size() - 1;
    }

    /**
     * ici on verfifie s'il reste une vague à jouer après la vague actuelle
     *
     * @return true s'il existe une vague suivante, false sinon
     */
    public boolean hasNextWave() {
        System.out.println("Current Wave: " + currentWave);
        System.out.println("Total Waves: " + (fichier.size() - 2));
        return currentWave < fichier.size() - 1;
    }

       /**
     * Extrait toutes les vagues du fichier de niveau.
     *
     * @return une liste d'instances de {@link Wave}, qui represente toutes les vagues du niveau.
     */

    public List<Wave> extraiWaves() {
        List<Wave> waves = new ArrayList<>();
        fichier.stream().skip(1).forEach(ligne -> waves.add(new Wave(ligne)));
        return waves;
    }

    public void resetWave() {
        currentWave = 1;
        this.waves = new Wave(fichier.get(currentWave));
    }

    
    public Wave getCurrentWaves() {
        return waves;
    }

     /**
     * Passe à la vague suivante si elle existe
     * Si aucune vague suivante n'existe, affiche un message d'erreur
     */
    public void nextWave() {
        if (currentWave < fichier.size() - 2) {
            currentWave++;
            this.waves = new Wave(fichier.get(currentWave));
        } else {
            System.out.println("Erreur : Tentative de passer à une vague inexistante !");
        }
    }

    public String getMap() {
        return map;
    }
     /**
     * Retourne le numero du niveau courant
     *
     * @return le numero du niveau.
     */

    public int getCurrentlevel() {
        return currentlevel;
    }
     /**
     * Retourne l'indice de la vague actuellement active
     *
     * @return l'indice  de la vague
     */

    public int getCurrentWave() {
        return currentWave;
    }
}
