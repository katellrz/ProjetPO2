package Gestion;

import Librairies.StdDraw;
import java.awt.Font;


/**
 * La classe Joueur représente un joueur dans le jeu
 *  Elle gère les attributs de vie et d'argent, 
 * ainsi que les méthodes permettant de les modifier.
 */

public class Joueur {
    private int vie;
    private int argent;


    public Joueur() {
        this.vie = 100;
        this.argent = 50;
    }

    /**
     * Constructeur permettant d'initialiser la vie et l'argent du joueur avec des valeurs qui sont specifiques
     *
     * @param vie    La vie du joueur.
     * @param argent L'argent du joueur.
     */

    public Joueur(int vie, int argent) {
        this.vie = vie;
        this.argent = argent;
    }

    
    public int getVie() {
        return vie;
    }

    public Joueur copier() {
        return new Joueur(this.vie, this.argent); // Retourne une nouvelle instance avec les mêmes attributs
    }

    public void perdreVie(int montant) {
        this.vie -= montant;
    }

    public void getSoin(int montant) {
        this.vie += montant;
    }

    public int getArgent() {
        return argent;
    }

    public void gagnerArgent(int montant) {
        this.argent += montant;
    }

    public void depenserArgent(int montant) {
        if (argent >= montant) {
            this.argent -= montant;
        }
    }

    public void gagnerVie(int montant) {
        this.vie += montant;
    }

    public boolean peutAcheter(int montant) {
        return argent >= montant;
    }

    

    public void setVie(int vie) {
        this.vie = vie;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public String toStingVie(){
        return Integer.toString(vie);
    }

    public String toStringArgent(){
        return Integer.toString(argent);
    }


    /**
     * Affiche les informations du joueur (vie et argent) à l'écran
     */
    public void afficheInfo() {
        StdDraw.setPenColor(StdDraw.BLACK);
        Font front = new Font("Arial", Font.PLAIN, 30);
        StdDraw.setFont(front);
        StdDraw.text(911,636, this.toStingVie()); // Exemple de position et texte
        StdDraw.text(796,636, this.toStringArgent());
    }

}


