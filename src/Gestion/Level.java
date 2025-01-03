// Gestion/Level.java
package Gestion;

import java.util.ArrayList;
import java.util.List;

import Librairies.FileExtraction;

public class Level {
    private int currentlevel;
    private Wave waves;
    private final String map;
    private final List<String> fichier;
    private int currentWave;

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

    public int NbrWaves() {
        return fichier.size() - 1;
    }

    public boolean hasNextWave() {
        System.out.println("Current Wave: " + currentWave);
        System.out.println("Total Waves: " + (fichier.size() - 2));
        return currentWave < fichier.size() - 1;
    }

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

    public int getCurrentlevel() {
        return currentlevel;
    }

    public int getCurrentWave() {
        return currentWave;
    }
}
