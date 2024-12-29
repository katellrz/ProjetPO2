package Gestion;

public class Joueur {
    private int vie;
    private int argent;


    public Joueur() {
        this.vie = 100;
        this.argent = 50;
    }

    
    public int getVie() {
        return vie;
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

    public boolean peutAcheter(int montant) {
        return argent >= montant;
    }

}


