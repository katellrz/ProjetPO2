package Gestion;

import static outils.Omnicient.*;

import java.time.Duration;
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
    public LocalTime time;


    public Wave(String nom){
        this.nom=nom;
        this.vague = new TreeMap<>();//sugjestion de chat GPT car ca beug 
        this.vague=ConstruitVague();
        this.VagueestFini = false;
        this.time=LocalTime.now();
    }

    public void setVaguefini(){
        VagueestFini=true;
    }

    public void setVagueStart(){
        VagueestFini=false;
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

    public void Vaguedemonstre(){
        
        Duration d = Duration.between(time, LocalTime.now());
        double sec = d.toMillis() / 1000.0;// la division sert à transformer les milisecondes en seconde
        System.out.println(sec);
        if(vague.isEmpty()){
            setVaguefini();
            return;
        }

        Long firstKey = ((TreeMap<Long, String>) vague).firstKey(); // Cast suggerer par CHATGPT car la methode firtkey de l'implementation map de java ne fonctionanait pas 

        if (sec>=firstKey) {
            Enemi ennemie = creeEnemi(vague.get(firstKey));
            SavetoOmni(ennemie);
            vague.remove(firstKey);
        }  
    }
}