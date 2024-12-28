package Gestion;

import static outils.Omnicient.*;
import static Map.DetectionSouris.*;
import Librairies.StdDraw;
import Map.DetectionSouris;
import entites.Archer;
import entites.Enemi;
import entites.Tour;
import entites.WindCaster;

public abstract class Game {

    public static void Start(){

        StdDraw.enableDoubleBuffering();

        Interface.AfficheInterface();
        Interface.AfficheStatique();
        Interface.AfficheDynamique("10-10");
       

        //Archer a1 = new Archer(1, 1, 1, 1, ElementType.NONE, 1,)

        Wave test = new Wave("waveMinion");

        

        while(true){

            DetectionSouris.DetectionSourisCase(StdDraw.mouseX(),StdDraw.mouseY()); 
            test.Vaguedemonstre();
            Interface.AfficheDynamique("10-10");

            Archer.afficheTourBoutique(50);
            WindCaster.afficheTourBoutique(50);
            

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

            if(StdDraw.isMousePressed()){
                System.out.println(DetectionZone(StdDraw.mouseX(), StdDraw.mouseY()));
            }


            StdDraw.show();

            StdDraw.pause(20);

            
        }


    }



}
