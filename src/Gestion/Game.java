package Gestion;


import Librairies.StdDraw;
import Map.Carte;
import entites.Tour;
import outils.Omnicient;
import outils.Reprise;

/**
 * La classe Game represente la logique principale du jeu, 
 * en gerant la progression du joueur à travers les niveaux, 
 * les vagues, et les interactions avec les entites.
 */

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

      /**
     * Passe au niveau suivant et réinitialise les composants nécessaires
     */

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


     /**
     * Reinitialise le niveau et la vague actuels, et permet au joueur de continuer à partir de son état précedent
     *
     * @param joueur L'objet joueur à utiliser pour reinitialiser l'état du jeu.
     */
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


     /**
     * Démarre le jeu en configurant la fenêtre du jeu et en lançant la boucle principale du jeu.
     */

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
            entite.ClickEnemi();

            //permette de gerer les entites et leur action 
            
            vagueActuelle.Vaguedemonstre();
            Tour.PlacerTour(joueur);
            entite.gestionEnemi(joueur);
            entite.gestionTour(joueur);
            entite.Empoisonement();
            entite.gestionBuffer();

            FinDePartie();
            
            
            StdDraw.show();
        }
    }

    

     /**
     * Met  a  jour l'état du jeu, y compris le passage aux vagues ou niveaux suivants si nécessaire
     */
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

     /**
     * Vérifie si le joueur a perdu et lui propose de recommencer le niveau si sa vie atteint zéro
     */

    public void FinDePartie() {
        if (joueur.getVie() <= 0) {
            boolean Retenter = fin.afficherDefaite();
            if (Retenter){
                recomencerNiveau(joueur);
            }
        }

        
    }


    /**
     * Active la fonctionnalite de triche permettant au joueur de gagner de la vie ou de l'argent
     */
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



