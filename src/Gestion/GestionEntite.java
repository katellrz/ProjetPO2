package Gestion;

import static outils.Omnicient.getBase;
import static outils.Omnicient.getPositionMonstre;
import static outils.Omnicient.getPositionTours;
import static outils.Omnicient.getSize;
import static outils.Omnicient.removeEnemi;
import static outils.Omnicient.removeTour;

import java.util.ArrayList;
import java.util.List;

import Librairies.StdDraw;
import entites.Enemi;
import entites.Tour;

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
                monstres.add(monstre);//TODO bomb
                joueur.gagnerArgent(monstre.getReward());
            }
            
        }
        for (Enemi monstre : monstres){
            removeEnemi(monstre);
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
            }
        }
        for (Enemi monstre : monstres){
            removeEnemi(monstre);
        }
    }

}
