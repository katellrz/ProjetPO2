package outils;


import Map.Case;

import java.util.LinkedList;
import java.util.List;
import entites.Enemi;
import entites.Entite;
import entites.Tour;


public abstract class Omnicient {

    private static List<Enemi> positionMonstre = new LinkedList<>();
    private static List<Tour> positionTours = new LinkedList<>();
    private static Case Spawn;
    private static Case Base;
    private static List<Case> Chemin;
    private static List<List<Case>> Carte;
    public static int Size;
     

    
    public static void SavetoOmni(List<Case> chemin) {
        Chemin = chemin;
    }

    public static void SaveToOmni(List<List<Case>> carte ){
        Carte=carte;
    }

    public static void SavetoOmni(Enemi ennemie) {
        if (ennemie != null) {
            positionMonstre.add(ennemie); // Ajouter l'ennemi à la liste
        } else {
            System.out.println("Ennemi non ajouté, valeur nulle.");
        }
    }

    public static void SavetoOmni(Tour e) {
        positionTours.add(e);
    }

    public static void SavetoOmniBase(Case base) {
        Base = base;
    }

    public static void SavetoOmniSpawn(Case spawn) {
        Spawn = spawn;
    }
    
    public static void SaveToOmni(int size){
        Size=size;
    }

    public static int getSize(){
        return Size;
    }
    
    public static List<Enemi> getPositionMonstre() {
        return positionMonstre;
    }

    public static List<Tour> getPositionTours() {
        return positionTours;
    }

    public static Case getSpawn() {
        return Spawn;
    }

    public static Case getBase() {
        return Base;
    }

    public static List<Case> getChemin() {
        return Chemin;
    }

    public static List<List<Case>> getCarte(){
        return Carte;
    }

    public static void removeEnemi(Enemi ennemi) {
        positionMonstre.remove(ennemi);
    }

    public static void removeTour(Tour tour) {
        positionTours.remove(tour);
    }

    public static void remouveEntite(Entite entite) {
        if (entite instanceof Enemi) {
            removeEnemi((Enemi) entite);
        } else if (entite instanceof Tour) {
            removeTour((Tour) entite);
        }
    }

    //TODO fonction de réinitialisation
    
}
