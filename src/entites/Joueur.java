package entites;



public class Joueur {
    private int vie;
    private int argent;
    private double x, y;
    public Joueur(int vie, int argent, double x, double y) {
        this.vie = vie;
        this.argent = argent;
        this.x = x;
        this.y = y;
    }

    
    public int getVie() {
        return vie;
    }

    public void perdreVie(int montant) {
        this.vie -= montant;
    }

    public int getArgent() {
        return argent;
    }

    public boolean depenserArgent(int montant) {
        if (argent >= montant) {
            this.argent -= montant;
            return true;
        }
        return false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}


