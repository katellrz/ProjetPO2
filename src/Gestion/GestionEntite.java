package Gestion;

import static outils.Omnicient.*;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import Librairies.StdDraw;
import Map.DetectionSouris;
import entites.Bomb;
import entites.Buffer;
import entites.Empoisoner;
import entites.Enemi;
import entites.MerchantKing;
import entites.RailGun;
import entites.Tour;
import outils.Omnicient;

public class GestionEntite {

    public GestionEntite(){}

    public void gestionTour(Joueur joueur) {
        List<Tour> tours = getPositionTours();
        for (Tour tour : tours) {
            tour.afficheTour(getSize());
            tour.attaquer(joueur);
        }
        StdDraw.show();
        gestionEnemiAvctif(joueur);
    }

    private void gestionEnemiAvctif(Joueur joueur){
        List<Enemi> monstres = new ArrayList<>();
        for (Enemi monstre : getPositionMonstre()) {
            if(monstre.getPV()<= 0){
                monstres.add(monstre);
                joueur.gagnerArgent(monstre.getReward());
                if( monstre instanceof Bomb){
                    DegatKamicase((Bomb)monstre);
                }
            }            
        }
        for (Enemi monstre : monstres){
            removeEnemi(monstre);
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

                if(monstres instanceof MerchantKing){

                }
            }
        }
        for (Enemi monstre : monstres){
            removeEnemi(monstre);
        }
    }

    private void AfichePropMerchant(){

        StdDraw.setPenColor(Color.PINK);
        StdDraw.filledRectangle(512,360,250,125);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(512,360,250,125);

        StdDraw.setPenColor(Color.LIGHT_GRAY);

        StdDraw.filledRectangle(512,450,240,25);
        StdDraw.filledRectangle(512,390,240,25);
        StdDraw.filledRectangle(512,330,240,25);
        StdDraw.filledRectangle(512,270,240,25);

        StdDraw.setPenColor(Color.BLACK);
        Font font1 = new Font("Arial", Font.PLAIN, 17);
        StdDraw.setFont(font1);
        StdDraw.text(512,450, " +10% de puissance d’attaque sur toutes les Tours. Côut 200.");
        StdDraw.text(512,390, " -10% de vitesse de d´eplacement chez les ennemis. Côut 300");
        StdDraw.text(512,330,"  +10% de vitesse d’attaque sur les Tours. Côut 200.");
        StdDraw.text(512,270," Aucun bonus. + 30 pièces.");

        StdDraw.show();
    }

    public static void main(String[] args) throws Exception {
        Interface.AfficheInterface();
        GestionEntite e = new GestionEntite();
        e.AfichePropMerchant();
    }


    public void Empoisonement(){
        List<Empoisoner> monstres = getEmpoisoners();
        
        for (Empoisoner empoisoner : monstres) {
            empoisoner.degatEmpoisonement();
        }
        
    }

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

    public void gestionBuffer(){
        List<Buffer> buffer = Omnicient.getBuffer();
        List<Enemi> monstre = getPositionMonstre();

        for (Buffer b : buffer){
            List <Enemi> cibles = b.Aportee(monstre,b.getRange());

            for (Enemi e : cibles){
                e.buffer=true;
                e.effetBuffer();
            }
        }
    }


}
