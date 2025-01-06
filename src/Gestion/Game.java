package Gestion;

import static outils.Omnicient.*;


import java.util.ArrayList;
import java.util.List;
import Librairies.StdDraw;
import Map.Carte;
import Map.DetectionSouris;
import entites.Empoisoner;
import entites.Enemi;
import entites.PoisonCaster;
import entites.RailGun;
import entites.Tour;
import outils.Omnicient;
import outils.Reprise;

public class Game {
    private LevelManager levelManager;
    private Level niveauActuel;
    private Wave vagueActuelle;
    private Joueur joueur;
    private Carte map;
    private Reprise StatLevel;
    private GestionEntite entite;
    private FinDePartie fin;


    public Game() {
        this.joueur = new Joueur();
        this.levelManager = new LevelManager();
        this.StatLevel= new Reprise(joueur.getArgent(), joueur.getVie());
        this.niveauActuel = levelManager.getCurrentLvl();
        this.vagueActuelle = niveauActuel.getCurrentWaves();
        this.map = new Carte(niveauActuel.getMap());
        this.entite = new GestionEntite();
        this.fin = new FinDePartie();
    }

    public void niveauSuivant(){
        System.out.println("Changement de niveau");
        Omnicient.ClearTours();
        levelManager.nextLvl();
        this.niveauActuel = levelManager.getCurrentLvl();
        niveauActuel.resetWave();
        this.vagueActuelle = niveauActuel.getCurrentWaves();
        this.map = new Carte(niveauActuel.getMap());
        this.StatLevel= new Reprise(joueur.getArgent(), joueur.getVie());
    }

    public void recomencerNiveau(Joueur joueur){
        niveauActuel.resetWave();
        Omnicient.resetLvl();
        this.vagueActuelle = niveauActuel.getCurrentWaves();
        StatLevel.Repprendre(joueur);
    }



    public void vagueSuivante() {
        niveauActuel.nextWave();
        this.vagueActuelle = niveauActuel.getCurrentWaves();
    }


    public void start() {
        StdDraw.enableDoubleBuffering();
        Interface.AfficheInterface();
        

        while (true) {
            StdDraw.clear();

            Interface.AfficheStatique();
            Interface.AfficheDynamique(map, joueur.getArgent(), joueur.getVie(), niveauActuel.getCurrentlevel(),levelManager.getMaxLvl(), niveauActuel.getCurrentWave(), niveauActuel.NbrWaves());
            joueur.afficheInfo();
            StdDraw.show();
            
            //fontion pour verifier ou en est 
            Update();
            Triche();
            ClickEnemi();

            //perette de gerer les entiter et leur action 
            
            vagueActuelle.Vaguedemonstre();
            Tour.PlacerTour(joueur);
            entite.gestionEnemi(joueur);
            entite.gestionTour(joueur);
            Empoisonement();

            FinDePartie();
            
            
            StdDraw.show();
        }
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

    

    public void Update() {
        
        if (vagueActuelle.getVaguefini() && Omnicient.getPositionMonstre().isEmpty()) {
            System.out.println("Changemet de vague");
            if (!niveauActuel.hasNextWave()) {
                System.out.println("changement de niveau");
                if (levelManager.isLast()) {
                    System.out.println("fin du jeux ");
                    fin.afficherVictoire(); 
                }
                niveauSuivant();
            } else {
                vagueSuivante();
            }
        }
    }

    public void FinDePartie() {
        if (joueur.getVie() <= 0) {
            boolean Retenter = fin.afficherDefaite();
            if (Retenter){
                recomencerNiveau(joueur);
            }
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



