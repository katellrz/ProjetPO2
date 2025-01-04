package entites;

import java.time.LocalTime;

import Librairies.StdDraw;

public class Empoisoner {

    private Enemi empoisoner; 
    private LocalTime dernierAttaque;

    public Empoisoner(Enemi empoisoner) {
        this.empoisoner = empoisoner;
        this.dernierAttaque = LocalTime.now();
    }

    public void degatEmpoisonement(){
        if (empoisoner.getPV() > 0) {
            if (LocalTime.now().getSecond() - dernierAttaque.getSecond() >= 2) {

                System.out.println("Empoisonement de l'enemi");
                empoisoner.setPV(empoisoner.getPV() - 1);
                dernierAttaque = LocalTime.now();
                afficheEmpoisonement();
            }
        }
    }

    public void afficheEmpoisonement(){
        StdDraw.setPenColor(StdDraw.PINK);
        StdDraw.square(empoisoner.getPosition().getX(), empoisoner.getPosition().getY(), 5);    
    }


    
}
