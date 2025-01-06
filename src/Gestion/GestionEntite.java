package Gestion;

import Librairies.StdDraw;
import Map.DetectionSouris;
import entites.Bomb;
import entites.Buffer;
import entites.Empoisoner;
import entites.Enemi;
import entites.MerchantKing;
import entites.RailGun;
import entites.Termiernator;
import entites.Tour;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import outils.Omnicient;
import static outils.Omnicient.*;


/**
 * La classe GestionEntite gere les entités du jeu, telles que les tours, les ennemis, et les interactions avec les joueurs,
 * Elle s'occupe des actions comme l'attaque, la gestion des monstres, l'affichage des informations sur les ennemis,
 * et la gestion des objets speciaux comme les bombes ou les marchands
 */
public class GestionEntite {

    public GestionEntite(){}

     /**
     * se charge de l'action des tours dans le jeu, y compris l'affichage et l'attaque des ennemis
     * @param joueur Le joueur actuel qui contrôle les tours
     */

    public void gestionTour(Joueur joueur) {
        List<Tour> tours = getPositionTours();
        for (Tour tour : tours) {
            tour.afficheTour(getSize());
            tour.attaquer(joueur);
        }
        StdDraw.show();
        gestionEnemiAvctif(joueur);
    }


    /**
     * Gère les ennemis actifs et les interactions avec eux récompenses, dégâts..
     * @param joueur Le joueur actuel qui interagit avec les ennemis
     */
    private void gestionEnemiAvctif(Joueur joueur){
        List<Enemi> monstres = new ArrayList<>();
        for (Enemi monstre : getPositionMonstre()) {
            if(monstre.getPV()<= 0){
                monstres.add(monstre);
                System.out.println("Monstre "+monstre+" estMort");
                joueur.gagnerArgent(monstre.getReward());
                if( monstre instanceof Bomb){
                    DegatKamicase((Bomb)monstre);
                }  
            }          
        }
        for (Enemi e : monstres){
            removeEnemi(e);
        }
    }
    

    public void gestionTermiernator(){
        List<Termiernator> l = Omnicient.getTermiernator();

        for(Termiernator t : l){
            t.affichagebulle();
        }
    }


    private void DegatKamicase(Bomb b){
        List<Tour> t = getPositionTours();
        List<Tour> victime = b.TourAportee(t, 1.5);
        for (Tour to : victime){
            to.setPV(to.getPV()-10*b.getATK());
            gestionToursActives();
        }
    }

    public void gestionToursActives(){
        List<Tour> tours = new ArrayList<>();
        for (Tour tour : getPositionTours()) {
            if(tour.getPV()<= 0){
                tours.add(tour);
            }
            StdDraw.show();
        }
        for (Tour tour : tours){
            removeTour(tour);
        }
    }

    public void gestionEnemi(Joueur joueur) {
        List<Enemi> monstres = getPositionMonstre();
        for (Enemi monstre : monstres) {
            monstre.avance(joueur);
            monstre.apparait();
            monstre.attaquer(joueur);
        }
        gestionEnemiArriver(joueur);
        gestionToursActives();
    }

    public void gestionEnemiArriver(Joueur joueur){
        List<Enemi> monstres = new ArrayList<>();
        for (Enemi monstre : getPositionMonstre()) {
            if(monstre.getPosition().equals(getBase().getCenterCase())){
                monstres.add(monstre);
                joueur.perdreVie(monstre.getATK());
                System.out.println("ARRive ici ");
                if(monstres instanceof MerchantKing){
                    System.out.println("ARRive la ");
                    AfichePropMerchant(joueur);
                }
            }
        }
        for (Enemi monstre : monstres){
            removeEnemi(monstre);
        }
    }


    /**
     * Affiche les propositions du marchand (MerchantKing) avec des options d'achat pour le joueur
     * @param j Le joueur qui peut acheter des améliorations ou obtenir de l'argent
     */

