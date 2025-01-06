package entites;

import java.util.List;
import java.util.Random;

import Gestion.Joueur;
import outils.Omnicient;

public class Buffer extends Enemi{
    /**
     * Constructeur par défaut de la classe EarthBrute.
     * Initialise les caractéristiques de l'EarthBrute avec des valeurs spécifiques.
     */
    
    public Buffer() {
        super(10, 2, 2, 3, Element.WIND, 5, 0.5);
        
    }

    public void attaquer(Joueur Joueur) {
        if (peutAttaquer()) {
            List<Tour> tours = Omnicient.getPositionTours();
            List<Tour> cibles = this.TourAportee(tours, this.Range);

            if (cibles == null || !cibles.isEmpty()) {

                Random rand = new Random();
                int randomNumber = rand.nextInt(cibles.size());

                Tour cible = cibles.get(randomNumber);
                if (cible != null) {
                    this.attaqueSimple(cible, Joueur);
                }
            }
        }
    }
}
