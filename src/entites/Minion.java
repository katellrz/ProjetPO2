package entites;

import Gestion.Joueur;


public class Minion extends Enemi {
    
    public Minion() {
        super(10, 3, 0, 4, Element.NONE, 1, 1);
    }

    
    @Override
    public void attaquer(Joueur Joueur) {
        // Les Minions ne peuvent pas attaquer
    }
}


