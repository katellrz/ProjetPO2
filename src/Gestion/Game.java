package Gestion;

import Librairies.StdDraw;

public abstract class Game {

    public static void Start(){

        Interface.AfficheInterface();
        Interface.AfficheStatique();
        Interface.AfficheDynamique("10-10");
        StdDraw.show();

        while(true){
            Interface.AfficheDynamique("10-10");
            StdDraw.show();
        }


    }



}
