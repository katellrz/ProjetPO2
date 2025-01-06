package entites;

import static outils.Omnicient.getPositionTours;

import java.util.List;

public class Bomb  extends  Enemi{

    public Bomb()
    {
        super(1, 5, 2, 3, Element.FIRE, 2, 2);
    }






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


