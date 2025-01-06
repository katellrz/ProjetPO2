package entites;

import Gestion.Joueur;
import java.util.List;
import outils.Omnicient;

/**
 * Classe représentant un ennemi de type EarthBrute.
 * L'EarthBrute est un ennemi élémentaire terrestre avec des statistiques équilibrées,
 * une portée d'attaque raisonnable et une vitesse moyenne.
 */
public class EarthBrute extends Enemi {

    /**
     * Constructeur par défaut de la classe EarthBrute.
     * Initialise les caractéristiques de l'EarthBrute avec des valeurs spécifiques
     */
    
    public EarthBrute() {
        super(30, 5, 1, 3, Element.EARTH, 1, 3);
        
    }

    /**
     * Permet à l'EarthBrute d'attaquer un joueur s'il peut attaquer,
     * cette  méthode recherche les tours ennemies à portée, sélectionne la plus proche,
     * et effectue une attaque simple sur cette cible.
     *
     * @param Joueur le joueur ciblé par l'attaque.
     */

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