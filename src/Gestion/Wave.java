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



/**
 * cette  Classe represente  une vague d'ennemis dans le jeu,
 * Chaque vague est caracterisée par un nom, une liste ordonnée d'ennemis à générer
 * avec un moment specifique d'apparition, et un état indiquant si la vague est terminée
 */
public class Wave {

     /** Nom de la vague */
    private String nom;

    /** Map contenant les ennemis à générer et leur moment d'apparition */
    private Map<Double ,String> vague;

    /** indique si la vague est terminée */
    public boolean VagueestFini;

     /** Heure de début de la vague */
    public LocalTime time;


    public Wave(String nom){
        this.nom=nom;
        this.vague = new TreeMap<>();//sugjestion de chat GPT car ca beug 
        this.vague=ConstruitVague();
        this.VagueestFini = false;
        this.time=LocalTime.now();
    }


     /**
     * Marque la vague comme terminée
     */
    public void setVaguefini(){
        VagueestFini=true;
    }


     /**
     * Marque la vague comme non terminée
     */
    public void setVagueStart(){
        VagueestFini=false;
    }


   /**
     * @return true si la vague est terminée, false sinon
     */
    public boolean getVaguefini() {
        return VagueestFini;
    }


      /**
     * cette methode Construit une vague à partir d'un fichier,
     * Le fichier doit contenir les ennemis et leurs moments d'apparition
     *
     * @return une map contenant les moments (en secondes) et les noms des ennemis
     */

    public Map<Double,String> ConstruitVague(){ //il faut mieux avoir ne nom de l'enemie ou l'enemie déja creé
        Map<Double,String> vague = new TreeMap<>();

        String filePath="resources/waves/"+nom+".wve";

        List<String> fichier = FileExtraction.ExtraireFichier(filePath);

        for (String ligne : fichier) {

            //System.out.println("Ligne extraite : " + ligne); 

            String[] tab = ligne.split("\\|");
            double temps = (Double.parseDouble(tab[0])*5)-20;// Long.parseLong -> transforme un String en Long la premiere case du tableux qui contient le temps auquel le monstre doit apparaitre 
            vague.put(temps, tab[1]);/*1000 car  on met en milli seconde  ----- tab[1] contient le nom de l'enemie qui doit etre crée au tempemp tab[1] */
        }
        return vague;
    }


     /**
     * cette methode Cree un ennemi à partir de son nom,
     *
     * @param enemie le nom de l'ennemi
     * @return une instance de l'ennemi correspondant, ou null si le nom est invalide
     */

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

     /**
     * cette methode gere l'apparition des ennemis dans la vague
     *et  Vérifie si le temps actuel dépasse le moment d'apparition du prochain ennemi
     * et le genère si sa vaut la peine 
     */
    public void Vaguedemonstre(){
        
        Duration d = Duration.between(time, LocalTime.now());
        double sec = d.toMillis() / 1000.0;// la division sert à transformer les milisecondes en seconde
        //System.out.println(sec);
        if(vague.isEmpty()){
            setVaguefini();
            return;
        }

        double firstKey = ((TreeMap<Double,String>) vague).firstKey(); // Cast suggerer par CHATGPT car la methode firtkey de l'implementation map de java ne fonctionanait pas il m'a donc suggerer de fair un cast en tree map afin de gagner tous 

        if (sec>=firstKey) {
            Enemi ennemie = creeEnemi(vague.get(firstKey));

            if (ennemie == null) {
                //System.out.println("Erreur : L'ennemi n'a pas été créé pour le type : " + vague.get(firstKey));
            } else {

                
                SavetoOmni(ennemie);
                vague.remove(firstKey);
            }
            
        }  
    }
}