package outils;


import Map.Case;

import java.util.ArrayList;
import java.util.List;

import entites.Empoisoner;
import entites.Enemi;
import entites.Entite;
import entites.RailGun;
import entites.Tour;
import entites.Buffer;

/**
 * La classe Omnicient est une classe utilitaire abstraite qui centralise et gère les données globales
 * relatives aux entités du jeu, comme les ennemis, les tours, les cases, le chemin, et la carte.
 * Elle agit comme un point d'accès centralisé pour diverses informations essentielles au fonctionnement du jeu.
 */

public abstract class Omnicient {


    /** Liste des ennemis presents dans le jeu. */
    private static List<Enemi> positionMonstre = new ArrayList<>();

     /** Liste des tours placées sur le terrain. */
    private static List<Tour> positionTours = new ArrayList<>();

    /** Case représentant le point de spawn des ennemis. */
    private static Case Spawn;

    /** Case représentant la base à défendre. */
    private static Case Base;

    /** Chemin emprunté par les ennemis. */
    private static List<Case> Chemin;

    /** Carte représentant le niveau sous forme de cases. */
    private static List<List<Case>> Carte;

      /** Taille de la carte. */
    public static int Size;

    public static List<Empoisoner> empoisoners = new ArrayList<>();

    public static List<RailGun> RailGunList = new ArrayList<>();

    public static List<Buffer> Buffer = new ArrayList<>();
     

    /**
     * fonction qui Sauvegarde un chemin dans Omnicient.
     * 
     * @param chemin Le chemin à sauvegarder.
     */
    
    public static void SavetoOmni(List<Case> chemin) {
        Chemin = chemin;
    }

     /**
     * Sauvegarde une carte complete dans Omnicient.
     * 
     * @param carte La carte à sauvegarder.
     */

    public static void SaveToOmni(List<List<Case>> carte ){
        Carte=carte;
    }

     /**
     * Ajoute un ennemi à la liste des ennemis
     * 
     * @param ennemie L'ennemi à ajouter
     */
    public static void SavetoOmni(Enemi ennemie) {
        if (ennemie != null) {
            positionMonstre.add(ennemie); // Ajouter l'ennemi à la liste
            if(ennemie instanceof Buffer){
                Buffer.add((Buffer)ennemie);
            }
        } else {
            System.out.println("Ennemi non ajouté, valeur nulle.");
        }
    }


     /**
     * Ajoute une tour à la liste des tours.
     * 
     * @param e La tour à ajouter.
     */
    public static void SavetoOmni(Tour e) {
        positionTours.add(e);
        if(e instanceof RailGun){
            RailGunList.add((RailGun)e);
        }
    }

    public static void SavetoOmni(Empoisoner e) {
        empoisoners.add(e);
    }

    public static List<Empoisoner> getEmpoisoners(){
        return empoisoners;
    }

    /**
     * la methode Définit la base dans Omnicient.
     * 
     * @param base La case représentant la base.
     */

    public static void SavetoOmniBase(Case base) {
        Base = base;
    }


    /**
     * Définit le spawn dans Omnicient.
     * 
     * @param spawn La case représentant le spawn.
     */

    public static void SavetoOmniSpawn(Case spawn) {
        Spawn = spawn;
    }

     /**
     * Définit la taille de la carte dans Omnicient.
     * 
     * @param size La taille de la carte.
     */
    
    public static void SaveToOmni(int size){
        Size=size;
    }

     /**
     * Retourne la taille de la carte.
     * 
     * @return La taille de la carte.
     */
    public static int getSize(){
        return Size;
    }
    
    /**
     * Retourne la liste des ennemis.
     * 
     * @return Liste des ennemis.
     */
    public static List<Enemi> getPositionMonstre() {
        return positionMonstre;
    }

    /**
     * Retourne la liste des tours.
     * 
     * @return Liste des tours.
     */

    public static List<Tour> getPositionTours() {
        return positionTours;
    }

     /**
     * Retourne la case de spawn.
     * 
     * @return La case de spawn.
     */
    public static Case getSpawn() {
        return Spawn;
    }

     /**
     * Retourne la case de la base.
     * 
     * @return La case de la base.
     */
    public static Case getBase() {
        return Base;
    }

     /**
     * Retourne le chemin emprunté par les ennemis.
     * 
     * @return Le chemin.
     */

    public static List<Case> getChemin() {
        return Chemin;
    }

    public static List<Buffer> getBuffer(){
        return Buffer;
    }

    /**
     * Retourne la carte du niveau.
     * 
     * @return La carte du niveau.
     */

    public static List<List<Case>> getCarte(){
        return Carte;
    }

    public static List<RailGun> GetRailGunList(){
        return RailGunList;
    }


    /**
     * Supprime un ennemi de la liste des ennemis.
     * 
     * @param ennemi L'ennemi à supprimer.
     */
    public static void removeEnemi(Enemi ennemi) {
        positionMonstre.remove(ennemi);
    }

    /**
     * Supprime une tour de la liste des tours.
     * 
     * @param tour La tour à supprimer.
     */

    public static void removeTour(Tour tour) {
        positionTours.remove(tour);
        if(tour instanceof RailGun){
            RailGunList.remove(tour);
        }
    }

     /**
     * Supprime une entité ennemi ou tour en fonction de son type.
     * 
     * @param entite L'entité à supprimer.
     */
    public static void remouveEntite(Entite entite) {
        if (entite instanceof Enemi) {
            removeEnemi((Enemi) entite);
        } else if (entite instanceof Tour) {
            removeTour((Tour) entite);
        }
    }

    /**
     * Réinitialise toutes les données du niveau.
     */

    public static void resetLvl(){
        positionMonstre.clear();
        positionTours.clear();
        RailGunList.clear();
    }

    /**
     * Supprime toutes les tours.
     */

    public static void ClearTours(){
        positionTours.clear();
        RailGunList.clear();
    }

    public static List<RailGun> getRailGunList() {
        return RailGunList;
    }

    //TODO fonction de réinitialisation
    
}
