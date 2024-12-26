package Gestion;

import static outils.Omnicient.*;
import static Map.DetectionSouris.*;
import Librairies.StdDraw;
import Map.DetectionSouris;
import entites.Enemi;
import entites.Tour;

public abstract class Game {

    public static void Start(){

        
        Interface.AfficheInterface();
        Interface.AfficheStatique();
        Interface.AfficheDynamique("10-10");
       
        StdDraw.show();

        //Archer a1 = new Archer(1, 1, 1, 1, ElementType.NONE, 1,)

        Wave test = new Wave("waveMinion");

        while(true){

            DetectionSouris.DetectionSourisCase(StdDraw.mouseX(),StdDraw.mouseY()); 
            test.Vaguedemonstre();
            Interface.AfficheDynamique("10-10");

            Tour.PlacerTour();

            for(Enemi monstre : getPositionMonstre()){
                //System.out.println("Monstre en position : " + monstre);
                monstre.avance();
                monstre.apparait();
            }  
            
            for(Tour TOURS : getPositionTours()){
                //System.out.println("Monstre en position : " + monstre);
                
                TOURS.afficheTour(getSize());
            }  


            StdDraw.show();
        }


    }



}
