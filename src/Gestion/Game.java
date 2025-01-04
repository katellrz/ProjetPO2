package Gestion;

import static outils.Omnicient.*;
import static Gestion.FinDePartie.*;

import java.util.ArrayList;
import java.util.List;

import Librairies.StdDraw;
import Map.Carte;
import entites.Empoisoner;
import entites.Enemi;
import entites.PoisonCaster;
import entites.Tour;
import outils.Omnicient;

public class Game {
    private LevelManager levelManager;
    private Level niveauActuel;
    private Wave vagueActuelle;
    private Joueur joueur;
    private Carte map;

    public Game() {
        this.joueur = new Joueur();
        this.levelManager = new LevelManager();
        this.niveauActuel = levelManager.getCurrentLvl();
        this.vagueActuelle = niveauActuel.getCurrentWaves();
        this.map = new Carte(niveauActuel.getMap());
    }

    public void niveauSuivant(){
        System.out.println("Changement de niveau");
        Omnicient.ClearTours();
        levelManager.nextLvl();
        this.niveauActuel = levelManager.getCurrentLvl();
        niveauActuel.resetWave();
        this.vagueActuelle = niveauActuel.getCurrentWaves();
        this.map = new Carte(niveauActuel.getMap());

    }

    public void vagueSuivante() {
        niveauActuel.nextWave();
        this.vagueActuelle = niveauActuel.getCurrentWaves();
    }

    public void gestionTour() {
        List<Tour> tours = getPositionTours();
        for (Tour tour : tours) {
            tour.afficheTour(getSize());
            tour.attaquer(joueur);
        }
        StdDraw.show();
        gestionEnemiAvctif();
    }

    public void gestionEnemiAvctif(){
        List<Enemi> monstres = new ArrayList<>();
        for (Enemi monstre : getPositionMonstre()) {
            if(monstre.getPV()<= 0){
                monstres.add(monstre);
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

    public void gestionEnemi() {
        List<Enemi> monstres = getPositionMonstre();
        for (Enemi monstre : monstres) {
            monstre.avance(joueur);
            monstre.apparait();
            monstre.attaquer(joueur);
        }
        gestionEnemiArriver();
        gestionToursActives();
    }

    public void gestionEnemiArriver(){
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

    public void start() {
        StdDraw.enableDoubleBuffering();
        Interface.AfficheInterface();

        while (true) {
            StdDraw.clear();
            Update();
            Triche();
            Empoisonement();
            vagueActuelle.Vaguedemonstre();
            Tour.PlacerTour(joueur);
            Interface.AfficheStatique();
            Interface.AfficheDynamique(map, joueur.getArgent(), joueur.getVie(), niveauActuel.getCurrentlevel(),levelManager.getMaxLvl(), niveauActuel.getCurrentWave(), niveauActuel.NbrWaves());
            joueur.afficheInfo();
            StdDraw.show();
            gestionEnemi();
            gestionTour();
            FinDePartie();
        }
    }

    public void Empoisonement(){
        List<Empoisoner> monstres = getEmpoisoners();
        System.out.println("3empoisonement collateral");
        for (Empoisoner empoisoner : monstres) {
            empoisoner.degatEmpoisonement();
        }
        
    }

    

    public void Update() {
        
        if (vagueActuelle.getVaguefini() && Omnicient.getPositionMonstre().isEmpty()) {
            System.out.println("Changemet de vague");
            if (!niveauActuel.hasNextWave()) {
                System.out.println("changement de niveau");
                if (levelManager.isLast()) {
                    System.out.println("fin du jeux ");
                    FinDePartie.afficherVictoire(); 
                }
                niveauSuivant();
            } else {
                vagueSuivante();
            }
        }
    }

    public void FinDePartie() {
        if (joueur.getVie() <= 0) {
            FinDePartie.afficherDefaite();
        }
        
    }

    

    public void Triche() {
        int x = (int) StdDraw.mouseX();
        int y = (int) StdDraw.mouseY();

        if (x > 712 && x < 812 && y > 0 && y < 50 && StdDraw.isMousePressed()) {
            System.out.println("Triche Vie");
            joueur.gagnerVie(100);
            while (StdDraw.isMousePressed()) {}
        } else if (x > 812 && x < 1000 && y > 0 && y < 50 && StdDraw.isMousePressed()) {
            System.out.println("Triche Argent");
            joueur.gagnerArgent( 1000);
            while (StdDraw.isMousePressed()) {}
        }
    }
}






/* package Gestion;

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
} */
