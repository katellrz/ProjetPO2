package Gestion;

import static outils.Omnicient.*;

import Librairies.StdDraw;
import Map.DetectionSouris;
import entites.Enemi;
import entites.Tour;


public abstract class Game {

    public static String Map; 
    public static String Wave;
    public static String Level;

    public static void Start(){

        StdDraw.enableDoubleBuffering();

        Interface.AfficheInterface();
        Interface.AfficheStatique();
        

        Wave test = new Wave("waveBoss");
        Joueur joueur = new Joueur();


        while(true){

            Interface.AfficheDynamique("10-10", joueur.getArgent(), joueur.getVie());
            joueur.afficheInfo();
            

            DetectionSouris.DetectionSourisCase(StdDraw.mouseX(),StdDraw.mouseY());

            test.Vaguedemonstre();

            

            Tour.PlacerTour();
            StdDraw.show();

            for(Enemi monstre : getPositionMonstre()){
                //System.out.println("Monstre en position : " + monstre);
                monstre.avance();
                monstre.apparait();
                monstre.attaquer();
                StdDraw.show();
            }  
            
            for(Tour TOURS : getPositionTours()){
                //System.out.println("Monstre en position : " + monstre);
                
                TOURS.afficheTour(getSize());
                TOURS.attaquer();
                StdDraw.show();
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
