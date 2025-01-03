package entites;

import java.util.List;

import Gestion.Joueur;
import outils.Omnicient;

/**
 * Représente un ennemi de type WindGrognard dans le jeu.
 * Hérite de la classe Enemi
 * 
 * Le WindGrognard est un ennemi rapide avec une attaque modérée et une portée d'attaque relativement élevée.
 * Il appartient à l'élément Vent et peut attaquer les tours proches dans son rayon d'action.
 * 
 */


 public class WindGrognard extends Enemi {

    public WindGrognard() {
        super(1, 7, 2, 5, Element.WIND, 1, 2);
    }

    @Override
    public void attaquer(Joueur Joueur) {
        if (peutAttaquer()) {
            List<Tour> tours = Omnicient.getPositionTours();
            List<Tour> cibles = this.TourAportee(tours, this.Range);

            if (cibles == null || !cibles.isEmpty()) {
                Tour cible = MoinsDePV(cibles);
                if (cible != null) {
                    this.attaqueSimple(cible, Joueur);
                }
            }
        }
    }
}
