package Outil;


import Map.Case;
import java.util.List;
// import entites.Enemi;
// import entites.Tour;

public abstract class Omnicient {

    //private static List<Enemi> positionMonstre = new LinkedList<>();
    //private static List<Tour> positionTours = new LinkedList<>();
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

    // public static void SavetoOmni(Enemi e) {
    //     positionMonstre.add(e);
    // }

    // public static void SavetoOmni(Tour e) {
    //     positionTours.add(e);
    // }

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
    
    // public static List<Enemi> getPositionMonstre() {
    //     return positionMonstre;
    // }

    // public static List<Tour> getPositionTours() {
    //     return positionTours;
    // }

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

    // public static void AddTour(Tour tour){
    //     positionTours.add(tour);
    // }

    // public static void AddEnemi(Enemi enemi){
    //     positionMonstre.add(enemi);
    // }

    



    
}
