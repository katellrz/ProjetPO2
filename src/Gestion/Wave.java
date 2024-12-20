package Gestion;

import static outils.Omnicient.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Librairies.FileExtraction;
import entites.Boss;
import entites.EarthBrute;
import entites.Enemi;
import entites.FireGrognard;
import entites.Minion;
import entites.WaterBrute;
import entites.WindGrognard;



public class Wave {

    private String nom;
    private Map<Long ,String> vague;
    public boolean VagueestFini;
    //jsp 

    public Wave(String nom){
        this.nom=nom;
        this.vague=ConstruitVague();
        this.VagueestFini = false;


    }


    public Map<Long,String> ConstruitVague(){ //il faut mieux avoir ne nom de l'enemie ou l'enemie déja creé
        Map<Long,String> vague = new TreeMap<>();

        String filePath="resources/wave/"+nom+".wve";

        List<String> fichier = FileExtraction.ExtraireFichier(filePath);

        for (String ligne : fichier) {
            String[] tab = ligne.split("\\|");
            Long temps = Long.parseLong(tab[0]);// Long.parseLong -> transforme un String en Long la premiere case du tableux qui contient le temps auquel le monstre doit apparaitre 
            vague.put(temps*1000, tab[1]);/*1000 car  on met en milli seconde  ----- tab[1] contient le nom de l'enemie qui doit etre crée au tempemp tab[1] */
        }
        return vague;
    }


    public static Enemi creeEnemi (String enemie){
        switch (enemie) {
            case "Earth Brute":
                return new EarthBrute();
            case "Minion":
                return new Minion();
            case "Wind Grognard":
                return new WindGrognard();
            case "Fire Grognard":
                return new FireGrognard();
            case "Water Brute":
                return new WaterBrute();
            case "Boss":
                return new Boss();
            default:
                return null;
        }
    }

    
        // Fonction pour calculer la différence en millisecondes entre T0 et le temps actuel
    public static long calculerDifferenceEnMillisecondes(LocalDateTime T0) {
        LocalDateTime tempsActuel = LocalDateTime.now(); // Temps actuel en millisecondes
        return Duration.between(T0, tempsActuel).toMillis(); // Différence entre T0 et le temps actuel
    }

    public void creeEnemi(){
        
        double sec = Math.round((d.toMillis() / 1000.0)*10.0)/10.0;// la division sert à transformer les milisecondes en seconde
        System.out.println(sec);
        if (v.containsKey(sec)) {//c good
            v.get(sec).seDeplacer2(chemin, game);
            e.add(v.get(sec));
            v.get(sec).setPositionX(chemin.get(0).getX());
            v.get(sec).setPositionY(chemin.get(0).getY());
            System.out.println(v.get(sec).getPositionY());
            v.remove(sec);
        } 
        
        if(sec == last){
            setVaguefini(true);
        }
    }

    

    }
    


    


}