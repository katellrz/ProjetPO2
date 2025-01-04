package entites;

import Gestion.Joueur;


public class Minion extends Enemi {
    
    public Minion() {
        super(10, 10, 0, 4, Element.NONE, 1, 10);//TODO: Changer les valeurs
    }

    
    @Override
    public void attaquer(Joueur Joueur) {
        // Les Minions ne peuvent pas attaquer
    }
}


