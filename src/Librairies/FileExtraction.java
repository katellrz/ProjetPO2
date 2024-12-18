package Librairies;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileExtraction {

    /**
     * Extrait ficheir depuis un fichier texte et la convertit en une liste (Arrays list) de String, ou une String corespond a une ligne.
     *
     * @return Une List de String représentant les lignes de la carte.
     */

    public static List<String> ExtraireFichier (String filePath){

        List<String>Lignescarte= new ArrayList<>();//on crée une liste de String

        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));//On ouvre un bufferreader sur une carte

            String ligne;//initilisation d'une ligne (String) pour agir comme variable temporaire qui prend la valeur de ce que lis br
            while ((ligne = br.readLine()) != null) {
                Lignescarte.add(ligne);//la ligne est ajouter a la ligne 
            }
            br.close();//le scanner est clos 
           
        }catch(FileNotFoundException e){//exeption si le fichier n'est pas trouver 
            e.printStackTrace();
            System.out.println("Le chemin d'accès est incorect ou il n'existe aucune carte avec ce nom");
        }catch(IOException eo){//exeption si le fiechier est illisible 
            eo.printStackTrace();
            System.out.println("fichier illisible");
        }    
        return Lignescarte;//return la liste de string contenant les ligne du fichier   
    }
}
