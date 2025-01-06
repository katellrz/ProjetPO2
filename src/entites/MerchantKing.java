package entites;

import Gestion.Joueur;
import entites.Entite.Element;

public class MerchantKing extends Enemi {

    public MerchantKing() {
        super(100, 0, 0, 0, Element.EARTH, -10, 2);
    }

    
    @Override
    public void attaquer(Joueur Joueur) {
        // Les MerchantKing ne peuvent pas attaquer
    }

}
