package entites;

import java.util.List;

import Gestion.Joueur;
import outils.Omnicient;

/**
 * Classe représentant un ennemi de type EarthBrute.
 * L'EarthBrute est un ennemi élémentaire terrestre avec des statistiques équilibrées,
 * une portée d'attaque raisonnable et une vitesse moyenne.
 */
public class EarthBrute extends Enemi {

    /**
     * Constructeur par défaut de la classe EarthBrute.
     * Initialise les caractéristiques de l'EarthBrute avec des valeurs spécifiques.
     */
    
    public EarthBrute() {
        super(30, 5, 1, 3, Element.EARTH, 1, 3);
        
    }

    public void attaquer(Joueur Joueur) {
        if (peutAttaquer()) {
            List<Tour> tours = Omnicient.getPositionTours();
            List<Tour> cibles = this.TourAportee(tours, this.Range);

            if (cibles == null || !cibles.isEmpty()) {
                Tour cible = PlusProche(cibles);
                if (cible != null) {
                    this.attaqueSimple(cible, Joueur);
                }
            }
        }
    }
}