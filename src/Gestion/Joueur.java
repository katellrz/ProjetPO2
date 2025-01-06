package Gestion;

import java.awt.Font;

import Librairies.StdDraw;

public class Joueur {
    private int vie;
    private int argent;


    public Joueur() {
        this.vie = 100;
        this.argent = 50;
    }

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


    public void afficheInfo() {
        StdDraw.setPenColor(StdDraw.BLACK);
        Font front = new Font("Arial", Font.PLAIN, 30);
        StdDraw.setFont(front);
        StdDraw.text(911,636, this.toStingVie()); // Exemple de position et texte
        StdDraw.text(796,636, this.toStringArgent());
    }

}