    private void AfichePropMerchant(Joueur j){

        StdDraw.setPenColor(Color.PINK);
        StdDraw.filledRectangle(512,360,250,125);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(512,360,250,125);

        if(j.peutAcheter(200)){
            StdDraw.setPenColor(Color.LIGHT_GRAY);
        }else{ 
            StdDraw.setPenColor(Color.GRAY);
        }

        StdDraw.filledRectangle(512,450,240,25);
        StdDraw.filledRectangle(512,330,240,25);
       

        if(j.peutAcheter(300)){
            StdDraw.setPenColor(Color.LIGHT_GRAY);
        }else{ 
            StdDraw.setPenColor(Color.GRAY);
        }

        StdDraw.filledRectangle(512,390,240,25);

        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.filledRectangle(512,270,240,25);

        StdDraw.setPenColor(Color.BLACK);
        Font font1 = new Font("Arial", Font.PLAIN, 17);
        StdDraw.setFont(font1);
        StdDraw.text(512,450, " +10% de puissance d’attaque sur toutes les Tours. Côut 200.");
        StdDraw.text(512,390, " -10% de vitesse de déplacement chez les ennemis. Côut 300");
        StdDraw.text(512,330,"  +10% de vitesse d’attaque sur les Tours. Côut 200.");
        StdDraw.text(512,270," Aucun bonus. + 30 pièces.");

        StdDraw.show();

        selectionPropMerchant(j);
    }


       /**
     * Permet au joueur de selectionner une proposition du marchand
     * @param joueur c'est le joueur qui interagit avec le marchand
     */
    private void selectionPropMerchant(Joueur joueur){

        while(true){

            double x=StdDraw.mouseX();
            double y=StdDraw.mouseY();

            if(x>272&&x<752&&y>425&&y<475&&StdDraw.isMousePressed()&&joueur.peutAcheter(200)){
                Omnicient.MerchantATKTour();
                joueur.depenserArgent(200);
                System.out.println("1");
                return;
            }else if(x>272&&x<752&&y>365&&y<415&&StdDraw.isMousePressed()&& joueur.peutAcheter(300) ){
                Omnicient.MerchantSpeedEnemi();
                joueur.depenserArgent(300);
                System.out.println("2");
                return;
            }else if(x>272&&x<752&&y>305&&y<355&&StdDraw.isMousePressed() &&joueur.peutAcheter(200)){
                Omnicient.MerchantATKspeedTour();
                joueur.depenserArgent(200);
                System.out.println("3");
                return;
            }else if(x>272&&x<752&&y>245&&y<295&&StdDraw.isMousePressed()){
                joueur.gagnerArgent(30);
                System.out.println("4");
                return;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Interface.AfficheInterface();
        GestionEntite e = new GestionEntite();
        Joueur j = new Joueur();
        e.AfichePropMerchant(j);
    }



    /**
     * Gere l'effet d'empoisonnement infligé par des ennemis de type Empoisoner
     */

    public void Empoisonement(){
        List<Empoisoner> monstres = getEmpoisoners();
        
        for (Empoisoner empoisoner : monstres) {
            empoisoner.degatEmpoisonement();
        }
        
    }
      /**
     * Permet de gerer l'attaque des ennemis en cliquant sur la carte avec un RailGun
     */

    public void ClickEnemi(){
        //System.out.println("ici");
        if(StdDraw.isMousePressed()&&DetectionSouris.DetectionZone(StdDraw.mouseX(),StdDraw.mouseY()).equals("Zone Map")){
            //System.out.println("ici2");
            for (RailGun r : Omnicient.GetRailGunList()){
                r.attaqueClick();
                System.out.println("ici");
            }
        }
    }

     /**
     * Gere les effets de buffer appliqués aux ennemis
     */

    public void gestionBuffer(){
        List<Buffer> buffer = Omnicient.getBuffer();
        List<Enemi> monstre = getPositionMonstre();

        for (Buffer b : buffer){
            List <Enemi> cibles = b.Aportee(monstre,b.getRange());

            System.out.println("buffer");

            for (Enemi e : cibles){
                e.buffer=true;
                e.effetBuffer();
            }
        }
    }


}
