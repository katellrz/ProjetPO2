package entites;

import java.util.List;
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
    /**
     * Constructeur de l'ennemi WindGrognard.
     * Initialise les caractéristiques de l'ennemi (PV, ATK, ATKSpeed, Range, Speed, Reward)
     * spécifiques au WindGrognard.
     */
    public WindGrognard() {
        this.PV = 1;
        this.ATK = 7;
        this.ATKSpeed = 2;
        this.Range = 5;
        this.element = Element.WIND;
        this.Speed = 2;
        this.Reward = 1;

    }

    @Override
<<<<<<< HEAD
=======
    public int getMaxPV() {
        return PVmax;
    }  
     /**
     * Effectue l'attaque du WindGrognard contre les tours proches dans sa portée.
     * Si des tours sont dans sa portée, l'ennemi attaque la tour ayant les moins de points de vie.
     * Après l'attaque, la méthode afficheattaque est appelée pour afficher l'attaque.
     */

    @Override
>>>>>>> 5d6d35354f71ccd077c9a446355ffd45a66a445a
    public void attaquer() {
        if (peutAttaquer()) {

            List<Tour> tours = Omnicient.getPositionTours();
            List<Tour> cibles = tours.stream()
                .filter(t -> t.getPosition().distance(this.position) <= this.Range)
                .toList();

            if (!cibles.isEmpty()) {
                Tour cible = MoinsDePV(cibles);
                if (cible != null) {
                    cible.setPV(cible.getPV() - this.ATK);
                    afficheattaque(cible);
                }
            }
        }
    }
}
