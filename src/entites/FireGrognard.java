package entites;

import java.util.List;
import outils.Omnicient;

/**
 * Classe representant un ennemi de type FireGrognard.
 * Le FireGrognard est un ennemi utilisant l'élément FEU pour attaquer les tours
 * à portée. Il possède des caractéristiques spécifiques comme ses points de vie (PV),
 * attaque (ATK), vitesse d'attaque, portée, vitesse de déplacement, et récompense.
 */

public class FireGrognard extends Enemi {
    

    /**
     * Constructeur de la classe FireGrognard.
     * Initialise les attributs specifiques de l'ennemi.
     */
    public FireGrognard() {
        super(1, 7, 2, 3, Element.FIRE, 1, 2);
    }
    

     /**
     * Attaque les tours à portée du FireGrognard.
     * Le FireGrognard inflige des dégâts à la tour la plus proche dans sa portée.
     * Les tours à proximité immédiate de la cible principale (distance <= 1.5)
     * subissent également des dégâts.
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
                    for (Tour t : cibles) {
                        if (t.getPosition().distance(cible.getPosition()) <= 1.5) {
                            t.setPV(t.getPV() - this.ATK);
                        }
                    }
                    afficheattaque(cible);
                }
            }
        }
    }
    
}