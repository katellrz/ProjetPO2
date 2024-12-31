package entites;

import java.util.List;
import outils.Omnicient;

/**
 * Classe représentant un ennemi de type Boss.
 * Le Boss possède des statistiques plus élevées que les ennemis classiques,
 * comme des points de vie élevés, une forte attaque, et des capacités spécifiques.
 */

public class Boss extends Enemi {

    /**
     * Constructeur pour créer un ennemi de type Boss avec des caractéristiques par défaut.
     */
      public Boss() {
        super(150, 100, 10, 2, Element.EARTH, 100, 0.5);
        
    }

    /**
     * Permet au Boss d'attaquer les tours à portée.
     * Le Boss attaque la tour la plus proche à sa portée et réduit ses points de vie
     * selon sa puissance d'attaque (ATK).
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

