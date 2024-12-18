package Map;

import java.util.ArrayList;
import java.util.List;

import Librairies.FileExtraction;
import Outil.Omnicient;

public class Carte {

    private List<List<Case>> CarteJeu;
    private String nom;
    private int size;
    //private List<Case> Chemin;

    
    
    public Carte(String nom){
        this.nom = nom;
        this.CarteJeu = ConvertiCase();
        Omnicient.SaveToOmni(CarteJeu);
        //this.chemin = 
    }


    /**
     * Prend une carte dans un fichier du même nom que celle ci et convertit chacun des caratère de ce fichier en une liste de liste de case 
     * @return Case[][] un tableau 2D de char 
     */
    public List<List<Case>> ConvertiCase(){

        String filePath="resources/maps/"+this.nom+".mtp";
        List <String> tabStrings = FileExtraction.ExtraireFichier(filePath); 
        
        if (tabStrings == null || tabStrings.isEmpty()) {//verification si la carte est null ou vide 
            System.out.println("Erreur: fichier de carte introuvable ou vide.");
            return null;
        }


        int rows = tabStrings.size();//compte le nombre de Ligne 
        int cols = tabStrings.get(0).length();//compte le nombre de caractère de la première ligne de caractèe (String)

        //CALCUL SIZE
        this.size = 700 / Math.max(rows, cols);//divise la taille de l'espace ou est afficher la carte par le max de case ente la largeur et la longueur

        //CALCUL START
        int startX = (cols < rows) ? (Math.abs(cols - rows) / 2) * this.size : 0;//determine le depard de la premier case afin que la carte soit center et dans le cadre prévus a cette effet 
        int startY = (rows < cols) ? (Math.abs(rows - cols) / 2) * this.size : 0;//TODO verifier que l'allignement est correct 


        List<List<Case>> Carte = new ArrayList<>();

        try {//ajout d'un try catch en debug 
            for (int i = 0; i < rows; i++) {
                String ligne = tabStrings.get(i);
                List<Case> lignCases = new ArrayList<>();
                for (int j = 0; j < cols; j++) {

                    //calcul des coordonnée pour avoir le cenrte de chaque case 
                    int centerX = startX + j * size + size / 2;
                    int centerY = startY + (rows - 1 - i) * size + size / 2;

                    lignCases.add(new Case(ligne.charAt(j), i, j, centerX, centerY));//TODO definir case
                }
                Carte.add(lignCases);
                lignCases.clear();
            }
    
        } catch (Exception e) {
            System.out.println("Erreur lors de la conversion des cases: " + e.getMessage());
            e.printStackTrace();
            return null;
        } 
        return Carte;
    }
    
    

}
