package entites;

import java.util.List;
import Gestion.Joueur;
import outils.Omnicient;

public class Healer extends Enemi  {

    public Healer() {
        super(10, 1, 1, 2, Element.NONE, 05, 3);
    }

 @Override
public void attaquer(Joueur joueur) {
    if (peutAttaquer()) { 
       
        List<Enemi> ennemis = Omnicient.getPositionEnnemis();
        // ici on Filtre les ennemis qui sont dans la portee du Healer
        List<Enemi> cibles = this.EnnemisAportee(ennemis, this.Range);

        if (cibles != null && !cibles.isEmpty()) {
            
            Enemi cible = MoinsDePV(cibles);
            if (cible != null) {
                //ppermet d'aplliquer les soins à l'ennemi cible
                cible.recevoirSoins(5); 
                System.out.println("Healer soigne l'ennemi " + cible.getNom() + " de 5 PV.");
            }
        }
    }
}
   
}