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
        super(30, 5, 1, 3, Element.EARTH, 1, 3);
        
    }

    @Override
    public void attaquer() {
        System.out.println("Arrive la");
        
        if (peutAttaquer()) {
            System.out.println("Attaque de l'EarthBrute");
            List<Tour> tours = Omnicient.getPositionTours();
            List<Tour> cibles = tours.stream()
                .filter(t -> t.getPosition().distance(this.position) <= this.Range)
                .toList();

            if (!cibles.isEmpty()) {
                Tour cible = PlusProche(cibles);
                if (cible != null) {
                    cible.setPV(cible.getPV() - this.ATK);
                    System.out.println("Attaque de l'EarthBrute");
                    afficheattaque(cible);
                }
            }
        }
    }
}