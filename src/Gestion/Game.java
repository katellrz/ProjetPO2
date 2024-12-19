package Gestion;

import Librairies.StdDraw;
import entites.Enemi;
import entites.Minion;

public abstract class Game {

    public static void Start(){

        Interface.AfficheInterface();
        Interface.AfficheStatique();
        Interface.AfficheDynamique("10-10");
        StdDraw.show();

        //Archer a1 = new Archer(1, 1, 1, 1, ElementType.NONE, 1,)

        Enemi monstre = new Minion();

        while(true){
            Interface.AfficheDynamique("10-10");

            monstre.avance();
            monstre.apparait();


            StdDraw.show();
        }


    }



}
