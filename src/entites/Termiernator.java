package entites;

import static outils.Omnicient.getPositionTours;

import java.awt.Font;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import Gestion.Joueur;
import Librairies.StdDraw;

public class Termiernator extends Enemi {

    public Termiernator() {
        super(999, 999, 15, 0, Element.NONE, 100, 0.5);
    }

    
    @Override
    public void attaquer(Joueur Joueur) {

        if(this.peutAttaquer()){

            List<Tour> tours = getPositionTours();

            if (!(tours.isEmpty()||tours==null)){

                Random random = new Random();
                int randomInt = random.nextInt(tours.size());
                
                Tour cible = tours.get(randomInt);

                this.attaqueSimple(cible, Joueur);
                System.out.println("Termiernator "+this.PV + " a attaquer :"+cible);

            }
        }

        
    }

    private LocalTime LastBulle = LocalTime.now();
    private boolean Bulle = false;

    public void affichagebulle(){
        
        Duration d = Duration.between(LastBulle, LocalTime.now());
        tempsDepuisDerniereAttaque = d.toMillis();
        if(Bulle){            
            if (tempsDepuisDerniereAttaque >= 5*1000) {
                Bulle=false;
                LastBulle = LocalTime.now();
            }
        }else{
            if (tempsDepuisDerniereAttaque >= 15*1000) {
                Bulle=true;
                LastBulle = LocalTime.now();
            }
        }

        if(Bulle){
            afficheBulle();
        }

    }

    public void afficheBulle(){

        double x = this.position.getX()+5;
        double y = this.position.getY()+10;
        double taille = 10;

        /* StdDraw.setPenColor(new Color(173, 216, 230, 150)); // Bleu clair transparent
        StdDraw.filledCircle(x, y, taille / 2);
        StdDraw.setPenColor(new Color(135, 206, 250)); // Bleu clair
        StdDraw.circle(x, y, taille / 2); */

        Font f = new Font("Arial", Font.PLAIN, 10);
        StdDraw.setFont(f);

        StdDraw.text(x, y, "L'examen final approche");
    }
    
}
