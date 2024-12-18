package Gestion;

import static outils.Omnicient.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import Librairies.FileExtraction;
import entites.Enemi;
import entites.Entite.Element;
import entites.enemies.Boss;
import entites.enemies.EarthBrute;
import entites.enemies.FireGrognard;
import entites.enemies.Minion;
import entites.enemies.WaterBrute;
import entites.enemies.WindGrognard;



public class Wave {

    protected String nom;
    protected Map<Long ,String> vague;


    public Map<Long,String> ConstruitVague(){ //il faut mieux avoir ne nom de l'enemie ou l'enemie déja creé
        Map<Long,String> vague = new TreeMap<>();

        String filePath="resources/wave/"+nom+".wve";

        List<String> fichier = FileExtraction.ExtraireFichier(filePath);

        for (String ligne : fichier) {
            String[] tab = ligne.split("\\|");
            Long temps = Long.parseLong(tab[0]);
            vague.put(temps*1000, tab[1]);/*1000 car  on met en milli seconde   */
        }

        return vague;

    }

    public static Enemi creeEnemi (String enemie){
        switch (enemie) {
            case "Earth Brute":
                return new EarthBrute(30,5,1,3, Element.EARTH,1,3);//TODO modifier les positions
            case "Minion":
                return new Minion(10,3,0,0,Element.NONE,1,1);//TODO modifier les positions
            case "Wind Grognard":
                return new WindGrognard(1, 7, 2, 2, Element.WIND, 2, 1);//TODO modifier les positions
            case "Fire Grognard":
                return new FireGrognard(1, 7, 2, 3, Element.FIRE, 2, 1);//TODO modifier les positions
            case "Water Brute":
                return new WaterBrute(30, 5, 1, 3, Element.WATER, 1, 3);//TODO modifier les positions
            case "Boss":
                return new Boss(150, 100, 10, 2, Element.FIRE, 0.5, 100);//TODO modifier les positions
            default:
                return null;
        }
    }

    
        // Fonction pour calculer la différence en millisecondes entre T0 et le temps actuel
    public static long calculerDifferenceEnMillisecondes(LocalDateTime T0) {
        LocalDateTime tempsActuel = LocalDateTime.now(); // Temps actuel en millisecondes
        return Duration.between(T0, tempsActuel).toMillis(); // Différence entre T0 et le temps actuel
    }

    public void autoVague(){
        LocalDateTime T0 = LocalDateTime.now();
        while(true){
            Long a = calculerDifferenceEnMillisecondes(T0);
            if(vague.get(a)!=null){
                Enemi e = creeEnemi(vague.get(a));
                SavetoOmni(e);
            }

        }

    }
    


    


}