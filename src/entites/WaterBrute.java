package entites;

import java.util.List;

import Gestion.Joueur;
import outils.Omnicient;

/**
 * Représente un ennemi de type WaterBrute dans le jeu.
 * Hérite de la classe Enemi
 * 
 * Cet ennmi possède des caractéristiques spécifiques, comme des points de vie (PV),
 * une attaque (ATK), une vitesse d'attaque (ATKSpeed), une portée (Range), et une récompense.
 * L'élément associé à cet ennemi est l'eau (Element.WATER).
 * 
 * L'attaque de cet ennemi cible les tours dans sa portée et inflige des dégâts à celles
 * ayant le moins de points de vie.
 * 

 */

public class WaterBrute extends Enemi {

    /**
     * Constructeur de la classe WaterBrute.
     * Initialise les caractéristiques de l'ennemi (PV, ATK, ATKSpeed, Range, Speed, Reward)
     * spécifiques à un WaterBrute.
     */
    
    public WaterBrute() {
        super(30, 5, 1, 3, Element.WATER, 3, 1);
    }

    
    /**
     * Effectue l'attaque du WaterBrute en vérifiant si l'ennemi peut attaquer.
     * L'attaque cible les tours qui sont dans la portée de l'ennemi.
     * L'ennemi attaque les tours proches du cible ayant le moins de points de vie.
     * Les tours proches reçoivent des dégâts d'attaque.
     * @param joueur 
     */
    @Override
    public void attaquer(Joueur Joueur) {
        if (peutAttaquer()) {
            List<Tour> tours = Omnicient.getPositionTours();
            List<Tour> cibles = this.TourAportee(tours, this.Range);

            if (cibles == null || !cibles.isEmpty()) {
                Tour cible = MoinsDePV(cibles);
                if (cible != null) {
                    this.attaqueSimple(cible, Joueur);
                    this.attaqueCollateral(cible,1.5, Joueur);
                }
            }
        }
    }
}
