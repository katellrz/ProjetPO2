package entites;

import java.util.List;
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
        this.PV = 30;
        this.ATK = 5;
        this.ATKSpeed = 1;
        this.Range = 3;
        this.element = Element.WATER;
        this.Speed = 1;
        this.Reward = 3;

    }
     /**
     * 
     * 
     * @return les points de vie maximaux.
     */

    @Override
    public int getMaxPV() {
        return PVmax;
    }  

    
    /**
     * Effectue l'attaque du WaterBrute en vérifiant si l'ennemi peut attaquer.
     * L'attaque cible les tours qui sont dans la portée de l'ennemi.
     * L'ennemi attaque les tours proches du cible ayant le moins de points de vie.
     * Les tours proches reçoivent des dégâts d'attaque.
     */
    @Override
    public void attaquer() {
        if (peutAttaquer()) {
            List<Tour> tours = Omnicient.getPositionTours();
            List<Tour> cibles = tours.stream()
                .filter(t -> t.getPosition().distance(this.position) <= this.Range)
                .toList();

            if (!cibles.isEmpty()) {
                Tour cible = MoinsDePV(cibles);
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
