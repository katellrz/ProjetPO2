package Gestion;

import static outils.Omnicient.*;
import static Map.DetectionSouris.*;
import Librairies.StdDraw;
import Map.DetectionSouris;
import entites.Archer;
import entites.EarthCaster;
import entites.Enemi;
import entites.FireCaster;
import entites.Tour;
import entites.WaterCaster;
import entites.WindCaster;


public abstract class Game {

    public static String Map; 
    public static String Wave;
    public static String Level;

    public static void Start(){

        StdDraw.enableDoubleBuffering();

        Interface.AfficheInterface();
        Interface.AfficheStatique();
        

        Wave test = new Wave("waveMinion");
        Joueur joueur = new Joueur();


        while(true){

            DetectionSouris.DetectionSourisCase(StdDraw.mouseX(),StdDraw.mouseY());

            test.Vaguedemonstre();

            Interface.AfficheDynamique("10-10", joueur.getArgent(), joueur.getVie());

            

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

    public static void dessinerTours() {
        for (Tour tour : getPositionTours()) { // Parcourez toutes les tours
            //if (tour.estActive()) { // Redessinez uniquement si nécessaire
                tour.afficheTour(getSize());
            //}
        }
    }

    public static void dessinerMonstres() {
        for (Enemi monstre : getPositionMonstre()) { // Parcourez tous les monstres
            //if (monstre.estActif()) { // Redessinez uniquement si nécessaire
                monstre.apparait();
            //}
        }
    }   

    
    



}
