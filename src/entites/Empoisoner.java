package entites;

import Librairies.StdDraw;
import java.time.LocalTime;

/**
 * La classe  Empoisoner represente un mecanisme qui empoisonne un ennemi, 
 * infligeant des dégâts au fil du temps, Elle gère l'application des dégâts 
 * et l'affichage visuel de l'empoisonnement
 */

public class Empoisoner {

    private Enemi empoisoner; 
    private LocalTime dernierAttaque;

     /**
     * Constructeur de la classe Empoisoner.
     *
     * @param empoisoner c'est l'ennemi qui subit l'empoisonnement
     */

    public Empoisoner(Enemi empoisoner) {
        this.empoisoner = empoisoner;
        this.dernierAttaque = LocalTime.now();
    }


    /**
     * cette methode applique les degâts d'empoisonnement à l'ennemi toutes les 2 secondes 
     * tant que ses points de vie (PV) sont superieurs à zéro
     */

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

    /**
     * cette methode affiche une indication visuelle de l'empoisonnement sur la position de l'ennemi,
     * puis Un carré rose est dessiné autour de l'ennemi 
     */

    public void afficheEmpoisonement(){
        StdDraw.setPenColor(StdDraw.PINK);
        StdDraw.square(empoisoner.getPosition().getX(), empoisoner.getPosition().getY(), 5);    
    }


    
}
