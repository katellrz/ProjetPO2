package entites;

import Gestion.Joueur;
import java.util.List;
import outils.Omnicient;


/**
 * cette classe  représente un ennemi de type Healer;
 * Le Healer est un ennemi spécialisé dans les soins, capable de restaurer les 
 * points de vie des ennemis alliés à portée.
 */

public class Healer extends Enemi  {

    /**
     * Constructeur par defaut de Healer
     * Initialise les caractéristiques spécifiques du Healer :
     * - PV (Points de Vie) : 10
     * - Dégâts : 1 (symbolique)
     * - Vitesse : 1
     * - Portée : 2
     * - Element : NONE (aucun élément particulier)
     * - Niveau : 5
     * - Gain d'expérience : 3
     */

    public Healer() {
        super(10, 1, 1, 2, Element.NONE, 05, 3);
    }


      /**
     * Permet au Healer d'attaquer, c'est a dire , de soigner un allié à portée,
     * Cette méthode recherche les ennemis alliés proches, identifie celui avec le moins de PV,
     * et lui applique des soins.
     *
     * @param joueur le joueur Note :c'est  utilisé dans certaines interactions, mais pas directement ici
     */
 @Override
public void attaquer(Joueur joueur) {
    if (peutAttaquer()) { 
       
        List<Enemi> ennemis = Omnicient.getPositionMonstre();
        // ici on Filtre les ennemis qui sont dans la portee du Healer
        List<Enemi> cibles = Aportee(ennemis, Range);
        if (cibles != null && !cibles.isEmpty()) {
            
            Enemi cible = MoinsDePVE(cibles);
            if (cible != null) {
                //permet d'aplliquer les soins à l'ennemi cible
                cible.recevoirSoins(5); 
            }
        }
    }
    
}

 /**
     * Permet à cet ennemi de recevoir des soins,
     * Si les points de vie après soin dépassent le maximum, ils sont ajustés à la limite maximale
     */
public void recevoirSoins()
{
      if(PV + 5 >PVmax)
      {
        PV=PVmax;
      }
else
    PV+=5;   
}


}

    
