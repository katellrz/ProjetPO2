package entites;

import static outils.Omnicient.getPositionTours;

import java.util.List;


/**
 * 
 * La Bomb est un ennemi specialisé dans les attaques explosives avec des dégâts collatéraux
 */

public class Bomb  extends  Enemi{

    public Bomb()
    {
        super(1, 5, 2, 3, Element.FIRE, 2, 2);
    }




/**
     * 
     * La Bomb effectue une attaque simple sur une cible et inflige des dégâts collatéraux 
     * aux cibles proches
     */

    public void attaquer(){
        if(peutAttaquer()){

            List<Tour> tours = getPositionTours();

            if(!tours.isEmpty()&&tours!=null){
                List<Tour> Cibles = TourAportee(tours, this.Range);

                if(!Cibles.isEmpty()&&Cibles!=null){

                    Tour cible = MoinsDePV(Cibles);

                    this.attaqueSimple(cible, null);
                    this.attaqueCollateral(cible,1.5,null);
                }

            }
    
        }
    }

   
}


