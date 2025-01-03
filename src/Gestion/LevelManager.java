package Gestion;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {
    private List<Level> levels;
    private int nbrLvls;
    private int currentLvl;

    public LevelManager() {
        this.nbrLvls = 3;
        this.levels = loadLevels();
        this.currentLvl = 1;
    }

    public boolean isLast() {
        return currentLvl == nbrLvls;
    }

    public List<Level> loadLevels() {
        List<Level> l = new ArrayList<>();
        for (int i = 1; i <= nbrLvls; i++) {
            Level lvl = new Level(i);
            l.add(lvl);
        }
        return l;
    }

    public Level getCurrentLvl() {
        if (currentLvl - 1 < levels.size()) {
            return levels.get(currentLvl - 1);
        } else {
            throw new IndexOutOfBoundsException("Niveau courant invalide : " + currentLvl);
        }
    }

    public void nextLvl() {
        if (currentLvl < nbrLvls) {
            currentLvl++;
        } else {
            System.out.println("Aucun niveau supplémentaire. Vous avez terminé tous les niveaux !");
        }
    }

    public int getMaxLvl(){
        return nbrLvls;
    }
}
    

