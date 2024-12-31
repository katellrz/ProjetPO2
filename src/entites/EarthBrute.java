package entites;

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
     * Initialise les caractéristiques de l'EarthBrute avec des valeurs spécifiques.
     */
    
    public EarthBrute() {
        this.PV = 30;
        this.ATK = 5;
        this.ATKSpeed = 1;
        this.Range = 3;
        this.element = Element.EARTH;
        this.Speed = 1;
        this.Reward = 3;
        this.PVmax = PV;
    }


    /**
     * Retourne les points de vie maximum de l'EarthBrute.
     *
     * @return Les points de vie maximum de l'EarthBrute.
     */
    @Override
    public int getMaxPV() {
        return PVmax;
    }  

     /**
     * Permet à l'EarthBrute d'attaquer les tours à sa portée.
     * L'EarthBrute sélectionne la tour la plus proche et réduit ses points de vie
     * en fonction de sa puissance d'attaque (ATK).
     */

    @Override
    public void attaquer() {
        if (peutAttaquer()) {
            List<Tour> tours = Omnicient.getPositionTours();
            List<Tour> cibles = tours.stream()
                .filter(t -> t.getPosition().distance(this.position) <= this.Range)
                .toList();

            if (!cibles.isEmpty()) {
                Tour cible = PlusProche(cibles);
                if (cible != null) {
                    cible.setPV(cible.getPV() - this.ATK);
                    afficheattaque(cible);
                }
            }
        }
    }
}